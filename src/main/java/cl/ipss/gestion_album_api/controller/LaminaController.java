package cl.ipss.gestion_album_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.ipss.gestion_album_api.models.Lamina;
import cl.ipss.gestion_album_api.models.dto.LaminasReporteDto; // Importación para el reporte
import cl.ipss.gestion_album_api.services.LaminaService;

@RestController
@RequestMapping("/api/laminas")
public class LaminaController {

    @Autowired
    private LaminaService laminaService;

    // REQUERIMIENTO: Carga masiva de láminas para facilitar la carga de datos
    @PostMapping("/masivo")
    public ResponseEntity<List<Lamina>> cargarMasivo(@RequestBody List<Lamina> laminas) {
        return ResponseEntity.ok(laminaService.crear(laminas));
    }

    // REQUERIMIENTO: Listado de láminas faltantes
    @GetMapping("/faltantes/{albumId}")
    public ResponseEntity<List<String>> obtenerFaltantes(@PathVariable Long albumId) {
        return ResponseEntity.ok(laminaService.obtenerFaltantes(albumId));
    }

    // REQUERIMIENTO: Listado de láminas repetidas
    @GetMapping("/repetidas/{albumId}")
    public ResponseEntity<List<Lamina>> obtenerRepetidas(@PathVariable Long albumId) {
        return ResponseEntity.ok(laminaService.obtenerRepetidas(albumId));
    }

    /**
     * REQUERIMIENTO ESPECIAL: Reporte consolidado completo.
     * Devuelve total de láminas, faltantes y repetidas con sus cantidades en un
     * solo JSON.
     */
    @GetMapping("/reporte/{albumId}")
    public ResponseEntity<LaminasReporteDto> obtenerReporteConsolidado(@PathVariable Long albumId) {
        return ResponseEntity.ok(laminaService.generarReporteCompleto(albumId));
    }
}