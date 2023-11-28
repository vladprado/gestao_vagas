package br.com.vladprado.gestao_vagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vladprado.gestao_vagas.modules.company.entities.CompanyEntity;
import java.util.List;


public interface CompanyRepositoty extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);

    Optional<CompanyEntity> findByUsername(String username);
    
}
