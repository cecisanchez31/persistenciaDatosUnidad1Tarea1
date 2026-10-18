package com.clinicaOdontologica.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tb_historiaClinica")
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class historiaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hc_id",nullable=false)
    private Long id;
    
   @Column(name = "nombre", nullable=false)
   private String nombre;
   
   @Column(name = "hc_direccion")
   private String direccion;
   
   @Column(name = "hc_motivoconsulta")
   private String motivoConsulta;
    
    // 🔧 Getters y Setters obligatorios
   public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }
    
    public historiaClinica() {
        
    }


    // Constructor útil para crear nuevos registros sin ID
    public historiaClinica(String nombre, String direccion, String motivoConsulta) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.motivoConsulta = motivoConsulta;
    }
}
