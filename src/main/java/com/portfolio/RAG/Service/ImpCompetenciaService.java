package com.portfolio.RAG.Service;

import com.portfolio.RAG.Entity.Competencia;
import com.portfolio.RAG.Repository.ICompetenciaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ImpCompetenciaService {
    @Autowired
    ICompetenciaRepository iCompetenciaRepository;
    
    public List<Competencia> list(){
        return iCompetenciaRepository.findAll();
    }
    
    public Optional<Competencia> getOne(Long id){
        return iCompetenciaRepository.findById(id);
    }
    
    public Optional<Competencia> getByNombre(String nombre){
        return iCompetenciaRepository.findByNombre(nombre);
    }
    
    public void save(Competencia comp){
        iCompetenciaRepository.save(comp);
    }
    
    public void delete(Long id){
        iCompetenciaRepository.deleteById(id);
    }
    
    public boolean existsById(Long id){
        return iCompetenciaRepository.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return iCompetenciaRepository.existsByNombre(nombre);
    }
}
