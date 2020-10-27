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
    private String codigoUnidad;
    
    @Column(name = "areaCuadrada")
    private Float areaCuadrada;
    
    @Column(name = "cantidadPoblacion")
    private Integer cantidadPoblacion;
    
    @ManyToOne 
    @JoinColumn(name="distritos_id")
    private Distrito distrito;
   
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        
    }

    @PreUpdate
    public void preUpdate() {
       
    }
}


