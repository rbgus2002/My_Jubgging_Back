package my.plogging.Repository;

import my.plogging.domain.UserRecord;
import my.plogging.domain.UserRecordID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRecordRepository extends JpaRepository<UserRecord, UserRecordID> {
    @Override
    Optional<UserRecord> findById(UserRecordID userRecordID);

    List<UserRecord> findUserRecordByDateContains(LocalDate localDate);
}
