package pe.edu.upeu.crudproductosh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Prueba de humo: verifica que todas las vistas Thymeleaf se renderizan (HTTP 200).
 * Sin esta prueba, un fragmento faltante o una expresion invalida pasan desapercibidos
 * hasta que el usuario abre la pagina.
 */
@SpringBootTest
@AutoConfigureMockMvc
class VistasWebTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String[] RUTAS = {
            "/",
            "/dashboard",
            "/objetivos",
            "/objetivos/nuevo",
            "/objetivos/editar/OBJ-F1",
            "/kpis",
            "/kpis?perspectiva=Financiera",
            "/kpis?soloRiesgo=true",
            "/kpis/nuevo",
            "/kpis/detalle/SW-01",
            "/kpis/editar/SW-01",
            "/planes",
            "/planes/nuevo",
            "/planes/editar/PA-001",
            "/mediciones",
            "/mediciones/nuevo",
            "/mediciones/nuevo/SW-01",
            "/api/bsc/metricas",
            "/api/bsc/kpis"
    };

    @Test
    void todasLasVistasResponden200() throws Exception {
        for (String ruta : RUTAS) {
            MvcResult resultado = mockMvc.perform(get(ruta))
                    .andExpect(status().isOk())
                    .andReturn();

            String cuerpo = resultado.getResponse().getContentAsString();
            assertTrue(cuerpo.length() > 0, "La ruta " + ruta + " devolvio una respuesta vacia");
        }
    }

    @Test
    void elDashboardRenderizaElLayoutCompleto() throws Exception {
        MvcResult resultado = mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andReturn();

        String cuerpo = resultado.getResponse().getContentAsString();
        assertTrue(cuerpo.contains("Southwest BSC"), "Falta el sidebar izquierdo");
        assertTrue(cuerpo.contains("Accesos rápidos"), "Falta el sidebar derecho");
        assertTrue(cuerpo.contains("hero-card"), "Falta el encabezado hero");
    }
}
