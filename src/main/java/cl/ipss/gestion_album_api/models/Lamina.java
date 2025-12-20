package cl.ipss.gestion_album_api.models;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Lamina {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "El nombre de la lámina no puede estar vacío")
  private String nombre;
  @NotNull(message = "El número de la lámina no puede ser nulo")
  private Integer numero;
  @NotBlank(message = "El tipo de lámina no puede estar vacío")
  private String tipoLamina;
  private String imagenUrl;
  private boolean activa;
  private Date fechaCreacion;
  private Date fechaActualizacion;
  private Date fechaEliminacion;

  @ManyToOne
  @JoinColumn(name = "album_id")
  @NotNull(message = "El álbum no puede ser nulo")
  @JsonBackReference
  private Album album;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "lamina", cascade = { CascadeType.ALL })
  private Inventario inventario;
}
