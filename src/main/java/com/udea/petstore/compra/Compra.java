package com.udea.petstore.compra;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Compra {

    @Id
    @GeneratedValue
    private Long id;
    private String nombreproducto;
    private int cantidadProductosCompra;

    public Compra() {
    }

    public Compra(String nombreproducto, int cantidadProductosCompra) {
        this.nombreproducto = nombreproducto;
        this.cantidadProductosCompra = cantidadProductosCompra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreproducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreproducto = nombreProducto;
    }

    public int getCantidadProductosCompra() {
        return cantidadProductosCompra;
    }

    public void setCantidadProductosCompra(int cantidadProductosCompra) {
        this.cantidadProductosCompra = cantidadProductosCompra;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", nombreproducto='" + nombreproducto + '\'' +
                ", cantidadProductosCompra=" + cantidadProductosCompra +
                '}';
    }
}
