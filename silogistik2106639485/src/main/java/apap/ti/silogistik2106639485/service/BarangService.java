package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.model.Barang;

import java.util.Dictionary;
import java.util.List;

public interface BarangService {
    void saveBarang(Barang barang);
    
    List<Barang> getAllBarang();

    Barang getBarangById(String sku);

    long getNextSku(int tipeBarang);

    List<Barang> getAllBarangSortedByMerk();

    String getNamaTipeBarang(int tipeBarang);

    Dictionary<Barang, Integer> getStokBarang(List<Barang> listBarang);

    boolean isMerkExist(String merk);

    boolean isMerkExist(String merk, String sku);

    Barang updateBarang(Barang barangFromDTO);

    String getStringTipeBarang(int tipeBarang);
}
