package pe.edu.upeu.crudproductosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.crudproductosh.model.Medicion;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicionRepository extends JpaRepository<Medicion, Long> {

    List<Medicion> findByKpiId(String kpiId);

    List<Medicion> findByKpiIdOrderByPeriodoAsc(String kpiId);

    List<Medicion> findByPeriodo(String periodo);

    Optional<Medicion> findByKpiIdAndPeriodo(String kpiId, String periodo);

    @Query("SELECT m FROM Medicion m WHERE m.kpiId = :kpiId ORDER BY m.periodo DESC")
    List<Medicion> findLatestByKpiId(@Param("kpiId") String kpiId);
}
