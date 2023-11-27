package br.com.vladprado.gestao_vagas.modules.company.UseCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vladprado.gestao_vagas.exceptions.UserFoundException;
import br.com.vladprado.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.vladprado.gestao_vagas.modules.company.repositories.CompanyRepositoty;


@Service
public class CreateCompanyUseCases {
    

    @Autowired
    private CompanyRepositoty companyRepositoty;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepositoty
        .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent((user) -> { 
            throw new UserFoundException();
        });
        
        return this.companyRepositoty.save(companyEntity);
    }
}
