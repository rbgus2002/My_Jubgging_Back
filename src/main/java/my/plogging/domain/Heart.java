package my.plogging.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Heart {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_trash_address_id")
    private CustomTrashAddress customTrashAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Heart(CustomTrashAddress customTrashAddress, User user){
        this.customTrashAddress = customTrashAddress;
        this.user = user;
    }
}
