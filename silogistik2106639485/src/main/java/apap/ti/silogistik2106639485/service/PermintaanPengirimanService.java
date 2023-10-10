package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106639485.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106639485.model.Barang;
import apap.ti.silogistik2106639485.model.PermintaanPengiriman;

import java.time.LocalDateTime;
import java.util.List;

public interface PermintaanPengirimanService {
    List<PermintaanPengiriman> getAllPermintaanPengiriman();

    String generateNomorPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
    
    void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
    
    PermintaanPengiriman getPermintaanPengirimanById(Long id);
    
    String getStringJenisLayanan(int jenisLayanan);
    
    void cancelPermintaan(PermintaanPengiriman permintaanPengiriman);
    
    List<ReadPermintaanPengirimanResponseDTO> filterWaktuPenerimaan(LocalDateTime start, LocalDateTime end, Barang barang);

    boolean isStokCukup(Barang barang, int kuantitasPengiriman);
}