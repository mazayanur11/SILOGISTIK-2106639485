package apap.ti.silogistik2106639485.repository;

import apap.ti.silogistik2106639485.model.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarangDb extends JpaRepository<Barang, String> {
    long countByTipeBarang(int tipeBarang);

    List<Barang> getAllByOrderByMerkAsc();
}