package cl.ipss.gestion_album_api.models;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Album {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "El nombre del álbum no puede estar vacío")
  private String nombre;
  @NotNull(message = "La fecha de lanzamiento no puede ser nula")
  private Date fechaLanzamiento;
  @NotNull(message = "El total de láminas no puede ser nulo")
  private Integer totalLaminas;
  private boolean activo;
  private Date fechaCreacion;
  private Date fechaActualizacion;
  private Date fechaEliminacion;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "album", cascade = { CascadeType.ALL })
  @JsonManagedReference
  private List<Lamina> laminas;

}
