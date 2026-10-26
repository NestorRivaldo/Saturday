package pe.edu.upeu.crudproductosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.crudproductosh.model.Kpi;

import java.util.List;

@Repository
public interface KpiRepository extends JpaRepository<Kpi, String> {
    List<Kpi> findByPerspectivaIgnoreCase(String perspectiva);
    List<Kpi> findByEstadoSemaforoContainingIgnoreCase(String estado);
    long countByPerspectivaIgnoreCase(String perspectiva);
}
