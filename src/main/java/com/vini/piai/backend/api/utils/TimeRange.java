package com.vini.piai.backend.api.utils;

import java.time.LocalTime;

public record TimeRange(LocalTime start, LocalTime end) {

    public boolean overlaps(TimeRange other) {
        return this.start().isBefore(other.end()) && other.start().isBefore(this.end());
    }

}
