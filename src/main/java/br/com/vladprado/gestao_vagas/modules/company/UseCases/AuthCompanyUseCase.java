package br.com.vladprado.gestao_vagas.modules.company.UseCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.vladprado.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.vladprado.gestao_vagas.modules.company.repositories.CompanyRepositoty;

@Service
public class AuthCompanyUseCase {
    
    @Autowired
    private CompanyRepositoty companyRepositoty;


    @Autowired
    private PasswordEncoder passwordEncoder;


    public void execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {

        var company = this.companyRepositoty.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
            () -> {
                throw new UsernameNotFoundException("Company not found.");

            }
        );

        // verificar a senha
        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if(!passwordMatches) {
            throw new AuthenticationException();
        }
            //senão for igual, retorna erro
            //Se for igual, retorna token

    }

}
