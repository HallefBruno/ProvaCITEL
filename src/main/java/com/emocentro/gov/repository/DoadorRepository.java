package com.emocentro.gov.repository;

import com.emocentro.gov.entity.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long>, DoadorRepositoryCustom {
    Boolean existsByCpf(@Param("cpf") String cpf);
}
