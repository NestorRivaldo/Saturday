package pe.edu.upeu.crudproductosh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.upeu.crudproductosh.model.Kpi;
import pe.edu.upeu.crudproductosh.model.Perspectiva;
import pe.edu.upeu.crudproductosh.service.KpiService;

import java.util.List;

@Controller
@RequestMapping("/kpis")
@RequiredArgsConstructor
public class KpiController {

    private final KpiService kpiService;

    @GetMapping
    public String listar(@RequestParam(value = "perspectiva", required = false) String perspectiva,
                         @RequestParam(value = "soloRiesgo", required = false, defaultValue = "false") boolean soloRiesgo,
                         Model model) {
        List<Kpi> kpis = kpiService.listarPorPerspectiva(perspectiva);
        if (soloRiesgo) {
            kpis = kpis.stream()
                    .filter(k -> k.getEstadoSemaforo() != null &&
                            (k.getEstadoSemaforo().contains("MODERADO") ||
                             k.getEstadoSemaforo().contains("ALTO") ||
                             k.getEstadoSemaforo().contains("CRÍTICO")))
                    .toList();
            model.addAttribute("soloRiesgo", true);
        } else {
            model.addAttribute("soloRiesgo", false);
        }
        model.addAttribute("kpis", kpis);
        model.addAttribute("perspectivas", Perspectiva.values());
        model.addAttribute("perspectivaSeleccionada", perspectiva != null ? perspectiva : "Todas");
        model.addAttribute("conteoPorPerspectiva", kpiService.conteoPorPerspectiva());
        model.addAttribute("totalKpis", kpiService.totalKpis());
        model.addAttribute("title", "Indicadores");
        return "kpis/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Kpi kpi = new Kpi();
        kpi.setDireccion("Mayor es mejor");
        kpi.setPeriodo("2025 anual");
        model.addAttribute("kpi", kpi);
        model.addAttribute("perspectivas", Perspectiva.values());
        model.addAttribute("titulo", "Registrar Nuevo Indicador (KPI)");
        return "kpis/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("kpi") Kpi kpi, RedirectAttributes redirectAttributes) {
        if (kpi.getId() == null || kpi.getId().trim().isEmpty()) {
            kpi.setId(siguienteId());
        }
        kpiService.guardar(kpi);
        redirectAttributes.addFlashAttribute("mensaje", "Indicador '" + kpi.getNombre() + "' (" + kpi.getId() + ") guardado y recalculado con éxito.");
        redirectAttributes.addFlashAttribute("tipoAlerta", "success");
        return "redirect:/kpis";
    }

    /** Genera el siguiente código SW-xx a partir del mayor existente (evita colisiones al eliminar). */
    private String siguienteId() {
        int mayor = kpiService.listarTodos().stream()
                .map(Kpi::getId)
                .filter(id -> id != null && id.matches("SW-\\d+"))
                .mapToInt(id -> Integer.parseInt(id.substring(3)))
                .max()
                .orElse(0);
        return String.format("SW-%02d", mayor + 1);
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        return kpiService.obtenerPorId(id)
                .map(kpi -> {
                    model.addAttribute("kpi", kpi);
                    model.addAttribute("perspectivas", Perspectiva.values());
                    model.addAttribute("titulo", "Editar Indicador: " + kpi.getId());
                    return "kpis/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("mensaje", "Indicador con ID " + id + " no encontrado.");
                    redirectAttributes.addFlashAttribute("tipoAlerta", "error");
                    return "redirect:/kpis";
                });
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        return kpiService.obtenerPorId(id)
                .map(kpi -> {
                    model.addAttribute("kpi", kpi);
                    model.addAttribute("title", "Ficha " + kpi.getId());
                    return "kpis/detail";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("mensaje", "Indicador no encontrado.");
                    redirectAttributes.addFlashAttribute("tipoAlerta", "error");
                    return "redirect:/kpis";
                });
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        kpiService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Indicador " + id + " eliminado con éxito.");
        redirectAttributes.addFlashAttribute("tipoAlerta", "info");
        return "redirect:/kpis";
    }
}
