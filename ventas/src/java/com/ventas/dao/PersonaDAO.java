
package com.ventas.dao;

import com.ventas.model.Persona;
import java.sql.PreparedStatement;


public class PersonaDAO extends DAO {
    
    
    
    public void registrar(Persona per) throws Exception{
        try{
            this.Conectar();
            
            String sql=("INSERT INTO persona(nombre, sexo) values(?,?)");
            PreparedStatement st= this.getCn().prepareStatement(sql);
            st.setString(1, per.getNombre());
            st.setString(2, per.getSexo());
            st.executeUpdate();
        }catch(Exception e){           
            throw e;
        }finally{
            this.Cerrar();
        }
 
    }
    
    
}
