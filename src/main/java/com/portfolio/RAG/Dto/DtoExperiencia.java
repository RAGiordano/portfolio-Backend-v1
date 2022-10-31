package com.portfolio.RAG.Dto;

import javax.validation.constraints.NotBlank;


public class DtoExperiencia {
    @NotBlank
    private String nombreEmpresa;
    private String urlLogo;
    @NotBlank
    private String puesto;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String fechaDesde;
    private String fechaHasta;
    
    //Constructores

    public DtoExperiencia() {
    }

    public DtoExperiencia(String nombreEmpresa, String urlLogo, String puesto, String descripcion, String fechaDesde, String fechaHasta) {
        this.nombreEmpresa = nombreEmpresa;
        this.urlLogo = urlLogo;
        this.puesto = puesto;
        this.descripcion = descripcion;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }
    
    //Getters & Setters

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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


    
    
}
