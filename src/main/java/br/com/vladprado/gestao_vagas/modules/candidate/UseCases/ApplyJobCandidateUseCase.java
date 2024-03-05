package br.com.vladprado.gestao_vagas.modules.candidate.UseCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vladprado.gestao_vagas.exceptions.JobNotFoundException;
import br.com.vladprado.gestao_vagas.exceptions.UserNotFoundException;
import br.com.vladprado.gestao_vagas.modules.candidate.controllers.CandidateRepository;
import br.com.vladprado.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.vladprado.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.vladprado.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {
    
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
        //Validade se candidato existe
        this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        // Validar se a vaga existe
        this.jobRepository.findById(idJob)
        .orElseThrow(() -> {
            throw new JobNotFoundException();
        });

        // Candidato se inscrever na vaga
        var applyJob = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobID(idJob)
        .build();

        applyJob = applyJobRepository.save(applyJob);

        return applyJob;
    }
    
}
