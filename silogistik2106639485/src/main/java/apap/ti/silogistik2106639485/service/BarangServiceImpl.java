package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.repository.BarangDb;
import apap.ti.silogistik2106639485.model.Barang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangDb barangDb;

    @Override
    public void saveBarang(Barang barang) {
        barangDb.save(barang);
    }

    @Override
    public List<Barang> getAllBarang() { return barangDb.findAll(); }

    @Override
    public Barang getBarangById(String sku) {
        for (Barang barang : getAllBarang()) {
            if (barang.getSku().equals(sku)) {
                return barang;
            }
        }        
        return null;
    }
}
