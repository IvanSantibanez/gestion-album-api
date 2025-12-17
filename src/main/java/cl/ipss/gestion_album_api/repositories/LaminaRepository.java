package cl.ipss.gestion_album_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ipss.gestion_album_api.models.Lamina;

public interface LaminaRepository extends JpaRepository<Lamina, Long> {

  Lamina findByNumero(int numero);

  List<Lamina> findByActivaTrue();

  List<String> findByAlbum_IdAndActivaTrue(Long albumId);

}
