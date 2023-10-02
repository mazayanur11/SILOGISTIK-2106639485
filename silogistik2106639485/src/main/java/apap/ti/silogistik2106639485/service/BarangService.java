package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.model.Barang;

import java.util.List;

public interface BarangService {
    void saveBarang(Barang barang);
    
    List<Barang> getAllBarang();

    Barang getBarangById(String sku);
}
