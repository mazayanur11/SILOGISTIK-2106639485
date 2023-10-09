package apap.ti.silogistik2106639485.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gudang_barang", uniqueConstraints = @UniqueConstraint(columnNames = {"id_gudang", "sku_barang"}))
public class GudangBarang {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gudang", referencedColumnName = "id")
    private Gudang gudang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku")
    private Barang barang;

    @NotNull
    @Column(name = "stok", nullable = false)
    private int stok;
}
