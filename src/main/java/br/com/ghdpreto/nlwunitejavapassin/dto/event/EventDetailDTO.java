package br.com.ghdpreto.nlwunitejavapassin.dto.event;


// record utilizado para DTO
public record EventDetailDTO(
        String id,
        String title,
        String details,
        String slug,
        Integer maximumAttendees,
        Integer attendeesAmount
) { }
