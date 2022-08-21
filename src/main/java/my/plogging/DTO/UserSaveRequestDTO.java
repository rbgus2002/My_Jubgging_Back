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
        private String address;
        private String gender;

        public UserSaveRequestDTO(Long userId, String name, String nickName, String email, String profileURL, String address, String gender) {
                this.userId = userId;
                this.name = name;
                this.nickName = nickName;
                this.email = email;
                this.profileURL = profileURL;
                this.address = address;
                this.gender = gender;
        }
}
