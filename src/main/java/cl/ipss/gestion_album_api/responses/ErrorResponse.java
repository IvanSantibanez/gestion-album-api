package cl.ipss.gestion_album_api.responses;

import lombok.Data;

@Data
public class ErrorResponse {

  private int status;
  private String message;
  private Object data;
}
