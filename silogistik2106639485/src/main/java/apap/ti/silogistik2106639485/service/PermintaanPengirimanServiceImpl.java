package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.repository.PermintaanPengirimanBarangDb;
import apap.ti.silogistik2106639485.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106639485.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106639485.model.PermintaanPengiriman;
import apap.ti.silogistik2106639485.model.PermintaanPengirimanBarang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() { return permintaanPengirimanDb.findAll(); }

    @Override
    public String generateNomorPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO) {
        int jumlahPesanan = 0;

        for (PermintaanPengirimanBarang permintaanPengirimanBarang : createPermintaanPengirimanRequestDTO.getListPermintaanPengirimanBarang()) {
            jumlahPesanan += permintaanPengirimanBarang.getKuantitasPengiriman();
        }

        String charJenisLayanan = "";

        if (createPermintaanPengirimanRequestDTO.getJenisLayanan() == 1) {
            charJenisLayanan = "SAM";
        } else if (createPermintaanPengirimanRequestDTO.getJenisLayanan() == 2) {
            charJenisLayanan = "KIL";
        } else if (createPermintaanPengirimanRequestDTO.getJenisLayanan() == 3) {
            charJenisLayanan = "REG";
        } else {
            charJenisLayanan = "HEM";
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String strWaktuPermintaan = LocalDateTime.now().format(timeFormatter);

        return "REG" + jumlahPesanan % 100 + charJenisLayanan + strWaktuPermintaan;
    }

    @Override
    public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengirimanDb.save(permintaanPengiriman);
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman.getListPermintaanPengirimanBarang()) {
            permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengiriman);
            permintaanPengirimanBarangDb.save(permintaanPengirimanBarang);
        }
    }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(Long id) {
        for (PermintaanPengiriman permintaanPengiriman : getAllPermintaanPengiriman()) {
            if (permintaanPengiriman.getId().equals(id)) {
                return permintaanPengiriman;
            }
        }

        return null;
    }

    @Override
    public String getStringJenisLayanan(int jenisLayanan) {
        if (jenisLayanan == 1) {
            return "Same Day";
        } else if (jenisLayanan == 2) {
            return "Kilat";
        } else if (jenisLayanan == 3) {
            return "Reguler";
        } else {
            return "Hemat";
        }
    }
}
