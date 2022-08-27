package my.plogging.Repository;

import my.plogging.DTO.TrashResponseDTO;
import my.plogging.domain.AttendingUser;
import my.plogging.domain.CustomTrashAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomTrashAddressRepository extends JpaRepository<CustomTrashAddress, Long> {
    Optional<CustomTrashAddress> findByLatitudeAndLongitude(String latitude, String longitude);

    List<CustomTrashAddress> findAllBy();
}
