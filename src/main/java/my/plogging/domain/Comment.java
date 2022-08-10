package my.plogging.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String content;

    private LocalDateTime createdTime;
}
