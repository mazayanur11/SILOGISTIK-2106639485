package apap.ti.silogistik2106639485.dto.request;

import apap.ti.silogistik2106639485.model.GudangBarang;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    private String sku;

    @NotBlank(message = "Tipe Barang tidak boleh kosong")
    private int tipeBarang;

    @NotBlank(message = "Merk Barang tidak boleh kosong")
    private String merk;

    @NotBlank(message = "Harga Barang tidak boleh kosong")
    private Long hargaBarang;
}
