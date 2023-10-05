package apap.ti.silogistik2106639485.dto.request;

import jakarta.validation.constraints.NotBlank;
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

    @Positive(message = "Harga tidak boleh kurang dari 0")
    private Long hargaBarang;
}
