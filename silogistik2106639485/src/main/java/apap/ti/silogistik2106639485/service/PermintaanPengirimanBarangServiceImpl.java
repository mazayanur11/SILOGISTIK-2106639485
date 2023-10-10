package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106639485.repository.PermintaanPengirimanBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermintaanPengirimanBarangServiceImpl implements PermintaanPengirimanBarangService {
    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;
    
    @Override
    public void savePermintaanPengirimanBarang(PermintaanPengirimanBarang permintaanPengirimanBarang) {
        permintaanPengirimanBarangDb.save(permintaanPengirimanBarang);
    }
}