package org.una.ExamenAdrianGamboa.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.ExamenAdrianGamboa.entities.Provincia;
import org.una.ExamenAdrianGamboa.entities.Canton;

/**
 *
 * @author Adrian
 */

public interface IProvinciaRepository extends JpaRepository<Provincia, Long>{

    @Query(value = "SELECT SUM(uni.cantidadPoblacion) FROM Unidad uni JOIN uni.distrito dis JOIN dis.canton can JOIN can.provincia pro WHERE "
            + "can.provincia IN (SELECT prov FROM Provincia prov WHERE prov.id = ?1) AND "
            + "dis.canton IN (SELECT cant.id FROM Canton cant WHERE cant.provincia = ?1) AND "
            + "uni.distrito IN (SELECT dist.id FROM Distrito dist WHERE dist.canton = (SELECT cant.id FROM Canton cant WHERE cant.provincia = ?1))")
    public Long SumaCantidadPoblacionByProvinciaId(Long idProvincia);
    
    @Query(value = "SELECT SUM(uni.areaEnMetrosCuadrados) FROM Unidad uni JOIN uni.distrito dis JOIN dis.canton can JOIN can.provincia pro WHERE "
            + "can.provincia IN (SELECT prov FROM Provincia prov WHERE prov.id = ?1) AND "
            + "dis.canton IN (SELECT cant.id FROM Canton cant WHERE cant.provincia = ?1) AND "
            + "uni.distrito IN (SELECT dist.id FROM Distrito dist WHERE dist.canton = (SELECT cant.id FROM Canton cant WHERE cant.provincia = ?1))")
    public Float SumaAreaCuadradaByProvinciaId(Long idProvincia);
    
    @Query(value = "SELECT can FROM Canton can WHERE can.provincia IN (SELECT prov FROM Provincia prov WHERE prov.id = ?1)")
    public List<Canton> findCantonById(Long idProvincia);
    
    public List<Provincia> findByNombreProvinciaContainingIgnoreCase(String nombreProvincia);
    
    public Optional<Provincia> findByCodigoProvincia(Integer codigoProvincia);
    
}
