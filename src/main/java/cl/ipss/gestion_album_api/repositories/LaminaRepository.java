package cl.ipss.gestion_album_api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import cl.ipss.gestion_album_api.models.Lamina;

public interface LaminaRepository extends JpaRepository<Lamina, Long> {

  Lamina findByNumero(int numero);

  List<Lamina> findByActivaTrue();

  // Esta consulta es la "llave" para que el reporte funcione
  @Query("SELECT CAST(l.numero AS string) FROM Lamina l WHERE l.album.id = :albumId AND l.activa = true")
  List<String> findByAlbum_IdAndActivaTrue(@Param("albumId") Long albumId);

}