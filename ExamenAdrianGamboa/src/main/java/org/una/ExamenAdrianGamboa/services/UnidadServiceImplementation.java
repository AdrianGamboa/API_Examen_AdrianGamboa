package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.ExamenAdrianGamboa.dtos.UnidadDTO;
import org.una.ExamenAdrianGamboa.entities.Unidad;
import org.una.ExamenAdrianGamboa.repositories.IUnidadRepository;
import org.una.ExamenAdrianGamboa.utils.MapperUtils;

/**
 *
 * @author Adrian
 */

@Service
public class UnidadServiceImplementation implements IUnidadService{
    
     @Autowired
    private IUnidadRepository unidadRepository;

    private Optional<List<UnidadDTO>> findList(List<Unidad> list) {
        if (list != null) {
            List<UnidadDTO> unidadDTO = MapperUtils.DtoListFromEntityList(list, UnidadDTO.class);
            return Optional.ofNullable(unidadDTO);
        } else {
            return null;
        }
    }

    private Optional<List<UnidadDTO>> findList(Optional<List<Unidad>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<UnidadDTO> oneToDto(Optional<Unidad> one) {
        if (one.isPresent()) {
            UnidadDTO AerolineaDTO = MapperUtils.DtoFromEntity(one.get(), UnidadDTO.class);
            return Optional.ofNullable(AerolineaDTO);
        } else {
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<UnidadDTO>> findAll() {
         return findList(unidadRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UnidadDTO> findById(Long id) {
        return oneToDto(unidadRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UnidadDTO>> findByNombreUnidadAproximateIgnoreCase(String nombreUnidad) {
        return findList(unidadRepository.findByNombreUnidadContainingIgnoreCase(nombreUnidad));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UnidadDTO>> findByCodigoUnidadAproximate(String codigoUnidad) {
        return findList(unidadRepository.findByCodigoUnidadContaining(codigoUnidad));
    }

    @Override
    @Transactional
    public UnidadDTO create(UnidadDTO unidadDTO) {
        Unidad unidad = MapperUtils.EntityFromDto(unidadDTO, Unidad.class);
        unidad = unidadRepository.save(unidad);
        return MapperUtils.DtoFromEntity(unidad, UnidadDTO.class);
    }

    @Override
    @Transactional
    public Optional<UnidadDTO> update(UnidadDTO unidadDTO, Long id) {
        if (unidadRepository.findById(id).isPresent()) {
            Unidad unidad = MapperUtils.EntityFromDto(unidadDTO, Unidad.class);
            unidad = unidadRepository.save(unidad);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(unidad, UnidadDTO.class));
        } else {
            return null;
        } 
    }  
}


