"use client";

import DateButtons from "@/components/DateButtons";
import { DatePicker } from "@/components/DatePicker";
import TimeSlots from "@/components/TimeSlots";
import React, { useEffect, useState } from "react";
import { useSearchParams } from "next/navigation";
import { toast } from "sonner";

type Booking = {
  startTime: string;
  endTime: string;
  status: string;
  courtId: number;
  userId: number;
};

const Booking = () => {
  const searchParams = useSearchParams();
  const courtId = searchParams.get("courtId");
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [baseDate, setBaseDate] = useState(new Date());
  const [selectedSlots, setSelectedSlots] = useState<number[]>([]);
  const [existingBookings, setExistingBookings] = useState<
    { startTime: string; endTime: string }[]
  >([]);
  const [blockedHours, setBlockedHours] = useState<Set<number>>(new Set());

  const pad = (n: number) => n.toString().padStart(2, "0");
  const formatLocalDateTime = (date: Date) => {
    return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())}T${pad(date.getHours())}:${pad(date.getMinutes())}:00`;
  };

  // fetch all existing bookings for the selected date and court, and disable the corresponding time slots
  useEffect(() => {
    const fetchBookings = async () => {
      if (!courtId) {
        toast.error("No court selected.", { position: "top-center" });
        return;
      }

      const res = await fetch(
        `http://localhost:8080/bookings?courtId=${courtId}&date=${selectedDate.toISOString().split("T")[0]}`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        },
      );

      if (!res.ok) {
        const text = await res.text();
        throw new Error(`Failed to fetch bookings: ${res.status} ${text}`);
      }

      const data = await res.json();
      setExistingBookings(data);
      console.log("Existing bookings:", data);
    };
    fetchBookings();
  }, [selectedDate, courtId]);

  useEffect(() => {
    // convert bookings -> blocked hours (6..23 etc)
    const blocked = new Set<number>();

    for (const b of existingBookings) {
      const start = new Date(b.startTime);
      const end = new Date(b.endTime);

      // block each hour slot covered by [start, end)
      // Example: 13:00-15:00 blocks 13 and 14
      const cur = new Date(start);
      cur.setMinutes(0, 0, 0);

      while (cur < end) {
        blocked.add(cur.getHours());
        cur.setHours(cur.getHours() + 1);
      }
    }

    // eslint-disable-next-line react-hooks/set-state-in-effect
    setBlockedHours(blocked);

    // if user already selected something that became blocked, remove it
    setSelectedSlots((prev) => prev.filter((h) => !blocked.has(h)));
  }, [existingBookings]);

  // submit a booking request to the backend
  const handleSubmit = async () => {
    if (selectedSlots.length === 0) {
      toast.error("Please select at least one time slot.", {
        position: "top-center",
      });
      return;
    }

    const sortedSlots = [...selectedSlots].sort((a, b) => a - b);

    const start = new Date(selectedDate);
    start.setHours(sortedSlots[0], 0, 0, 0);

    const end = new Date(selectedDate);
    end.setHours(sortedSlots[sortedSlots.length - 1] + 1, 0, 0, 0);

    const token = localStorage.getItem("token");
    if (!token) {
      toast.error("Not logged in (missing token).", { position: "top-center" });
      return;
    }

    const res = await fetch("http://localhost:8080/bookings", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
      body: JSON.stringify({
        startTime: formatLocalDateTime(start),
        endTime: formatLocalDateTime(end),
        status: "PENDING",
        courtId: Number(courtId),
      }),
    });

    if (!res.ok) {
      const text = await res.text();
      throw new Error(`Booking failed: ${res.status} ${text}`);
    }

    const data = await res.json();
    console.log("Booking created:", data);
    toast.success("Booking created!", { position: "top-center" });
  };

  return (
    <div className="flex justify-center items-center min-h-screen px-4 md:px-0">
      <div className="space-y-4">
        <DatePicker
          selectedDate={selectedDate}
          setSelectedDate={setSelectedDate}
          setBaseDate={setBaseDate}
        />
        <DateButtons
          selectedDate={selectedDate}
          setSelectedDate={setSelectedDate}
          setBaseDate={setBaseDate}
          baseDate={baseDate}
        />
        <TimeSlots
          selectedDate={selectedDate}
          selectedSlots={selectedSlots}
          setSelectedSlots={setSelectedSlots}
          blockedHours={blockedHours}
        />

        <div className="flex justify-center">
          <button
            onClick={() => setSelectedSlots([])}
            className="px-4 py-2 bg-rose-600 hover:bg-rose-700 text-white rounded-md mr-2"
          >
            Clear
          </button>
          <button
            onClick={() => handleSubmit()}
            className="px-4 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded-md"
          >
            Book Now
          </button>
        </div>
      </div>
    </div>
  );
};

export default Booking;
