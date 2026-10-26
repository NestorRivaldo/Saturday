package pe.edu.upeu.crudproductosh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.upeu.crudproductosh.model.Perspectiva;
import pe.edu.upeu.crudproductosh.model.PlanAccion;
import pe.edu.upeu.crudproductosh.service.KpiService;
import pe.edu.upeu.crudproductosh.service.PlanAccionService;

import java.util.List;

@Controller
@RequestMapping("/planes")
@RequiredArgsConstructor
public class PlanAccionController {

    private final PlanAccionService planAccionService;
    private final KpiService kpiService;

    @GetMapping
    public String listar(@RequestParam(value = "perspectiva", required = false) String perspectiva, Model model) {
        List<PlanAccion> planes = planAccionService.listarPorPerspectiva(perspectiva);
        model.addAttribute("planes", planes);
        model.addAttribute("perspectivas", Perspectiva.values());
        model.addAttribute("perspectivaSeleccionada", perspectiva != null ? perspectiva : "Todas");
        model.addAttribute("presupuestoTotal", planAccionService.presupuestoTotal());
        model.addAttribute("title", "Planes de Acción");
        return "planes/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        PlanAccion plan = new PlanAccion();
        plan.setEstado("Propuesto");
        plan.setNivelRiesgo("Riesgo moderado");
        model.addAttribute("plan", plan);
        model.addAttribute("kpis", kpiService.listarTodos());
        model.addAttribute("perspectivas", Perspectiva.values());
        model.addAttribute("titulo", "Registrar Nuevo Plan de Acción");
        return "planes/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("plan") PlanAccion plan, RedirectAttributes redirectAttributes) {
        if (plan.getId() == null || plan.getId().trim().isEmpty()) {
            plan.setId(siguienteId());
        }
        planAccionService.guardar(plan);
        redirectAttributes.addFlashAttribute("mensaje", "Plan de acción " + plan.getId() + " guardado con éxito.");
        redirectAttributes.addFlashAttribute("tipoAlerta", "success");
        return "redirect:/planes";
    }

    /** Genera el siguiente código PA-xxx a partir del mayor existente (evita colisiones al eliminar). */
    private String siguienteId() {
        int mayor = planAccionService.listarTodos().stream()
                .map(PlanAccion::getId)
                .filter(id -> id != null && id.matches("PA-\\d+"))
                .mapToInt(id -> Integer.parseInt(id.substring(3)))
                .max()
                .orElse(0);
        return String.format("PA-%03d", mayor + 1);
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        return planAccionService.obtenerPorId(id)
                .map(plan -> {
                    model.addAttribute("plan", plan);
                    model.addAttribute("kpis", kpiService.listarTodos());
                    model.addAttribute("perspectivas", Perspectiva.values());
                    model.addAttribute("titulo", "Editar Plan de Acción: " + plan.getId());
                    return "planes/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("mensaje", "Plan no encontrado.");
                    redirectAttributes.addFlashAttribute("tipoAlerta", "error");
                    return "redirect:/planes";
                });
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        planAccionService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Plan " + id + " eliminado con éxito.");
        redirectAttributes.addFlashAttribute("tipoAlerta", "info");
        return "redirect:/planes";
    }
}
