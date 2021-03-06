package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.una.ExamenAdrianGamboa.dtos.CantonDTO;
import org.una.ExamenAdrianGamboa.dtos.DistritoDTO;

/**
 *
 * @author Adrian
 */

public interface ICantonService {
    
    public Optional<List<CantonDTO>> findAll();

    public Optional<CantonDTO> findById(Long id);

    public Optional<List<CantonDTO>> findByNombreCantonAproximateIgnoreCase(String nombreCanton);
    
    public Optional<CantonDTO> findByCodigoCanton(Integer codigoCanton);
    
    public Long SumaCantidadPoblacionByCantonId(Long idCanton);
    
    public Float SumaAreaCuadradaByCantonId(Long idCanton);
    
    public Optional<List<DistritoDTO>> findDistritoById(Long idCanton);
    
    public CantonDTO create(CantonDTO canton);

    public Optional<CantonDTO> update(CantonDTO cantonDTO, Long id);
    
    public void deleteById(Long id);

    public void deleteAll();
}

