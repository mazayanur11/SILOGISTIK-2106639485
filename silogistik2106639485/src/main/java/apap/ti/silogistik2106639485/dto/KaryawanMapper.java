package apap.ti.silogistik2106639485.dto;

import apap.ti.silogistik2106639485.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106639485.model.Karyawan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);
}