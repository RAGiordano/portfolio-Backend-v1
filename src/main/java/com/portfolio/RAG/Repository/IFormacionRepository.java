package com.portfolio.RAG.Repository;

import com.portfolio.RAG.Entity.Formacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormacionRepository extends JpaRepository<Formacion, Long>{
    public Optional<Formacion> findByTitulo(String titulo);
    public boolean existsByTitulo(String titulo);
}
    

