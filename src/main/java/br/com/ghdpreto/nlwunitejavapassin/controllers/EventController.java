package br.com.ghdpreto.nlwunitejavapassin.controllers;

import br.com.ghdpreto.nlwunitejavapassin.config.event.CreateEventSwagger;
import br.com.ghdpreto.nlwunitejavapassin.config.event.GetAttendeesEventSwagger;
import br.com.ghdpreto.nlwunitejavapassin.config.event.GetEventSwagger;
import br.com.ghdpreto.nlwunitejavapassin.config.event.RegisterParticipantEventSwagger;
import br.com.ghdpreto.nlwunitejavapassin.domain.attendee.Attendee;
import br.com.ghdpreto.nlwunitejavapassin.dto.attendee.AttendeeDetailsDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.attendee.AttendeeIdDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.attendee.AttendeeRequestDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.attendee.AttendeesListResponseDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.event.EventDetailDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.event.EventIdDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.event.EventRequestDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.event.EventResponseDTO;
import br.com.ghdpreto.nlwunitejavapassin.services.AttendeeService;
import br.com.ghdpreto.nlwunitejavapassin.services.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(name = "Eventos", description = "Informações de eventos")
public class EventController {

    private final EventService eventService;
    private final AttendeeService attendeeService;

    // criar um evento
    @PostMapping("")
    @CreateEventSwagger
    public ResponseEntity<EventIdDTO> creatEvent(@Valid @RequestBody EventRequestDTO event,
                                                 UriComponentsBuilder uriComponentsBuilder) {

        var newEvent = this.eventService.createEvent(event);

        // criando a uri
        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(newEvent.getId()).toUri();

        return ResponseEntity.created(uri).body(new EventIdDTO(newEvent.getId()));
    }


    // dados do evento
    @GetMapping("/{id}")
    @GetEventSwagger
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id) {

        EventDetailDTO event = this.eventService.getEventDetail(id);

        return ResponseEntity.ok(new EventResponseDTO(event));
    }


    // buscar participantes do evento
    @GetMapping("/attendees/{id}")
    @GetAttendeesEventSwagger
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id) {
        this.eventService.getEventDetail(id);
        List<AttendeeDetailsDTO> attendeesFromEvent = this.attendeeService.getEventsAttende(id);

        return ResponseEntity.ok(new AttendeesListResponseDTO(attendeesFromEvent));
    }

    /*
    * registra um participante a um evento
    * */
    @PostMapping("/{eventId}/attendees")
    @RegisterParticipantEventSwagger
    public ResponseEntity<AttendeeIdDTO> registerParticipant(
            @PathVariable String eventId,
            @Valid @RequestBody AttendeeRequestDTO attendeeRequestDTO,
            UriComponentsBuilder uriComponentsBuilder) {

        Attendee newAttendeRegister = this.eventService.registerAttendeeOnEvent(eventId, attendeeRequestDTO);

        // criando a uri
        var uri = uriComponentsBuilder.path("/attendees/{attendeeeId}/badge").buildAndExpand(newAttendeRegister.getId()).toUri();

        return ResponseEntity.created(uri).body(new AttendeeIdDTO(newAttendeRegister.getId()));
    }

}
