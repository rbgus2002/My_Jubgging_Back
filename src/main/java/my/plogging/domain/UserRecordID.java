package my.plogging.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserRecordID implements Serializable {

    private Long user;

    private LocalDate date;
}
