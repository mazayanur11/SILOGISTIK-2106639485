package apap.ti.silogistik2106639485.repository;

import apap.ti.silogistik2106639485.model.Gudang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface GudangDb extends JpaRepository<Gudang, BigInteger> {
    
}
