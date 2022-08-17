package my.plogging.DTO;

import lombok.Builder;
import my.plogging.domain.Board;
import my.plogging.domain.BoardRegion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class BoardResponseDTO {
    private int peopleNum;
    private int nowAttendingNum;
    private String title;
    private String content;
    private String region1;
    private String region2;
    private String region3;
    private LocalDateTime modifiedTime;

    public BoardResponseDTO(Board board, BoardRegion boardRegion) {
        this.peopleNum = board.getPeopleNum();
        this.nowAttendingNum = board.getNowAttendingNum();
        this.title = board.getTitle();
        this.content = board.getTitle();
        this.modifiedTime = board.getModifiedTime();
        this.region1 = boardRegion.getRegion1();
        this.region2 = boardRegion.getRegion2();
        this.region3 = boardRegion.getRegion3();
    }

}
