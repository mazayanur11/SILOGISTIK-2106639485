package apap.ti.silogistik2106639485.service;

import apap.ti.silogistik2106639485.model.Karyawan;

import java.util.List;

public interface KaryawanService {
    void saveKaryawan(Karyawan karyawan);
    
    List<Karyawan> getAllKaryawan();
}