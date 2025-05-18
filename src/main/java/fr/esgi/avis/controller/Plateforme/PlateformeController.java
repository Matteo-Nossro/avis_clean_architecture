package fr.esgi.avis.controller.Plateforme;

import fr.esgi.avis.controller.Plateforme.dto.CreatePlateformeDto;
import fr.esgi.avis.controller.Plateforme.dto.PlateformeDto;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import fr.esgi.avis.useCases.Plateforme.PlateformeUseCases;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlateformeController {

    private final PlateformeUseCases plateformeUseCases;

    public PlateformeController(PlateformeUseCases plateformeUseCases) {
        this.plateformeUseCases = plateformeUseCases;
    }

    public PlateformeDto create(CreatePlateformeDto dto) {
        Plateforme created = plateformeUseCases.createPlateforme(dto.getNom());
        return PlateformeDtoMapper.toDto(created);
    }

    public List<PlateformeDto> getAll() {
        return plateformeUseCases.getAllPlateformes()
                .stream()
                .map(PlateformeDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public PlateformeDto getById(Long id) {
        return plateformeUseCases.getPlateformeById(id)
                .map(PlateformeDtoMapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Plateforme introuvable"));
    }

    public void delete(Long id) {
        plateformeUseCases.deletePlateforme(id);
    }
}