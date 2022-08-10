package my.plogging.Service;

import my.plogging.Repository.UserRepository;
import my.plogging.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        User user = new User();
        user.setName("lee");

        //when
        Long savedId = userService.join(user);
        User findUser = userService.findOne(savedId);

        //then
        Assertions.assertThat(user).isEqualTo(findUser);
    }

}