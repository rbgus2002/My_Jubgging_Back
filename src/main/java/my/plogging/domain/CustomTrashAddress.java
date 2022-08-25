package my.plogging.domain;

import javax.persistence.*;

@Entity
public class CustomTrashAddress {
    @Id @GeneratedValue @Column(name = "custom_trash_address_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String latitude;
    private String longitude;
}
