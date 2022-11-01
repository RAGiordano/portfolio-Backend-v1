
package com.portfolio.RAG.Controller;

import com.portfolio.RAG.Dto.DtoExperiencia;
import com.portfolio.RAG.Entity.Experiencia;
import com.portfolio.RAG.Security.Controller.Mensaje;
import com.portfolio.RAG.Service.ImpExperienciaService;
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
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://rag-ap-frontend.web.app")
public class ExperienciaController {
    @Autowired
    ImpExperienciaService sExperiencia;
    
    //Para solicitar lista de experiencias
    @GetMapping("experiencia/traer")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = sExperiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    //Para dar de alta una nueva experiencia
    @PostMapping("experiencia/crear")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoExperiencia){
        if(StringUtils.isBlank(dtoExperiencia.getNombreEmpresa()))
            return new ResponseEntity(new Mensaje("El nombre de la empresa es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = new Experiencia(dtoExperiencia.getNombreEmpresa(),
                                                dtoExperiencia.getUrlLogo(),
                                                dtoExperiencia.getPuesto(),
                                                dtoExperiencia.getDescripcion(),
                                                dtoExperiencia.getFechaDesde(),
                                                dtoExperiencia.getFechaHasta());
        sExperiencia.save(experiencia);
        return new ResponseEntity(new Mensaje("Se agregó la experiencia."), HttpStatus.OK);
    }
    
    //Para solicitar datos de una experiencia laboral en particular
    @GetMapping("experiencia/detalle/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") Long id){
        if(!sExperiencia.existById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        }
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    

    

    
    //Para actualizar experiencia laboral
    @PutMapping("experiencia/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DtoExperiencia dtoExperiencia){
        //Validación: no existe ID
        if(!sExperiencia.existById(id))
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.BAD_REQUEST);
    
        //Validación: nombre de empresa vacío
        if(StringUtils.isBlank(dtoExperiencia.getNombreEmpresa()))
            return new ResponseEntity(new Mensaje("Se debe ingresar el nombre de la empresa."), HttpStatus.BAD_REQUEST);
        
        //Creo un objeto experiencia
        Experiencia experiencia = sExperiencia.getOne(id).get();
        
        //Al objeto experiencia le asigno los valores correspondientes
        experiencia.setNombreEmpresa(dtoExperiencia.getNombreEmpresa());
        experiencia.setUrlLogo(dtoExperiencia.getUrlLogo());
        experiencia.setPuesto(dtoExperiencia.getPuesto());
        experiencia.setDescripcion(dtoExperiencia.getDescripcion());
        experiencia.setFechaDesde(dtoExperiencia.getFechaDesde());
        experiencia.setFechaHasta(dtoExperiencia.getFechaHasta());    
        
        //Llamo al service para que guarde el objeto
        sExperiencia.save(experiencia);
        
        return new ResponseEntity(new Mensaje("Se guardaron los cambios en Experiencia Laboral."), HttpStatus.OK);
    }
    
    //Para borrar una experiencia laboral
    @DeleteMapping("experiencia/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        //Validación: no existe ID
        if(!sExperiencia.existById(id))
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        sExperiencia.delete(id);
        
        return new ResponseEntity(new Mensaje("La experiencia se eliminó correctamente."), HttpStatus.OK);
    }
}
