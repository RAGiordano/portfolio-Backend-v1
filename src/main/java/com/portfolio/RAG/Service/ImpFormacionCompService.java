package com.portfolio.RAG.Service;

import com.portfolio.RAG.Entity.FormacionComp;
import com.portfolio.RAG.Repository.IFormacionCompRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpFormacionCompService {
    @Autowired
    IFormacionCompRepository iFormacionCompRepository;
    
    public List<FormacionComp> list(){
        return iFormacionCompRepository.findAll();
    }
    
    public Optional<FormacionComp> getOne(Long id) {
        return iFormacionCompRepository.findById(id);
    }
    
    public Optional<FormacionComp> getByTitulo(String titulo){
        return iFormacionCompRepository.findByTitulo(titulo);
    }
    
    public void save(FormacionComp formacionComp){
        iFormacionCompRepository.save(formacionComp);
    }
    
    public void delete(Long id){
        iFormacionCompRepository.deleteById(id);
    }
    
    public boolean existById(Long id){
        return iFormacionCompRepository.existsById(id);
    }
    
    public boolean existsByTitulo(String titulo){
        return iFormacionCompRepository.existsByTitulo(titulo);
    }
}
