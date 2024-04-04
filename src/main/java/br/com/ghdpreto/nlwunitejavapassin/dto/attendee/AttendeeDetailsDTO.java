package br.com.ghdpreto.nlwunitejavapassin.dto.attendee;

import java.time.LocalDateTime;

public record AttendeeDetailsDTO(
        String id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime checkedInAt) {
}
