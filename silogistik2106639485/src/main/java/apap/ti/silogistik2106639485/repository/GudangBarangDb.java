package apap.ti.silogistik2106639485.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106639485.model.GudangBarang;

@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, Long> {
    
}
