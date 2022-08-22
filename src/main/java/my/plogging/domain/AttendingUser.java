package my.plogging.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class AttendingUser {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String isUsed;


    @Builder
    public AttendingUser(User user, Board board, String isUsed) {
        this.user = user;
        this.board = board;
        this.isUsed = isUsed;
    }

    public void changeIsUsedToN(){
        this.isUsed = "N";
    }
}
