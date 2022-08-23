package my.plogging.Repository;

import my.plogging.domain.PublicTrashAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicTrashAddressRepository extends JpaRepository<PublicTrashAddress, Long> {

}
