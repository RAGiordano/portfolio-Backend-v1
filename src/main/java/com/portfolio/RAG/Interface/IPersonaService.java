
package com.portfolio.RAG.Interface;

import com.portfolio.RAG.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    //Traer lista de personas
    public List<Persona> getPersona();
    
    //Crear o modificar una persona
    public void savePersona(Persona persona);
    
    //Eliminar una persona
    public void deletePersona(Long id);
    
    //Buscar una persona con el id
    public Persona findPersona(Long id);
}
