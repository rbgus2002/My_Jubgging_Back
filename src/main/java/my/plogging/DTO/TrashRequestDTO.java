package my.plogging.DTO;

import lombok.Getter;

@Getter
public class TrashRequestDTO {
    private Long userId;
    private String latitude;
    private String longitude;
    private String kind;

    public TrashRequestDTO(Long userId, String latitude, String longitude, String kind) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kind = kind;
    }
}
