
package com.ventas.dao;

import com.ventas.model.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PersonaDAO extends DAO {
    
    
    
    public void registrar(Persona per) throws Exception{
        try{
            this.Conectar();
            PreparedStatement st= this.getCn().prepareStatement("INSERT INTO persona(nombre, sexo) values(?,?)");
            st.setString(1, per.getNombre());
            st.setString(2, per.getSexo());
            st.executeUpdate();
        }catch(Exception e){           
            throw e;
        }finally{
            this.Cerrar();
        } 
    }
    
    
    
    public List<Persona> listar() throws Exception{
        List<Persona> lista;
        ResultSet rs;
        try{
            this.Conectar();
            PreparedStatement st=this.getCn().prepareCall("select codigo, nombre, sexo from persona");
            rs= st.executeQuery();
            lista=new ArrayList();
            while(rs.next()){
                Persona per=new Persona();
                per.setCodigo(rs.getInt("codigo"));
                per.setNombre(rs.getString("nombre"));
                per.setSexo(rs.getString("sexo"));                
                lista.add(per);                
            }
             
        }catch(Exception e){           
            throw e;
        }finally{
            this.Cerrar();
        }
       return lista;
    }
   
}
