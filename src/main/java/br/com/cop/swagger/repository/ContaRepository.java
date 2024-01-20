package br.com.cop.swagger.repository;

import br.com.cop.swagger.model.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<ContaCorrente, Long> {
}
