package cl.ipss.gestion_album_api.controller;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import cl.ipss.gestion_album_api.models.Album;
import cl.ipss.gestion_album_api.services.AlbumService;
import cl.ipss.gestion_album_api.responses.AlbumResponse;
import cl.ipss.gestion_album_api.responses.AlbumesResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Álbumes", description = "Operaciones CRUD de álbumes")
@RestController
@RequestMapping("/api/albumes")
@Validated
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<Object> listar() {
        AlbumesResponse response = new AlbumesResponse();
        response.setStatus(200);
        response.setMessage("Listado de álbumes obtenido exitosamente");
        response.setData(albumService.listar());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Object> crear(@Valid @RequestBody Album album) {
        album.setActivo(true);
        AlbumResponse response = new AlbumResponse();
        response.setStatus(200);
        response.setMessage("Álbum creado exitosamente");
        response.setData(albumService.crear(album));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscar(@PathVariable @Min(value = 1, message = "El ID debe ser mayor a 0") Long id) {
        AlbumResponse response = new AlbumResponse();
        response.setStatus(200);
        response.setMessage("Álbum obtenido exitosamente");
        response.setData(albumService.buscar(id));
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizar(
            @PathVariable @Min(value = 1, message = "El ID debe ser mayor a 0") Long id,
            @Valid @RequestBody Album album) {
        AlbumResponse response = new AlbumResponse();
        response.setStatus(200);
        response.setMessage("Álbum actualizado exitosamente");
        response.setData(albumService.actualizar(id, album));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable @Min(value = 1, message = "El ID debe ser mayor a 0") Long id) {
        albumService.eliminar(id);
        AlbumResponse response = new AlbumResponse();
        response.setStatus(202);
        response.setMessage("Álbum eliminado exitosamente");
        response.setData(null);
        return ResponseEntity.accepted().body(response);
    }
}