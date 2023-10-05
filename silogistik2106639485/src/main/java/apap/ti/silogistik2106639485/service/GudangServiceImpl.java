package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.repository.GudangDb;
import apap.ti.silogistik2106639485.model.Gudang;
import apap.ti.silogistik2106639485.model.GudangBarang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    GudangDb gudangDb;

    @Autowired
    GudangBarangService gudangBarangService;

    @Override
    public void saveGudang(Gudang gudang) {
        gudangDb.save(gudang);
    }

    @Override
    public List<Gudang> getAllGudang() { return gudangDb.findAll(); }

    @Override
    public Gudang getGudangById(Long kodeGudang) {
		for (Gudang gudang : getAllGudang()) {
            if (gudang.getId().equals(kodeGudang)) {
                return gudang;
            }
        }        
        return null;
	}

    @Override
    public void restockBarang(Gudang gudangFromDto) {
        Gudang gudang = getGudangById(gudangFromDto.getId());

        for (GudangBarang gudangBarangDTO : gudangFromDto.getListGudangBarang()) {
            if (gudangBarangDTO.getId() != null && gudangBarangService.getGudangBarangById(gudangBarangDTO.getId()) != null) {
                GudangBarang gudangBarang = gudangBarangService.getGudangBarangById(gudangBarangDTO.getId());
                gudangBarang.setStok(gudangBarangDTO.getStok());
                gudangBarangService.saveGudangBarang(gudangBarang);
            } else {
                gudangBarangDTO.setGudang(gudang);
                gudangBarangService.saveGudangBarang(gudangBarangDTO);
            }
        }
    }
}
