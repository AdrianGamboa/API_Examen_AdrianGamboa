package org.una.ExamenAdrianGamboa.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.ExamenAdrianGamboa.entities.Canton;
import org.una.ExamenAdrianGamboa.entities.Distrito;

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
    
    @Query(value = "SELECT dis FROM Distrito dis WHERE dis.canton IN (SELECT cant FROM Canton cant WHERE cant.id = ?1)")
    public List<Distrito> findDistritoById(Long idCanton);
    
    public List<Canton> findByNombreCantonContainingIgnoreCase(String nombreCanton);
    
    public Optional<Canton> findByCodigoCanton(Integer codigoCanton);
    
}

