package br.com.ghdpreto.nlwunitejavapassin.services;

import br.com.ghdpreto.nlwunitejavapassin.domain.attendee.Attendee;
import br.com.ghdpreto.nlwunitejavapassin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import br.com.ghdpreto.nlwunitejavapassin.domain.attendee.exceptions.AttendeeNotFoundException;
import br.com.ghdpreto.nlwunitejavapassin.domain.checkin.CheckIn;
import br.com.ghdpreto.nlwunitejavapassin.dto.attendee.AttendeeDetailsDTO;
import br.com.ghdpreto.nlwunitejavapassin.dto.attendee.AttendeeBadgeDTO;
import br.com.ghdpreto.nlwunitejavapassin.repositories.AttendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final CheckInService checkInService;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);
    }

    public List<AttendeeDetailsDTO> getEventsAttende(String eventId){

        List<Attendee> attendees = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetailsDTO> attendeeDetailsList = attendees.stream().map((attendee) -> {
            // buscando o checkin
            Optional<CheckIn> checkIn = this.checkInService.getCheckIn(attendee.getId());

            // verificando se esta check
            LocalDateTime checkdInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;

            // retornando o AttendeeDetails
            return new AttendeeDetailsDTO(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkdInAt);

        }).toList();

        return attendeeDetailsList;
    }

    public Attendee registerAttendee(Attendee newAttendee) {
        return this.attendeeRepository.save(newAttendee);
    }

    public void verifyAttendeeSubscription(String email, String eventId) {
        Optional<Attendee> isAttendeeRegistred = this.attendeeRepository.findByEventIdAndEmail(eventId, email);

        if(isAttendeeRegistred.isPresent()) throw new AttendeeAlreadyExistException("Attendee is already registered");

    }

    public AttendeeBadgeDTO getAttendeeBadge(String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        Attendee attendee = this.getAttendee(attendeeId);

        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/check-in")
                .buildAndExpand(attendee.getId())
                .toUri()
                .toString();

        return new AttendeeBadgeDTO(
                attendee.getName(),
                attendee.getEmail(),
                uri,
                attendee.getEvent().getId());
    }

    public void checkInAttendee(String attendeeId) {
        Attendee attendee = this.getAttendee(attendeeId);
        this.checkInService.registerCheckIn(attendee);
    }

    private Attendee getAttendee(String attendeeId) {
        return this.attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new AttendeeNotFoundException("Attendee not found with ID: " + attendeeId));
    }
}
