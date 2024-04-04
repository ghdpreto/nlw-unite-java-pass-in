package br.com.ghdpreto.nlwunitejavapassin.repositories;

import br.com.ghdpreto.nlwunitejavapassin.domain.checkin.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {

    Optional<CheckIn> findByAttendeeId(String attendeeId);
}
