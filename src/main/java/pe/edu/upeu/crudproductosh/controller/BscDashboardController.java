package pe.edu.upeu.crudproductosh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.upeu.crudproductosh.model.Kpi;
import pe.edu.upeu.crudproductosh.model.Perspectiva;
import pe.edu.upeu.crudproductosh.service.KpiService;
import pe.edu.upeu.crudproductosh.service.PlanAccionService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BscDashboardController {

    private final KpiService kpiService;
    private final PlanAccionService planAccionService;

    @GetMapping({"/", "/dashboard"})
    public String dashboard(@RequestParam(value = "perspectiva", required = false) String perspectiva, Model model) {
        List<Kpi> todosKpis = kpiService.listarTodos();
        List<Kpi> kpisFiltrados = kpiService.listarPorPerspectiva(perspectiva);

        double cumplimientoGlobal = kpiService.promedioCumplimientoGlobal();
        Map<String, Double> cumplimientoPerspectivas = kpiService.promedioCumplimientoPorPerspectiva();
        Map<String, Long> semaforoCounts = kpiService.conteoPorSemaforo();

        // Identificar KPIs que requieren atención urgente (Riesgo moderado, alto o crítico)
        List<Kpi> kpisEnRiesgo = todosKpis.stream()
                .filter(k -> k.getEstadoSemaforo() != null &&
                        (k.getEstadoSemaforo().contains("MODERADO") ||
                         k.getEstadoSemaforo().contains("ALTO") ||
                         k.getEstadoSemaforo().contains("CRÍTICO")))
                .collect(Collectors.toList());

        model.addAttribute("perspectivas", Perspectiva.values());
        model.addAttribute("perspectivaActiva", perspectiva != null && !perspectiva.isEmpty() ? perspectiva : "Todas");
        model.addAttribute("kpis", kpisFiltrados);
        model.addAttribute("totalKpis", (long) todosKpis.size());
        model.addAttribute("cumplimientoGlobal", cumplimientoGlobal);
        model.addAttribute("cumplimientoPerspectivas", cumplimientoPerspectivas);
        model.addAttribute("conteoPorPerspectiva", kpiService.conteoPorPerspectiva());
        model.addAttribute("semaforoCounts", semaforoCounts);
        model.addAttribute("kpisEnRiesgo", kpisEnRiesgo);
        model.addAttribute("planesRecientes", planAccionService.listarTodos());
        model.addAttribute("presupuestoTotal", planAccionService.presupuestoTotal());
        model.addAttribute("fechaHoy", LocalDate.now()
                .format(DateTimeFormatter.ofPattern("d MMM yyyy", Locale.of("es", "PE"))));

        return "dashboard";
    }
}
