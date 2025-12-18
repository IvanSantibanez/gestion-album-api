package cl.ipss.gestion_album_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.ipss.gestion_album_api.models.Album;
import cl.ipss.gestion_album_api.services.AlbumService;

@RestController
@RequestMapping("/api/albumes")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public List<Album> listar() {
        return albumService.listar();
    }

    @PostMapping
    public Album crear(@RequestBody Album album) {
        album.setActivo(true);
        return albumService.crear(album);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.buscar(id));
    }

    @PutMapping("/{id}")
    public Album actualizar(@PathVariable Long id, @RequestBody Album album) {
        return albumService.actualizar(id, album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        albumService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}