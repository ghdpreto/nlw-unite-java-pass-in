package br.com.ghdpreto.nlwunitejavapassin.controllers;

import br.com.ghdpreto.nlwunitejavapassin.domain.event.Event;
import br.com.ghdpreto.nlwunitejavapassin.dto.event.EventDetailDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.event.EventIdDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.event.EventRequestDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.event.EventResponseDTO;
import br.com.ghdpreto.nlwunitejavapassin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id) {

        EventDetailDTO event = this.eventService.getEventDetail(id);

        return ResponseEntity.ok(new EventResponseDTO(event));
    }

    @PostMapping("")
    public ResponseEntity<EventIdDTO> creatEvent(@RequestBody EventRequestDTO event, UriComponentsBuilder uriComponentsBuilder) {

        var newEvent = this.eventService.createEvent(event);

        // criando a uri
        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(newEvent.getId()).toUri();

        return ResponseEntity.created(uri).body(new EventIdDTO(newEvent.getId()));
    }
}
