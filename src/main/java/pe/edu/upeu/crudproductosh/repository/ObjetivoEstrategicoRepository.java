package pe.edu.upeu.crudproductosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.crudproductosh.model.ObjetivoEstrategico;

import java.util.List;
import java.util.Optional;

@Repository
public interface ObjetivoEstrategicoRepository extends JpaRepository<ObjetivoEstrategico, String> {

    List<ObjetivoEstrategico> findByPerspectiva(String perspectiva);

    List<ObjetivoEstrategico> findByPerspectivaIgnoreCase(String perspectiva);

    List<ObjetivoEstrategico> findByEstado(String estado);

    Optional<ObjetivoEstrategico> findById(String id);
}
