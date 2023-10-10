package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.repository.PermintaanPengirimanBarangDb;
import apap.ti.silogistik2106639485.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106639485.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106639485.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106639485.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106639485.model.Barang;
import apap.ti.silogistik2106639485.model.PermintaanPengiriman;
import apap.ti.silogistik2106639485.model.PermintaanPengirimanBarang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Autowired
    BarangService barangService;

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() { return permintaanPengirimanDb.findAllByOrderByWaktuPermintaanDesc(); }

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
        }

        permintaanPengirimanBarangDb.saveAll(permintaanPengiriman.getListPermintaanPengirimanBarang());
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

    @Override
    public void cancelPermintaan(PermintaanPengiriman permintaanPengiriman) {
        if (!LocalDateTime.now().isAfter(permintaanPengiriman.getWaktuPermintaan().plusHours(24)) && !permintaanPengiriman.isCanceled()) {
            permintaanPengiriman.setCanceled(true);
            savePermintaanPengiriman(permintaanPengiriman);
        } else {
            throw new DataIntegrityViolationException("Permintaan pengiriman dengan nomor " + permintaanPengiriman.getNomorPengiriman() + " sudah tidak dapat dicancel!");
        }
    }

    @Override
    public List<ReadPermintaanPengirimanResponseDTO> filterWaktuPenerimaan(LocalDateTime start, LocalDateTime end, Barang barang) {
        List<Long> listIdPermintaanFromBarang = new ArrayList<>();

        for (PermintaanPengirimanBarang permintaanPengirimanBarang : barang.getListPermintaanPengirimanBarang()) {
            listIdPermintaanFromBarang.add(permintaanPengirimanBarang.getPermintaanPengiriman().getId());
        }

        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanDb.findByWaktuPermintaanBetweenAndIdInOrderByWaktuPermintaanDesc(start, end, listIdPermintaanFromBarang);

        List<ReadPermintaanPengirimanResponseDTO> formattedPermintaanPengiriman = new ArrayList<>();
        for (PermintaanPengiriman permintaanPengiriman : listPermintaanPengiriman) {
            formattedPermintaanPengiriman.add(permintaanPengirimanMapper.permintaanPengirimanToReadPermintaanPengirimanResponseDTO(permintaanPengiriman));
        }

        return formattedPermintaanPengiriman;
    }

    @Override
    public boolean isStokCukup(Barang barang, int kuantitasPengiriman) {
        int stokBarang = barangService.getStockBarang(barang);

        if (kuantitasPengiriman > stokBarang) {
            return false;
        }
        
        return true;
    }
}