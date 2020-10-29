package org.una.ExamenAdrianGamboa.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.ExamenAdrianGamboa.dtos.ProvinciaDTO;
import org.una.ExamenAdrianGamboa.services.IProvinciaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


import java.util.Optional;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 *
 * @author Adrian
 */

@RestController
@RequestMapping("/exa_adr_provincias") 
@Api(tags = {"Provincias"})
public class ProvinciaController {
    
    @Autowired
    private IProvinciaService provinciaService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findAll")
    @ApiOperation(value = "Obtiene una lista de todas las Provincias", response = ProvinciaDTO.class, responseContainer = "List", tags = "Provincias")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(provinciaService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene un Provincia por su Id", response = ProvinciaDTO.class, tags = "Provincias")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(provinciaService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByNombreProvinciaAproximateIgnoreCase/{nombre}")
    @ApiOperation(value = "Obtiene un Provincia mediante una apoximacion a su nombre", response = ProvinciaDTO.class, tags = "Provincias")
    public ResponseEntity<?> findByNombreProvinciaAproximateIgnoreCase(@PathVariable(value = "nombre") String nombre) {
        try {
            return new ResponseEntity(provinciaService.findByNombreProvinciaAproximateIgnoreCase(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByCodigoProvinciaAproximate/{codigo}")
    @ApiOperation(value = "Obtiene un Provincia mediante una apoximacion a su codigo", response = ProvinciaDTO.class, tags = "Provincias")
    public ResponseEntity<?> findByCodigoProvinciaAproximate(@PathVariable(value = "codigo") String codigo) {
        try {
            return new ResponseEntity(provinciaService.findByCodigoProvinciaAproximate(codigo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/SumaCantidadPoblacionByProvinciaId/{id}")
    @ApiOperation(value = "Obtiene la población de la provincia según su Id", response = ProvinciaDTO.class, tags = "Provincias")
    public ResponseEntity<?> SumaCantidadPoblacionByProvinciaId(@PathVariable(value = "id") Long idProvincia) {
        try {
            return new ResponseEntity(provinciaService.SumaCantidadPoblacionByProvinciaId(idProvincia), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/SumaAreaCuadradaByProvinciaId/{id}")
    @ApiOperation(value = "Obtiene el area en metros cuadrados de la provincia según su Id", response = ProvinciaDTO.class, tags = "Provincias")
    public ResponseEntity<?> SumaAreaCuadradaByProvinciaId(@PathVariable(value = "id") Long idProvincia) {
        try {
            return new ResponseEntity(provinciaService.SumaAreaCuadradaByProvinciaId(idProvincia), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear una Provincia", response = ProvinciaDTO.class, tags = "Provincias")
    public ResponseEntity<?> create(@Valid @RequestBody ProvinciaDTO provinciaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(provinciaService.create(provinciaDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar una Provincia a partir de su Id", response = ProvinciaDTO.class, tags = "Provincias")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ProvinciaDTO provinciaDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ProvinciaDTO> provinciaUpdated = provinciaService.update(provinciaDTO, id);
                if (provinciaUpdated.isPresent()) {
                    return new ResponseEntity(provinciaUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/deleteById/{id}")
    @ApiOperation(value = "Permite eliminar una Provincia a partir de su Id", response = ProvinciaDTO.class, tags = "Provincias")
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        try {
            provinciaService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    @ApiOperation(value = "Permite eliminar todas las Provincias", response = ProvinciaDTO.class, tags = "Provincias")
    public ResponseEntity<?> deleteAll() {
        try {
            provinciaService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
