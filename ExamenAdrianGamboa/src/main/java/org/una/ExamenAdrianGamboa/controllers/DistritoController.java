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
import org.una.ExamenAdrianGamboa.dtos.DistritoDTO;
import org.una.ExamenAdrianGamboa.services.IDistritoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;
import javax.validation.Valid;

/**
 *
 * @author Adrian
 */

@RestController
@RequestMapping("/exa_adr_distritos") 
@Api(tags = {"Distritos"})
public class DistritoController {
    
    @Autowired
    private IDistritoService distritoService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/findAll")
    @ApiOperation(value = "Obtiene una lista de todos los Distritos", response = DistritoDTO.class, responseContainer = "List", tags = "Distritos")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(distritoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Obtiene un Distrito por su Id", response = DistritoDTO.class, tags = "Distritos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(distritoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByNombreDistritoAproximateIgnoreCase/{nombre}")
    @ApiOperation(value = "Obtiene un Distrito mediante una apoximacion a su nombre", response = DistritoDTO.class, tags = "Distritos")
    public ResponseEntity<?> findByNombreDistritoAproximateIgnoreCase(@PathVariable(value = "nombre") String nombre) {
        try {
            return new ResponseEntity(distritoService.findByNombreDistritoAproximateIgnoreCase(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/findByCodigoDistritoAproximate/{codigo}")
    @ApiOperation(value = "Obtiene un Distrito mediante una apoximacion a su codigo", response = DistritoDTO.class, tags = "Distritos")
    public ResponseEntity<?> findByCodigoDistritoAproximate(@PathVariable(value = "codigo") String codigo) {
        try {
            return new ResponseEntity(distritoService.findByCodigoDistritoAproximate(codigo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Distrito", response = DistritoDTO.class, tags = "Distritos")
    public ResponseEntity<?> create(@Valid @RequestBody DistritoDTO distritoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(distritoService.create(distritoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite modificar un Distrito a partir de su Id", response = DistritoDTO.class, tags = "Distritos")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody DistritoDTO distritoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<DistritoDTO> distritoUpdated = distritoService.update(distritoDTO, id);
                if (distritoUpdated.isPresent()) {
                    return new ResponseEntity(distritoUpdated, HttpStatus.OK);
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