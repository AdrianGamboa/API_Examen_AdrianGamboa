package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.una.ExamenAdrianGamboa.dtos.ProvinciaDTO;

/**
 *
 * @author Adrian
 */

public interface IProvinciaService {
    
    public Optional<List<ProvinciaDTO>> findAll();

    public Optional<ProvinciaDTO> findById(Long id);

    public Optional<List<ProvinciaDTO>> findByNombreProvinciaAproximateIgnoreCase(String nombreProvincia);
    
    public Optional<List<ProvinciaDTO>> findByCodigoProvinciaAproximate(String codigoProvincia);
    
    public ProvinciaDTO create(ProvinciaDTO provincia);

    public Optional<ProvinciaDTO> update(ProvinciaDTO provinciaDTO, Long id);
    
}