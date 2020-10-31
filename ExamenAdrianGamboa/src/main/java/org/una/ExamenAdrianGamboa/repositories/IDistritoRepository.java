package org.una.ExamenAdrianGamboa.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.ExamenAdrianGamboa.entities.Distrito;
import org.una.ExamenAdrianGamboa.entities.Unidad;

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
    
    @Query(value = "SELECT uni FROM Unidad uni WHERE uni.distrito IN (SELECT dist FROM Distrito dist WHERE dist.id = ?1)")
    public List<Unidad> findUnidadById(Long idDistrito);
    
    public List<Distrito> findByNombreDistritoContainingIgnoreCase(String nombreDistrito);
    
    public Optional<Distrito> findByCodigoDistrito(Integer codigoDistrito);
    
}


