package apap.ti.silogistik2106639485.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106639485.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106639485.model.Karyawan;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);
}
