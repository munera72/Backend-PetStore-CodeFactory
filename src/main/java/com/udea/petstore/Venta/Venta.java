package com.udea.petstore.Venta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Venta {
    @Id
    @GeneratedValue
    private Long id;
    private String usuario;
    private Double total;
    private String mediopago;
    private Boolean ventaespecial = false;
    private int cantidadProductosVenta;

    public Venta() {
    }

    public Venta(String usuario, Double total, String mediopago, Boolean ventaespecial, int cantidadProductosVenta) {
        this.usuario = usuario;
        this.total = total;
        this.mediopago = mediopago;
        this.ventaespecial = ventaespecial;
        this.cantidadProductosVenta = cantidadProductosVenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVentaespecial() {
        return ventaespecial;
    }

    public void setVentaespecial(Boolean ventaespecial) {
        this.ventaespecial = ventaespecial;
    }

    public String getMediopago() {
        return mediopago;
    }

    public void setMediopago(String mediopago) {
        this.mediopago = mediopago;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getCantidadProductosVenta() {return cantidadProductosVenta; }

    public void setCantidadProductosVenta(int cantidadProductosVenta) {
        this.cantidadProductosVenta = cantidadProductosVenta;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "usuario='" + usuario + '\'' +
                ", total=" + total +
                ", mediopago='" + mediopago + '\'' +
                ", cantidadProductosVenta='" + cantidadProductosVenta + '\'' +
                ", ventaespecial=" + ventaespecial +
                '}';
    }
}
