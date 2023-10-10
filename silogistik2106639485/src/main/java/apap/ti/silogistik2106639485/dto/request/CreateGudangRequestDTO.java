package apap.ti.silogistik2106639485.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangRequestDTO {
    @NotBlank(message = "Nama Gudang tidak boleh kosong")
    @Size(max = 255, message = "Nama Gudang tidak boleh lebih dari 255 karakter")
    private String nama;

    @NotBlank(message = "Alamat Gudang tidak boleh kosong")
    @Size(max = 255, message = "Alamat Gudang tidak boleh lebih dari 255 karakter")
    private String alamatGudang;
}