package com.portfolio.RAG.Service;

import com.portfolio.RAG.Entity.Persona;
import com.portfolio.RAG.Repository.IPersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpPersonaService{
    
    @Autowired
    IPersonaRepository iPersonaRepository;
    
    public List<Persona> list(){
        return iPersonaRepository.findAll();
    }
    
    public Optional<Persona>getOne(Long id){
        return iPersonaRepository.findById(id);
    }
    
    public Optional<Persona> getByDni(String dni){
        return iPersonaRepository.findByDni(dni);
    }
    
    public void save(Persona persona){
        iPersonaRepository.save(persona);
    }
    
    public void delete(Long id){
        iPersonaRepository.deleteById(id);
    }
    
    public boolean existById(Long id){
        return iPersonaRepository.existsById(id);
    }
    
    public boolean existsByDni(String dni){
        return iPersonaRepository.existsByDni(dni);
    }
}
