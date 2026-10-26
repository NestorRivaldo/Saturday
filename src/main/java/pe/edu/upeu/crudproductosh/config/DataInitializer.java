package pe.edu.upeu.crudproductosh.config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pe.edu.upeu.crudproductosh.model.Kpi;
import pe.edu.upeu.crudproductosh.model.ObjetivoEstrategico;
import pe.edu.upeu.crudproductosh.model.PlanAccion;
import pe.edu.upeu.crudproductosh.repository.KpiRepository;
import pe.edu.upeu.crudproductosh.repository.ObjetivoEstrategicoRepository;
import pe.edu.upeu.crudproductosh.repository.PlanAccionRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final KpiRepository kpiRepository;
    private final PlanAccionRepository planAccionRepository;
    private final ObjetivoEstrategicoRepository objetivoRepository;

    @Override
    public void run(String... args) {
        if (kpiRepository.count() == 0) {
            log.info("Iniciando carga de 36 KPIs del Balanced Scorecard (Southwest Airlines 2025)...");
            List<Kpi> kpis = new ArrayList<>();
            {
                Kpi k = Kpi.builder()
                    .id("SW-01")
                    .perspectiva("Financiera")
                    .objetivoEstrategico("OBJ-F1: Mejorar la rentabilidad sostenible")
                    .nombre("Margen operativo GAAP")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("2025 anual")
                    .meta(2.0)
                    .real(1.5)
                    .tipoDato("Calculado público")
                    .fuente("S1")
                    .urlFuente("https://investors.southwest.com/sec-filings/all-sec-filings/content/0000092380-26-000004/luv-20251231.htm")
                    .nota("Calculado desde utilidad operativa US$428 MM / ingresos operativos US$28.1 mil MM; meta propuesta.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-02")
                    .perspectiva("Financiera")
                    .objetivoEstrategico("OBJ-F1: Mejorar la rentabilidad sostenible")
                    .nombre("Utilidad neta GAAP")
                    .direccion("Mayor es mejor")
                    .unidad("US$ MM")
                    .periodo("2025 anual")
                    .meta(500.0)
                    .real(441.0)
                    .tipoDato("Público")
                    .fuente("S3")
                    .urlFuente("https://investors.southwest.com/news-events/press-releases/detail/1914/southwest-airlines-reports-fourth-quarter-and-full-year-2025-results-expects-strong-2026-financial-performance-from-business-transformation")
                    .nota("Cifra reportada para 2025; meta propuesta.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-03")
                    .perspectiva("Financiera")
                    .objetivoEstrategico("OBJ-F1: Mejorar la rentabilidad sostenible")
                    .nombre("EBIT ajustado")
                    .direccion("Mayor es mejor")
                    .unidad("US$ MM")
                    .periodo("2025 anual")
                    .meta(600.0)
                    .real(574.0)
                    .tipoDato("Público")
                    .fuente("S3")
                    .urlFuente("https://investors.southwest.com/news-events/press-releases/detail/1914/southwest-airlines-reports-fourth-quarter-and-full-year-2025-results-expects-strong-2026-financial-performance-from-business-transformation")
                    .nota("Resultado ajustado publicado; meta propuesta.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-04")
                    .perspectiva("Financiera")
                    .objetivoEstrategico("OBJ-F2: Impulsar ingresos y demanda de pasajeros")
                    .nombre("Ingresos operativos")
                    .direccion("Mayor es mejor")
                    .unidad("US$ mil MM")
                    .periodo("2025 anual")
                    .meta(29.0)
                    .real(28.1)
                    .tipoDato("Público")
                    .fuente("S3")
                    .urlFuente("https://investors.southwest.com/news-events/press-releases/detail/1914/southwest-airlines-reports-fourth-quarter-and-full-year-2025-results-expects-strong-2026-financial-performance-from-business-transformation")
                    .nota("Récord anual reportado; meta propuesta.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-05")
                    .perspectiva("Financiera")
                    .objetivoEstrategico("OBJ-F2: Impulsar ingresos y demanda de pasajeros")
                    .nombre("Ingresos de pasajeros")
                    .direccion("Mayor es mejor")
                    .unidad("US$ mil MM")
                    .periodo("2025 anual")
                    .meta(26.0)
                    .real(25.5)
                    .tipoDato("Público")
                    .fuente("S1")
                    .urlFuente("https://investors.southwest.com/sec-filings/all-sec-filings/content/0000092380-26-000004/luv-20251231.htm")
                    .nota("Ingresos anuales de pasajeros reportados; meta propuesta.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-06")
                    .perspectiva("Financiera")
                    .objetivoEstrategico("OBJ-F2: Impulsar ingresos y demanda de pasajeros")
                    .nombre("Pasajeros transportados")
                    .direccion("Mayor es mejor")
                    .unidad("millones")
                    .periodo("2025 anual")
                    .meta(140.0)
                    .real(134.11)
                    .tipoDato("Público")
                    .fuente("S1")
                    .urlFuente("https://investors.southwest.com/sec-filings/all-sec-filings/content/0000092380-26-000004/luv-20251231.htm")
                    .nota("134.110 millones en 2025 frente a 140.023 millones en 2024; meta propuesta cercana al nivel anterior.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-07")
                    .perspectiva("Financiera")
                    .objetivoEstrategico("OBJ-F3: Controlar costos unitarios y combustible")
                    .nombre("Gastos operativos por ASM")
                    .direccion("Menor es mejor")
                    .unidad("centavos")
                    .periodo("2025 anual")
                    .meta(15.0)
                    .real(15.35)
                    .tipoDato("Público")
                    .fuente("S1")
                    .urlFuente("https://investors.southwest.com/sec-filings/all-sec-filings/content/0000092380-26-000004/luv-20251231.htm")
                    .nota("Costo operativo unitario reportado; meta propuesta.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-08")
                    .perspectiva("Financiera")
                    .objetivoEstrategico("OBJ-F3: Controlar costos unitarios y combustible")
                    .nombre("Gastos por ASM sin combustible")
                    .direccion("Menor es mejor")
                    .unidad("centavos")
                    .periodo("2025 anual")
                    .meta(12.0)
                    .real(12.44)
                    .tipoDato("Público")
                    .fuente("S1")
                    .urlFuente("https://investors.southwest.com/sec-filings/all-sec-filings/content/0000092380-26-000004/luv-20251231.htm")
                    .nota("Costo unitario reportado; meta propuesta.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-09")
                    .perspectiva("Financiera")
                    .objetivoEstrategico("OBJ-F3: Controlar costos unitarios y combustible")
                    .nombre("Combustible por 1,000 ASM")
                    .direccion("Menor es mejor")
                    .unidad("galones")
                    .periodo("2025 anual")
                    .meta(11.8)
                    .real(12.05)
                    .tipoDato("Calculado público")
                    .fuente("S1")
                    .urlFuente("https://investors.southwest.com/sec-filings/all-sec-filings/content/0000092380-26-000004/luv-20251231.htm")
                    .nota("Calculado como 2,169 millones de galones / 180,046 millones de ASM × 1,000; meta propuesta.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-10")
                    .perspectiva("Clientes")
                    .objetivoEstrategico("OBJ-C1: Ofrecer una experiencia confiable y satisfactoria")
                    .nombre("NPS de pasajeros")
                    .direccion("Mayor es mejor")
                    .unidad("puntos")
                    .periodo("2025 ilustrativo")
                    .meta(60.0)
                    .real(52.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-11")
                    .perspectiva("Clientes")
                    .objetivoEstrategico("OBJ-C1: Ofrecer una experiencia confiable y satisfactoria")
                    .nombre("CSAT post-vuelo")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("2025 ilustrativo")
                    .meta(90.0)
                    .real(86.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-12")
                    .perspectiva("Clientes")
                    .objetivoEstrategico("OBJ-C1: Ofrecer una experiencia confiable y satisfactoria")
                    .nombre("Ranking de aerolíneas en EE. UU. (WSJ)")
                    .direccion("Menor es mejor")
                    .unidad("posición")
                    .periodo("2025 anual")
                    .meta(1.0)
                    .real(1.0)
                    .tipoDato("Público")
                    .fuente("S3")
                    .urlFuente("https://investors.southwest.com/news-events/press-releases/detail/1914/southwest-airlines-reports-fourth-quarter-and-full-year-2025-results-expects-strong-2026-financial-performance-from-business-transformation")
                    .nota("Southwest fue clasificada #1 en “Best U.S. Airlines of 2025”; posición 1 es mejor. Meta propuesta.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-13")
                    .perspectiva("Clientes")
                    .objetivoEstrategico("OBJ-C2: Fortalecer lealtad y recompra")
                    .nombre("Tasa de recompra de pasajeros")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("2025 ilustrativo")
                    .meta(82.0)
                    .real(78.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-14")
                    .perspectiva("Clientes")
                    .objetivoEstrategico("OBJ-C2: Fortalecer lealtad y recompra")
                    .nombre("Retención de miembros Rapid Rewards")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("2025 ilustrativo")
                    .meta(88.0)
                    .real(84.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-15")
                    .perspectiva("Clientes")
                    .objetivoEstrategico("OBJ-C2: Fortalecer lealtad y recompra")
                    .nombre("Equipaje mal manejado por cada 100 piezas")
                    .direccion("Menor es mejor")
                    .unidad("por 100")
                    .periodo("Octubre 2025")
                    .meta(0.28)
                    .real(0.3)
                    .tipoDato("Público DOT")
                    .fuente("S2")
                    .urlFuente("https://www.transportation.gov/sites/dot.gov/files/2026-01/December%202025%20ATCR.pdf")
                    .nota("0.30 por cada 100 piezas en octubre de 2025; meta propuesta.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-16")
                    .perspectiva("Clientes")
                    .objetivoEstrategico("OBJ-C3: Ampliar la base y el acceso de clientes")
                    .nombre("Nuevos miembros de Rapid Rewards")
                    .direccion("Mayor es mejor")
                    .unidad("millones")
                    .periodo("2025 ilustrativo")
                    .meta(13.5)
                    .real(12.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-17")
                    .perspectiva("Clientes")
                    .objetivoEstrategico("OBJ-C3: Ampliar la base y el acceso de clientes")
                    .nombre("Participación en mercado doméstico")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("2025 ilustrativo")
                    .meta(17.0)
                    .real(16.5)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-18")
                    .perspectiva("Clientes")
                    .objetivoEstrategico("OBJ-C3: Ampliar la base y el acceso de clientes")
                    .nombre("Reservas digitales / total de reservas")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("2025 ilustrativo")
                    .meta(88.0)
                    .real(82.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-19")
                    .perspectiva("Procesos internos")
                    .objetivoEstrategico("OBJ-P1: Mejorar puntualidad, finalización y accesibilidad")
                    .nombre("Llegadas puntuales")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("Octubre 2025")
                    .meta(82.0)
                    .real(77.1)
                    .tipoDato("Público DOT")
                    .fuente("S2")
                    .urlFuente("https://www.transportation.gov/sites/dot.gov/files/2026-01/December%202025%20ATCR.pdf")
                    .nota("Southwest: 77.1% llegadas puntuales en octubre 2025; meta propuesta.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-20")
                    .perspectiva("Procesos internos")
                    .objetivoEstrategico("OBJ-P1: Mejorar puntualidad, finalización y accesibilidad")
                    .nombre("Vuelos cancelados")
                    .direccion("Menor es mejor")
                    .unidad("%")
                    .periodo("Octubre 2025")
                    .meta(0.2)
                    .real(0.3)
                    .tipoDato("Público DOT")
                    .fuente("S2")
                    .urlFuente("https://www.transportation.gov/sites/dot.gov/files/2026-01/December%202025%20ATCR.pdf")
                    .nota("0.3% de operaciones canceladas en octubre 2025; meta propuesta.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-21")
                    .perspectiva("Procesos internos")
                    .objetivoEstrategico("OBJ-P1: Mejorar puntualidad, finalización y accesibilidad")
                    .nombre("Sillas de ruedas/scooters mal manejados por cada 100")
                    .direccion("Menor es mejor")
                    .unidad("por 100")
                    .periodo("Octubre 2025")
                    .meta(0.8)
                    .real(0.99)
                    .tipoDato("Público DOT")
                    .fuente("S2")
                    .urlFuente("https://www.transportation.gov/sites/dot.gov/files/2026-01/December%202025%20ATCR.pdf")
                    .nota("0.99 por cada 100 ayudas de movilidad embarcadas en octubre 2025; meta propuesta.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-22")
                    .perspectiva("Procesos internos")
                    .objetivoEstrategico("OBJ-P2: Aumentar productividad de flota y tecnología")
                    .nombre("Tiempo de escala promedio")
                    .direccion("Menor es mejor")
                    .unidad("minutos")
                    .periodo("2025 ilustrativo")
                    .meta(30.0)
                    .real(34.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-23")
                    .perspectiva("Procesos internos")
                    .objetivoEstrategico("OBJ-P2: Aumentar productividad de flota y tecnología")
                    .nombre("Utilización diaria de aeronave")
                    .direccion("Mayor es mejor")
                    .unidad("horas/día")
                    .periodo("2025 ilustrativo")
                    .meta(11.0)
                    .real(10.3)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-24")
                    .perspectiva("Procesos internos")
                    .objetivoEstrategico("OBJ-P2: Aumentar productividad de flota y tecnología")
                    .nombre("Vuelos completados / programados")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("2025 ilustrativo")
                    .meta(99.5)
                    .real(99.2)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-25")
                    .perspectiva("Procesos internos")
                    .objetivoEstrategico("OBJ-P3: Asegurar calidad y resiliencia de proveedores")
                    .nombre("Entregas puntuales de proveedores críticos")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("2025 ilustrativo")
                    .meta(96.0)
                    .real(92.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-26")
                    .perspectiva("Procesos internos")
                    .objetivoEstrategico("OBJ-P3: Asegurar calidad y resiliencia de proveedores")
                    .nombre("Disponibilidad de sistemas operativos")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("2025 ilustrativo")
                    .meta(99.9)
                    .real(99.6)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-27")
                    .perspectiva("Procesos internos")
                    .objetivoEstrategico("OBJ-P3: Asegurar calidad y resiliencia de proveedores")
                    .nombre("Incidentes de mantenimiento no programado")
                    .direccion("Menor es mejor")
                    .unidad("por 1,000 vuelos")
                    .periodo("2025 ilustrativo")
                    .meta(1.3)
                    .real(1.6)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-28")
                    .perspectiva("Aprendizaje y crecimiento")
                    .objetivoEstrategico("OBJ-A1: Desarrollar capacidades para el nuevo modelo")
                    .nombre("Horas de capacitación por empleado")
                    .direccion("Mayor es mejor")
                    .unidad("horas")
                    .periodo("2025 ilustrativo")
                    .meta(40.0)
                    .real(32.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-29")
                    .perspectiva("Aprendizaje y crecimiento")
                    .objetivoEstrategico("OBJ-A1: Desarrollar capacidades para el nuevo modelo")
                    .nombre("Personal capacitado en cambios de producto")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("2025 ilustrativo")
                    .meta(95.0)
                    .real(78.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-30")
                    .perspectiva("Aprendizaje y crecimiento")
                    .objetivoEstrategico("OBJ-A1: Desarrollar capacidades para el nuevo modelo")
                    .nombre("Cumplimiento de capacitación de seguridad")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("2025 ilustrativo")
                    .meta(100.0)
                    .real(94.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-31")
                    .perspectiva("Aprendizaje y crecimiento")
                    .objetivoEstrategico("OBJ-A2: Cuidar clima, compromiso y retención")
                    .nombre("Índice de compromiso del empleado")
                    .direccion("Mayor es mejor")
                    .unidad("puntos / 100")
                    .periodo("2025 ilustrativo")
                    .meta(80.0)
                    .real(73.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-32")
                    .perspectiva("Aprendizaje y crecimiento")
                    .objetivoEstrategico("OBJ-A2: Cuidar clima, compromiso y retención")
                    .nombre("Rotación voluntaria de empleados")
                    .direccion("Menor es mejor")
                    .unidad("%")
                    .periodo("2025 ilustrativo")
                    .meta(12.0)
                    .real(16.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-33")
                    .perspectiva("Aprendizaje y crecimiento")
                    .objetivoEstrategico("OBJ-A2: Cuidar clima, compromiso y retención")
                    .nombre("Incidentes de seguridad laboral")
                    .direccion("Menor es mejor")
                    .unidad("por 200 mil horas")
                    .periodo("2025 ilustrativo")
                    .meta(2.5)
                    .real(3.1)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-34")
                    .perspectiva("Aprendizaje y crecimiento")
                    .objetivoEstrategico("OBJ-A3: Impulsar mejora continua e innovación")
                    .nombre("Ideas de mejora implementadas")
                    .direccion("Mayor es mejor")
                    .unidad("ideas/año")
                    .periodo("2025 ilustrativo")
                    .meta(60.0)
                    .real(45.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-35")
                    .perspectiva("Aprendizaje y crecimiento")
                    .objetivoEstrategico("OBJ-A3: Impulsar mejora continua e innovación")
                    .nombre("Vacantes cubiertas internamente")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("2025 ilustrativo")
                    .meta(45.0)
                    .real(36.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            {
                Kpi k = Kpi.builder()
                    .id("SW-36")
                    .perspectiva("Aprendizaje y crecimiento")
                    .objetivoEstrategico("OBJ-A3: Impulsar mejora continua e innovación")
                    .nombre("Empleados que usan herramientas digitales nuevas")
                    .direccion("Mayor es mejor")
                    .unidad("%")
                    .periodo("2025 ilustrativo")
                    .meta(85.0)
                    .real(72.0)
                    .tipoDato("Ficticio")
                    .fuente("F0")
                    .urlFuente("—")
                    .nota("Estimación ficticia para fines académicos; no es un dato oficial de Southwest.")
                    .build();
                k.calcularIndicadores();
                kpis.add(k);
            }
            kpiRepository.saveAll(kpis);
            log.info("Cargados {} KPIs exitosamente.", kpis.size());
        }

        if (objetivoRepository.count() == 0) {
            log.info("Iniciando carga de Objetivos Estratégicos...");
            List<ObjetivoEstrategico> objetivos = new ArrayList<>();
            objetivos.add(ObjetivoEstrategico.builder()
                .id("OBJ-F1")
                .nombre("Mejorar la rentabilidad sostenible")
                .perspectiva("Financiera")
                .descripcion("Mejorar el retorno del capital y la generación de valor.")
                .responsable("Gerencia Financiera")
                .prioridad("Alta")
                .peso(100)
                .estado("Activo")
                .fechaCreacion("2025-01-05")
                .build());
            objetivos.add(ObjetivoEstrategico.builder()
                .id("OBJ-F2")
                .nombre("Impulsar ingresos y demanda de pasajeros")
                .perspectiva("Financiera")
                .descripcion("Crecer en ventas priorizando margen y retención.")
                .responsable("Gerencia Comercial")
                .prioridad("Alta")
                .peso(90)
                .estado("Activo")
                .fechaCreacion("2025-01-05")
                .build());
            objetivos.add(ObjetivoEstrategico.builder()
                .id("OBJ-F3")
                .nombre("Controlar costos unitarios y combustible")
                .perspectiva("Financiera")
                .descripcion("Elevar la eficiencia de gastos y uso de recursos.")
                .responsable("Gerencia Operativa")
                .prioridad("Alta")
                .peso(95)
                .estado("Activo")
                .fechaCreacion("2025-01-05")
                .build());
            objetivos.add(ObjetivoEstrategico.builder()
                .id("OBJ-C1")
                .nombre("Ofrecer una experiencia confiable y satisfactoria")
                .perspectiva("Clientes")
                .descripcion("Ofrecer una experiencia consistente y confiable.")
                .responsable("Gerencia de Servicio")
                .prioridad("Alta")
                .peso(100)
                .estado("Activo")
                .fechaCreacion("2025-01-05")
                .build());
            objetivos.add(ObjetivoEstrategico.builder()
                .id("OBJ-C2")
                .nombre("Fortalecer lealtad y recompra")
                .perspectiva("Clientes")
                .descripcion("Fortalecer la recompra y las relaciones comerciales.")
                .responsable("Gerencia de Lealtad")
                .prioridad("Media")
                .peso(85)
                .estado("Activo")
                .fechaCreacion("2025-01-05")
                .build());
            objetivos.add(ObjetivoEstrategico.builder()
                .id("OBJ-C3")
                .nombre("Ampliar la base y el acceso de clientes")
                .perspectiva("Clientes")
                .descripcion("Aumentar presencia en segmentos prioritarios.")
                .responsable("Gerencia Comercial")
                .prioridad("Media")
                .peso(80)
                .estado("Activo")
                .fechaCreacion("2025-01-05")
                .build());
            objetivos.add(ObjetivoEstrategico.builder()
                .id("OBJ-P1")
                .nombre("Mejorar puntualidad, finalización y accesibilidad")
                .perspectiva("Procesos internos")
                .descripcion("Reducir tiempos, reprocesos y desperdicio.")
                .responsable("Operaciones")
                .prioridad("Alta")
                .peso(95)
                .estado("Activo")
                .fechaCreacion("2025-01-05")
                .build());
            objetivos.add(ObjetivoEstrategico.builder()
                .id("OBJ-P2")
                .nombre("Aumentar productividad de flota y tecnología")
                .perspectiva("Procesos internos")
                .descripcion("Optimizar uso de aeronaves y sistemas.")
                .responsable("Tecnología y Operaciones")
                .prioridad("Alta")
                .peso(90)
                .estado("Activo")
                .fechaCreacion("2025-01-05")
                .build());
            objetivos.add(ObjetivoEstrategico.builder()
                .id("OBJ-P3")
                .nombre("Asegurar calidad y resiliencia de proveedores")
                .perspectiva("Procesos internos")
                .descripcion("Prevenir incidentes y asegurar cumplimiento.")
                .responsable("Proveedores")
                .prioridad("Media")
                .peso(85)
                .estado("Activo")
                .fechaCreacion("2025-01-05")
                .build());
            objetivos.add(ObjetivoEstrategico.builder()
                .id("OBJ-A1")
                .nombre("Desarrollar capacidades para el nuevo modelo")
                .perspectiva("Aprendizaje y crecimiento")
                .descripcion("Alinear capacidades técnicas con la estrategia.")
                .responsable("Recursos Humanos")
                .prioridad("Alta")
                .peso(90)
                .estado("Activo")
                .fechaCreacion("2025-01-05")
                .build());
            objetivos.add(ObjetivoEstrategico.builder()
                .id("OBJ-A2")
                .nombre("Cuidar clima, compromiso y retención")
                .perspectiva("Aprendizaje y crecimiento")
                .descripcion("Promover clima laboral positivo y retención.")
                .responsable("Recursos Humanos")
                .prioridad("Alta")
                .peso(95)
                .estado("Activo")
                .fechaCreacion("2025-01-05")
                .build());
            objetivos.add(ObjetivoEstrategico.builder()
                .id("OBJ-A3")
                .nombre("Impulsar mejora continua e innovación")
                .perspectiva("Aprendizaje y crecimiento")
                .descripcion("Promover innovación y mejora continua.")
                .responsable("Innovación")
                .prioridad("Media")
                .peso(80)
                .estado("Activo")
                .fechaCreacion("2025-01-05")
                .build());
            objetivoRepository.saveAll(objetivos);
            log.info("Cargados {} Objetivos Estratégicos exitosamente.", objetivos.size());
        }

        if (planAccionRepository.count() == 0) {
            log.info("Iniciando carga de Planes de Acción...");
            List<PlanAccion> planes = new ArrayList<>();
            planes.add(PlanAccion.builder()
                .id("PA-001")
                .perspectiva("Procesos internos")
                .indicadorNombre("Llegadas puntuales")
                .nivelRiesgo("Riesgo moderado")
                .causa("Hipótesis académica: turnarounds y buffers de horario no absorben bien la variabilidad de la red.")
                .accionCorrectiva("Revisar puntualidad por estación y franja; pilotear un plan de recuperación de rotación y tripulación.")
                .responsable("Dirección de Operaciones")
                .fechaInicio("2026-11-01")
                .fechaLimite("2027-01-31")
                .presupuesto(250000.0)
                .estado("Propuesto")
                .metaEsperada("Elevar puntualidad del 77.1% observado en octubre 2025 hacia una meta de trabajo de 82%.")
                .nota("Caso hipotético; dato base DOT, presupuesto y causa inventados.")
                .build());
            planes.add(PlanAccion.builder()
                .id("PA-002")
                .perspectiva("Procesos internos")
                .indicadorNombre("Vuelos cancelados")
                .nivelRiesgo("Riesgo alto")
                .causa("Hipótesis académica: baja holgura de aeronaves o tripulaciones en eventos de disrupción.")
                .accionCorrectiva("Definir reserva operacional por base y simulacros de recuperación de itinerarios.")
                .responsable("Control Operacional")
                .fechaInicio("2026-11-01")
                .fechaLimite("2027-01-31")
                .presupuesto(180000.0)
                .estado("Propuesto")
                .metaEsperada("Reducir cancelaciones desde el 0.3% de octubre 2025 a una meta ilustrativa de 0.2%.")
                .nota("Caso hipotético; DOT informa el resultado, la causa y el plan son inventados.")
                .build());
            planes.add(PlanAccion.builder()
                .id("PA-003")
                .perspectiva("Clientes")
                .indicadorNombre("Equipaje mal manejado por cada 100 piezas")
                .nivelRiesgo("Riesgo moderado")
                .causa("Hipótesis académica: puntos de transferencia manual concentran extravíos y demoras.")
                .accionCorrectiva("Auditar estaciones de mayor volumen y reforzar escaneo en cada transferencia.")
                .responsable("Operaciones de Equipaje")
                .fechaInicio("2026-11-01")
                .fechaLimite("2027-02-28")
                .presupuesto(120000.0)
                .estado("Propuesto")
                .metaEsperada("Reducir la tasa de 0.30 a 0.28 por cada 100 piezas; meta de trabajo.")
                .nota("Caso hipotético; dato base DOT de octubre 2025, causa y presupuesto inventados.")
                .build());
            planes.add(PlanAccion.builder()
                .id("PA-004")
                .perspectiva("Financiera")
                .indicadorNombre("Gastos operativos por ASM")
                .nivelRiesgo("Riesgo moderado")
                .causa("Dato público: gasto unitario sin combustible aumentó 3.2% en 2025. Hipótesis: salarios y costos de operación presionan el costo unitario.")
                .accionCorrectiva("Revisar productividad y turnarounds por estación; separar combustible, salarios y costos controlables.")
                .responsable("Finanzas y Operaciones")
                .fechaInicio("2026-11-01")
                .fechaLimite("2027-03-31")
                .presupuesto(300000.0)
                .estado("Propuesto")
                .metaEsperada("Acercar el costo operativo de 15.35 centavos/ASM a una meta ilustrativa de 15.00.")
                .nota("Resultado 2025 público; causa detallada, acción y presupuesto propuestos.")
                .build());
            planes.add(PlanAccion.builder()
                .id("PA-005")
                .perspectiva("Aprendizaje y crecimiento")
                .indicadorNombre("Personal capacitado en cambios de producto")
                .nivelRiesgo("Riesgo moderado")
                .causa("Hipótesis académica: adopción de nuevos productos requiere refuerzo práctico para equipos de primera línea.")
                .accionCorrectiva("Implementar módulos breves y validar conocimiento en mostrador, cabina y soporte.")
                .responsable("Recursos Humanos y Servicio")
                .fechaInicio("2026-11-01")
                .fechaLimite("2027-02-28")
                .presupuesto(95000.0)
                .estado("Propuesto")
                .metaEsperada("Elevar el porcentaje ficticio de capacitación de 78% a una meta ilustrativa de 95%.")
                .nota("Datos de capacitación ficticios; causa, acción y presupuesto hipotéticos.")
                .build());
            planAccionRepository.saveAll(planes);
            log.info("Cargados {} Planes de Acción exitosamente.", planes.size());
        }
    }
}
