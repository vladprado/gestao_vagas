package br.com.vladprado.gestao_vagas.modules.candidate.UseCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vladprado.gestao_vagas.modules.company.entities.JobEntity;
import br.com.vladprado.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ListAllJobsByFilterUseCase {
    
    
    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {
        return this.jobRepository.findByDescriptionContaining(filter);



    }
}
