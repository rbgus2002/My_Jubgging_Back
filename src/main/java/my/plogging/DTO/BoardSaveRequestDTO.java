package my.plogging.DTO;

import lombok.Builder;
import lombok.Getter;
import my.plogging.domain.Board;

@Getter
public class BoardSaveRequestDTO {
    private Long userId;
    private String region1;
    private String region2;
    private String region3;
    private String title;
    private String content;
    private int peopleNum;
    private String possibleGender;
    private String localDate;
    private String localTime;
    private String kakaoChatAddress;
    private String place;

    @Builder
    public BoardSaveRequestDTO(Long userId, String region1, String region2, String region3, String title, String content, int peopleNum, String possibleGender, String localDate, String localTime, String kakaoChatAddress, String place) {
        this.userId = userId;
        this.region1 = region1;
        this.region2 = region2;
        this.region3 = region3;
        this.title = title;
        this.content = content;
        this.peopleNum = peopleNum;
        this.possibleGender = possibleGender;
        this.localDate = localDate;
        this.localTime = localTime;
        this.kakaoChatAddress = kakaoChatAddress;
        this.place = place;
    }
}
