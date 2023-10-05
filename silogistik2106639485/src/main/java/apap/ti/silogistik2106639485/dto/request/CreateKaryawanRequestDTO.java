package apap.ti.silogistik2106639485.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKaryawanRequestDTO {
    @NotBlank(message = "Nama Karyawan tidak boleh kosong")
    private String nama;

    @NotBlank(message = "Jenis Kelamin tidak boleh kosong tidak boleh kosong")
    private int jenisKelamin;

    @NotBlank(message = "Tanggal Lahir tidak boleh kosong")
    private LocalDate tanggalLahir;
}
