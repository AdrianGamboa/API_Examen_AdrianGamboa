package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.ExamenAdrianGamboa.dtos.DistritoDTO;
import org.una.ExamenAdrianGamboa.dtos.UnidadDTO;
import org.una.ExamenAdrianGamboa.entities.Distrito;
import org.una.ExamenAdrianGamboa.entities.Unidad;
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

    
    private Optional<List<UnidadDTO>> findListC(List<Unidad> list) {
        if (list != null) {
            List<UnidadDTO> unidadDTO = MapperUtils.DtoListFromEntityList(list, UnidadDTO.class);
            return Optional.ofNullable(unidadDTO);
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
    public Optional<DistritoDTO> findByCodigoDistrito(Integer codigoDistrito) {
        return oneToDto(distritoRepository.findByCodigoDistrito(codigoDistrito));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long SumaCantidadPoblacionByDistritoId(Long idDistrito) {
        return distritoRepository.SumaCantidadPoblacionByDistritoId(idDistrito);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Float SumaAreaCuadradaByDistritoId(Long idDistrito) {
        return distritoRepository.SumaAreaCuadradaByDistritoId(idDistrito);
    }

    
    @Override
    public Optional<List<UnidadDTO>> findUnidadById(Long idDistrito) {
        return findListC(distritoRepository.findUnidadById(idDistrito));
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
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        distritoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        distritoRepository.deleteAll();
    }
}
