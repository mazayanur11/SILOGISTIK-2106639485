package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.model.Gudang;

import java.util.List;

public interface GudangService {
    void saveGudang(Gudang gudang);

    List<Gudang> getAllGudang();

    Gudang getGudangById(Long id);
}
