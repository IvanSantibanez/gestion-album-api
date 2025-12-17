package cl.ipss.gestion_album_api.models.dto;

import lombok.Data;

@Data
public class LaminaRepetidaReporteDto {

  private Long idLamina;
  private String numeroLamina;
  private int cantidadRepetidas;

}
