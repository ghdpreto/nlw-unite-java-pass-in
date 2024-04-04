package br.com.ghdpreto.nlwunitejavapassin.services;

import br.com.ghdpreto.nlwunitejavapassin.domain.attendee.Attendee;
import br.com.ghdpreto.nlwunitejavapassin.domain.checkin.CheckIn;
import br.com.ghdpreto.nlwunitejavapassin.dto.attendee.AttendeeDetailsDTO;
import br.com.ghdpreto.nlwunitejavapassin.repositories.AttendeeRepository;
import br.com.ghdpreto.nlwunitejavapassin.repositories.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final CheckInRepository checkInRepository;


    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);
    }

    public List<AttendeeDetailsDTO> getEventsAttende(String eventId){

        List<Attendee> attendees = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetailsDTO> attendeeDetailsList = attendees.stream().map((attendee) -> {
            // buscando o checkin
            Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());

            // verificando se esta check
            LocalDateTime checkdInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;

            // retornando o AttendeeDetails
            return new AttendeeDetailsDTO(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkdInAt);

        }).toList();

        return attendeeDetailsList;
    }
}
