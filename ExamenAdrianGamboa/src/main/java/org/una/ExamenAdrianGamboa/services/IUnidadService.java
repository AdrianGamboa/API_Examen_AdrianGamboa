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
    
    public Optional<UnidadDTO> findByCodigoUnidad(Integer codigoUnidad);
    
    public Long SumaUnidadCantidadPoblacion(Long idUnidad); 
    
    public Float SumaUnidadAreaCuadrada(Long idUnidad);
    
    public UnidadDTO create(UnidadDTO unidad);

    public Optional<UnidadDTO> update(UnidadDTO unidadDTO, Long id);
    
    public void deleteById(Long id);

    public void deleteAll();
}
