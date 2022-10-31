package com.portfolio.RAG.Service;

import com.portfolio.RAG.Entity.Experiencia;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfolio.RAG.Repository.IExperienciaRepository;

@Service
@Transactional
public class ImpExperienciaService {
    @Autowired
    IExperienciaRepository rExperiencia;
    
    public List<Experiencia> list(){
        return rExperiencia.findAll();
    }
    
    public Optional<Experiencia>getOne(Long id){
        return rExperiencia.findById(id);
    }
    
    public Optional<Experiencia> getByNombreEmpresa(String nombreEmpresa){
        return rExperiencia.findByNombreEmpresa(nombreEmpresa);
    }
    
    public void save(Experiencia experiencia){
        rExperiencia.save(experiencia);
    }
    
    public void delete(Long id){
        rExperiencia.deleteById(id);
    }
    
    public boolean existById(Long id){
        return rExperiencia.existsById(id);
    }
    
    public boolean existsByNombreEmpresa(String nombreEmpresa){
        return rExperiencia.existsByNombreEmpresa(nombreEmpresa);
    }
}
