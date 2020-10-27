package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.una.ExamenAdrianGamboa.dtos.UnidadDTO;

/**
 *
 * @author Adrian
 */

public interface IUnidadService {
    
    public Optional<List<UnidadDTO>> findAll();

    public Optional<UnidadDTO> findById(Long id);
 
    public Optional<List<UnidadDTO>> findByNombreUnidadAproximateIgnoreCase(String nombreUnidad);
    
    public Optional<List<UnidadDTO>> findByCodigoUnidadAproximate(String codigoUnidad);
    
    public UnidadDTO create(UnidadDTO unidad);

    public Optional<UnidadDTO> update(UnidadDTO unidadDTO, Long id);
    
}