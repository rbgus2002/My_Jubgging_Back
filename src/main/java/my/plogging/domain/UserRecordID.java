package my.plogging.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserRecordID implements Serializable {

    private Long user;
    private LocalDate date;

    public UserRecordID(Long user, LocalDate date) {
        this.user = user;
        this.date = date;
    }
}
