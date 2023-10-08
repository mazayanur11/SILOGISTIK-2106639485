package apap.ti.silogistik2106639485.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import apap.ti.silogistik2106639485.model.Karyawan;
import apap.ti.silogistik2106639485.model.PermintaanPengirimanBarang;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequestDTO {
    private String nomorPengiriman;

    @NotBlank(message = "Nama Penerima tidak boleh kosong")
    private String namaPenerima;

    @NotBlank(message = "Alamat Penerima tidak boleh kosong")
    private String alamatPenerima;

    @NotNull(message = "Tanggal Pengiriman tidak boleh kosong")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate tanggalPengiriman;

    @NotNull(message = "Biaya Pengiriman tidak boleh kosong")
    @Positive(message = "Biaya Pengiriman harus lebih dari 0")
    private Integer biayaPengiriman;

    private int jenisLayanan;

    private LocalDateTime waktuPermintaan;

    @NotNull
    private Karyawan karyawan;

    @Size(min = 1, message = "Tolong tambahkan barang untuk dikirim")
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang = new ArrayList<>();
}
