package pe.edu.upeu.crudproductosh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.crudproductosh.model.PlanAccion;
import pe.edu.upeu.crudproductosh.repository.PlanAccionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanAccionServiceImpl implements PlanAccionService {

    private final PlanAccionRepository planAccionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PlanAccion> listarTodos() {
        return planAccionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlanAccion> listarPorPerspectiva(String perspectiva) {
        if (perspectiva == null || perspectiva.trim().isEmpty() || "todas".equalsIgnoreCase(perspectiva)) {
            return planAccionRepository.findAll();
        }
        return planAccionRepository.findByPerspectivaIgnoreCase(perspectiva);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PlanAccion> obtenerPorId(String id) {
        return planAccionRepository.findById(id);
    }

    @Override
    @Transactional
    public PlanAccion guardar(PlanAccion plan) {
        return planAccionRepository.save(plan);
    }

    @Override
    @Transactional
    public void eliminar(String id) {
        planAccionRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public double presupuestoTotal() {
        return planAccionRepository.findAll().stream()
                .mapToDouble(p -> p.getPresupuesto() != null ? p.getPresupuesto() : 0.0)
                .sum();
    }
}
