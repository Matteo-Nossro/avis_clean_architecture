package fr.esgi.avis.controller.Avatar.rest;

import fr.esgi.avis.controller.Avatar.AvatarController;
import fr.esgi.avis.controller.Avatar.AvatarDtoMapper;
import fr.esgi.avis.controller.Avatar.dto.AvatarDTO;
import fr.esgi.avis.controller.Avatar.dto.CreateAvatarDTO;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avatars")
public class AvatarRestController {
    private final AvatarController avatarController;

    public AvatarRestController(AvatarController avatarController) {
        this.avatarController = avatarController;
    }

    @PostMapping
    public ResponseEntity<AvatarDTO> create(@RequestBody CreateAvatarDTO request) {
        Avatar domain = AvatarDtoMapper.toDomain(request);
        Avatar created = avatarController.createAvatar(domain);
        AvatarDTO dto = AvatarDtoMapper.toDto(created);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvatarDTO> getById(@PathVariable Long id) {
        return avatarController.getAvatarById(id)
                .map(AvatarDtoMapper::toDto)
                .map(dto -> ResponseEntity.ok(dto))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        avatarController.deleteAvatar(id);
        return ResponseEntity.noContent().build();
    }
}
