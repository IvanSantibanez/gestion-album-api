package cl.ipss.gestion_album_api.responses;

import cl.ipss.gestion_album_api.models.Album;
import lombok.Data;

@Data
public class AlbumResponse {

  private int status;
  private String message;
  private Album data;
}
