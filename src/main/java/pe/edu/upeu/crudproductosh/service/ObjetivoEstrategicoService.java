package pe.edu.upeu.crudproductosh.service;

import pe.edu.upeu.crudproductosh.model.ObjetivoEstrategico;

import java.util.List;
import java.util.Optional;

public interface ObjetivoEstrategicoService {
    List<ObjetivoEstrategico> listarTodos();
    List<ObjetivoEstrategico> listarPorPerspectiva(String perspectiva);
    ObjetivoEstrategico guardar(ObjetivoEstrategico objetivo);
    Optional<ObjetivoEstrategico> obtenerPorId(String id);
    void eliminar(String id);
    Long totalObjetivos();
}
