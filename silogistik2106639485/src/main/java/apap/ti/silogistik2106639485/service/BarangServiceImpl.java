package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.repository.BarangDb;
import apap.ti.silogistik2106639485.model.Barang;
import apap.ti.silogistik2106639485.model.GudangBarang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

@Service
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangDb barangDb;

    @Autowired
    GudangBarangService gudangBarangService;

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

    @Override
    public long getNextSku(int tipeBarang) {
        return 1 + barangDb.countByTipeBarang(tipeBarang);
    }

    @Override
    public List<Barang> getAllBarangSortedByMerk() {
        return barangDb.getAllByOrderByMerk();
    }

    @Override
    public String getNamaTipeBarang(int tipeBarang) {
        if (tipeBarang == 1) {
            return "ELEC";
        } else if (tipeBarang == 2) {
            return "CLOT";
        } else if (tipeBarang == 3) {
            return "FOOD";
        } else if (tipeBarang == 4) {
            return "COSM";
        } else {
            return "TOOL";
        }
    }

    @Override
    public Dictionary<Barang, Integer> getStokBarang(List<Barang> listBarang) {
        Dictionary<Barang, Integer> stokBarang = new Hashtable<>();

        
        for (Barang barang : listBarang) {
            List<GudangBarang> listGudangBarang = gudangBarangService.getStokOfBarang(barang);
            int totalStok = 0;

            for (GudangBarang gudangBarang : listGudangBarang) {
                totalStok += gudangBarang.getStok();
            }

            stokBarang.put(barang, totalStok);
        }

        return stokBarang;
    }

    @Override
    public boolean isMerkExist(String merk) {
        return getAllBarang().stream().anyMatch(b -> b.getMerk().equals(merk));
    }

    @Override
    public boolean isMerkExist(String merk, String sku) {
        return getAllBarang().stream().anyMatch(b -> b.getMerk().equals(merk) && !b.getSku().equals(sku));
    }

    @Override
    public Barang updateBarang(Barang barangFromDTO) {
        Barang barang = getBarangById(barangFromDTO.getSku());

        if (barang != null) {
            barang.setTipeBarang(barangFromDTO.getTipeBarang());
            barang.setMerk(barangFromDTO.getMerk());
            barang.setHargaBarang(barangFromDTO.getHargaBarang());
            barangDb.save(barang);
        }

        return barang;
    }

    @Override
    public String getStringTipeBarang(int tipeBarang) {
        if (tipeBarang == 1) {
            return "Produk Elektronik";
        } else if (tipeBarang == 2) {
            return "Pakaian & Aksesoris";
        } else if (tipeBarang == 3) {
            return "Makanan & Minuman";
        } else if (tipeBarang == 4) {
            return "Kosmetik";
        } else {
            return "Perlengkapan Rumah";
        }
    }
}
