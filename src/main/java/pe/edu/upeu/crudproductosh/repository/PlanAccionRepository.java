package pe.edu.upeu.crudproductosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.crudproductosh.model.PlanAccion;

import java.util.List;

@Repository
public interface PlanAccionRepository extends JpaRepository<PlanAccion, String> {
    List<PlanAccion> findByPerspectivaIgnoreCase(String perspectiva);
    List<PlanAccion> findByEstadoIgnoreCase(String estado);
}
