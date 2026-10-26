package pe.edu.upeu.crudproductosh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.upeu.crudproductosh.model.ObjetivoEstrategico;
import pe.edu.upeu.crudproductosh.model.Perspectiva;
import pe.edu.upeu.crudproductosh.service.ObjetivoEstrategicoService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/objetivos")
@RequiredArgsConstructor
public class ObjetivoEstrategicoController {

    private final ObjetivoEstrategicoService objetivoService;

    @GetMapping
    public String listar(@RequestParam(value = "perspectiva", required = false) String perspectiva, Model model) {
        List<ObjetivoEstrategico> objetivos = objetivoService.listarPorPerspectiva(perspectiva);
        model.addAttribute("objetivos", objetivos);
        model.addAttribute("perspectivas", Perspectiva.values());
        model.addAttribute("perspectivaSeleccionada", perspectiva != null ? perspectiva : "Todas");
        model.addAttribute("title", "Objetivos Estratégicos");
        return "objetivos/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        ObjetivoEstrategico objetivo = new ObjetivoEstrategico();
        objetivo.setEstado("Activo");
        objetivo.setPrioridad("Media");
        objetivo.setPeso(100);
        java.time.LocalDate today = java.time.LocalDate.now();
        objetivo.setFechaCreacion(today.toString());
        model.addAttribute("objetivo", objetivo);
        model.addAttribute("perspectivas", Perspectiva.values());
        model.addAttribute("titulo", "Registrar Nuevo Objetivo Estratégico");
        return "objetivos/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("objetivo") ObjetivoEstrategico objetivo, RedirectAttributes redirectAttributes) {
        if (objetivo.getId() == null || objetivo.getId().trim().isEmpty()) {
            objetivo.setId(siguienteId());
        }
        objetivoService.guardar(objetivo);
        redirectAttributes.addFlashAttribute("mensaje", "Objetivo '" + objetivo.getNombre() + "' (" + objetivo.getId() + ") guardado con éxito.");
        redirectAttributes.addFlashAttribute("tipoAlerta", "success");
        return "redirect:/objetivos";
    }

    /** Genera el siguiente código OBJ-xx a partir del mayor existente (evita colisiones al eliminar). */
    private String siguienteId() {
        int mayor = objetivoService.listarTodos().stream()
                .map(ObjetivoEstrategico::getId)
                .filter(id -> id != null && id.matches("OBJ-\\d+"))
                .mapToInt(id -> Integer.parseInt(id.substring(4)))
                .max()
                .orElse(0);
        return String.format("OBJ-%02d", mayor + 1);
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        return objetivoService.obtenerPorId(id)
                .map(objetivo -> {
                    model.addAttribute("objetivo", objetivo);
                    model.addAttribute("perspectivas", Perspectiva.values());
                    model.addAttribute("titulo", "Editar Objetivo: " + objetivo.getId());
                    return "objetivos/form";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("mensaje", "Objetivo con ID " + id + " no encontrado.");
                    redirectAttributes.addFlashAttribute("tipoAlerta", "error");
                    return "redirect:/objetivos";
                });
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        objetivoService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Objetivo " + id + " eliminado con éxito.");
        redirectAttributes.addFlashAttribute("tipoAlerta", "info");
        return "redirect:/objetivos";
    }
}
