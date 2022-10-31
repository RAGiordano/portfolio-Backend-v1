package com.portfolio.RAG.Dto;

import javax.validation.constraints.NotBlank;


public class DtoPersona {
    
    @NotBlank
    private String dni;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    //private String domicilio;
    private String fechaNac;
    private String lugarNac;
    private String telefono;
    //Presentación es cómo me presento, de acuerdo a mi perfil (por ejemplo, desarrollador full stack)
    private String presentacion;
    private String sobreMi;
    //private String contrasena;
    //private String tipo_usuario;
    //private String objetivo;
    private String email;
    private String urlFoto;
    private String urlBanner;

    
    //Constructores

    public DtoPersona() {
    }

    public DtoPersona(String dni, String nombre, String apellido, String fechaNac, String lugarNac, String telefono, String presentacion, String sobreMi, String email, String urlFoto, String urlBanner) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.lugarNac = lugarNac;
        this.telefono = telefono;
        this.presentacion = presentacion;
        this.sobreMi = sobreMi;
        this.email = email;
        this.urlFoto = urlFoto;
        this.urlBanner = urlBanner;
    }

    //Getters & Setters

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getLugarNac() {
        return lugarNac;
    }

    public void setLugarNac(String lugarNac) {
        this.lugarNac = lugarNac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getSobreMi() {
        return sobreMi;
    }

    public void setSobreMi(String sobreMi) {
        this.sobreMi = sobreMi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUrlBanner() {
        return urlBanner;
    }

    public void setUrlBanner(String urlBanner) {
        this.urlBanner = urlBanner;
    }

    
}
