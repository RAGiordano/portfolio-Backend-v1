
package com.portfolio.RAG.Repository;

import com.portfolio.RAG.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository <Persona,Long>{
    public Optional<Persona> findByDni(String dni);
    public boolean existsByDni(String dni);
}
