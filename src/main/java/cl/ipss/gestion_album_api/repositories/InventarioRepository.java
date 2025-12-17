package cl.ipss.gestion_album_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.ipss.gestion_album_api.models.Inventario;
import cl.ipss.gestion_album_api.models.Lamina;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

  Inventario findByLamina(Lamina lamina);

  List<Inventario> findByLamina_Album_IdAndCantidadGreaterThan(Long albumId, int cantidad);
}
