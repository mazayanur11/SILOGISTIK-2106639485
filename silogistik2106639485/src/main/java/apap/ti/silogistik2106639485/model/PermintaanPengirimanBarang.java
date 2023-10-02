package apap.ti.silogistik2106639485.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman_barang")
public class PermintaanPengirimanBarang {
    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_permintaan_pengiriman", referencedColumnName = "id")
    private PermintaanPengiriman permintaanPengiriman;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_barang", referencedColumnName = "sku")
    private Barang barang;

    @NotNull
    @Column(name = "kuantitas_pengiriman", nullable = false)
    private int kuantitasPengiriman;
}
