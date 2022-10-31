package com.portfolio.RAG.Service;

import com.portfolio.RAG.Entity.Formacion;
import com.portfolio.RAG.Repository.IFormacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpFormacionService {
    @Autowired
    IFormacionRepository iFormacionRepository;
    
    public List<Formacion> list(){
        return iFormacionRepository.findAll();
    }
    
    public Optional<Formacion> getOne(Long id) {
        return iFormacionRepository.findById(id);
    }
    
    public Optional<Formacion> getByTitulo(String titulo){
        return iFormacionRepository.findByTitulo(titulo);
    }
    
    public void save(Formacion formacion){
        iFormacionRepository.save(formacion);
    }
    
    public void delete(Long id){
        iFormacionRepository.deleteById(id);
    }
    
    public boolean existById(Long id){
        return iFormacionRepository.existsById(id);
    }
    
    public boolean existsByTitulo(String titulo){
        return iFormacionRepository.existsByTitulo(titulo);
    }
}
