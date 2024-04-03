package br.com.ghdpreto.nlwunitejavapassin.repositories;

import br.com.ghdpreto.nlwunitejavapassin.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
