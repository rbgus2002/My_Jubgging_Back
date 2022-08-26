package my.plogging.Controller;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.*;
import my.plogging.Service.MapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map")
public class MapController {
    private final MapService mapService;

    @PostMapping("/heart")
    public Map heartPlus(@RequestBody HeartRequestDTO dto){
        return mapService.plusHeart(dto);
    }

    @PostMapping("/heart/cancel")
    public Map heartCancel(@RequestBody HeartRequestDTO dto) {
        return mapService.cancelHeart(dto);
    }
    @PostMapping("/trash")
    public Map trashRegist(@RequestBody TrashRequestDTO dto){
        return mapService.registTrash(dto);
    }

    @GetMapping("/trash/info")
    public List<TrashResponseDTO> trashPrint() {
        return mapService.printTrash();
    }

    @GetMapping("trash/user")
    public TrashUserInfoResponseDTO trashWriter(@RequestParam Long customTrashAddressId) {
        return mapService.writerTrash(customTrashAddressId);
    }
}