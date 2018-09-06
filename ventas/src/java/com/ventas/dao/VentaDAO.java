
package com.ventas.dao;

import com.ventas.model.DetalleVenta;
import com.ventas.model.Venta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class VentaDAO extends DAO{
    
     
    public void registrar(Venta venta, List<DetalleVenta> lista) throws Exception{
        try{
            this.Conectar();
            this.getCn().setAutoCommit(false);
            
            PreparedStatement st1= this.getCn().prepareStatement("INSERT INTO persona(fecha, codPersona, monto) values(?,?,?)");
            st1.setDate(1, venta.getFecha());
            st1.setInt(2, venta.getPersona().getCodigo());
            st1.setDouble(3, venta.getMonto());
            st1.executeUpdate();
            st1.close();
            
            PreparedStatement st2= this.getCn().prepareStatement("SELECT codigo FROM `venta` order by codigo DESC limit 1"); //capturar el ultimo ID
            ResultSet rs;
            int codVenta=0;
            rs=st2.executeQuery();
            while(rs.next()){
                codVenta=rs.getInt(1);
            }
            rs.close();
            
            
            
        for(DetalleVenta det: lista){
            PreparedStatement st3= this.getCn().prepareStatement("INSERT INTO DetalleVenta(codVenta, codProducto, cantidad) values(?,?,?)");
            st3.setInt(1, codVenta);
            st3.setInt(2, det.getProducto().getCodigo());
            st3.setInt(3, det.getCantidad());
            st3.executeUpdate();
            st3.close(); 
            }
           
         this.getCn().commit();
            
        }catch(Exception e){  
            this.getCn().rollback();
            throw e;
        }finally{
            this.Cerrar();
        } 
    }
}
