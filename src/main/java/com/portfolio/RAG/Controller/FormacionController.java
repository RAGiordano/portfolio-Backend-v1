package com.portfolio.RAG.Controller;

import com.portfolio.RAG.Dto.DtoFormacion;
import com.portfolio.RAG.Entity.Formacion;
import com.portfolio.RAG.Security.Controller.Mensaje;
import com.portfolio.RAG.Service.ImpFormacionService;
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
@RequestMapping("/formacion")
@CrossOrigin(origins = "http://localhost:4200")
public class FormacionController {
    @Autowired
    ImpFormacionService impFormacionService;
    
    //Para solicitar lista de Formación Académica
    @GetMapping("/traer")
    public ResponseEntity<List<Formacion>> list(){
        List<Formacion> list = impFormacionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    //Para solicitar datos de una formación académica determinada
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Formacion> getById(@PathVariable("id") Long id){
        if(!impFormacionService.existById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        }
        Formacion formacion = impFormacionService.getOne(id).get();
        return new ResponseEntity(formacion, HttpStatus.OK);
    }
    
    
    //Para eliminar una formación
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!impFormacionService.existById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        }
        impFormacionService.delete(id);
        
        return new ResponseEntity(new Mensaje("La formación se eliminó correctamente."), HttpStatus.OK);
    }
    
    //Para dar de alta una nueva Formación
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoFormacion dtoFormacion) {
        if(StringUtils.isBlank(dtoFormacion.getTitulo())){
            return new ResponseEntity(new Mensaje("El título es obligatorio."), HttpStatus.BAD_REQUEST);
        }
        if(impFormacionService.existsByTitulo(dtoFormacion.getTitulo())){
            return new ResponseEntity(new Mensaje("El título ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Formacion formacion = new Formacion (
            dtoFormacion.getTitulo(),
            dtoFormacion.getUrlLogo(),
            dtoFormacion.getInstitucion(),
            dtoFormacion.getFechaDesde(),
            dtoFormacion.getFechaHasta(),
            dtoFormacion.getDescripcion()
        );
        
        impFormacionService.save(formacion);
        return new ResponseEntity(new Mensaje("Se guardaron los cambios"), HttpStatus.OK);
    }
    
    //Para actualizar formación académica
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DtoFormacion dtoFormacion){
        if(!impFormacionService.existById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        }
        if(StringUtils.isBlank(dtoFormacion.getTitulo())){
            return new ResponseEntity(new Mensaje("Se debe ingresar el título."), HttpStatus.BAD_REQUEST);
        }
        Formacion formacion = impFormacionService.getOne(id).get();
        formacion.setTitulo(dtoFormacion.getTitulo());
        formacion.setUrlLogo(dtoFormacion.getUrlLogo());
        formacion.setInstitucion(dtoFormacion.getInstitucion());
        formacion.setFechaDesde(dtoFormacion.getFechaDesde());
        formacion.setFechaHasta(dtoFormacion.getFechaHasta());
        formacion.setDescripcion(dtoFormacion.getDescripcion());
        
        impFormacionService.save(formacion);
        
        return new ResponseEntity(new Mensaje("Se guardaron los cambios en Formación Académica."), HttpStatus.OK);
    }
}
