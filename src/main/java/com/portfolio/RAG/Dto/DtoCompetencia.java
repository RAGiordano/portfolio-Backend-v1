package com.portfolio.RAG.Dto;

import javax.validation.constraints.NotBlank;


public class DtoCompetencia {
    @NotBlank
    private String nombre;
    private String descripcion;
    private String urlLogo;
    private int porcentaje;

    public DtoCompetencia() {
    }

    public DtoCompetencia(String nombre, String descripcion, String urlLogo, int porcentaje) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlLogo = urlLogo;
        this.porcentaje = porcentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
