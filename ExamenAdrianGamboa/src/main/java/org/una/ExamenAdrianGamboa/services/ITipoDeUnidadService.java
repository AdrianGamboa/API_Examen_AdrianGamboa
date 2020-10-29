package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.una.ExamenAdrianGamboa.dtos.TipoDeUnidadDTO;

/**
 *
 * @author Adrian
 */

public interface ITipoDeUnidadService {
    
    public Optional<List<TipoDeUnidadDTO>> findAll();

    public Optional<TipoDeUnidadDTO> findById(Long id);

    public Optional<List<TipoDeUnidadDTO>> findByNombreTipoUnidadAproximateIgnoreCase(String nombreTipoUnidad);
    
    public TipoDeUnidadDTO create(TipoDeUnidadDTO tipoUnidad);

    public Optional<TipoDeUnidadDTO> update(TipoDeUnidadDTO tipoUnidad, Long id);
    
    public void deleteById(Long id);

    public void deleteAll();
    
}
