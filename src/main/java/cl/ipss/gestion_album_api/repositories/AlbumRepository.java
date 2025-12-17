package cl.ipss.gestion_album_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ipss.gestion_album_api.models.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {

  List<Album> findByActivoTrue();

}
