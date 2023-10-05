package apap.ti.silogistik2106639485.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106639485.model.Barang;
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

    @Override
    public List<GudangBarang> getAllGudangBarang() { return gudangBarangDb.findAll(); }

    @Override
    public GudangBarang getGudangBarangById(Long id) {
        for (GudangBarang gudangBarang : getAllGudangBarang()) {
            if (gudangBarang.getId().equals(id)) {
                return gudangBarang;
            }
        }
        return null;
    }

    @Override
    public List<GudangBarang> getStokOfBarang(Barang barang) {
        return gudangBarangDb.findAllByBarang(barang);
    }
}
