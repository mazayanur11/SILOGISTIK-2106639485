package apap.ti.silogistik2106639485.dto.request;

import apap.ti.silogistik2106639485.model.Barang;
import apap.ti.silogistik2106639485.model.Gudang;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangBarangRequestDTO {
    @NotNull
    private Gudang gudang;

    @NotNull
    private Barang barang;

    @NotBlank(message = "Stok tidak boleh kosong")
    @Positive(message = "Stok tidak boleh kurang dari 0")
    private int stok;
}
