package my.plogging.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserRecordID implements Serializable {

    private int userId;

    private LocalDate date;
}
