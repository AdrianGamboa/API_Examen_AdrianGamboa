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
public class TipoDeUnidadDTO {
    
    private Long id; 
    private String nombreTipoUnidad;    
}