package apap.ti.silogistik2106639485.dto;

import apap.ti.silogistik2106639485.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106639485.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106639485.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106639485.model.Barang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);

    ReadBarangResponseDTO barangToReadBarangResponseDTO(Barang barang);

    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);

    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);
}
