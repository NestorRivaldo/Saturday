package pe.edu.upeu.crudproductosh.service;

import pe.edu.upeu.crudproductosh.model.PlanAccion;

import java.util.List;
import java.util.Optional;

public interface PlanAccionService {
    List<PlanAccion> listarTodos();
    List<PlanAccion> listarPorPerspectiva(String perspectiva);
    Optional<PlanAccion> obtenerPorId(String id);
    PlanAccion guardar(PlanAccion plan);
    void eliminar(String id);
    double presupuestoTotal();
}
