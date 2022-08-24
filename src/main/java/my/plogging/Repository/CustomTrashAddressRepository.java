package my.plogging.Repository;

import my.plogging.domain.CustomTrashAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomTrashAddressRepository extends JpaRepository<CustomTrashAddress, Long> {

}
