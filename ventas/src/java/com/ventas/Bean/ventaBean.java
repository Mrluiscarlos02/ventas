package com.ventas.Bean;

import com.ventas.model.DetalleVenta;
import com.ventas.model.Producto;
import com.ventas.model.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ventaBean {

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<DetalleVenta> getLista() {
        return lista;
    }

    public void setLista(List<DetalleVenta> lista) {
        this.lista = lista;
    }
    public void agregar(){
        DetalleVenta det=new DetalleVenta();
        det.setCantidad(cantidad);
        det.setProducto(producto);  
        this.lista.add(det);
    }
    
    private Venta venta=new Venta();
    private Producto producto = new Producto();
    private int cantidad;
    private List<DetalleVenta> lista= new ArrayList();
}
