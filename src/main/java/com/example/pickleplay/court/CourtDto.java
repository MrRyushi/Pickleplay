package com.example.pickleplay.court;

public record CourtDto(
        Integer id,
        String name,
        String location,
        int hourly_rate
) {
    @Override
    public Integer id() {
        return id;
    }
    @Override
    public String name() {
        return name;
    }

    @Override
    public String location() {
        return location;
    }

    @Override
    public int hourly_rate() {
        return hourly_rate;
    }
}
