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
import org.una.ExamenAdrianGamboa.dtos.CantonDTO;
import org.una.ExamenAdrianGamboa.services.ICantonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


import java.util.Optional;
import javax.validation.Valid;

/**
 *
 * @author Adrian
 */

@RestController
@RequestMapping("/exa_adr_cantones") 
@Api(tags = {"Cantones"})
public class CantonController {
    
    @Autowired
    private ICantonService cantonService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findAll")
    @ApiOperation(value = "Obtiene una lista de todos los Cantons", response = CantonDTO.class, responseContainer = "List", tags = "Cantones")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(cantonService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene un Canton por su Id", response = CantonDTO.class, tags = "Cantones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(cantonService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByNombreCantonAproximateIgnoreCase/{nombre}")
    @ApiOperation(value = "Obtiene un Canton mediante una apoximacion a su nombre", response = CantonDTO.class, tags = "Cantones")
    public ResponseEntity<?> findByNombreCantonAproximateIgnoreCase(@PathVariable(value = "nombre") String nombre) {
        try {
            return new ResponseEntity(cantonService.findByNombreCantonAproximateIgnoreCase(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByCodigoCantonAproximate/{codigo}")
    @ApiOperation(value = "Obtiene un Canton mediante una apoximacion a su codigo", response = CantonDTO.class, tags = "Cantones")
    public ResponseEntity<?> findByCodigoCantonAproximate(@PathVariable(value = "codigo") String codigo) {
        try {
            return new ResponseEntity(cantonService.findByCodigoCantonAproximate(codigo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Canton", response = CantonDTO.class, tags = "Cantones")
    public ResponseEntity<?> create(@Valid @RequestBody CantonDTO cantonDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(cantonService.create(cantonDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Canton a partir de su Id", response = CantonDTO.class, tags = "Cantones")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody CantonDTO cantonDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<CantonDTO> cantonUpdated = cantonService.update(cantonDTO, id);
                if (cantonUpdated.isPresent()) {
                    return new ResponseEntity(cantonUpdated, HttpStatus.OK);
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
}

