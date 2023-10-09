package apap.ti.silogistik2106639485.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106639485.model.Barang;
import apap.ti.silogistik2106639485.model.GudangBarang;
import jakarta.transaction.Transactional;

import java.util.List;


@Repository
@Transactional
public interface GudangBarangDb extends JpaRepository<GudangBarang, Long> {
    List<GudangBarang> findAllByBarang(Barang barang);
}
