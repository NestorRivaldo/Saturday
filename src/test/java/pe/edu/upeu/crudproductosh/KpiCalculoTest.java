package pe.edu.upeu.crudproductosh;

import org.junit.jupiter.api.Test;
import pe.edu.upeu.crudproductosh.model.Kpi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Verifica la logica de cumplimiento y semaforo sin levantar el contexto de Spring. */
class KpiCalculoTest {

    @Test
    void mayorEsMejorCalculaSobreLaMeta() {
        Kpi kpi = Kpi.builder()
                .direccion("Mayor es mejor")
                .meta(100.0)
                .real(110.0)
                .build();

        kpi.calcularIndicadores();

        assertEquals(110.0, kpi.getPorcentajeCumplimiento());
        assertEquals("🌟 SUPERA META", kpi.getEstadoSemaforo());
    }

    @Test
    void menorEsMejorInvierteElCalculo() {
        Kpi kpi = Kpi.builder()
                .direccion("Menor es mejor")
                .meta(10.0)
                .real(12.0)
                .build();

        kpi.calcularIndicadores();

        assertEquals(83.33, kpi.getPorcentajeCumplimiento());
        assertEquals("🟠 RIESGO MODERADO", kpi.getEstadoSemaforo());
    }

    @Test
    void metaCeroConResultadoPositivoSeConsideraLogrado() {
        Kpi kpi = Kpi.builder()
                .direccion("Mayor es mejor")
                .meta(0.0)
                .real(5.0)
                .build();

        kpi.calcularIndicadores();

        assertEquals(100.0, kpi.getPorcentajeCumplimiento());
        assertEquals("🟢 LOGRADO", kpi.getEstadoSemaforo());
    }

    @Test
    void sinDatosQuedaEnCritico() {
        Kpi kpi = Kpi.builder()
                .direccion("Mayor es mejor")
                .build();

        kpi.calcularIndicadores();

        assertEquals(0.0, kpi.getPorcentajeCumplimiento());
        assertTrue(kpi.getEstadoSemaforo().contains("CRÍTICO"));
    }
}
