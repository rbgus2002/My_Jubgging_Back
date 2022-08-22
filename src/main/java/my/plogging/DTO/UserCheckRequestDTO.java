package my.plogging.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserCheckRequestDTO {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
