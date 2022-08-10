package my.plogging.domain;

import javax.persistence.*;

@Entity
public class CustomTrashAddress {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String address;
}
