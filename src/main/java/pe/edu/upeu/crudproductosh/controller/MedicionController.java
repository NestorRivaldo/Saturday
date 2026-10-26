package pe.edu.upeu.crudproductosh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.upeu.crudproductosh.model.Kpi;
import pe.edu.upeu.crudproductosh.model.Medicion;
import pe.edu.upeu.crudproductosh.service.KpiService;
import pe.edu.upeu.crudproductosh.service.MedicionService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/mediciones")
@RequiredArgsConstructor
public class MedicionController {

    private final MedicionService medicionService;
    private final KpiService kpiService;

    @GetMapping
    public String listar(Model model) {
        List<Medicion> mediciones = medicionService.listarTodos();
        model.addAttribute("mediciones", mediciones);
        model.addAttribute("kpis", kpiService.listarTodos());
        model.addAttribute("title", "Mediciones");
        return "mediciones/list";
    }

    @GetMapping("/kpi/{kpiId}")
    public String listarPorKpi(@PathVariable("kpiId") String kpiId, Model model) {
        List<Medicion> mediciones = medicionService.listarPorKpiIdOrdenado(kpiId);
        Optional<Kpi> kpi = kpiService.obtenerPorId(kpiId);
        model.addAttribute("mediciones", mediciones);
        model.addAttribute("kpi", kpi.orElse(null));
        return "mediciones/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Medicion medicion = new Medicion();
        java.time.LocalDate today = java.time.LocalDate.now();
        medicion.setFecha(today.toString());
        model.addAttribute("medicion", medicion);
        model.addAttribute("kpis", kpiService.listarTodos());
        model.addAttribute("titulo", "Registrar Nueva Medición");
        return "mediciones/form";
    }

    @GetMapping("/nuevo/{kpiId}")
    public String nuevoParaKpi(@PathVariable("kpiId") String kpiId, Model model) {
        Medicion medicion = new Medicion();
        medicion.setKpiId(kpiId);
        java.time.LocalDate today = java.time.LocalDate.now();
        medicion.setFecha(today.toString());
        model.addAttribute("medicion", medicion);
        model.addAttribute("kpis", kpiService.listarTodos());
        model.addAttribute("titulo", "Registrar Medición para KPI: " + kpiId);
        return "mediciones/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("medicion") Medicion medicion, RedirectAttributes redirectAttributes) {
        medicionService.guardar(medicion);
        redirectAttributes.addFlashAttribute("mensaje", "Medición registrada con éxito.");
        redirectAttributes.addFlashAttribute("tipoAlerta", "success");
        return "redirect:/mediciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        return medicionService.obtenerPorId(id)
                .map(medicion -> {
                    model.addAttribute("medicion", medicion);
                    model.addAttribute("kpis", kpiService.listarTodos());
                    model.addAttribute("titulo", "Editar Medición #" + id);
                    return "mediciones/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("mensaje", "Medición con ID " + id + " no encontrada.");
                    redirectAttributes.addFlashAttribute("tipoAlerta", "error");
                    return "redirect:/mediciones";
                });
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        medicionService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Medición #" + id + " eliminada con éxito.");
        redirectAttributes.addFlashAttribute("tipoAlerta", "info");
        return "redirect:/mediciones";
    }
}
