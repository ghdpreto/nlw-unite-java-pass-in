package br.com.ghdpreto.nlwunitejavapassin.repositories;

import br.com.ghdpreto.nlwunitejavapassin.domain.attendee.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
}
