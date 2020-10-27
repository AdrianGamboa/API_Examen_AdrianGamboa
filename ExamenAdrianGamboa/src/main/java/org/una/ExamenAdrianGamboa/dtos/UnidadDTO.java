package org.una.ExamenAdrianGamboa.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Adrian
 */

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class UnidadDTO {
    
    private Long id; 
    private String nombreUnidad;
    private String codigoUnidad;
    private Float areaCuadrada;
    private Integer cantidadPoblacion;
    private DistritoDTO distrito;
    
}

