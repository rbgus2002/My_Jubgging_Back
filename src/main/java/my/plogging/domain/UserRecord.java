package my.plogging.domain;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@IdClass(UserRecordID.class)
public class UserRecord {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    private LocalDate date;

    private int walkingNum;
    private int walkingTime;

}
