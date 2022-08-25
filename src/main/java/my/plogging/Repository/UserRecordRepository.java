package my.plogging.Repository;

import my.plogging.domain.UserRecord;
import my.plogging.domain.UserRecordID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRecordRepository extends JpaRepository<UserRecord, UserRecordID> {
    @Override
    Optional<UserRecord> findById(UserRecordID userRecordID);
}
