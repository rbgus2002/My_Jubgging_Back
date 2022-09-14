package my.plogging.Repository;

import my.plogging.domain.AttendingUser;
import my.plogging.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendingUserRepository extends JpaRepository<AttendingUser, Long> {
    List<AttendingUser> findAttendingUserByBoardIdAndIsUsed(Long boardId, String isUsed);

    List<AttendingUser> findAttendingUserByUserAndIsUsed(User user, String isUsed);
}
