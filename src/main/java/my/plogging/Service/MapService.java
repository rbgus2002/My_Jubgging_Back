package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.Repository.CustomTrashAddressRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapService {
    private final CustomTrashAddressRepository customTrashAddressRepository;

}
