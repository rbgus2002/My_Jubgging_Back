package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public long join(User user){
        return userRepository.save(user).getId();
    }

    public User findUser(Long id){
        return userRepository.findById(id).get();
    }

    //나중에 다 바꿔야해 User는
}
