package cl.ipss.gestion_album_api.responses;

import java.util.List;
import lombok.Data;

@Data
public class StringsResponse {
    private int status;
    private String message;
    private List<String> data;
}