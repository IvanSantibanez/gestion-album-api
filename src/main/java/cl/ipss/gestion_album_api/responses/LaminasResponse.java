package cl.ipss.gestion_album_api.responses;

import java.util.List;

import cl.ipss.gestion_album_api.models.Lamina;
import lombok.Data;

@Data
public class LaminasResponse {

  private int status;
  private String message;
  private List<Lamina> data;

}
