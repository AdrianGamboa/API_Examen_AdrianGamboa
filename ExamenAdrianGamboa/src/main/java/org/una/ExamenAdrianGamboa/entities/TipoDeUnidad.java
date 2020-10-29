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
@Table(name = "exa_adr_tipos_de_unidades")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class TipoDeUnidad implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50)
    private String nombreTipoUnidad;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDeUnidad")
    private List<Unidad> unidades = new ArrayList<>();
   
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        
    }

    @PreUpdate
    public void preUpdate() {
       
    }
}
