package my.plogging.Service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import my.plogging.DTO.*;
import my.plogging.Repository.AttendingUserRepository;
import my.plogging.Repository.BoardRepository;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.AttendingUser;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final AttendingUserRepository attendingUserRepository;

    public User findUser(Long id){
        return userRepository.findById(id).get();
    }

    /* address때문에 오류나서 주석처리
    public UserFindAddressResponseDTO findUserAddress(Long userId){
        User user = userRepository.findById(userId).get();

        //추후 해당 알고리즘 수정시 BoardService에 getBoard 역시 수정 필요
        String[] split = user.getAddress().split(" ");
        String address = split[2];

        return new UserFindAddressResponseDTO(address);
    }
     */
    public UserProfileResponseDTO getUserProfile(Long userId){
        User user = userRepository.findById(userId).get();

        return new UserProfileResponseDTO(user);
    }

    public User UserSaveDTOtoEntity(UserSaveRequestDTO dto) {
        return User.builder()
                .id(dto.getUserId())
                .name(dto.getName())
                .nickName(dto.getNickName())
                .gender(dto.getGender())
                .roadAddress(dto.getRoadAddress())
                .specificAddress(dto.getSpecificAddress())
                .dong(dto.getDong())
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

    public Map getAppointments(Long userId){
        List<UserAppointmentResponseDTO> list = new ArrayList();

        // userId 에러처리
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "userId error");

        // AttendingUser Table에서 query
        List<AttendingUser> attendingUserList = attendingUserRepository.findAttendingUserByUser(user.get());

        // boardId로 현재 날짜 이상이면 Response 객체를 리스트에 추가
        for(AttendingUser attendingUser : attendingUserList){
            if(LocalDate.now().isBefore(attendingUser.getBoard().getDate()) || LocalDate.now().isEqual(attendingUser.getBoard().getDate())){
                // set LocalDateTime
                LocalDateTime localDateTime;
                LocalDate tmpDate = attendingUser.getBoard().getDate();
                LocalTime tmpTime = attendingUser.getBoard().getTime();
                localDateTime = LocalDateTime.of(tmpDate,tmpTime);

                // 오늘 줍깅인 지 체크
                String today = "N";
                if(tmpDate.isEqual(LocalDate.now()))
                    today = "Y";


                //Response 객체 생성
                UserAppointmentResponseDTO dto = UserAppointmentResponseDTO.builder()
                        .boardId(attendingUser.getBoard().getId())
                        .localDateTime(localDateTime)
                        .place(attendingUser.getBoard().getPlace())
                        .today(today)
                        .build();

                //list에 append
                list.add(dto);
            }
        }

        Collections.sort(list, new Comparator<UserAppointmentResponseDTO>() {
            @Override
            public int compare(UserAppointmentResponseDTO o1, UserAppointmentResponseDTO o2) {
                return o1.getLocalDateTime().compareTo(o2.getLocalDateTime());
            }
        });

        Map map = new HashMap();
        map.put("Results", list);

        return map;
    }

    public Map addUserPoint(UserPointRequestDTO dto){
        // set user
        Optional<User> user = userRepository.findById(dto.getUserId());
        if(user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "userId error");

        // calculate point
        int point = dto.getWalkingNum() / 100;


        // user에게 지급
        user.get().setPoint(user.get().getPoint() + point);
        userRepository.save(user.get());

        Map map = new HashMap();
        map.put("nowPoint", user.get().getPoint());
        map.put("add", point);

        return map;
    }
}
