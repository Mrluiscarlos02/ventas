
package com.ventas.Bean;

import com.ventas.dao.PersonaDAO;
import com.ventas.model.Persona;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class PersonaBean {

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<Persona> lstPersonas) {
        this.lstPersonas = lstPersonas;
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
    
    
    public void listar() throws Exception{
        PersonaDAO dao;
        try{
            dao= new PersonaDAO();
            lstPersonas= dao.listar();
        }catch(Exception e){
            throw e;
        }
    }
    
    private Persona persona=new Persona();
    private List<Persona> lstPersonas;
}
