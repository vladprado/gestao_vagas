package br.com.vladprado.gestao_vagas.modules.company.UseCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.vladprado.gestao_vagas.exceptions.UserFoundException;
import br.com.vladprado.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.vladprado.gestao_vagas.modules.company.repositories.CompanyRepositoty;


@Service
public class CreateCompanyUseCase {
    

    @Autowired
    private CompanyRepositoty companyRepositoty;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepositoty
        .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent((user) -> { 
            throw new UserFoundException();
        });

        var password = passwordEncoder.encode(companyEntity.getPassword());

        companyEntity.setPassword(password);
        
        return this.companyRepositoty.save(companyEntity);
    }
}
