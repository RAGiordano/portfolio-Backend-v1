package com.portfolio.RAG.Controller;

import com.portfolio.RAG.Dto.DtoPersona;
import com.portfolio.RAG.Entity.Persona;
import com.portfolio.RAG.Security.Controller.Mensaje;
import com.portfolio.RAG.Service.ImpPersonaService;
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
@RequestMapping("/personas")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    @Autowired
    ImpPersonaService impPersonaService;
    
    //Para solicitar lista de personas
    @GetMapping("/traer")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = impPersonaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    //Para solicitar datos de una persona determinada
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") Long id){
        if(!impPersonaService.existById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        }
        Persona persona = impPersonaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
        //Para dar de alta una persona
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoPersona dtoPersona){
        //Validación: no se ingresó el nombre
        if(StringUtils.isBlank(dtoPersona.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre de la persona es obligatorio"), HttpStatus.BAD_REQUEST);
        //Validación: no se ingresó el apellido
        if(StringUtils.isBlank(dtoPersona.getApellido()))
            return new ResponseEntity(new Mensaje("El apellido de la persona es obligatorio"), HttpStatus.BAD_REQUEST);
        //Validación: no se ingresó el DNI
        if(StringUtils.isBlank(dtoPersona.getDni()))
            return new ResponseEntity(new Mensaje("El DNI de la persona es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Persona persona = new Persona(dtoPersona.getDni(),
                                        dtoPersona.getNombre(),
                                        dtoPersona.getApellido(),
                                        dtoPersona.getFechaNac(),
                                        dtoPersona.getLugarNac(),
                                        dtoPersona.getTelefono(),
                                        dtoPersona.getPresentacion(),
                                        dtoPersona.getSobreMi(),
                                        dtoPersona.getEmail(),
                                        dtoPersona.getUrlFoto(),
                                        dtoPersona.getUrlBanner());
      
        impPersonaService.save(persona);
        return new ResponseEntity(new Mensaje("Se agregó la persona."), HttpStatus.OK);
    }
    
    //Para borrar una persona
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        //Validación: no existe ID
        if(!impPersonaService.existById(id))
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        impPersonaService.delete(id);
        
        return new ResponseEntity(new Mensaje("El registro se eliminó correctamente."), HttpStatus.OK);
    }
   
    //Para actualizar los datos de una persona
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DtoPersona dtoPersona){
        if(!impPersonaService.existById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID."), HttpStatus.NOT_FOUND);
        }
        if(StringUtils.isBlank(dtoPersona.getNombre())){
            return new ResponseEntity(new Mensaje("Se debe ingresar el nombre."), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoPersona.getApellido())){
            return new ResponseEntity(new Mensaje("Se debe ingresar el apellido."), HttpStatus.BAD_REQUEST);
        }
        Persona persona = impPersonaService.getOne(id).get();
        persona.setNombre(dtoPersona.getNombre());
        persona.setApellido(dtoPersona.getApellido());
        persona.setTelefono(dtoPersona.getTelefono());
        persona.setPresentacion(dtoPersona.getPresentacion());
        persona.setSobreMi(dtoPersona.getSobreMi());
        persona.setEmail(dtoPersona.getEmail());
        persona.setUrlFoto(dtoPersona.getUrlFoto());
        persona.setUrlBanner(dtoPersona.getUrlBanner());
        

        impPersonaService.save(persona);
        
        return new ResponseEntity(new Mensaje("Se guardaron los cambios en los datos de la persona."), HttpStatus.OK);
    }
}
