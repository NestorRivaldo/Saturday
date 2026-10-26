package pe.edu.upeu.crudproductosh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "planes_accion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanAccion {

    @Id
    @Column(length = 20)
    private String id; // Ej: "PA-001"

    @Column(nullable = false, length = 60)
    private String perspectiva;

    @Column(nullable = false, length = 150)
    private String indicadorNombre;

    @Column(length = 50)
    private String nivelRiesgo; // "Riesgo moderado", "Riesgo alto", etc.

    @Column(length = 1000)
    private String causa;

    @Column(nullable = false, length = 1000)
    private String accionCorrectiva;

    @Column(length = 100)
    private String responsable;

    @Column(length = 30)
    private String fechaInicio;

    @Column(length = 30)
    private String fechaLimite;

    private Double presupuesto; // en USD

    @Column(length = 30)
    private String estado; // "Propuesto", "En ejecución", "Completado"

    @Column(length = 500)
    private String metaEsperada;

    @Column(length = 500)
    private String nota;
}
