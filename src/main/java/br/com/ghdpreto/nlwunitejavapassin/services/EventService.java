package br.com.ghdpreto.nlwunitejavapassin.services;

import br.com.ghdpreto.nlwunitejavapassin.domain.attendee.Attendee;
import br.com.ghdpreto.nlwunitejavapassin.domain.event.Event;
import br.com.ghdpreto.nlwunitejavapassin.dto.event.EventResponseDTO;
import br.com.ghdpreto.nlwunitejavapassin.repositories.AttendeeRepository;
import br.com.ghdpreto.nlwunitejavapassin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor // gera o construtor e injeta a dependencia (eventRepository)
public class EventService {

    // FINAL -> nao vai alterar
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventResponseDTO getEventDetail(String eventId) {

        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found with ID:" + eventId));

        List<Attendee> attendeeList = this.attendeeRepository.findByEventId(eventId);


        return new EventResponseDTO(event, attendeeList.size());
    }


}
