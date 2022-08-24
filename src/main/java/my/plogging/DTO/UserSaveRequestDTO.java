package my.plogging.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserSaveRequestDTO {
        private Long userId;
        private String name;
        private String nickName;
        private String email;
        private String profileURL;
        private String roadAddress;
        private String specificAddress;
        private String dong;
        private String gender;

        public UserSaveRequestDTO(Long userId, String name, String nickName, String email, String profileURL, String roadAddress, String specificAddress, String dong, String gender) {
                this.userId = userId;
                this.name = name;
                this.nickName = nickName;
                this.email = email;
                this.profileURL = profileURL;
                this.roadAddress = roadAddress;
                this.specificAddress = specificAddress;
                this.dong = dong;
                this.gender = gender;
        }
}
