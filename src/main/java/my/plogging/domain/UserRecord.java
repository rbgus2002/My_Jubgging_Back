package my.plogging.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.convert.Jsr310Converters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.StringTokenizer;

@Entity
@IdClass(UserRecordID.class)
@Getter @Setter
@NoArgsConstructor
public class UserRecord implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    private LocalDate date;

    private int walkingNum;
    private LocalTime walkingTime;

    @Builder
    public UserRecord(User user, LocalDate date, int walkingNum, LocalTime walkingTime) {
        this.user = user;
        this.date = date;
        this.walkingNum = walkingNum;
        this.walkingTime = walkingTime;
    }
}
