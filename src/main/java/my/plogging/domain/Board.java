package my.plogging.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class Board {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attending_user_table_id")
    private AttendingUser attendingUser;

    private String title;

    private String content;

    private String place;

    private int peopleNum;

    private String possibleGender;

    private LocalDate date;

    private LocalTime time;

    private String kakaoChatAddress;

    private int nowAttendingNum;

    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private char isUsed;

    private String isUsed;

    @Builder
    public Board(User user, String title, String content, String place, int peopleNum, String possibleGender, LocalDate date, LocalTime time, String kakaoChatAddress){
        this.user = user;
        this.title = title;
        this.content = content;
        this.place = place;
        this.peopleNum = peopleNum;
        this.possibleGender = possibleGender;
        this.date = date;
        this.time = time;
        this.kakaoChatAddress = kakaoChatAddress;
        this.nowAttendingNum = 1;
        this.createdTime = LocalDateTime.now();
        this.modifiedTime = LocalDateTime.now();
        this.isUsed = 'Y';
    }

    public void updateModifiedTime(){
        this.modifiedTime = LocalDateTime.now();
    }

    public void updateNowAttendingNum(){
        this.nowAttendingNum++;
    }
}
