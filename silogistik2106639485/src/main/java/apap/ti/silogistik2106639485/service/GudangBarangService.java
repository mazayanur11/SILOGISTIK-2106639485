package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.model.Barang;
import apap.ti.silogistik2106639485.model.GudangBarang;

import java.util.List;

public interface GudangBarangService {
    void saveGudangBarang(GudangBarang gudangBarang);

    List<GudangBarang> getAllGudangBarang();

    GudangBarang getGudangBarangById(Long id);

    List<GudangBarang> getStokOfBarang(Barang barang);

    void saveAllGudangBarang(List<GudangBarang> listGudangBarang);
}