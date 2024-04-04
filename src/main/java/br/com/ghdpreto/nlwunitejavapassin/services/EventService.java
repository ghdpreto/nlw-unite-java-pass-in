package br.com.ghdpreto.nlwunitejavapassin.services;

import br.com.ghdpreto.nlwunitejavapassin.domain.attendee.Attendee;
import br.com.ghdpreto.nlwunitejavapassin.domain.event.Event;
import br.com.ghdpreto.nlwunitejavapassin.domain.event.exceptions.EventNotFoundException;
import br.com.ghdpreto.nlwunitejavapassin.dto.event.EventDetailDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.event.EventRequestDTO;
import br.com.ghdpreto.nlwunitejavapassin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;


@Service
@RequiredArgsConstructor // gera o construtor e injeta a dependencia (eventRepository)
public class EventService {

    // FINAL -> nao vai alterar
    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;

    public EventDetailDTO getEventDetail(String eventId) {

        Event event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with ID:" + eventId));

        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);


        return new EventDetailDTO(
                event.getId(),
                event.getTitle(),
                event.getDetails(),
                event.getSlug(),
                event.getMaximumAttendees(),
                attendeeList.size());
    }



    public Event createEvent(EventRequestDTO event) {
        Event newEvent = new Event();

        newEvent.setTitle(event.title());
        newEvent.setDetails(event.details());
        newEvent.setMaximumAttendees(event.maximumAttendees());
        newEvent.createSlug(event.title());

        System.out.println("event = " + newEvent.getSlug());


        this.eventRepository.save(newEvent);

        return newEvent;
    }



    private String createSlug(String text) {
        // normalizer
        // São Paulo -> Sa~o Paulo
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);

        // remove os acentos
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }

}
