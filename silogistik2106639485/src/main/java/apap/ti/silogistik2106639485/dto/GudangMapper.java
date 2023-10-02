package apap.ti.silogistik2106639485.dto;

import apap.ti.silogistik2106639485.model.Gudang;
import apap.ti.silogistik2106639485.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106639485.dto.response.ReadGudangResponseDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);

    ReadGudangResponseDTO gudangToReadGudangResponseDTO(Gudang gudang);
}
