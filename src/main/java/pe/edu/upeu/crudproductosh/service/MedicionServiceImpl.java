package pe.edu.upeu.crudproductosh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.crudproductosh.model.Medicion;
import pe.edu.upeu.crudproductosh.repository.MedicionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicionServiceImpl implements MedicionService {

    private final MedicionRepository medicionRepository;

    @Override
    public List<Medicion> listarTodos() {
        return medicionRepository.findAll();
    }

    @Override
    public List<Medicion> listarPorKpiId(String kpiId) {
        return medicionRepository.findByKpiId(kpiId);
    }

    @Override
    public List<Medicion> listarPorKpiIdOrdenado(String kpiId) {
        return medicionRepository.findByKpiIdOrderByPeriodoAsc(kpiId);
    }

    @Override
    public List<Medicion> listarPorPeriodo(String periodo) {
        return medicionRepository.findByPeriodo(periodo);
    }

    @Override
    public Medicion guardar(Medicion medicion) {
        return medicionRepository.save(medicion);
    }

    @Override
    public Optional<Medicion> obtenerPorId(Long id) {
        return medicionRepository.findById(id);
    }

    @Override
    public Optional<Medicion> obtenerPorKpiIdYPeriodo(String kpiId, String periodo) {
        return medicionRepository.findByKpiIdAndPeriodo(kpiId, periodo);
    }

    @Override
    public void eliminar(Long id) {
        medicionRepository.deleteById(id);
    }

    @Override
    public Long totalMediciones() {
        return medicionRepository.count();
    }
}
