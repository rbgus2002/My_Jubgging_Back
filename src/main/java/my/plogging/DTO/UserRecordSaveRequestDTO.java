package my.plogging.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRecordSaveRequestDTO {
    private Long userId;
    private LocalDate date;
    private Integer walkingNum;
    private String walkingTime;

    @Builder
    public UserRecordSaveRequestDTO(Long userId, Integer walkingNum, String walkingTime) {
        this.userId = userId;
        this.date = LocalDate.now();
        this.walkingNum = walkingNum;
        this.walkingTime = walkingTime;
    }
}
