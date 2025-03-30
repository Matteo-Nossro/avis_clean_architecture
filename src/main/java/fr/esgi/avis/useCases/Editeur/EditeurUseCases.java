package fr.esgi.avis.useCases.Editeur;

import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Editeur.EditeurDataSourcePort;

import java.util.List;
import java.util.Optional;

public class EditeurUseCases {

    private final EditeurDataSourcePort editeurDataSourcePort;

    public EditeurUseCases(EditeurDataSourcePort editeurDataSourcePort) {
        this.editeurDataSourcePort = editeurDataSourcePort;
    }

    public Editeur createEditeur(String nom) {
        Editeur editeur = Editeur.builder()
                .nom(nom)
                .build();
        return editeurDataSourcePort.save(editeur);
    }

    public Optional<Editeur> getEditeurById(Long id) {
        return editeurDataSourcePort.findById(id);
    }

    public List<Editeur> getAllEditeurs() {
        return editeurDataSourcePort.findAll();
    }

    public void deleteEditeur(Long id) {
        editeurDataSourcePort.deleteById(id);
    }
}