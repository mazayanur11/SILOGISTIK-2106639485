package apap.ti.silogistik2106639485.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106639485.model.GudangBarang;
import apap.ti.silogistik2106639485.repository.GudangBarangDb;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void saveGudangBarang(GudangBarang gudangBarang) {
        gudangBarangDb.save(gudangBarang);
    }
}
