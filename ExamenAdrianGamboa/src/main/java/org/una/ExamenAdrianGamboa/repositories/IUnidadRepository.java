package org.una.ExamenAdrianGamboa.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.ExamenAdrianGamboa.entities.Unidad;

/**
 *
 * @author Adrian
 */

public interface IUnidadRepository extends JpaRepository<Unidad, Long>{
   
         
 //  public List<Unidad> findCantidadPoblacionById(Long id); 
   
  // public List<Unidad> findUnidadAreaCuadradaById(Long id);   
    
   public List<Unidad> findByNombreUnidadContainingIgnoreCase(String nombreUnidad);
    
   public List<Unidad> findByCodigoUnidadContaining(String codigoUnidad);
    
}


