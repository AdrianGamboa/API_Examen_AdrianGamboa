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
import org.una.ExamenAdrianGamboa.dtos.UnidadDTO;
import org.una.ExamenAdrianGamboa.services.IUnidadService;
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
@RequestMapping("/exa_adr_unidades") 
@Api(tags = {"Unidades"})
public class UnidadController {
    
    @Autowired
    private IUnidadService unidadService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findAll")
    @ApiOperation(value = "Obtiene una lista de todas las Unidades", response = UnidadDTO.class, responseContainer = "List", tags = "Unidades")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(unidadService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene una Unidad por su Id", response = UnidadDTO.class, tags = "Unidades")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(unidadService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByNombreUnidadAproximateIgnoreCase/{nombre}")
    @ApiOperation(value = "Obtiene una Unidad mediante una apoximacion a su nombre", response = UnidadDTO.class, tags = "Unidades")
    public ResponseEntity<?> findByNombreUnidadAproximateIgnoreCase(@PathVariable(value = "nombre") String nombre) {
        try {
            return new ResponseEntity(unidadService.findByNombreUnidadAproximateIgnoreCase(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByCodigoUnidadAproximate/{codigo}")
    @ApiOperation(value = "Obtiene una Unidad mediante una apoximacion a su codigo", response = UnidadDTO.class, tags = "Unidades")
    public ResponseEntity<?> findByCodigoUnidadAproximate(@PathVariable(value = "codigo") String codigo) {
        try {
            return new ResponseEntity(unidadService.findByCodigoUnidadAproximate(codigo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/SumaUnidadCantidadPoblacion/{id}")
    @ApiOperation(value = "Obtiene una la población de la unidad dependiendo del Id", response = UnidadDTO.class, tags = "Unidades")
    public ResponseEntity<?> SumaUnidadCantidadPoblacion(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(unidadService.SumaUnidadCantidadPoblacion(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/SumaUnidadAreaCuadrada/{id}")
    @ApiOperation(value = "Obtiene una el area cuadrada de la unidad dependiendo del Id", response = UnidadDTO.class, tags = "Unidades")
    public ResponseEntity<?> SumaUnidadAreaCuadrada(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(unidadService.SumaUnidadAreaCuadrada(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear una Unidad", response = UnidadDTO.class, tags = "Unidades")
    public ResponseEntity<?> create(@Valid @RequestBody UnidadDTO unidadDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(unidadService.create(unidadDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar una Unidad a partir de su Id", response = UnidadDTO.class, tags = "Unidades")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UnidadDTO unidadDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<UnidadDTO> unidadUpdated = unidadService.update(unidadDTO, id);
                if (unidadUpdated.isPresent()) {
                    return new ResponseEntity(unidadUpdated, HttpStatus.OK);
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
    @ApiOperation(value = "Permite eliminar una Unidad a partir de su Id", response = UnidadDTO.class, tags = "Unidades")
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {
        try {
            unidadService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    @ApiOperation(value = "Permite eliminar todas las Unidades", response = UnidadDTO.class, tags = "Unidades")
    public ResponseEntity<?> deleteAll() {
        try {
            unidadService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


