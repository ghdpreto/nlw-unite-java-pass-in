package br.com.ghdpreto.nlwunitejavapassin.dto.event;

public record EventRequestDTO(
        String title,
        String details,
        Integer maximumAttendees
) { }
