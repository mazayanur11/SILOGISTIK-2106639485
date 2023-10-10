package apap.ti.silogistik2106639485.dto.response;

import apap.ti.silogistik2106639485.model.Karyawan;
import apap.ti.silogistik2106639485.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadPermintaanPengirimanResponseDTO {
    private Long id;
    private String nomorPengiriman;
    private boolean isCanceled;
    private String namaPenerima;
    private String alamatPenerima;
    private LocalDate tanggalPengiriman;
    private int biayaPengiriman;
    private int jenisLayanan;
    private LocalDateTime waktuPermintaan;
    private Karyawan karyawan;
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
    private Boolean isCancelable;
    private String formattedWaktuPermintaan;
    private String formattedTanggalPengiriman;
}