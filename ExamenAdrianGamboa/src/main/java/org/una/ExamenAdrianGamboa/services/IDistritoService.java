package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.una.ExamenAdrianGamboa.dtos.DistritoDTO;
import org.una.ExamenAdrianGamboa.dtos.UnidadDTO;

/**
 *
 * @author Adrian
 */

public interface IDistritoService {
    
    public Optional<List<DistritoDTO>> findAll();

    public Optional<DistritoDTO> findById(Long id);

    public Optional<List<DistritoDTO>> findByNombreDistritoAproximateIgnoreCase(String nombreDistrito);
    
    public Optional<DistritoDTO> findByCodigoDistrito(Integer codigoDistrito);
    
    public Long SumaCantidadPoblacionByDistritoId(Long idDistrito);
    
    public Float SumaAreaCuadradaByDistritoId(Long idDistrito);
    
    public Optional<List<UnidadDTO>> findUnidadById(Long idDistrito);
    
    public DistritoDTO create(DistritoDTO distrito);

    public Optional<DistritoDTO> update(DistritoDTO distritoDTO, Long id);
    
    public void deleteById(Long id);

    public void deleteAll();
}
