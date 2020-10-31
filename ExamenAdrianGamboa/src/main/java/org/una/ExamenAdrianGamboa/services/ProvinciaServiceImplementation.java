package org.una.ExamenAdrianGamboa.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.ExamenAdrianGamboa.dtos.CantonDTO;
import org.una.ExamenAdrianGamboa.dtos.ProvinciaDTO;
import org.una.ExamenAdrianGamboa.entities.Provincia;
import org.una.ExamenAdrianGamboa.entities.Canton;
import org.una.ExamenAdrianGamboa.repositories.IProvinciaRepository;
import org.una.ExamenAdrianGamboa.utils.MapperUtils;

/**
 *
 * @author Adrian
 */

@Service
public class ProvinciaServiceImplementation implements IProvinciaService{
    
     @Autowired
    private IProvinciaRepository provinciaRepository;

    private Optional<List<ProvinciaDTO>> findList(List<Provincia> list) {
        if (list != null) {
            List<ProvinciaDTO> provinciaDTO = MapperUtils.DtoListFromEntityList(list, ProvinciaDTO.class);
            return Optional.ofNullable(provinciaDTO);
        } else {
            return null;
        }
    }
    
    private Optional<List<CantonDTO>> findListC(List<Canton> list) {
        if (list != null) {
            List<CantonDTO> cantonDTO = MapperUtils.DtoListFromEntityList(list, CantonDTO.class);
            return Optional.ofNullable(cantonDTO);
        } else {
            return null;
        }
    }
    
    private Optional<List<ProvinciaDTO>> findList(Optional<List<Provincia>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }

    private Optional<ProvinciaDTO> oneToDto(Optional<Provincia> one) {
        if (one.isPresent()) {
            ProvinciaDTO AerolineaDTO = MapperUtils.DtoFromEntity(one.get(), ProvinciaDTO.class);
            return Optional.ofNullable(AerolineaDTO);
        } else {
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ProvinciaDTO>> findAll() {
         return findList(provinciaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProvinciaDTO> findById(Long id) {
        return oneToDto(provinciaRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ProvinciaDTO>> findByNombreProvinciaAproximateIgnoreCase(String nombreProvincia) {
        return findList(provinciaRepository.findByNombreProvinciaContainingIgnoreCase(nombreProvincia));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProvinciaDTO> findByCodigoProvincia(Integer codigoProvincia) {
        return oneToDto(provinciaRepository.findByCodigoProvincia(codigoProvincia));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long SumaCantidadPoblacionByProvinciaId(Long idProvincia) {
        return provinciaRepository.SumaCantidadPoblacionByProvinciaId(idProvincia);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Float SumaAreaCuadradaByProvinciaId(Long idProvincia) {
        return provinciaRepository.SumaAreaCuadradaByProvinciaId(idProvincia);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<CantonDTO>> findCantonById(Long idProvincia) {
        return findListC(provinciaRepository.findCantonById(idProvincia));
    }
    
    @Override
    @Transactional
    public ProvinciaDTO create(ProvinciaDTO provinciaDTO) {
        Provincia provincia = MapperUtils.EntityFromDto(provinciaDTO, Provincia.class);
        provincia = provinciaRepository.save(provincia);
        return MapperUtils.DtoFromEntity(provincia, ProvinciaDTO.class);
    }

    @Override
    @Transactional
    public Optional<ProvinciaDTO> update(ProvinciaDTO provinciaDTO, Long id) {
        if (provinciaRepository.findById(id).isPresent()) {
            Provincia provincia = MapperUtils.EntityFromDto(provinciaDTO, Provincia.class);
            provincia = provinciaRepository.save(provincia);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(provincia, ProvinciaDTO.class));
        } else {
            return null;
        } 
    }  
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        provinciaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        provinciaRepository.deleteAll();
    }
}
