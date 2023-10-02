package apap.ti.silogistik2106639485.dto.response;

import java.util.List;

import apap.ti.silogistik2106639485.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {
    private String sku;
    private int tipeBarang;
    private String merk;
    private Long hargaBarang;
    private List<GudangBarang> listGudangBarang;
}
