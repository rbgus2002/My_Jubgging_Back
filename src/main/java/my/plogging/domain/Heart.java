package my.plogging.domain;

import javax.persistence.*;

@Entity
public class Heart {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_trash_address_id")
    private CustomTrashAddress customTrashAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
