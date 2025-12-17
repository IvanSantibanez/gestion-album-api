package cl.ipss.gestion_album_api.models;

import java.util.Date;

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
  private String nombre;
  private int numero;
  private String tipoLamina;
  private String imagenUrl;
  private boolean activa;
  private Date fechaCreacion;
  private Date fechaActualizacion;
  private Date fechaEliminacion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "album_id")
  private Album album;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "lamina", cascade = { CascadeType.ALL })
  private Inventario inventario;
}
