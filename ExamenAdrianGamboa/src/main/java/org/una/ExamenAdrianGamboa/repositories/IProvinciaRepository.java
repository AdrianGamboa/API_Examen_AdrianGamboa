package org.una.ExamenAdrianGamboa.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.ExamenAdrianGamboa.entities.Provincia;

/**
 *
 * @author Adrian
 */

public interface IProvinciaRepository extends JpaRepository<Provincia, Long>{
   
   // @Query(value = "SELECT can FROM Canton can JOIN can.distrito dis JOIN dis.unidad uni where pw.id=:id1 and nw.id=:id2")
  //  @Query(value = "Select Emp.codigo_emp, Emp.nombre_emp, Emp.edad, Emp.direccion_emp From t_empleado emp Where emp.id_departamento = (Select dep.id_departamento From t_departamento dep Where dep.codigo_dep = pin_codigo_dep)")
   /* @Query(value = "SELECT Can.id FROM Canton Can , "
            + "(SELECT Dis.id FROM Distrito Dis) id_dis "
            + "WHERE Can.id = id_dis.id_dis")*/
    //public List<Provincia> findUnidadCantidadPoblacionByProvinciaId(Long id); //Implementar los service
    
    public List<Provincia> findByNombreProvinciaContainingIgnoreCase(String nombreProvincia);
    
    public List<Provincia> findByCodigoProvinciaContaining(String codigoProvincia);
    
}
