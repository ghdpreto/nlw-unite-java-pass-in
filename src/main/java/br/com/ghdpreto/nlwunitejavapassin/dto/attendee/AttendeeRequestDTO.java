package br.com.ghdpreto.nlwunitejavapassin.dto.attendee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AttendeeRequestDTO(
        @NotNull
        @NotEmpty
        String name,

        @Email
        @NotNull
        String email
) { }
