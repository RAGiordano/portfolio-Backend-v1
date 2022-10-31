package com.portfolio.RAG.Controller;

import com.portfolio.RAG.Dto.DtoProyecto;
import com.portfolio.RAG.Entity.Proyecto;
import com.portfolio.RAG.Security.Controller.Mensaje;
import com.portfolio.RAG.Service.ImpProyectoService;
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
@RequestMapping("/proyectos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectoController {
    @Autowired
    ImpProyectoService impProyectoService;
    
    @GetMapping("/traer")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = impProyectoService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") Long id){
        if(!impProyectoService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.BAD_REQUEST);
        }
        
        Proyecto proyecto = impProyectoService.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!impProyectoService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        }
        impProyectoService.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }
    
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoProyecto dtoProyecto){
        if(StringUtils.isBlank(dtoProyecto.getNombre())){
            return new ResponseEntity(new Mensaje("Debe ingresar un nombre."), HttpStatus.BAD_REQUEST);
        }
        if(impProyectoService.existsByNombre(dtoProyecto.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre ya existe."), HttpStatus.BAD_REQUEST);
        }
        
        Proyecto proyecto = new Proyecto(dtoProyecto.getNombre(),
                                        dtoProyecto.getUrlImagen(),
                                        dtoProyecto.getDescripcion(),
                                        dtoProyecto.getUrlProyecto(),
                                        dtoProyecto.getFechaRealizacion());
        impProyectoService.save(proyecto);
        return new ResponseEntity(new Mensaje("El proyecto fue guardado correctamente."), HttpStatus.OK);
    }
    
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DtoProyecto dtoProyecto){
        if(!impProyectoService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        }
        if(impProyectoService.existsByNombre(dtoProyecto.getNombre()) && impProyectoService.getByNombre(dtoProyecto.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("El nombre ya existe."), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoProyecto.getNombre())){
            return new ResponseEntity(new Mensaje("El proyecto debe tener un nombre."), HttpStatus.BAD_REQUEST);
        }
        
        Proyecto proyecto = impProyectoService.getOne(id).get();
        
        proyecto.setNombre(dtoProyecto.getNombre());
        proyecto.setUrlImagen(dtoProyecto.getUrlImagen());
        proyecto.setDescripcion(dtoProyecto.getDescripcion());
        proyecto.setUrlProyecto(dtoProyecto.getUrlProyecto());
        proyecto.setFechaRealizacion(dtoProyecto.getFechaRealizacion());
        
        impProyectoService.save(proyecto);
        
        return new ResponseEntity(new Mensaje("Los datos se actualizaron correctamente"), HttpStatus.OK);
    }
}
