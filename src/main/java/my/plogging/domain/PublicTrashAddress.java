package my.plogging.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class PublicTrashAddress {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String address;
    private String kind;
    private String longitude;
    private String latitude;
    private String spec;

    @Builder
    public PublicTrashAddress(String address, String kind, String longitude, String latitude, String spec) {
        this.address = address;
        this.kind = kind;
        this.longitude = longitude;
        this.latitude = latitude;
        this.spec = spec;
    }
}
