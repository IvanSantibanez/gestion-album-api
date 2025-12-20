package cl.ipss.gestion_album_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Inventario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Min(value = 0, message = "La cantidad no puede ser negativa")
  private int cantidad;

  @OneToOne
  @JoinColumn(name = "lamina_id")
  @JsonIgnore
  @NotNull(message = "La lámina no puede ser nula")
  private Lamina lamina;

}
