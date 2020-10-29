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
@Table(name = "exa_adr_cantones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Canton implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50)
    private String nombreCanton;
    
    @Column(name = "codigo", length = 5)
    private Integer codigoCanton;
    
    @ManyToOne 
    @JoinColumn(name="provincias_id")
    private Provincia provincia;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "canton")
    private List<Distrito> distritos = new ArrayList<>();
    
    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        
    }

    @PreUpdate
    public void preUpdate() {
       
    }
}

