package my.plogging.DTO;

import lombok.Builder;
import lombok.Data;
import my.plogging.domain.User;

@Data
public class UserRankResponseDTO {
    private Integer rank;
    private String userName;
    private String userNickName;
    private int walkingNum;
    private String profileURL;
    private Long userId;

    @Builder
    public UserRankResponseDTO(Integer rank, User user, int walkingNum) {
        this.rank = rank;
        this.userName = user.getName();
        this.userNickName = user.getNickName();
        this.profileURL = user.getProfileURL();
        this.walkingNum = walkingNum;
        this.userId = user.getId();
    }
}
