package br.com.ghdpreto.nlwunitejavapassin.controllers;

import br.com.ghdpreto.nlwunitejavapassin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getEvent(@PathVariable String id) {

        this.eventService.getEventDetail(id);

        return ResponseEntity.ok().body("Sucesso event");
    }
}
