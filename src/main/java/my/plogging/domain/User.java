package my.plogging.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String nickName;

    private String profileURL;

    private int point;

    private String gender;

    private String address;

    private int addPlaceNum;

    private int heart;

    @Builder
    public User(Long id, String name, String nickName, String gender, String address){
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.profileURL = null;
        this.point = 0;
        this.gender = gender;
        this.address = address;
        this.addPlaceNum = 0;
        this.heart = 0;
    }

}
