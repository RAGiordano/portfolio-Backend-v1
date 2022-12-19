package com.portfolio.RAG.Controller;

import com.portfolio.RAG.Dto.DtoFormacionComp;
import com.portfolio.RAG.Entity.FormacionComp;
import com.portfolio.RAG.Security.Controller.Mensaje;
import com.portfolio.RAG.Service.ImpFormacionCompService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formacioncomp")

@CrossOrigin(origins = "https://rag-ap-frontend.web.app")
public class FormacionCompController {
    @Autowired
    ImpFormacionCompService impFormacionCompService;
    
    //Para solicitar lista de Formación Académica
    @GetMapping("/traer")
    public ResponseEntity<List<FormacionComp>> list(){
        List<FormacionComp> list = impFormacionCompService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    //Para solicitar datos de una formación académica determinada
    @GetMapping("/detalle/{id}")
    public ResponseEntity<FormacionComp> getById(@PathVariable("id") Long id){
        if(!impFormacionCompService.existById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        }
        FormacionComp formacionComp = impFormacionCompService.getOne(id).get();
        return new ResponseEntity(formacionComp, HttpStatus.OK);
    }
    
    
    //Para eliminar una formación
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!impFormacionCompService.existById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        }
        impFormacionCompService.delete(id);
        
        return new ResponseEntity(new Mensaje("La formación complementaria se eliminó correctamente."), HttpStatus.OK);
    }
    
    //Para dar de alta una nueva Formación
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoFormacionComp dtoFormacionComp) {
        if(StringUtils.isBlank(dtoFormacionComp.getTitulo())){
            return new ResponseEntity(new Mensaje("El título es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if(impFormacionCompService.existsByTitulo(dtoFormacionComp.getTitulo())){
            return new ResponseEntity(new Mensaje("El título ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        FormacionComp formacionComp = new FormacionComp (
            dtoFormacionComp.getTitulo(),
            dtoFormacionComp.getUrlLogo(),
            dtoFormacionComp.getInstitucion(),
            dtoFormacionComp.getFechaDesde(),
            dtoFormacionComp.getFechaHasta(),
            dtoFormacionComp.getDescripcion()
        );
        
        impFormacionCompService.save(formacionComp);
        return new ResponseEntity(new Mensaje("Se guardaron los cambios"), HttpStatus.OK);
    }
    
    //Para actualizar formación académica
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DtoFormacionComp dtoFormacionComp){
        if(!impFormacionCompService.existById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        }
        if(StringUtils.isBlank(dtoFormacionComp.getTitulo())){
            return new ResponseEntity(new Mensaje("Se debe ingresar el título."), HttpStatus.BAD_REQUEST);
        }
        FormacionComp formacionComp = impFormacionCompService.getOne(id).get();
        formacionComp.setTitulo(dtoFormacionComp.getTitulo());
        formacionComp.setUrlLogo(dtoFormacionComp.getUrlLogo());
        formacionComp.setInstitucion(dtoFormacionComp.getInstitucion());
        formacionComp.setFechaDesde(dtoFormacionComp.getFechaDesde());
        formacionComp.setFechaHasta(dtoFormacionComp.getFechaHasta());
        formacionComp.setDescripcion(dtoFormacionComp.getDescripcion());
        
        impFormacionCompService.save(formacionComp);
        
        return new ResponseEntity(new Mensaje("Se guardaron los cambios en Formación Complementaria."), HttpStatus.OK);
    }
}
