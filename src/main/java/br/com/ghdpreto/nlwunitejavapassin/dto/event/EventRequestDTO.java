package br.com.ghdpreto.nlwunitejavapassin.dto.event;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record EventRequestDTO(
        @NotNull
        @NotEmpty
        String title,

        @NotNull
        String details,
        @NotNull
        @Min(1)
        Integer maximumAttendees
) { }
