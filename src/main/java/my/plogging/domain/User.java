package my.plogging.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private int point;

    private String gender;

    private String address;

    private int addPlaceNum;

    private int heart;

}
