package org.una.ExamenAdrianGamboa.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.ExamenAdrianGamboa.entities.TipoDeUnidad;

/**
 *
 * @author Adrian
 */

public interface ITipoDeUnidadRepository extends JpaRepository<TipoDeUnidad, Long>{
    
    public List<TipoDeUnidad> findByNombreTipoUnidadContainingIgnoreCase(String nombreDistrito);    
}
