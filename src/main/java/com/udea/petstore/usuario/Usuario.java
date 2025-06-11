package com.udea.petstore.usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    @GeneratedValue

    private Long id;

    private String nombreusuario;

    private String contrasenia;

    public Usuario() {
    }

    public Usuario(String contrasenia, String nombreusuario) {
        this.contrasenia = contrasenia;
        this.nombreusuario = nombreusuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombreusuario='" + nombreusuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}
