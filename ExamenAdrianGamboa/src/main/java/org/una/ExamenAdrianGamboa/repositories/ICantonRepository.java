package org.una.ExamenAdrianGamboa.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.ExamenAdrianGamboa.entities.Canton;

/**
 *
 * @author Adrian
 */

public interface ICantonRepository extends JpaRepository<Canton, Long>{
   
         
    @Query(value = "SELECT SUM(uni.cantidadPoblacion) FROM Unidad uni JOIN uni.distrito dis JOIN dis.canton can JOIN can.provincia pro WHERE "        
            + "dis.canton IN (SELECT cant.id FROM Canton cant WHERE cant.id = ?1) AND "
            + "uni.distrito IN (SELECT dist.id FROM Distrito dist WHERE dist.canton = ?1)")
    public Long SumaCantidadPoblacionByCantonId(Long idCanton);
  
    @Query(value = "SELECT SUM(uni.areaEnMetrosCuadrados) FROM Unidad uni JOIN uni.distrito dis JOIN dis.canton can JOIN can.provincia pro WHERE "        
            + "dis.canton IN (SELECT cant.id FROM Canton cant WHERE cant.id = ?1) AND "
            + "uni.distrito IN (SELECT dist.id FROM Distrito dist WHERE dist.canton = ?1)")
    public Float SumaAreaCuadradaByCantonId(Long idCanton);
    
    public List<Canton> findByNombreCantonContainingIgnoreCase(String nombreCanton);
    
    public List<Canton> findByCodigoCantonContaining(String codigoCanton);
    
}

