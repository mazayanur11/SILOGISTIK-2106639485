package apap.ti.silogistik2106639485.repository;

import apap.ti.silogistik2106639485.model.PermintaanPengiriman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PermintaanPengirimanDb extends JpaRepository<PermintaanPengiriman, Long> {
    List<PermintaanPengiriman> findAllByOrderByWaktuPermintaanDesc();
    
    List<PermintaanPengiriman> findByWaktuPermintaanBetweenAndIdInOrderByWaktuPermintaanDesc(LocalDateTime start, LocalDateTime end, List<Long> id);
}