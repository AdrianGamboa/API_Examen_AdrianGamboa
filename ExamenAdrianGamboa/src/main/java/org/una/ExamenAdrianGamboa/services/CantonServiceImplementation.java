package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.ExamenAdrianGamboa.dtos.CantonDTO;
import org.una.ExamenAdrianGamboa.dtos.DistritoDTO;
import org.una.ExamenAdrianGamboa.entities.Canton;
import org.una.ExamenAdrianGamboa.entities.Distrito;
import org.una.ExamenAdrianGamboa.repositories.ICantonRepository;
import org.una.ExamenAdrianGamboa.utils.MapperUtils;

/**
 *
 * @author Adrian
 */

@Service
public class CantonServiceImplementation implements ICantonService{
    
     @Autowired
    private ICantonRepository cantonRepository;

    private Optional<List<CantonDTO>> findList(List<Canton> list) {
        if (list != null) {
            List<CantonDTO> cantonDTO = MapperUtils.DtoListFromEntityList(list, CantonDTO.class);
            return Optional.ofNullable(cantonDTO);
        } else {
            return null;
        }
    }
    
    private Optional<List<DistritoDTO>> findListC(List<Distrito> list) {
        if (list != null) {
            List<DistritoDTO> distritoDTO = MapperUtils.DtoListFromEntityList(list, DistritoDTO.class);
            return Optional.ofNullable(distritoDTO);
        } else {
            return null;
        }
    }

    private Optional<List<CantonDTO>> findList(Optional<List<Canton>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<CantonDTO> oneToDto(Optional<Canton> one) {
        if (one.isPresent()) {
            CantonDTO AerolineaDTO = MapperUtils.DtoFromEntity(one.get(), CantonDTO.class);
            return Optional.ofNullable(AerolineaDTO);
        } else {
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<CantonDTO>> findAll() {
         return findList(cantonRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CantonDTO> findById(Long id) {
        return oneToDto(cantonRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<CantonDTO>> findByNombreCantonAproximateIgnoreCase(String nombreCanton) {
        return findList(cantonRepository.findByNombreCantonContainingIgnoreCase(nombreCanton));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CantonDTO> findByCodigoCanton(Integer codigoCanton) {
        return oneToDto(cantonRepository.findByCodigoCanton(codigoCanton));
    }

    
    @Override
    public Long SumaCantidadPoblacionByCantonId(Long idCanton) {
        return cantonRepository.SumaCantidadPoblacionByCantonId(idCanton);
    }
    
    @Override
    public Float SumaAreaCuadradaByCantonId(Long idCanton) {
        return cantonRepository.SumaAreaCuadradaByCantonId(idCanton);
    }
    
    @Override
    public Optional<List<DistritoDTO>> findDistritoById(Long idCanton) {
        return findListC(cantonRepository.findDistritoById(idCanton));
    }
    
    @Override
    @Transactional
    public CantonDTO create(CantonDTO cantonDTO) {
        Canton canton = MapperUtils.EntityFromDto(cantonDTO, Canton.class);
        canton = cantonRepository.save(canton);
        return MapperUtils.DtoFromEntity(canton, CantonDTO.class);
    }

    @Override
    @Transactional
    public Optional<CantonDTO> update(CantonDTO cantonDTO, Long id) {
        if (cantonRepository.findById(id).isPresent()) {
            Canton canton = MapperUtils.EntityFromDto(cantonDTO, Canton.class);
            canton = cantonRepository.save(canton);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(canton, CantonDTO.class));
        } else {
            return null;
        } 
    }  
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        cantonRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        cantonRepository.deleteAll();
    }
}