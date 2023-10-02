package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106639485.model.PermintaanPengiriman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDbDb;

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() { return permintaanPengirimanDbDb.findAll(); }
}
