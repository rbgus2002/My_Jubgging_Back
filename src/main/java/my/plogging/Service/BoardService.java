package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.BoardListsResponseDTO;
import my.plogging.DTO.BoardResponseDTO;
import my.plogging.DTO.BoardSaveRequestDTO;
import my.plogging.Repository.BoardRegionRepository;
import my.plogging.Repository.BoardRepository;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.Board;
import my.plogging.domain.BoardRegion;
import my.plogging.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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


    public Map saveBoard(BoardSaveRequestDTO dto) {
        // userid 존재하는 지 체크 (exception handling)
        Optional<User> user = userRepository.findById(dto.getUserId());
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "userId error");
        }

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

        Map<String, Object> map = new HashMap<>();
        map.put("boardId", board.getId());

        return map;
    }

    public Map getBoardLists(String targetRegion) {
        // scan Board table
        List<Board> list = boardRepository.findAll();

        // board_id 통해 BoardRegion에서 get item
        // get 해온 item에서 loop돌며 targetRegion이랑 같은 거 있나 찾기
        List<Map> targetList = new ArrayList<>();
        for (Board board : list) {
            BoardRegion boardRegion = boardRegionRepository.findByBoardId(board.getId());
            // 같은 거 있으면 list에 append
            if(boardRegion.getRegion1().equals(targetRegion) || boardRegion.getRegion2().equals(targetRegion) || boardRegion.getRegion3().equals(targetRegion)){
                // 필요한 값 추가
                BoardListsResponseDTO dto = BoardListsResponseDTO.builder()
                        .board(board)
                        .boardRegion(boardRegion)
                        .build();
                targetList.add(dto.boardResponseDTOToMap());
            }
        }

        // Result Map에 list 넣고 리턴
        Map<String, Object> answer = new HashMap<>();
        answer.put("Results", targetList);

        return answer;
    }

    public BoardResponseDTO getBoard(Long boardId){
        Optional<Board> board = boardRepository.findById(boardId);

        // 예외처리
        if(board.isEmpty()){
            System.out.println("boardId error");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "boardId error");
        }


        //User 객체 생성
        User user = board.get().getUser();

        // board를 BoardResponseDTO
        BoardResponseDTO dto = BoardResponseDTO.builder()
                .board(board.get())
                .user(user)
                .build();

        // address 수정 (면 혹은 동 출력)
        String[] split = dto.getAddress().split(" ");
        String address = split[2];
        dto.setAddress(address);

        // dto 리턴
        return dto;
    }

    public Map deleteBoard(Long boardId){ // 미완성
        Optional<Board> board = boardRepository.findById(boardId);
        if (board.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "boardId error");

        User tmpUser = board.get().getUser();

//        // 잠시 tmpUser 삭제
//        userRepository.delete(tmpUser);
//
//        // 해당 board 삭제하기
//        boardRepository.delete(board.get());
//
//        // 다시 tmpUser 넣기
//        userRepository.save(tmpUser);

        Map<String, Object> map = new HashMap<>();
        map.put("boardId", board.get().getId());
        return map;
    }
}
