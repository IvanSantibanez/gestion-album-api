package cl.ipss.gestion_album_api.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ipss.gestion_album_api.models.Album;
import cl.ipss.gestion_album_api.repositories.AlbumRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AlbumService {

  @Autowired
  private AlbumRepository albumRepository;

  public Album crear(Album album) {
    return albumRepository.save(album);
  }

  public List<Album> listar() {
    return albumRepository.findByActivoTrue();
  }

  public Album buscar(Long id) {
    return albumRepository.findById(id).orElseThrow(() -> {
      throw new EntityNotFoundException("Album no encontrado con id: " + id);
    });
  }

  public Album actualizar(Long id, Album albumActualizado) {
    System.out.println("ID: " + id);
    System.out.println("Album recibido: " + albumActualizado);
    try {
        Album albumExistente = albumRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Album no encontrado con id: " + id);
        });

        albumExistente.setNombre(albumActualizado.getNombre());
        albumExistente.setFechaLanzamiento(albumActualizado.getFechaLanzamiento());
        albumExistente.setTotalLaminas(albumActualizado.getTotalLaminas());
        albumExistente.setFechaActualizacion(new Date());

        return albumRepository.save(albumExistente);
    } catch (Exception e) {
        e.printStackTrace(); 
        throw e;
    }
  }

  public void eliminar(Long id) {
    albumRepository.findById(id).ifPresentOrElse(album -> {
      album.setActivo(false);
      album.setFechaEliminacion(new Date());
      albumRepository.save(album);
    }, () -> {
      throw new EntityNotFoundException("Album no encontrado con id: " + id);
    });
  }

}
