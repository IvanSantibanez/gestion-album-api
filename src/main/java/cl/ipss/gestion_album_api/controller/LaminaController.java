package cl.ipss.gestion_album_api.controller;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cl.ipss.gestion_album_api.models.Lamina;
import cl.ipss.gestion_album_api.models.dto.LaminasReporteDto;
import cl.ipss.gestion_album_api.services.LaminaService;
import cl.ipss.gestion_album_api.responses.LaminaResponse;
import cl.ipss.gestion_album_api.responses.LaminasResponse;
import cl.ipss.gestion_album_api.responses.ReporteResponse;
import cl.ipss.gestion_album_api.responses.StringsResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Láminas", description = "Operaciones CRUD y gestión especial de láminas")
@RestController
@RequestMapping("/api/laminas")
@Validated
public class LaminaController {

    @Autowired
    private LaminaService laminaService;

    // REQUERIMIENTO: Carga masiva de láminas para facilitar la carga de datos
    @PostMapping("/masivo")
    public ResponseEntity<Object> cargarMasivo(@Valid @RequestBody List<Lamina> laminas) {
        LaminasResponse response = new LaminasResponse();
        response.setStatus(200);
        response.setMessage("Láminas cargadas exitosamente");
        response.setData(laminaService.crear(laminas));
        return ResponseEntity.ok().body(response);
    }

    // REQUERIMIENTO: Listado de láminas faltantes
    @GetMapping("/faltantes/{albumId}")
    public ResponseEntity<Object> obtenerFaltantes(
        @PathVariable @Min(value = 1, message = "El ID debe ser mayor a 0") Long albumId) {
        StringsResponse response = new StringsResponse();
        response.setStatus(200);
        response.setMessage("Listado de láminas faltantes obtenido exitosamente");
        response.setData(laminaService.obtenerFaltantes(albumId));
        return ResponseEntity.ok().body(response);
    }

    // REQUERIMIENTO: Listado de láminas repetidas
    @GetMapping("/repetidas/{albumId}")
    public ResponseEntity<Object> obtenerRepetidas(
        @PathVariable @Min(value = 1, message = "El ID debe ser mayor a 0") Long albumId) {
        LaminasResponse response = new LaminasResponse();
        response.setStatus(200);
        response.setMessage("Listado de láminas repetidas obtenido exitosamente");
        response.setData(laminaService.obtenerRepetidas(albumId));
        return ResponseEntity.ok().body(response);
    }

    /**
     * REQUERIMIENTO ESPECIAL: Reporte consolidado completo.
     * Devuelve total de láminas, faltantes y repetidas con sus cantidades en un
     * solo JSON.
     */
    @GetMapping("/reporte/{albumId}")
    public ResponseEntity<Object> obtenerReporteConsolidado(
        @PathVariable @Min(value = 1, message = "El ID debe ser mayor a 0") Long albumId) {
        ReporteResponse response = new ReporteResponse();
        response.setStatus(200);
        response.setMessage("Reporte consolidado obtenido exitosamente");
        response.setData(laminaService.generarReporteCompleto(albumId));
        return ResponseEntity.ok().body(response);
    }
}