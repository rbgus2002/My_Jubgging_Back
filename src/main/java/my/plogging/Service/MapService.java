package my.plogging.Service;

import lombok.RequiredArgsConstructor;
import my.plogging.DTO.*;
import my.plogging.Repository.CustomTrashAddressRepository;
import my.plogging.Repository.HeartRepository;
import my.plogging.Repository.PublicTrashAddressRepository;
import my.plogging.Repository.UserRepository;
import my.plogging.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MapService {
    private final CustomTrashAddressRepository customTrashAddressRepository;
    private final UserRepository userRepository;
    private final HeartRepository heartRepository;
    private final PublicTrashAddressRepository publicTrashAddressRepository;

    public Map plusHeart(HeartRequestDTO dto) {
        Map<String, Object> map = new HashMap<>();
        Optional<CustomTrashAddress> customTrashAddressOptional = customTrashAddressRepository.findById(dto.getCustomTrashAddressId());
        // 잘못된 정보라면
        if(customTrashAddressOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
        // 올바른 정보라면
        else {
            CustomTrashAddress customTrashAddress = customTrashAddressOptional.get();
            Optional<Heart> heartOptional = heartRepository.findByCustomTrashAddressIdAndUserId(dto.getCustomTrashAddressId(), dto.getUserId());
            // 좋아요 증가
            if(heartOptional.isEmpty()) {
                Optional<User> userOptional = userRepository.findById(dto.getUserId());
                User user = userOptional.get();
                Heart heart = Heart.builder()
                        .customTrashAddress(customTrashAddress)
                        .user(user)
                        .build();

                heartRepository.save(heart);
                Optional<User> writerOptional = userRepository.findById(customTrashAddress.getUser().getId());
                User writer = writerOptional.get();
                writer.setHeart(writer.getHeart()+1);
                userRepository.save(writer);
                map.put("heart", writer.getHeart());
            }
            // 좋아요 중복 체크
            else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Overlap");
        }
        return map;
    }

    public Map cancelHeart(HeartRequestDTO dto) {
        Map<String, Object> map = new HashMap<>();
        Optional<CustomTrashAddress> customTrashAddressOptional = customTrashAddressRepository.findById(dto.getCustomTrashAddressId());
        // 잘못된 정보라면
        if(customTrashAddressOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
            // 올바른 정보라면
        else {
            CustomTrashAddress customTrashAddress = customTrashAddressOptional.get();
            Optional<Heart> heartOptional = heartRepository.findByCustomTrashAddressIdAndUserId(dto.getCustomTrashAddressId(), dto.getUserId());
            // 좋아요를 누르지 않은 경우
            if (heartOptional.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
            // 좋아요 취소
            else {
                Optional<User> userOptional = userRepository.findById(dto.getUserId());
                User user = userOptional.get();
                Heart heart = heartOptional.get();
                heartRepository.delete(heart);
                Optional<User> writerOptional = userRepository.findById(customTrashAddress.getUser().getId());
                User writer = writerOptional.get();
                writer.setHeart(writer.getHeart() - 1);
                userRepository.save(writer);
                map.put("heart", writer.getHeart());
            }
        }
        return map;
    }

    public Map checkHeart(HeartRequestDTO dto) {
        Map<String, Object> map = new HashMap<>();
        Optional<CustomTrashAddress> customTrashAddressOptional = customTrashAddressRepository.findById(dto.getCustomTrashAddressId());
        // 잘못된 정보라면
        if(customTrashAddressOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
        // 올바른 정보라면
        else {
            CustomTrashAddress customTrashAddress = customTrashAddressOptional.get();
            Optional<Heart> heartOptional = heartRepository.findByCustomTrashAddressIdAndUserId(dto.getCustomTrashAddressId(), dto.getUserId());
            // 좋아요를 누르지 않은 경우
            if (heartOptional.isEmpty())
                map.put("heart", "N");
            // 좋아요를 누른 경우
            else
                map.put("heart", "Y");
        }
        return map;
    }

    public Map registTrash(TrashRequestDTO dto) {
        Map<String, Object> map = new HashMap<>();
        Optional<User> userOptional = userRepository.findById(dto.getUserId());
        // 잘못된 유저 체크
        if(userOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
        else {
            User user = userOptional.get();
            Optional<CustomTrashAddress> customTrashAddressOptional = customTrashAddressRepository.findByLatitudeAndLongitude(dto.getLatitude(), dto.getLongitude());
            if(customTrashAddressOptional.isEmpty()) {
                CustomTrashAddress customTrashAddress = CustomTrashAddress.builder()
                        .user(user)
                        .latitude(dto.getLatitude())
                        .longitude(dto.getLongitude())
                        .kind(dto.getKind())
                        .build();

                user.setAddPlaceNum(user.getAddPlaceNum() + 1);
                customTrashAddressRepository.save(customTrashAddress);
                userRepository.save(user);

                map.put("userId", user.getId());
            }
            // 장소 중복 체크
            else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exist");
        }
        return map;
    }

    public List<TrashResponseDTO> printTrash() {
        List<TrashResponseDTO> list = new ArrayList<>();
        List<CustomTrashAddress> customList = customTrashAddressRepository.findAllBy();
        for (CustomTrashAddress customTrashAddress : customList) {
            User user = customTrashAddress.getUser();
            list.add(new TrashResponseDTO(customTrashAddress, user));
        }
        return list;
    }

    public TrashUserInfoResponseDTO writerTrash(Long customTrashAddressId) {
        Map<String, Object> map = new HashMap<>();
        Optional<CustomTrashAddress> customTrashAddressOptional = customTrashAddressRepository.findById(customTrashAddressId);
        CustomTrashAddress customTrashAddress = customTrashAddressOptional.get();
        return new TrashUserInfoResponseDTO(customTrashAddress.getUser());
    }

    public Map publicTrashPrints() {
        Map map = new HashMap();
        List list = publicTrashAddressRepository.findAll();
        map.put("results", list);
        return map;
    }
}
