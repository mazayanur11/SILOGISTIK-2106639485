package apap.ti.silogistik2106639485.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.dao.DataIntegrityViolationException;

import apap.ti.silogistik2106639485.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106639485.dto.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106639485.model.PermintaanPengiriman;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);

    ReadPermintaanPengirimanResponseDTO permintaanPengirimanToReadPermintaanPengirimanResponseDTO(PermintaanPengiriman permintaanPengiriman);

    @AfterMapping
    default void setIsCancelAble(@MappingTarget ReadPermintaanPengirimanResponseDTO readPermintaanPengirimanResponseDTO, PermintaanPengiriman permintaanPengiriman) {
        LocalDateTime dateTime24Hours = LocalDateTime.now().plus(24, ChronoUnit.HOURS);

        if (!permintaanPengiriman.getWaktuPermintaan().isAfter(dateTime24Hours) && (!permintaanPengiriman.getWaktuPermintaan().isBefore(LocalDateTime.now())) && !permintaanPengiriman.isCanceled()) {
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
