package com.portfolio.RAG.Dto;

import javax.validation.constraints.NotBlank;


public class DtoFormacion {
    @NotBlank
    private String titulo;
    private String urlLogo;
    @NotBlank
    private String institucion;
    private String fechaDesde;
    private String fechaHasta;
    @NotBlank
    private String descripcion;
    
    //Constructores

    public DtoFormacion() {
    }

    public DtoFormacion(String titulo, String urlLogo, String institucion, String fechaDesde, String fechaHasta, String descripcion) {
        this.titulo = titulo;
        this.urlLogo = urlLogo;
        this.institucion = institucion;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.descripcion = descripcion;
    }
    
    //Getters & Setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
