package apap.ti.silogistik2106639485.dto.request;

import apap.ti.silogistik2106639485.model.Barang;
import apap.ti.silogistik2106639485.model.PermintaanPengiriman;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanBarangRequestDTO {
    private PermintaanPengiriman permintaanPengiriman;
    private Barang barang;
    private int kuantitasPengiriman;
}