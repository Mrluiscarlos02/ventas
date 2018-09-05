
package com.ventas.Bean;

import com.ventas.dao.ProductoDAO;
import com.ventas.model.Producto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@ViewScoped
public class ProductoBean {

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getLstProductos() {
        return lstProductos;
    }

    public void setLstProductos(List<Producto> lstProductos) {
        this.lstProductos = lstProductos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
   
    
    public void listar(String valor) throws Exception{
        ProductoDAO dao;
        try{
             if(valor.equals("F")){
              if(isPostBack()==false){
              dao= new ProductoDAO();
              lstProductos= dao.listar();  
              }
            }else{
              dao= new ProductoDAO();
              lstProductos= dao.listar();             
            }
        }catch(Exception e){
            throw e;
        }
    }
    
    public void leerID(Producto per) throws Exception{
        ProductoDAO dao;
        Producto temp;
        try{
            dao = new ProductoDAO();
            temp = dao.leerID(per);
               
            if (temp != null) {
                this.producto = temp;
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
         this.producto.setCodigo(0);
         this.producto.setNombre("");
         this.producto.setPrecio(0.0);
     }
     
    private void registrar() throws Exception{
        ProductoDAO dao;
        try{
            dao= new ProductoDAO();
            dao.registrar(producto);     
            this.listar("V");
        }catch(Exception e){
            throw e;
        }
    }
    
    
    private void modificar() throws Exception{
        ProductoDAO dao;
        try{
            dao = new ProductoDAO();
            dao.modificar(producto);
            this.listar("V");
        }catch (Exception e){
            throw e;
        }
    }
    
    public void eliminar(Producto per) throws Exception{
        ProductoDAO dao;
        try{
            dao = new ProductoDAO();
            dao.eliminar(per);
             this.listar("V");
        }catch (Exception e){
            throw e;
        }
    }
    
    private Producto producto=new Producto();
    private List<Producto> lstProductos;
    private String accion;
}
