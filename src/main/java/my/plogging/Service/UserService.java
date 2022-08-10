package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.plogging.DTO.UserDTO;
import my.plogging.Entity.User;
import my.plogging.Repository.UserRepository;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public String signup(UserDTO request){
        User user = new User();
        user.setUsername(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userRepository.save(user);

        return "Success";
    }
}
