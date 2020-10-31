package org.una.ExamenAdrianGamboa.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.ExamenAdrianGamboa.entities.Unidad;

/**
 *
 * @author Adrian
 */

public interface IUnidadRepository extends JpaRepository<Unidad, Long>{
          
   @Query(value = "SELECT SUM(uni.cantidadPoblacion) FROM Unidad uni WHERE uni.id = ?1")
   public Long SumaUnidadCantidadPoblacion(Long idUnidad); 
      
   @Query(value = "SELECT SUM(uni.areaEnMetrosCuadrados) FROM Unidad uni WHERE uni.id = ?1")
   public Float SumaUnidadAreaCuadrada(Long idUnidad); 
  
   public List<Unidad> findByNombreUnidadContainingIgnoreCase(String nombreUnidad);
    
   public Optional<Unidad> findByCodigoUnidad(Integer codigoUnidad);    
}
