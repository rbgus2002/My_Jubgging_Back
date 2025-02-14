package my.plogging.Controller;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.*;
import my.plogging.Service.MapService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.OncePerRequestFilter;

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

    @PostMapping("/heart/check")
    public Map heartCheck(@RequestBody HeartRequestDTO dto) { return mapService.checkHeart(dto); }

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

    @GetMapping("/trash/publicInfo")
    public Map publicTrashPrints(@RequestParam String latitude, @RequestParam String longitude, @RequestParam String findMeter){
        return mapService.publicTrashPrints(latitude, longitude, findMeter);
    }
}