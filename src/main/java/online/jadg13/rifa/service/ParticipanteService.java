package online.jadg13.rifa.service;

import online.jadg13.rifa.model.Participante;
import online.jadg13.rifa.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository repository;

    public Participante save(Participante participante) {
        return repository.save(participante);
    }

    public List<Participante> showAll() {
        return repository.findAll();
    }

    public Optional<Participante> showById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Participante update(Participante participante) {
        return repository.save(participante);
    }



}
