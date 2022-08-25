package my.plogging.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class UserRecordResponseDTO {
    private int calorie;
    private int walkingNum;
    private LocalTime walkingTime;
    private double kilometer;

    @Builder
    public UserRecordResponseDTO(int calorie, int walkingNum, LocalTime walkingTime, double kilometer) {
        this.calorie = calorie;
        this.walkingNum = walkingNum;
        this.walkingTime = walkingTime;
        this.kilometer = kilometer;
    }
}
