package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.una.ExamenAdrianGamboa.dtos.CantonDTO;

/**
 *
 * @author Adrian
 */

public interface ICantonService {
    
    public Optional<List<CantonDTO>> findAll();

    public Optional<CantonDTO> findById(Long id);

    public Optional<List<CantonDTO>> findByNombreCantonAproximateIgnoreCase(String nombreCanton);
    
    public Optional<List<CantonDTO>> findByCodigoCantonAproximate(String codigoCanton);
    
    public CantonDTO create(CantonDTO canton);

    public Optional<CantonDTO> update(CantonDTO cantonDTO, Long id);
    
}

