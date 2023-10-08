package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106639485.model.PermintaanPengiriman;

import java.util.List;

public interface PermintaanPengirimanService {
    List<PermintaanPengiriman> getAllPermintaanPengiriman();
    String generateNomorPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
    void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
    PermintaanPengiriman getPermintaanPengirimanById(Long id);
    String getStringJenisLayanan(int jenisLayanan);
}
