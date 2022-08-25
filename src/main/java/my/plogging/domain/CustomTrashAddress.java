package my.plogging.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CustomTrashAddress {
    @Id @GeneratedValue @Column(name = "custom_trash_address_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String latitude;
    private String longitude;
    private String kind;

    @Builder
    public CustomTrashAddress(User user, String latitude, String longitude, String kind) {
        this.user = user;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kind = kind;
    }
}
