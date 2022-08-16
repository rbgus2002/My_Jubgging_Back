package my.plogging.Service;

import my.plogging.Repository.UserRecordRepository;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.User;
import my.plogging.domain.UserRecord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired UserRecordRepository userRecordRepository;

//    @Test
//    public void 회원가입() throws Exception {
//        //given
////        User user = new User();
////        user.setName("jin");
////
////        UserRecord userRecord = new UserRecord();
////        userRecord.setUser(user);
////        userRecord.setDate(LocalDate.now());
////
////        //when
////        User save = userRepository.save(user);
////        Optional<User> findUser = userRepository.findById(save.getId());
////
////        userRecordRepository.save(userRecord);
//
//
//
//        //then
//    }

}