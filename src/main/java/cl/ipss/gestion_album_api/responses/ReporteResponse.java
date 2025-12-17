package cl.ipss.gestion_album_api.responses;

import cl.ipss.gestion_album_api.models.dto.LaminasReporteDto;
import lombok.Data;

@Data
public class ReporteResponse {

  private int status;
  private String message;
  private LaminasReporteDto data;

}
