package apap.ti.silogistik2106639485.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106639485.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106639485.model.GudangBarang;

@Mapper(componentModel = "spring")
public interface GudangBarangMapper {
    GudangBarang createGudangBarangRequestDTOToGudangBarang(CreateGudangBarangRequestDTO createGudangBarangRequestDTO);
}
