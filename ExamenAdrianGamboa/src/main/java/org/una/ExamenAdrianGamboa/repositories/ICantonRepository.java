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
   
         
  //public List<Canton> findUnidadCantidadPoblacionByCantonId(Long id); 
    
    public List<Canton> findByNombreCantonContainingIgnoreCase(String nombreCanton);
    
    public List<Canton> findByCodigoCantonContaining(String codigoCanton);
    
}

