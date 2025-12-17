package cl.ipss.gestion_album_api.models.dto;

import java.util.List;

import lombok.Data;

@Data
public class LaminasReporteDto {

  Long idAlbum;
  String nombreAlbum;
  int totalLaminas;
  int totalFaltantes;
  List<String> laminasFaltantes;
  int totalRepetidas;
  List<LaminaRepetidaReporteDto> laminasRepetidas;

}
