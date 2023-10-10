package apap.ti.silogistik2106639485.dto;

import apap.ti.silogistik2106639485.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106639485.model.GudangBarang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GudangBarangMapper {
    GudangBarang createGudangBarangRequestDTOToGudangBarang(CreateGudangBarangRequestDTO createGudangBarangRequestDTO);
}