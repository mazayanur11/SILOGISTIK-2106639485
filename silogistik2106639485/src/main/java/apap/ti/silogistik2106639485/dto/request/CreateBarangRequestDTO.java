package apap.ti.silogistik2106639485.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    private String sku;

    private int tipeBarang;

    @NotBlank(message = "Merk Barang tidak boleh kosong")
    private String merk;

    @Positive(message = "Harga harus lebih dari 0")
    @NotNull(message = "Harga Barang tidak boleh kosong")
    private Long hargaBarang;
}
