
package com.ventas.Bean;

import com.ventas.dao.PersonaDAO;
import com.ventas.model.Persona;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@ViewScoped
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

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
   
    
    public void listar(String valor) throws Exception{
        PersonaDAO dao;
        try{
            if(valor.equals("F")){
              if(isPostBack()==false){
              dao= new PersonaDAO();
              lstPersonas= dao.listar();  
              }
            }else{
              dao= new PersonaDAO();
              lstPersonas= dao.listar();             
            }
        }catch(Exception e){
            throw e;
        }
    }
    
    public void leerID(Persona per) throws Exception{
        PersonaDAO dao;
        Persona temp;
        try{
            dao = new PersonaDAO();
            temp = dao.leerID(per);
               
            if (temp != null) {
                this.persona = temp;
                this.accion="Modificar";
            }
        }catch (Exception e){
            throw e;
        }
    }
    
    private boolean isPostBack(){
        boolean rpta;
       rpta=FacesContext.getCurrentInstance().isPostback();
        return rpta;
    }
    
     public void operar() throws Exception{
         switch(accion){
             case "Registrar":
                 this.registrar();
                 this.limpiar();
                break;
             case "Modificar":
                 this.modificar();
                 this.limpiar();
             break;             
         }
     }
    
     public void limpiar(){
         this.persona.setCodigo(0);
         this.persona.setNombre("");
         this.persona.setSexo("");
     }
     
    private void registrar() throws Exception{
        PersonaDAO dao;
        try{
            dao= new PersonaDAO();
            dao.registrar(persona);     
            this.listar("V");
        }catch(Exception e){
            throw e;
        }
    }
    
    
    private void modificar() throws Exception{
        PersonaDAO dao;
        try{
            dao = new PersonaDAO();
            dao.modificar(persona);
            this.listar("V");
        }catch (Exception e){
            throw e;
        }
    }
    
    public void eliminar(Persona per) throws Exception{
        PersonaDAO dao;
        try{
            dao = new PersonaDAO();
            dao.eliminar(per);
            this.listar("V");
        }catch (Exception e){
            throw e;
        }
    }
    
    private Persona persona=new Persona();
    private List<Persona> lstPersonas;
    private String accion;
}
