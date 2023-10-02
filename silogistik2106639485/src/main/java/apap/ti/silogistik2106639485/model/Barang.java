package apap.ti.silogistik2106639485.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barang")
public class Barang {
    @Id
    private String sku;

    @NotNull
    @Column(name = "tipe_barang", nullable = false)
    private int tipeBarang;

    @NotNull
    @Column(name = "merk", nullable = false)
    private String merk;

    @NotNull
    @Column(name = "harga_barang", nullable = false)
    private Long hargaBarang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangBarang;
}
