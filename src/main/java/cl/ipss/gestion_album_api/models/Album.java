package cl.ipss.gestion_album_api.models;

import java.util.Date;
import java.util.List;

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
  private String nombre;
  private Date fechaLanzamiento;
  private int totalLaminas;
  private boolean activo;
  private Date fechaCreacion;
  private Date fechaActualizacion;
  private Date fechaEliminacion;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "album", cascade = { CascadeType.ALL })
  private List<Lamina> laminas;

}
