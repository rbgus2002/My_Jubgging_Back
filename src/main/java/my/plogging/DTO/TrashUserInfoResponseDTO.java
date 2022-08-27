package my.plogging.DTO;

import lombok.Builder;
import lombok.Getter;
import my.plogging.domain.User;

@Getter
public class TrashUserInfoResponseDTO {
    private Long userId;
    private String profileURL;
    private String nickName;
    private int heart;
    private int addPlaceNum;

    @Builder
    public TrashUserInfoResponseDTO(User user) {
        this.userId = user.getId();
        this.profileURL = user.getProfileURL();
        this.nickName = user.getNickName();
        this.heart = user.getHeart();
        this.addPlaceNum = user.getAddPlaceNum();
    }

}
