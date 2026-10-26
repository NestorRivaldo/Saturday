package pe.edu.upeu.crudproductosh.service;

import pe.edu.upeu.crudproductosh.model.Kpi;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface KpiService {
    List<Kpi> listarTodos();
    List<Kpi> listarPorPerspectiva(String perspectiva);
    Optional<Kpi> obtenerPorId(String id);
    Kpi guardar(Kpi kpi);
    void eliminar(String id);
    
    // Métricas y estadísticas para el Dashboard
    long totalKpis();
    double promedioCumplimientoGlobal();
    Map<String, Double> promedioCumplimientoPorPerspectiva();
    Map<String, Long> conteoPorPerspectiva();
    Map<String, Long> conteoPorSemaforo();
}
