package com.portfolio.RAG.Repository;

import com.portfolio.RAG.Entity.FormacionComp;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormacionCompRepository extends JpaRepository<FormacionComp, Long>{
    public Optional<FormacionComp> findByTitulo(String titulo);
    public boolean existsByTitulo(String titulo);
}
