package com.portfolio.RAG.Repository;

import com.portfolio.RAG.Entity.Competencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompetenciaRepository extends JpaRepository<Competencia, Long>{
    Optional<Competencia> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
 
}
