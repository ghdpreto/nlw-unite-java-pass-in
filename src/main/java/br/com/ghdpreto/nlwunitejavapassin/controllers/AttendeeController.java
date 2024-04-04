package br.com.ghdpreto.nlwunitejavapassin.controllers;

import br.com.ghdpreto.nlwunitejavapassin.dto.attendee.AttendeeBadgeDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.attendee.AttendeeBadgeResponseDTO;
import br.com.ghdpreto.nlwunitejavapassin.services.AttendeeService;
import br.com.ghdpreto.nlwunitejavapassin.services.CheckInService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {
    private final AttendeeService attendeeService;
    private final CheckInService checkInService;

    // buscar cracha
    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<AttendeeBadgeResponseDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {

        AttendeeBadgeDTO attendeeBadge = this.attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);

        return ResponseEntity.ok(new AttendeeBadgeResponseDTO(attendeeBadge));
    }

    // realizar checkIn
    @PostMapping("/{attendeeId}/check-in")
    public ResponseEntity registerCheckIn(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        this.attendeeService.checkInAttendee(attendeeId);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();

        return ResponseEntity.created(uri).build();
    }
}
