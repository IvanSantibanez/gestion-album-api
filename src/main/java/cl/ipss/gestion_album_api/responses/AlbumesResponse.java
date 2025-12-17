package cl.ipss.gestion_album_api.responses;

import java.util.List;

import cl.ipss.gestion_album_api.models.Album;
import lombok.Data;

@Data
public class AlbumesResponse {

  private int status;
  private String message;
  private List<Album> data;

}
