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
   
         
  //public List<Distrito> findUnidadCantidadPoblacionByDistritoId(Long id); 
    
    public List<Distrito> findByNombreDistritoContainingIgnoreCase(String nombreDistrito);
    
    public List<Distrito> findByCodigoDistritoContaining(String codigoDistrito);
    
}


