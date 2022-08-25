package my.plogging.DTO;

import lombok.Getter;

@Getter
public class TrashResponseDTO {
    private Long id;
    private Long userId;
    private String latitude;
    private String longitude;
    private String kind;

    public TrashResponseDTO(Long id, Long userId, String latitude, String longitude, String kind) {
        this.id = id;
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kind = kind;
    }
}
