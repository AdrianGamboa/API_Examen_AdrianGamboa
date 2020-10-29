package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.ExamenAdrianGamboa.dtos.TipoDeUnidadDTO;
import org.una.ExamenAdrianGamboa.entities.TipoDeUnidad;
import org.una.ExamenAdrianGamboa.repositories.ITipoDeUnidadRepository;
import org.una.ExamenAdrianGamboa.utils.MapperUtils;

/**
 *
 * @author Adrian
 */

@Service
public class TipoDeUnidadServiceImplementation implements ITipoDeUnidadService{
    
     @Autowired
    private ITipoDeUnidadRepository tipoDeUnidadRepository;

    private Optional<List<TipoDeUnidadDTO>> findList(List<TipoDeUnidad> list) {
        if (list != null) {
            List<TipoDeUnidadDTO> tipoDeUnidadDTO = MapperUtils.DtoListFromEntityList(list, TipoDeUnidadDTO.class);
            return Optional.ofNullable(tipoDeUnidadDTO);
        } else {
            return null;
        }
    }

    private Optional<List<TipoDeUnidadDTO>> findList(Optional<List<TipoDeUnidad>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<TipoDeUnidadDTO> oneToDto(Optional<TipoDeUnidad> one) {
        if (one.isPresent()) {
            TipoDeUnidadDTO AerolineaDTO = MapperUtils.DtoFromEntity(one.get(), TipoDeUnidadDTO.class);
            return Optional.ofNullable(AerolineaDTO);
        } else {
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoDeUnidadDTO>> findAll() {
         return findList(tipoDeUnidadRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoDeUnidadDTO> findById(Long id) {
        return oneToDto(tipoDeUnidadRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TipoDeUnidadDTO>> findByNombreTipoUnidadAproximateIgnoreCase(String nombreTipoDeUnidad) {
        return findList(tipoDeUnidadRepository.findByNombreTipoUnidadContainingIgnoreCase(nombreTipoDeUnidad));
    }
    
    @Override
    @Transactional
    public TipoDeUnidadDTO create(TipoDeUnidadDTO tipoDeUnidadDTO) {
        TipoDeUnidad tipoDeUnidad = MapperUtils.EntityFromDto(tipoDeUnidadDTO, TipoDeUnidad.class);
        tipoDeUnidad = tipoDeUnidadRepository.save(tipoDeUnidad);
        return MapperUtils.DtoFromEntity(tipoDeUnidad, TipoDeUnidadDTO.class);
    }

    @Override
    @Transactional
    public Optional<TipoDeUnidadDTO> update(TipoDeUnidadDTO tipoDeUnidadDTO, Long id) {
        if (tipoDeUnidadRepository.findById(id).isPresent()) {
            TipoDeUnidad tipoDeUnidad = MapperUtils.EntityFromDto(tipoDeUnidadDTO, TipoDeUnidad.class);
            tipoDeUnidad = tipoDeUnidadRepository.save(tipoDeUnidad);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(tipoDeUnidad, TipoDeUnidadDTO.class));
        } else {
            return null;
        } 
    }  

    @Override
    @Transactional
    public void deleteById(Long id) {
        tipoDeUnidadRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tipoDeUnidadRepository.deleteAll();
    }
}

