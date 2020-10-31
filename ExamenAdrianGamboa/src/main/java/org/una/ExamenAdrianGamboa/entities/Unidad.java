package org.una.ExamenAdrianGamboa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 *
 * @author Adrian
 */

@Entity
@Table(name = "exa_adr_unidades")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Unidad implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50)
    private String nombreUnidad;
    
    @Column(name = "codigo", length = 5)
    private Integer codigoUnidad;
    
    @Column(name = "areaEnMetrosCuadrados")
    private Double areaEnMetrosCuadrados;
    
    @Column(name = "cantidadPoblacion")
    private Long cantidadPoblacion;
    
    @ManyToOne 
    @JoinColumn(name="distritos_id")
    private Distrito distrito;
    
    @ManyToOne 
    @JoinColumn(name="tiposDeUnidades_id")
    private TipoDeUnidad tipoDeUnidad;
   
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        
    }

    @PreUpdate
    public void preUpdate() {
       
    }
}


