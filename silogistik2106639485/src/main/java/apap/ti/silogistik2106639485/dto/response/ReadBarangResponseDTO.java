package apap.ti.silogistik2106639485.dto.response;

import apap.ti.silogistik2106639485.model.GudangBarang;
import apap.ti.silogistik2106639485.model.PermintaanPengiriman;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {
    private String sku;
    private int tipeBarang;
    private String merk;
    private Long hargaBarang;
    private List<GudangBarang> listGudangBarang;
    private List<PermintaanPengiriman> listPermintaanPengiriman;
}