package br.com.ghdpreto.nlwunitejavapassin.services;

import br.com.ghdpreto.nlwunitejavapassin.domain.attendee.Attendee;
import br.com.ghdpreto.nlwunitejavapassin.domain.event.Event;
import br.com.ghdpreto.nlwunitejavapassin.domain.event.exceptions.EventFullException;
import br.com.ghdpreto.nlwunitejavapassin.domain.event.exceptions.EventNotFoundException;
import br.com.ghdpreto.nlwunitejavapassin.dto.attendee.AttendeeRequestDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.event.EventDetailDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.event.EventRequestDTO;
import br.com.ghdpreto.nlwunitejavapassin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor // gera o construtor e injeta a dependencia (eventRepository)
public class EventService {

    // FINAL -> nao vai alterar
    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;

    public EventDetailDTO getEventDetail(String eventId) {

        Event event = this.getEventById(eventId);

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


    public  Attendee registerAttendeeOnEvent(String eventId, AttendeeRequestDTO attendeeRequestDTO) {

        this.attendeeService.verifyAttendeeSubscription(attendeeRequestDTO.email(), eventId);

        Event event = this.getEventById(eventId);

        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);

        // valida se o event tem vaga disponivel
        if(event.getMaximumAttendees()<= attendeeList.size())  throw new EventFullException("Event is full");

        Attendee newAttendee = new Attendee();
        newAttendee.setName(attendeeRequestDTO.name());
        newAttendee.setEmail(attendeeRequestDTO.email());
        newAttendee.setEvent(event);
        newAttendee.setCreatedAt(LocalDateTime.now());

        this.attendeeService.registerAttendee(newAttendee);

        return newAttendee;
    }


    private Event getEventById(String eventId) {
        return this.eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with ID:" + eventId));
    }
}
