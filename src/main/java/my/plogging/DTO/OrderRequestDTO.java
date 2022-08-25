package my.plogging.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderRequestDTO {
    private Long userId;
    private Long itemId;
    private int count;
}
