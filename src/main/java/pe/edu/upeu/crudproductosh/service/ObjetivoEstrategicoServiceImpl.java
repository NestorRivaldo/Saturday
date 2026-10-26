package pe.edu.upeu.crudproductosh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.crudproductosh.model.ObjetivoEstrategico;
import pe.edu.upeu.crudproductosh.repository.ObjetivoEstrategicoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObjetivoEstrategicoServiceImpl implements ObjetivoEstrategicoService {

    private final ObjetivoEstrategicoRepository objetivoRepository;

    @Override
    public List<ObjetivoEstrategico> listarTodos() {
        return objetivoRepository.findAll();
    }

    @Override
    public List<ObjetivoEstrategico> listarPorPerspectiva(String perspectiva) {
        if (perspectiva == null || perspectiva.trim().isEmpty() || "todas".equalsIgnoreCase(perspectiva)) {
            return listarTodos();
        }
        return objetivoRepository.findByPerspectivaIgnoreCase(perspectiva);
    }

    @Override
    public ObjetivoEstrategico guardar(ObjetivoEstrategico objetivo) {
        return objetivoRepository.save(objetivo);
    }

    @Override
    public Optional<ObjetivoEstrategico> obtenerPorId(String id) {
        return objetivoRepository.findById(id);
    }

    @Override
    public void eliminar(String id) {
        objetivoRepository.deleteById(id);
    }

    @Override
    public Long totalObjetivos() {
        return objetivoRepository.count();
    }
}
