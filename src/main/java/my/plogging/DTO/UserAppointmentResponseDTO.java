package my.plogging.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserAppointmentResponseDTO {
    private Long boardId;
    private LocalDateTime localDateTime;
    private String place;

    private String today;


    @Builder
    public UserAppointmentResponseDTO(Long boardId, LocalDateTime localDateTime, String place, String today) {
        this.boardId = boardId;
        this.localDateTime = localDateTime;
        this.place = place;
        this.today = today;
    }
}
