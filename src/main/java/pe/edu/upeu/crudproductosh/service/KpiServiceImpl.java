package pe.edu.upeu.crudproductosh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.crudproductosh.model.Kpi;
import pe.edu.upeu.crudproductosh.model.Perspectiva;
import pe.edu.upeu.crudproductosh.repository.KpiRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class KpiServiceImpl implements KpiService {

    private final KpiRepository kpiRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Kpi> listarTodos() {
        return kpiRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Kpi> listarPorPerspectiva(String perspectiva) {
        if (perspectiva == null || perspectiva.trim().isEmpty() || "todas".equalsIgnoreCase(perspectiva)) {
            return kpiRepository.findAll();
        }
        return kpiRepository.findByPerspectivaIgnoreCase(perspectiva);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Kpi> obtenerPorId(String id) {
        return kpiRepository.findById(id);
    }

    @Override
    @Transactional
    public Kpi guardar(Kpi kpi) {
        kpi.calcularIndicadores();
        return kpiRepository.save(kpi);
    }

    @Override
    @Transactional
    public void eliminar(String id) {
        kpiRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long totalKpis() {
        return kpiRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public double promedioCumplimientoGlobal() {
        List<Kpi> list = kpiRepository.findAll();
        if (list.isEmpty()) return 0.0;
        double sum = list.stream()
                .mapToDouble(k -> k.getPorcentajeCumplimiento() != null ? k.getPorcentajeCumplimiento() : 0.0)
                .sum();
        return Math.round((sum / list.size()) * 100.0) / 100.0;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Double> promedioCumplimientoPorPerspectiva() {
        List<Kpi> list = kpiRepository.findAll();
        Map<String, List<Double>> grouped = new LinkedHashMap<>();
        
        for (Kpi k : list) {
            String p = k.getPerspectiva();
            grouped.computeIfAbsent(p, k1 -> new ArrayList<>())
                   .add(k.getPorcentajeCumplimiento() != null ? k.getPorcentajeCumplimiento() : 0.0);
        }

        Map<String, Double> result = new LinkedHashMap<>();
        for (Perspectiva p : Perspectiva.values()) {
            List<Double> valores = grouped.get(p.getNombre());
            double avg = valores == null || valores.isEmpty()
                    ? 0.0
                    : valores.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            result.put(p.getNombre(), Math.round(avg * 100.0) / 100.0);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> conteoPorPerspectiva() {
        Map<String, Long> counts = new LinkedHashMap<>();
        for (Perspectiva p : Perspectiva.values()) {
            counts.put(p.getNombre(), 0L);
        }
        for (Kpi k : kpiRepository.findAll()) {
            String p = k.getPerspectiva() != null ? k.getPerspectiva() : "Sin perspectiva";
            counts.merge(p, 1L, Long::sum);
        }
        return counts;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> conteoPorSemaforo() {
        List<Kpi> list = kpiRepository.findAll();
        Map<String, Long> counts = new LinkedHashMap<>();
        counts.put("SUPERA_META", 0L);
        counts.put("LOGRADO", 0L);
        counts.put("CASI_LOGRADO", 0L);
        counts.put("RIESGO_MODERADO", 0L);
        counts.put("RIESGO_ALTO", 0L);
        counts.put("CRITICO", 0L);

        for (Kpi k : list) {
            String s = k.getEstadoSemaforo();
            if (s == null) counts.put("CRITICO", counts.get("CRITICO") + 1);
            else if (s.contains("SUPERA")) counts.put("SUPERA_META", counts.get("SUPERA_META") + 1);
            else if (s.contains("LOGRADO")) counts.put("LOGRADO", counts.get("LOGRADO") + 1);
            else if (s.contains("CASI")) counts.put("CASI_LOGRADO", counts.get("CASI_LOGRADO") + 1);
            else if (s.contains("MODERADO")) counts.put("RIESGO_MODERADO", counts.get("RIESGO_MODERADO") + 1);
            else if (s.contains("ALTO")) counts.put("RIESGO_ALTO", counts.get("RIESGO_ALTO") + 1);
            else counts.put("CRITICO", counts.get("CRITICO") + 1);
        }
        return counts;
    }
}
