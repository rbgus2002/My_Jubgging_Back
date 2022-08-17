package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.UserFindAddressResponseDTO;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Long join(User user){
        return userRepository.save(user).getId();
    }

    public User findUser(Long id){
        return userRepository.findById(id).get();
    }

    public UserFindAddressResponseDTO findUserAddress(Long userId){
        User user = userRepository.findById(userId).get();
        String[] split = user.getAddress().split(" ");
        String address = split[2];

        return new UserFindAddressResponseDTO(address);
    }



    //나중에 다 바꿔야해 User는
}
