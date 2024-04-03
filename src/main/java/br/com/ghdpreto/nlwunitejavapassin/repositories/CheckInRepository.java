package br.com.ghdpreto.nlwunitejavapassin.repositories;

import br.com.ghdpreto.nlwunitejavapassin.domain.checkin.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
}
