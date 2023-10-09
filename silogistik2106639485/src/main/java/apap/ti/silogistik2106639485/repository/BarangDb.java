package apap.ti.silogistik2106639485.repository;

import apap.ti.silogistik2106639485.model.Barang;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangDb extends JpaRepository<Barang, String> {
    long countByTipeBarang(int tipeBarang);

    List<Barang> getAllByOrderByMerkAsc();
}
