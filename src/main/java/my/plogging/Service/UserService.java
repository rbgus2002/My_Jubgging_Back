package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.UserFindAddressResponseDTO;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Map join(User user){
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userRepository.save(user).getId());
        return map;
    }

    public User findUser(Long id){
        return userRepository.findById(id).get();
    }

    public UserFindAddressResponseDTO findUserAddress(Long userId){
        User user = userRepository.findById(userId).get();

        //추후 해당 알고리즘 수정시 BoardService에 getBoard 역시 수정 필요
        String[] split = user.getAddress().split(" ");
        String address = split[2];

        return new UserFindAddressResponseDTO(address);
    }



    //나중에 다 바꿔야해 User는
}
