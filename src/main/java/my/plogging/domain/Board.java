package my.plogging.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Board {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    private String place;

    private int peopleNum;

    private String possibleGender;

    private int heart;

    private LocalDate date;

    private LocalDateTime time;

    private String kakaoChatAddress;

    private int nowAttendingNum;

    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;



}
