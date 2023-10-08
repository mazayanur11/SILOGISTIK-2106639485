package apap.ti.silogistik2106639485.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106639485.dto.request.CreatePermintaanPengirimanBarangRequestDTO;
import apap.ti.silogistik2106639485.model.PermintaanPengirimanBarang;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanBarangMapper {
    PermintaanPengirimanBarang createPermintaanPengirimanBarangRequestDTOToPermintaanPengirimanBarang(CreatePermintaanPengirimanBarangRequestDTO createPermintaanPengirimanBarangRequestDTO);
}
