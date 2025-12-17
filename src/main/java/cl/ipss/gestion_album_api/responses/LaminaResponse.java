package cl.ipss.gestion_album_api.responses;

import cl.ipss.gestion_album_api.models.Lamina;
import lombok.Data;

@Data
public class LaminaResponse {

  private int status;
  private String message;
  private Lamina data;

}
