package my.plogging.DTO;

public class PublicTrashResponseDTO {
    private String address;
    private String kind;
    private String longitude;
    private String latitude;
    private String spec;


    public PublicTrashResponseDTO(String address, String kind, String longitude, String latitude, String spec) {
        this.address = address;
        this.kind = kind;
        this.longitude = longitude;
        this.latitude = latitude;
        this.spec = spec;
    }
}
