package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.una.ExamenAdrianGamboa.dtos.DistritoDTO;

/**
 *
 * @author Adrian
 */

public interface IDistritoService {
    
    public Optional<List<DistritoDTO>> findAll();

    public Optional<DistritoDTO> findById(Long id);

    public Optional<List<DistritoDTO>> findByNombreDistritoAproximateIgnoreCase(String nombreDistrito);
    
    public Optional<List<DistritoDTO>> findByCodigoDistritoAproximate(String codigoDistrito);
    
    public DistritoDTO create(DistritoDTO distrito);

    public Optional<DistritoDTO> update(DistritoDTO distritoDTO, Long id);
    
}