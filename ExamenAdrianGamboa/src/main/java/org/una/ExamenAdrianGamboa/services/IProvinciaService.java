package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.una.ExamenAdrianGamboa.dtos.ProvinciaDTO;
import org.una.ExamenAdrianGamboa.dtos.CantonDTO;

/**
 *
 * @author Adrian
 */

public interface IProvinciaService {
    
    public Optional<List<ProvinciaDTO>> findAll();

    public Optional<ProvinciaDTO> findById(Long id);

    public Optional<List<ProvinciaDTO>> findByNombreProvinciaAproximateIgnoreCase(String nombreProvincia);
    
    public Optional<ProvinciaDTO> findByCodigoProvincia(Integer codigoProvincia);
    
    public Long SumaCantidadPoblacionByProvinciaId(Long idProvincia);
    
    public Float SumaAreaCuadradaByProvinciaId(Long idProvincia);
    
    public Optional<List<CantonDTO>> findCantonById(Long idProvincia);
    
    public ProvinciaDTO create(ProvinciaDTO provincia);

    public Optional<ProvinciaDTO> update(ProvinciaDTO provinciaDTO, Long id);
    
    public void deleteById(Long id);

    public void deleteAll();
}
