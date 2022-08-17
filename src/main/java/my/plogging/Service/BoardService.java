package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.BoardSaveRequestDTO;
import my.plogging.Repository.BoardRegionRepository;
import my.plogging.Repository.BoardRepository;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.Board;
import my.plogging.domain.BoardRegion;
import my.plogging.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardRegionRepository boardRegionRepository;

    public Board BoardSaveDTOtoEntity(BoardSaveRequestDTO dto) {
        // set user
        User user = userRepository.findById(dto.getUserId()).get();

        // set date
        LocalDate localDate;
        StringTokenizer st = new StringTokenizer(dto.getLocalDate());
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        localDate = LocalDate.of(year, month, day);

        //set time
        LocalTime localTime;
        st = new StringTokenizer(dto.getLocalTime());
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        localTime = LocalTime.of(hour, minute);


        return Board.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .place(dto.getPlace())
                .peopleNum(dto.getPeopleNum())
                .possibleGender(dto.getPossibleGender())
                .date(localDate)
                .time(localTime)
                .kakaoChatAddress(dto.getKakaoChatAddress())
                .build();
    }


    public Long saveBoard(BoardSaveRequestDTO dto) {
        // userid 존재하는 지 체크 (exception handling)


        // 게시물 저장
        Board board = BoardSaveDTOtoEntity(dto);
        boardRepository.save(board);

        //Region 저장
        BoardRegion boardRegion = BoardRegion.builder()
                .board(board)
                .region1(dto.getRegion1())
                .region2(dto.getRegion2())
                .region3(dto.getRegion3())
                .build();
        boardRegionRepository.save(boardRegion);

        return board.getId();
    }

    public List getBoardLists(String targetRegion) {
        // scan Board table
        List<Board> list = boardRepository.findAll();

        // board_id 통해 BoardRegion에서 get item
        // get 해온 item에서 loop돌며 targetRegion이랑 같은 거 있나 찾기

        List<Board> targetList = new ArrayList<>();
        for (Board board : list) {
            BoardRegion boardRegion = boardRegionRepository.findByBoardId(board.getId());
            // 같은 거 있으면 list에 append
            if(boardRegion.getRegion1().equals(targetRegion) || boardRegion.getRegion2().equals(targetRegion) || boardRegion.getRegion3().equals(targetRegion)){
                targetList.add(board);
            }
        }

        // Result Map에 list 넣고 리턴
//        Map<String, Object> answer = new HashMap<>();
//        answer.put("Results", targetList);
//        System.out.println(answer.get("Results").toString());
//        return answer;
        return targetList;
    }
}
