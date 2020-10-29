package org.una.ExamenAdrianGamboa.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.ExamenAdrianGamboa.entities.Distrito;

/**
 *
 * @author Adrian
 */

public interface IDistritoRepository extends JpaRepository<Distrito, Long>{
   
         
    @Query(value = "SELECT SUM(uni.cantidadPoblacion) FROM Unidad uni JOIN uni.distrito dis JOIN dis.canton can JOIN can.provincia pro WHERE "
            + "uni.distrito IN (SELECT dist.id FROM Distrito dist WHERE dist.id = ?1)")
    public Long SumaCantidadPoblacionByDistritoId(Long idDistrito);
    
    @Query(value = "SELECT SUM(uni.areaEnMetrosCuadrados) FROM Unidad uni JOIN uni.distrito dis JOIN dis.canton can JOIN can.provincia pro WHERE "
            + "uni.distrito IN (SELECT dist.id FROM Distrito dist WHERE dist.id = ?1)")
    public Float SumaAreaCuadradaByDistritoId(Long idDistrito);
    
    public List<Distrito> findByNombreDistritoContainingIgnoreCase(String nombreDistrito);
    
    public List<Distrito> findByCodigoDistritoContaining(String codigoDistrito);
    
}


