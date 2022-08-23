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
    private String coordinate;
    private String spec;

    @Builder
    public PublicTrashAddress(String address, String kind, String coordinate, String spec) {
        this.address = address;
        this.kind = kind;
        this.coordinate = coordinate;
        this.spec = spec;
    }
}
