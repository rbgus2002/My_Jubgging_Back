package my.plogging.Repository;

import my.plogging.domain.CustomTrashAddress;
import my.plogging.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByCustomTrashAddressIdAndUserId(Long custom_trash_address_id, Long userId);
}
