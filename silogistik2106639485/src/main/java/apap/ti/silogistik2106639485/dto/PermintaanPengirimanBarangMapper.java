package apap.ti.silogistik2106639485.dto;

import apap.ti.silogistik2106639485.dto.request.CreatePermintaanPengirimanBarangRequestDTO;
import apap.ti.silogistik2106639485.model.PermintaanPengirimanBarang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanBarangMapper {
    PermintaanPengirimanBarang createPermintaanPengirimanBarangRequestDTOToPermintaanPengirimanBarang(CreatePermintaanPengirimanBarangRequestDTO createPermintaanPengirimanBarangRequestDTO);
}