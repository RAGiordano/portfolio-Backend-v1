package com.portfolio.RAG.Controller;
import com.portfolio.RAG.Dto.DtoCompetencia;
import com.portfolio.RAG.Entity.Competencia;
import com.portfolio.RAG.Security.Controller.Mensaje;
import com.portfolio.RAG.Service.ImpCompetenciaService;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/competencias")
public class CompetenciaController {
    @Autowired
    ImpCompetenciaService impCompetenciaService;
 
    
    //Para solicitar lista de competencias
    @GetMapping("/traer")
    public ResponseEntity<List<Competencia>> list(){
        List<Competencia> list = impCompetenciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    //Para dar de alta una nueva competencia
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoCompetencia dtoCompetencia){
        if(StringUtils.isBlank(dtoCompetencia.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre de la competencia es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Competencia competencia = new Competencia(dtoCompetencia.getNombre(),
                                                dtoCompetencia.getUrlLogo(),
                                                dtoCompetencia.getDescripcion(),
                                                dtoCompetencia.getPorcentaje());
        impCompetenciaService.save(competencia);
        return new ResponseEntity(new Mensaje("Se agregó la competencia."), HttpStatus.OK);
    }
    
    //Para solicitar datos de una competencia en particular
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Competencia> getById(@PathVariable("id") Long id){
        if(!impCompetenciaService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        Competencia competencia = impCompetenciaService.getOne(id).get();
        return new ResponseEntity(competencia, HttpStatus.OK);
    }
    


    
    //Para actualizar experiencia laboral
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DtoCompetencia dtoCompetencia){
        //Validación: no existe ID
        if(!impCompetenciaService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.BAD_REQUEST);
    
        //Validación: nombre de competencia vacío
        if(StringUtils.isBlank(dtoCompetencia.getNombre()))
            return new ResponseEntity(new Mensaje("Se debe ingresar el nombre de la competencia."), HttpStatus.BAD_REQUEST);
        
        //Creo un objeto competencia
        Competencia competencia = impCompetenciaService.getOne(id).get();
        
        //Al objeto competencia le asigno los valores correspondientes
        competencia.setNombre(dtoCompetencia.getNombre());
        competencia.setUrlLogo(dtoCompetencia.getUrlLogo());
        competencia.setDescripcion(dtoCompetencia.getDescripcion());
        competencia.setPorcentaje(dtoCompetencia.getPorcentaje());
        
        //Llamo al service para que guarde el objeto
        impCompetenciaService.save(competencia);
        
        return new ResponseEntity(new Mensaje("Se guardaron los cambios en la competencia."), HttpStatus.OK);
    }
    
    //Para borrar una competencia laboral
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        //Validación: no existe ID
        if(!impCompetenciaService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        impCompetenciaService.delete(id);
        
        return new ResponseEntity(new Mensaje("La competencia se eliminó correctamente."), HttpStatus.OK);
    }
}


