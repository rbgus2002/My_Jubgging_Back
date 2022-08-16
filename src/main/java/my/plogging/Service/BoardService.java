package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.BoardSaveRequestDTO;
import my.plogging.Repository.BoardRepository;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.Board;
import my.plogging.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.StringTokenizer;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public Board toEntity(BoardSaveRequestDTO dto){
        // set user
        User user = userRepository.findById(dto.getUserId()).get();
        System.out.println(user.getName());

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

    public long boardSave(BoardSaveRequestDTO dto){
        //userid 존재하는 지 체크 (exception handling)

        Board board = toEntity(dto);
        boardRepository.save(board);
        return board.getId();
    }
}
