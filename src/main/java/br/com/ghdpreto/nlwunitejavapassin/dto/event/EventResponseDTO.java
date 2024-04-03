package br.com.ghdpreto.nlwunitejavapassin.dto.event;


import br.com.ghdpreto.nlwunitejavapassin.domain.event.Event;

// class usada para response
public class EventResponseDTO {


    EventDetailDTO event;

    //constructor
    public EventResponseDTO(Event event, Integer numberOfAttendees){
        this.event = new EventDetailDTO(
                event.getId(),
                event.getTitle(),
                event.getDetails(),
                event.getSlug(),
                event.getMaximumAttendees(),
                numberOfAttendees);
    }
}
