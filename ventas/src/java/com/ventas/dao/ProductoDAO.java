
package com.ventas.dao;

import com.ventas.model.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProductoDAO extends DAO {
    
    
    
    public void registrar(Producto per) throws Exception{
        try{
            this.Conectar();
            PreparedStatement st= this.getCn().prepareStatement("INSERT INTO producto(nombre, precio) values(?,?)");
            st.setString(1, per.getNombre());
            st.setDouble(2, per.getPrecio());
            st.executeUpdate();
        }catch(Exception e){           
            throw e;
        }finally{
            this.Cerrar();
        } 
    }
    
    
    public List<Producto> listar() throws Exception{
        List<Producto> lista;
        ResultSet rs;
        try{
            this.Conectar();
            PreparedStatement st=this.getCn().prepareCall("select codigo, nombre, precio from producto");
            rs= st.executeQuery();
            lista=new ArrayList();
            while(rs.next()){
                Producto per=new Producto();
                per.setCodigo(rs.getInt("codigo"));
                per.setNombre(rs.getString("nombre"));
                per.setPrecio(rs.getDouble("precio"));                
                lista.add(per);                
            }
             
        }catch(Exception e){           
            throw e;
        }finally{
            this.Cerrar();
        }
       return lista;
    }
   
        public Producto leerID(Producto per) throws Exception{
            Producto pers=null;
            ResultSet rs;
        try{
            this.Conectar();
            PreparedStatement st= this.getCn().prepareStatement("select codigo, nombre, precio from Producto where codigo=?");
            st.setInt(1, per.getCodigo());
            rs=st.executeQuery();
            while(rs.next()){
                pers=new Producto();
                pers.setCodigo(rs.getInt("codigo"));
                pers.setNombre(rs.getString("nombre"));
                pers.setPrecio(rs.getDouble("precio"));    
            }
        }catch(Exception e){           
            throw e;
        }finally{
            this.Cerrar();
        }
        return pers;
    }
         
    public void modificar(Producto per) throws Exception{
        try{
            this.Conectar();
            PreparedStatement st= this.getCn().prepareStatement("UPDATE Producto SET nombre=? , precio=? where codigo=?");
            st.setString(1, per.getNombre());
            st.setDouble(2, per.getPrecio());
            st.setInt(3, per.getCodigo());
            st.executeUpdate();
        }catch(Exception e){           
            throw e;
        }finally{
            this.Cerrar();
        } 
    }
     
    public void eliminar(Producto per) throws Exception{
        try{
            this.Conectar();
            PreparedStatement st= this.getCn().prepareStatement("delete from producto where codigo=?");
            st.setInt(1, per.getCodigo());
            st.executeUpdate();
        }catch(Exception e){           
            throw e;
        }finally{
            this.Cerrar();
        } 
    }
    
}
