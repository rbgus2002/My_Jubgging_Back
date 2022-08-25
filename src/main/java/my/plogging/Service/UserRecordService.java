package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.BoardSaveRequestDTO;
import my.plogging.DTO.UserRecordResponseDTO;
import my.plogging.DTO.UserRecordSaveRequestDTO;
import my.plogging.Repository.UserRecordRepository;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.Board;
import my.plogging.domain.User;
import my.plogging.domain.UserRecord;
import my.plogging.domain.UserRecordID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor
public class UserRecordService {
    private final UserRecordRepository userRecordRepository;
    private final UserRepository userRepository;

    public UserRecord UserRecordSaveDTOtoEntity(UserRecordSaveRequestDTO dto){
        // set user
        Optional<User> user = userRepository.findById(dto.getUserId());
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "userRecordID error");
        }

        // set walkingTime (String to LocalTime)
        StringTokenizer st = new StringTokenizer(dto.getWalkingTime(), ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        LocalTime localTime = LocalTime.of(hour, minute, second);

        return UserRecord.builder()
                .user(user.get())
                .walkingTime(localTime)
                .date(dto.getDate())
                .walkingNum(dto.getWalkingNum())
                .build();
    }

    public Map saveUserRecord(UserRecordSaveRequestDTO dto){
        // find original data
        UserRecordID userRecordID = new UserRecordID(dto.getUserId(), LocalDate.now());
        Optional<UserRecord> userRecord = userRecordRepository.findById(userRecordID);

        // 기존 데이터 존재 시 더해주기
        UserRecord now;
        if(userRecord.isPresent()){
            // set now UserRecord
            now = userRecord.get();

            // set walkingTime (String to LocalTime)
            StringTokenizer st = new StringTokenizer(dto.getWalkingTime(), ":");
            int hour = Integer.parseInt(st.nextToken());
            int minute = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            LocalTime nowTime = now.getWalkingTime();
            nowTime = nowTime.plusHours(hour);
            nowTime = nowTime.plusMinutes(minute);
            nowTime = nowTime.plusSeconds(second);

            // save
            userRecordRepository.save(UserRecord.builder()
                            .walkingNum(now.getWalkingNum() + dto.getWalkingNum())
                            .date(dto.getDate())
                            .user(now.getUser())
                            .walkingTime(nowTime)
                    .build());

        }else{
            now = UserRecordSaveDTOtoEntity(dto);
            userRecordRepository.save(now);
        }

        Map map = new HashMap();
        map.put("userId", now.getUser().getId());
        return map;
    }

    public UserRecordResponseDTO getUserRecord(Long userId, String date){
        // set localDate
        StringTokenizer st = new StringTokenizer(date, "-");
        LocalDate localDate = LocalDate.of(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        // set UserRecordID
        UserRecordID userRecordID = new UserRecordID(userId, localDate);

        // set UserRecord
        Optional<UserRecord> userRecord = userRecordRepository.findById(userRecordID);
        if (userRecord.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "userRecordID error");
        }

        /*
        set calorie
        10,000보에 400 칼로리
        == 1보에 0.04 칼로리
         */
        double calorie = userRecord.get().getWalkingNum() * 0.04;

        /*
        set kilometer
        14보에 0.01km
         */
        double kilometer = userRecord.get().getWalkingNum() * 0.00071429;

        // set UserRecordResponseDTO
        UserRecordResponseDTO userRecordResponseDTO = UserRecordResponseDTO.builder()
                .calorie((int)calorie)
                .walkingNum(userRecord.get().getWalkingNum())
                .walkingTime(userRecord.get().getWalkingTime())
                .kilometer(Double.parseDouble(String.format("%.3f", kilometer)))
                .build();

        return userRecordResponseDTO;
    }
}
