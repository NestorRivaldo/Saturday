package pe.edu.upeu.crudproductosh.service;

import pe.edu.upeu.crudproductosh.model.Medicion;

import java.util.List;
import java.util.Optional;

public interface MedicionService {
    List<Medicion> listarTodos();
    List<Medicion> listarPorKpiId(String kpiId);
    List<Medicion> listarPorKpiIdOrdenado(String kpiId);
    List<Medicion> listarPorPeriodo(String periodo);
    Medicion guardar(Medicion medicion);
    Optional<Medicion> obtenerPorId(Long id);
    Optional<Medicion> obtenerPorKpiIdYPeriodo(String kpiId, String periodo);
    void eliminar(Long id);
    Long totalMediciones();
}
