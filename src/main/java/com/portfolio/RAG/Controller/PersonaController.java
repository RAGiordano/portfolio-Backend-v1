package com.portfolio.RAG.Controller;

import com.portfolio.RAG.Entity.Persona;
import com.portfolio.RAG.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    @Autowired IPersonaService iPersonaService;
    
    @GetMapping("personas/traer")
    public List<Persona> getPersona(){
        return iPersonaService.getPersona();
    }
    
    @PostMapping("personas/crear")
    public String createPersona(@RequestBody Persona persona){
        iPersonaService.savePersona(persona);
        return "La persona se creó exitosamente";
    }
    
    @DeleteMapping("personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        iPersonaService.deletePersona(id);
        return "La persona se eliminó exitosamente";
    }
    
    //Para modificar
    @PutMapping("personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                                @RequestParam("dni") String nuevoDni,
                                @RequestParam("nombre") String nuevoNombre,
                                @RequestParam("apellido") String nuevoApellido,
                                @RequestParam("domicilio") String nuevoDomicilio,
                                @RequestParam("fecha_nac") String nuevoFecha_nac,
                                @RequestParam("lugar_nac") String nuevoLugar_nac,
                                @RequestParam("telefono") String nuevoTelefono,
                                @RequestParam("presentacion") String nuevoPresentacion,
                                @RequestParam("sobre_mi") String nuevoSobre_mi,
                                @RequestParam("contrasena") String nuevoContrasena,
                                @RequestParam("tipo_usuario") String nuevoTipo_usuario,
                                @RequestParam("objetivo") String nuevoObjetivo,
                                @RequestParam("email") String nuevoEmail,
                                @RequestParam("url_foto") String nuevoUrl_foto,
                                @RequestParam("url_banner") String nuevoUrl_banner){
        Persona persona = iPersonaService.findPersona(id);
        persona.setDni(nuevoDni);
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setDomicilio(nuevoDomicilio);
        persona.setFecha_nac(nuevoFecha_nac);
        persona.setLugar_nac(nuevoLugar_nac);
        persona.setTelefono(nuevoTelefono);
        persona.setPresentacion(nuevoPresentacion);
        persona.setSobre_mi(nuevoSobre_mi);
        persona.setContrasena(nuevoContrasena);
        persona.setTipo_usuario(nuevoTipo_usuario);
        persona.setObjetivo(nuevoObjetivo);
        persona.setEmail(nuevoEmail);
        persona.setUrl_foto(nuevoUrl_foto);
        persona.setUrl_banner(nuevoUrl_banner);
        
        iPersonaService.savePersona(persona);
        return persona;
    }
    
    @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return iPersonaService.findPersona((long)1);
    }
}
