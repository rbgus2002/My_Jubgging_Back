package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.BoardSaveRequestDTO;
import my.plogging.DTO.UserRankResponseDTO;
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
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserRecordService {
    private final UserRecordRepository userRecordRepository;
    private final UserRepository userRepository;

    public UserRecord UserRecordSaveDTOtoEntity(UserRecordSaveRequestDTO dto) {
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

    public Map saveUserRecord(UserRecordSaveRequestDTO dto) {
        // find original data
        UserRecordID userRecordID = new UserRecordID(dto.getUserId(), LocalDate.now());
        Optional<UserRecord> userRecord = userRecordRepository.findById(userRecordID);

        // 기존 데이터 존재 시 더해주기
        UserRecord now;
        if (userRecord.isPresent()) {
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

        } else {
            now = UserRecordSaveDTOtoEntity(dto);
            userRecordRepository.save(now);
        }

        Map map = new HashMap();
        map.put("userId", now.getUser().getId());
        return map;
    }

    public UserRecordResponseDTO getUserRecord(Long userId, String date) {
        // set localDate
        StringTokenizer st = new StringTokenizer(date, "-");
        LocalDate localDate = LocalDate.of(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        // set UserRecordID
        UserRecordID userRecordID = new UserRecordID(userId, localDate);

        // set UserRecord
        UserRecordResponseDTO userRecordResponseDTO;
        Optional<UserRecord> userRecord = userRecordRepository.findById(userRecordID);
        if (userRecord.isEmpty()) {
            // set UserRecordResponseDTO
            userRecordResponseDTO = UserRecordResponseDTO.builder()
                    .calorie(0)
                    .walkingNum(0)
                    .walkingTime(LocalTime.of(0, 0))
                    .kilometer(0)
                    .build();
        } else {
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
            userRecordResponseDTO = UserRecordResponseDTO.builder()
                    .calorie((int) calorie)
                    .walkingNum(userRecord.get().getWalkingNum())
                    .walkingTime(userRecord.get().getWalkingTime())
                    .kilometer(Double.parseDouble(String.format("%.3f", kilometer)))
                    .build();


        }
        return userRecordResponseDTO;
    }

    /*
    랭킹 가져오기 ( 1 ~ 50)
     */
    //List<Board> list = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "modifiedTime"));
    public Map getUserRank() {
        List<UserRankResponseDTO> list = new ArrayList<>();
        Map<User, Integer> recordMap = new HashMap<>();

        // 모든 유저 가져오기
        List<User> userList = userRepository.findAll();

        // 모든 유저 loop
        for (User user : userList) {
            // [UserRecord Table] 유저 아이디로 query
            List<UserRecord> userRecordList = userRecordRepository.findUserRecordByUser(user);

            // 모든 기록에 대해서 walkingNum 총합 구하기
            int allWalkingNum = 0;
            for (UserRecord userRecord : userRecordList) {
                allWalkingNum += userRecord.getWalkingNum();
            }

            // Map에 기록 저장
            recordMap.put(user, allWalkingNum);
        }

        // Map에서 value를 기준으로 내림차순 Sorting
        Map<User, Integer> sortedRecordMap = sortByValue(recordMap);

        // 1 ~ 50위 까지만 list에 append
        int i = 0;
        for (User user : sortedRecordMap.keySet()) {
            // 50위까지만 return 해주기 위한 조건문
            if (i++ == 50)
                break;

            // dto 생성
            UserRankResponseDTO dto = UserRankResponseDTO.builder()
                    .rank(i)
                    .user(user)
                    .walkingNum(sortedRecordMap.get(user))
                    .build();

            // list에 dto add
            list.add(dto);
        }

        // list to map
        Map finalResult = new HashMap();
        finalResult.put("Results", list);

        return finalResult;
    }

    // Map을 Value 기준으로 내림차순 정렬해주는 메소드
    public HashMap<User, Integer> sortByValue(Map<User, Integer> hm) {
        List<Map.Entry<User, Integer>> list =
                new LinkedList<Map.Entry<User, Integer>>(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<User, Integer>>() {
            @Override
            public int compare(Map.Entry<User, Integer> o1, Map.Entry<User, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        HashMap<User, Integer> answer = new LinkedHashMap<User, Integer>();
        for (Map.Entry<User, Integer> aa : list) {
            answer.put(aa.getKey(), aa.getValue());
        }

        return answer;
    }
}
