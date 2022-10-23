package com.portfolio.RAG.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String fecha_nac;
    private String lugar_nac;
    private String telefono;
    //Presentación es cómo me presento, de acuerdo a mi perfil (por ejemplo, desarrollador full stack)
    private String presentacion;
    private String sobre_mi;
    private String contrasena;
    private String tipo_usuario;
    private String objetivo;
    private String email;
    private String url_foto;
    private String url_banner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
