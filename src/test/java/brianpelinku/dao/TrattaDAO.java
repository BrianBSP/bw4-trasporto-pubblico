package brianpelinku.dao;

import brianpelinku.entities.Tratta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TrattaDAO {

    private List<Tratta> trattaList = new ArrayList<>();


    public void addTratta(Tratta tratta) {
        trattaList.add(tratta);
    }

    public Optional<Tratta> getTrattaById(UUID id) {
        return trattaList.stream()
                .filter(tratta -> tratta.getId().equals(id))
                .findFirst();
    }

    public List<Tratta> getAllTratte() {
        return trattaList;
    }

    public boolean removeTratta(UUID id) {
        return trattaList.removeIf(tratta -> tratta.getId().equals(id));
    }
}