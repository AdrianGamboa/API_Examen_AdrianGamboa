package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.ExamenAdrianGamboa.dtos.DistritoDTO;
import org.una.ExamenAdrianGamboa.entities.Distrito;
import org.una.ExamenAdrianGamboa.repositories.IDistritoRepository;
import org.una.ExamenAdrianGamboa.utils.MapperUtils;

/**
 *
 * @author Adrian
 */

@Service
public class DistritoServiceImplementation implements IDistritoService{
    
     @Autowired
    private IDistritoRepository distritoRepository;

    private Optional<List<DistritoDTO>> findList(List<Distrito> list) {
        if (list != null) {
            List<DistritoDTO> distritoDTO = MapperUtils.DtoListFromEntityList(list, DistritoDTO.class);
            return Optional.ofNullable(distritoDTO);
        } else {
            return null;
        }
    }

    private Optional<List<DistritoDTO>> findList(Optional<List<Distrito>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<DistritoDTO> oneToDto(Optional<Distrito> one) {
        if (one.isPresent()) {
            DistritoDTO AerolineaDTO = MapperUtils.DtoFromEntity(one.get(), DistritoDTO.class);
            return Optional.ofNullable(AerolineaDTO);
        } else {
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<DistritoDTO>> findAll() {
         return findList(distritoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DistritoDTO> findById(Long id) {
        return oneToDto(distritoRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DistritoDTO>> findByNombreDistritoAproximateIgnoreCase(String nombreDistrito) {
        return findList(distritoRepository.findByNombreDistritoContainingIgnoreCase(nombreDistrito));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DistritoDTO>> findByCodigoDistritoAproximate(String codigoDistrito) {
        return findList(distritoRepository.findByCodigoDistritoContaining(codigoDistrito));
    }

    @Override
    @Transactional
    public DistritoDTO create(DistritoDTO distritoDTO) {
        Distrito distrito = MapperUtils.EntityFromDto(distritoDTO, Distrito.class);
        distrito = distritoRepository.save(distrito);
        return MapperUtils.DtoFromEntity(distrito, DistritoDTO.class);
    }

    @Override
    @Transactional
    public Optional<DistritoDTO> update(DistritoDTO distritoDTO, Long id) {
        if (distritoRepository.findById(id).isPresent()) {
            Distrito distrito = MapperUtils.EntityFromDto(distritoDTO, Distrito.class);
            distrito = distritoRepository.save(distrito);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(distrito, DistritoDTO.class));
        } else {
            return null;
        } 
    }  
}


