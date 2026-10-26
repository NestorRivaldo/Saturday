package pe.edu.upeu.crudproductosh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.crudproductosh.model.Kpi;
import pe.edu.upeu.crudproductosh.model.PlanAccion;
import pe.edu.upeu.crudproductosh.service.KpiService;
import pe.edu.upeu.crudproductosh.service.PlanAccionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bsc")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BscRestController {

    private final KpiService kpiService;
    private final PlanAccionService planAccionService;

    @GetMapping("/kpis")
    public List<Kpi> getKpis(@RequestParam(value = "perspectiva", required = false) String perspectiva) {
        return kpiService.listarPorPerspectiva(perspectiva);
    }

    @GetMapping("/kpis/{id}")
    public ResponseEntity<Kpi> getKpi(@PathVariable String id) {
        return kpiService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/kpis")
    public Kpi createKpi(@RequestBody Kpi kpi) {
        return kpiService.guardar(kpi);
    }

    @DeleteMapping("/kpis/{id}")
    public ResponseEntity<Void> deleteKpi(@PathVariable String id) {
        kpiService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/planes")
    public List<PlanAccion> getPlanes(@RequestParam(value = "perspectiva", required = false) String perspectiva) {
        return planAccionService.listarPorPerspectiva(perspectiva);
    }

    @GetMapping("/metricas")
    public Map<String, Object> getMetricas() {
        Map<String, Object> res = new HashMap<>();
        res.put("totalKpis", kpiService.totalKpis());
        res.put("cumplimientoGlobal", kpiService.promedioCumplimientoGlobal());
        res.put("cumplimientoPerspectivas", kpiService.promedioCumplimientoPorPerspectiva());
        res.put("conteoPorSemaforo", kpiService.conteoPorSemaforo());
        res.put("presupuestoTotalPlanes", planAccionService.presupuestoTotal());
        return res;
    }
}
