
package com.ventas.Bean;

import com.ventas.dao.PersonaDAO;
import com.ventas.model.Persona;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "personaBean")
@RequestScoped
public class PersonaBean {

   
    public PersonaBean() {  
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    public void registrar() throws Exception{
        PersonaDAO dao;
        try{
            dao= new PersonaDAO();
            dao.registrar(persona);
        }catch(Exception e){
            throw e;
        }
    }
    
    private Persona persona=new Persona();
}
