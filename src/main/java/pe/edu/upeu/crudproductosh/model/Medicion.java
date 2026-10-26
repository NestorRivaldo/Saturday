package pe.edu.upeu.crudproductosh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mediciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String kpiId; // ID del KPI asociado

    @Column(nullable = false, length = 20)
    private String periodo; // Ej: "2026-01", "2026-02"

    @Column(length = 20)
    private String fecha; // Formato "2026-01-28"

    private Double valor; // Valor medido del KPI

    @Column(length = 500)
    private String notas; // Notas sobre la medición

    @Column(length = 100)
    private String responsable; // Responsable de la medición
}
