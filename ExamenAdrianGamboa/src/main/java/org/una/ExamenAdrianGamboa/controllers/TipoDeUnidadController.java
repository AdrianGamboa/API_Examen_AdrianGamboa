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
import org.una.ExamenAdrianGamboa.dtos.TipoDeUnidadDTO;
import org.una.ExamenAdrianGamboa.services.ITipoDeUnidadService;
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
@RequestMapping("/exa_adr_tiposDeUnidades") 
@Api(tags = {"Tipos de unidades"})
public class TipoDeUnidadController {
    
    @Autowired
    private ITipoDeUnidadService tipoDeUnidadService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findAll")
    @ApiOperation(value = "Obtiene una lista de todos los Tipos de Unidades", response = TipoDeUnidadDTO.class, responseContainer = "List", tags = "Tipos de unidades")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(tipoDeUnidadService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene un Tipo de Unidad por su Id", response = TipoDeUnidadDTO.class, tags = "Tipos de unidades")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(tipoDeUnidadService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByNombreTipoUnidadAproximateIgnoreCase/{nombre}")
    @ApiOperation(value = "Obtiene un Tipo de Unidad mediante una apoximacion a su nombre", response = TipoDeUnidadDTO.class, tags = "Tipos de unidades")
    public ResponseEntity<?> findByNombreTipoUnidadAproximateIgnoreCase(@PathVariable(value = "nombre") String nombre) {
        try {
            return new ResponseEntity(tipoDeUnidadService.findByNombreTipoUnidadAproximateIgnoreCase(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Tipo de Unidad", response = TipoDeUnidadDTO.class, tags = "Tipos de unidades")
    public ResponseEntity<?> create(@Valid @RequestBody TipoDeUnidadDTO tipoDeUnidadDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(tipoDeUnidadService.create(tipoDeUnidadDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Tipo de Unidad a partir de su Id", response = TipoDeUnidadDTO.class, tags = "Tipos de unidades")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody TipoDeUnidadDTO tipoDeUnidadDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<TipoDeUnidadDTO> tipoDeUnidadUpdated = tipoDeUnidadService.update(tipoDeUnidadDTO, id);
                if (tipoDeUnidadUpdated.isPresent()) {
                    return new ResponseEntity(tipoDeUnidadUpdated, HttpStatus.OK);
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
    @ApiOperation(value = "Permite eliminar un Tipo de Unidad a partir de su Id", response = TipoDeUnidadDTO.class, tags = "Tipos de unidades")
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        try {
            tipoDeUnidadService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    @ApiOperation(value = "Permite eliminar todos los Tipos de Unidades", response = TipoDeUnidadDTO.class, tags = "Tipos de unidades")
    public ResponseEntity<?> deleteAll() {
        try {
            tipoDeUnidadService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

