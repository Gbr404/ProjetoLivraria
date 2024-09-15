package com.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import com.curso.domains.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, UUID> {

}
