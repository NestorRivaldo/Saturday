package pe.edu.upeu.crudproductosh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "kpis")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Kpi {

    @Id
    @Column(length = 20)
    private String id; // Ej: "SW-01"

    @Column(nullable = false, length = 60)
    private String perspectiva; // Financiera, Clientes, Procesos internos, Aprendizaje y crecimiento

    @Column(length = 150)
    private String objetivoEstrategico; // Ej: "OBJ-F1: Mejorar la rentabilidad sostenible"

    @Column(nullable = false, length = 150)
    private String nombre; // Ej: "Margen operativo GAAP"

    @Column(nullable = false, length = 30)
    private String direccion; // "Mayor es mejor" o "Menor es mejor"

    @Column(length = 30)
    private String unidad; // "%", "US$ MM", "centavos", "galones", "puntos", etc.

    @Column(length = 50)
    private String periodo; // "2025 anual", "Octubre 2025", etc.

    private Double meta;
    private Double real;
    private Double porcentajeCumplimiento;

    @Column(length = 50)
    private String estadoSemaforo; // "🌟 SUPERA META", "🟢 LOGRADO", "🟡 CASI LOGRADO", "🟠 RIESGO MODERADO", "🔴 RIESGO ALTO", "⛔ CRÍTICO"

    @Column(length = 50)
    private String tipoDato; // "Público", "Calculado público", "Ficticio"

    @Column(length = 50)
    private String fuente; // "S1", "S2", "S3", etc.

    @Column(length = 500)
    private String urlFuente;

    @Column(length = 500)
    private String nota;

    @PrePersist
    @PreUpdate
    public void calcularIndicadores() {
        if (meta == null || real == null) {
            this.porcentajeCumplimiento = 0.0;
            this.estadoSemaforo = "⛔ CRÍTICO";
            return;
        }

        boolean menorEsMejor = direccion != null && direccion.trim().equalsIgnoreCase("Menor es mejor");
        double calculo;

        if (menorEsMejor) {
            if (real <= 0) {
                calculo = 100.0;
            } else {
                calculo = (meta / real) * 100.0;
            }
        } else {
            if (meta == 0) {
                calculo = 100.0;
            } else {
                calculo = (real / meta) * 100.0;
            }
        }

        this.porcentajeCumplimiento = Math.round(calculo * 100.0) / 100.0;

        if (porcentajeCumplimiento >= 110.0) {
            this.estadoSemaforo = "🌟 SUPERA META";
        } else if (porcentajeCumplimiento >= 100.0) {
            this.estadoSemaforo = "🟢 LOGRADO";
        } else if (porcentajeCumplimiento >= 90.0) {
            this.estadoSemaforo = "🟡 CASI LOGRADO";
        } else if (porcentajeCumplimiento >= 75.0) {
            this.estadoSemaforo = "🟠 RIESGO MODERADO";
        } else if (porcentajeCumplimiento >= 60.0) {
            this.estadoSemaforo = "🔴 RIESGO ALTO";
        } else {
            this.estadoSemaforo = "⛔ CRÍTICO";
        }
    }

    public String getColorBadgeClass() {
        if (estadoSemaforo == null) return "badge-critico";
        if (estadoSemaforo.contains("SUPERA")) return "badge-supera";
        if (estadoSemaforo.contains("LOGRADO")) return "badge-logrado";
        if (estadoSemaforo.contains("CASI")) return "badge-casi";
        if (estadoSemaforo.contains("MODERADO")) return "badge-moderado";
        if (estadoSemaforo.contains("ALTO")) return "badge-alto";
        return "badge-critico";
    }
}
