package my.plogging.DTO;

import lombok.Builder;
import my.plogging.domain.Board;
import my.plogging.domain.BoardRegion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class BoardListsResponseDTO {
    private Long boardId;
    private int peopleNum;
    private int nowAttendingNum;
    private String title;
    private String content;
    private String region1;
    private String region2;
    private String region3;
    private LocalDateTime modifiedTime;
    private LocalDate date;

    @Builder
    public BoardListsResponseDTO(Board board, BoardRegion boardRegion) {
        this.boardId = board.getId();
        this.peopleNum = board.getPeopleNum();
        this.nowAttendingNum = board.getNowAttendingNum();
        this.title = board.getTitle();
        this.content = board.getTitle();
        this.modifiedTime = board.getModifiedTime();
        this.date = board.getDate();
        this.region1 = boardRegion.getRegion1();
        this.region2 = boardRegion.getRegion2();
        this.region3 = boardRegion.getRegion3();
    }

    public Map<String, Object> boardResponseDTOToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("boardId", this.boardId);
        map.put("peopleNum", this.peopleNum);
        map.put("nowAttendingNum", this.nowAttendingNum);
        map.put("title", this.title);
        map.put("content", this.content);
        map.put("region1", this.region1);
        map.put("region2", this.region2);
        map.put("region3", this.region3);
        map.put("modifiedTime", this.modifiedTime);
        map.put("date", this.date);

        return map;
    }
}
