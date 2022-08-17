package my.plogging.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@IdClass(UserRecordID.class)
@Getter @Setter
public class UserRecord {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    private LocalDate date;

    private int walkingNum;
    private int walkingTime;
}
