package apap.ti.silogistik2106639485.dto;

import apap.ti.silogistik2106639485.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106639485.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106639485.model.PermintaanPengiriman;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);

    ReadPermintaanPengirimanResponseDTO permintaanPengirimanToReadPermintaanPengirimanResponseDTO(PermintaanPengiriman permintaanPengiriman);

    @AfterMapping
    default void setIsCancelAbleWaktuPermintaanTanggalPengiriman(@MappingTarget ReadPermintaanPengirimanResponseDTO readPermintaanPengirimanResponseDTO, PermintaanPengiriman permintaanPengiriman) {
        if (!LocalDateTime.now().isAfter(permintaanPengiriman.getWaktuPermintaan()) && !permintaanPengiriman.isCanceled()) {
            readPermintaanPengirimanResponseDTO.setIsCancelable(true);
        } else {
            readPermintaanPengirimanResponseDTO.setIsCancelable(false);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        readPermintaanPengirimanResponseDTO.setFormattedWaktuPermintaan(permintaanPengiriman.getWaktuPermintaan().format(dateTimeFormatter));
        readPermintaanPengirimanResponseDTO.setFormattedTanggalPengiriman(permintaanPengiriman.getTanggalPengiriman().format(dateFormatter));
    }
}