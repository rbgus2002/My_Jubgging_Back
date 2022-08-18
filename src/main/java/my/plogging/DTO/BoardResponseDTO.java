package my.plogging.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import my.plogging.domain.Board;
import my.plogging.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class BoardResponseDTO {
    private String nickName;
    private String address;
    private LocalDateTime modifiedTime;
    private int peopleNum;
    private int nowAttendingNum;
    private LocalDate date;
    private String title;
    private String content;
    private String possibleGender;
    private String place;

    /*
     ************** address는 유저 동/면 조회 메소드 사용해서 서비스에서 따로 setting **************
     */

    @Builder
    public BoardResponseDTO(Board board, User user) {
        this.address = user.getAddress();
        this.nickName = user.getNickName();
        this.modifiedTime = board.getModifiedTime();
        this.peopleNum = board.getPeopleNum();
        this.nowAttendingNum = board.getNowAttendingNum();
        this.date = board.getDate();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.possibleGender = board.getPossibleGender();
        this.place = board.getPlace();
    }
}




/*
{
	"nickName":"규규",
	"address":"도장리",
	"modifiedTime":"2022-08-17 15:28:02.24721",
	"peopleNum":4,
	"nowAttendingNum":2,
	"date":"2022-08-18",
	"title":"같이 플로깅 하실 20대 구합니다."
	"content":"숭실대 한바퀴 돌면서 재밌게 플로깅 하실 분 구합니다."
	"possibleGender":"Male",    //Male or Female or All
	"place":"숭실대학교 정보과학관 앞"
}
 */
