package giovanni.epicenergy.entities;

import giovanni.epicenergy.enums.TipoFatturaENUM;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "fatture")
@Getter
@Setter
@ToString

@NoArgsConstructor
public class Fattura {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO )

  private UUID id;
    @ManyToOne
    @JoinColumn(name = "clienti_ID")
  private Cliente clienti;
  private long numero;
  private LocalDate data;
  private long importo;
 private TipoFatturaENUM stato;

  public Fattura(long numero, LocalDate data, long importo, TipoFatturaENUM stato) {
    this.numero = numero;
    this.data = data;
    this.importo = importo;
    this.stato = stato;
  }
}
