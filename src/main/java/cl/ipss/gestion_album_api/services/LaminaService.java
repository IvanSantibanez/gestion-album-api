package cl.ipss.gestion_album_api.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cl.ipss.gestion_album_api.models.Album;
import cl.ipss.gestion_album_api.models.Inventario;
import cl.ipss.gestion_album_api.models.Lamina;
import cl.ipss.gestion_album_api.repositories.AlbumRepository;
import cl.ipss.gestion_album_api.repositories.InventarioRepository;
import cl.ipss.gestion_album_api.repositories.LaminaRepository;
import jakarta.persistence.EntityNotFoundException;

public class LaminaService {

  @Autowired
  private LaminaRepository laminaRepository;

  @Autowired
  private AlbumRepository albumRepository;

  @Autowired
  private InventarioRepository inventarioRepository;

  public List<Lamina> crear(List<Lamina> laminas) {

    List<Lamina> laminasProcesadas = new ArrayList<>();

    for (Lamina lamina : laminas) {
      Lamina laminaExistente = laminaRepository.findByNumero(lamina.getNumero());
      if (laminaExistente != null) {

        Inventario inventarioExistente = inventarioRepository.findByLamina(laminaExistente);

        if (inventarioExistente != null) {
          inventarioExistente.setCantidad(inventarioExistente.getCantidad() + 1);
          inventarioRepository.save(inventarioExistente);
          laminaExistente.setFechaActualizacion(new Date());
          laminaRepository.save(laminaExistente);
          laminasProcesadas.add(laminaExistente);
        }

      } else {
        lamina.setFechaCreacion(new Date());
        lamina.setActiva(true);
        laminaRepository.save(lamina);

        Inventario inventario = new Inventario();
        inventario.setLamina(lamina);
        inventario.setCantidad(1);
        inventarioRepository.save(inventario);
        laminasProcesadas.add(lamina);
      }
    }
    return laminasProcesadas;
  }

  public List<Lamina> listar() {
    return laminaRepository.findByActivaTrue();
  }

  public Lamina buscar(Long id) {
    return laminaRepository.findById(id).orElseThrow(() -> {
      throw new EntityNotFoundException("Lamina no encontrada con id: " + id);
    });
  }

  public Lamina actualizar(Long id, Lamina laminaActualizada) {
    Lamina laminaExistente = laminaRepository.findById(id).orElseThrow(() -> {
      throw new EntityNotFoundException("Lamina no encontrada con id: " + id);
    });

    laminaExistente.setNumero(laminaActualizada.getNumero());
    laminaExistente.setNombre(laminaActualizada.getNombre());
    laminaExistente.setTipoLamina(laminaActualizada.getTipoLamina());
    laminaExistente.setImagenUrl(laminaActualizada.getImagenUrl());
    laminaExistente.setFechaActualizacion(new Date());
    laminaRepository.save(laminaExistente);

    return laminaExistente;
  }

  public void eliminar(Long id) {
    laminaRepository.findById(id).ifPresentOrElse(lamina -> {
      lamina.setActiva(false);
      lamina.setFechaEliminacion(new Date());
      laminaRepository.save(lamina);
    }, () -> {
      throw new EntityNotFoundException("Lamina no encontrada con id: " + id);
    });
  }

  public List<Lamina> obtenerRepetidas(Long albumId) {

    albumRepository.findById(albumId).orElseThrow(() -> {
      throw new EntityNotFoundException("Album no encontrado con id: " + albumId);
    });

    List<Inventario> inventarioRepetidas = inventarioRepository.findByLamina_Album_IdAndCantidadGreaterThan(albumId, 1);
    List<Lamina> laminasRepetidas = new ArrayList<>();

    for (Inventario inventario : inventarioRepetidas) {
      laminasRepetidas.add(inventario.getLamina());
    }
    return laminasRepetidas;

  }

  public List<String> obtenerFaltantes(Long albumId) {

    Album album = albumRepository.findById(albumId).orElseThrow(() -> {
      throw new EntityNotFoundException("Album no encontrado con id: " + albumId);
    });

    List<String> laminasExistentes = laminaRepository.findByAlbum_IdAndActivaTrue(albumId);

    int totalLaminas = album.getTotalLaminas();
    List<String> laminasFaltantes = new ArrayList<>();

    for (int i = 1; i <= totalLaminas; i++) {
      String numeroLaminaBuscada = String.valueOf(i);

      if (!laminasExistentes.contains(numeroLaminaBuscada)) {
        laminasFaltantes.add(String.valueOf(numeroLaminaBuscada));
      }
    }
    return laminasFaltantes;
  }
}
