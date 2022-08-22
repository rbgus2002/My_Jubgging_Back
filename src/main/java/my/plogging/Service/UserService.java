package my.plogging.Service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import my.plogging.DTO.BoardSaveRequestDTO;
import my.plogging.DTO.UserCheckRequestDTO;
import my.plogging.DTO.UserFindAddressResponseDTO;
import my.plogging.DTO.UserSaveRequestDTO;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.Board;
import my.plogging.domain.BoardRegion;
import my.plogging.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

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

    public User UserSaveDTOtoEntity(UserSaveRequestDTO dto) {
        return User.builder()
                .id(dto.getUserId())
                .name(dto.getName())
                .nickName(dto.getNickName())
                .gender(dto.getGender())
                .address(dto.getAddress())
                .email(dto.getEmail())
                .profileURL(dto.getProfileURL())
                .build();
    }

    public Map saveUser(UserSaveRequestDTO dto) {
        // 게시물 저장
        User user = UserSaveDTOtoEntity(dto);
        userRepository.save(user);

        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        return map;
    }

    public Map checkUser(Long userId) {
        Map<String, Object> map = new HashMap<>();

        // 유저 존재 여부 조회
        Optional<User> user = userRepository.findById(userId);
        // 존재하는 경우
        if(!user.isEmpty())
            map.put("user", "Y");
        // 존재하지 않는 경우
        else
            map.put("user", "N");

        return map;
    }

    public Map checkNickName(String nickName) {
        Map<String, Object> map = new HashMap<>();

        // 유저 존재 여부 조회
        Optional<User> user = userRepository.findBynickName(nickName);
        // 존재하는 경우
        if(!user.isEmpty())
            map.put("nickName", "Y");
        // 존재하지 않는 경우
        else
            map.put("nickName", "N");

        return map;
    }
    //나중에 다 바꿔야해 User는
}
