package pe.edu.upeu.crudproductosh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "objetivos_estrategicos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjetivoEstrategico {

    @Id
    @Column(length = 20)
    private String id; // Ej: "OBJ-01"

    @Column(nullable = false, length = 150)
    private String nombre; // Ej: "Incrementar la rentabilidad"

    @Column(nullable = false, length = 60)
    private String perspectiva; // Financiera, Clientes, Procesos internos, Aprendizaje y crecimiento

    @Column(length = 500)
    private String descripcion; // Descripción detallada del objetivo

    @Column(length = 100)
    private String responsable; // Responsable del objetivo

    @Column(length = 30)
    private String prioridad; // Alta, Media, Baja

    private Integer peso; // Peso en porcentaje (ej: 100, 90, 80)

    @Column(length = 30)
    private String estado; // Activo, Inactivo

    @Column(length = 20)
    private String fechaCreacion; // Formato "2026-01-05"
}
