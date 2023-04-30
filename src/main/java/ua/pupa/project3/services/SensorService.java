package ua.pupa.project3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pupa.project3.models.Sensor;
import ua.pupa.project3.reposiotories.SensorRepository;
import ua.pupa.project3.util.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Sensor findOne(String id) throws ChangeSetPersister.NotFoundException {
        return sensorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public Optional<Sensor> findByName (String name) {
        return sensorRepository.findByName(name);
    }

    @Transactional
    public Sensor save(Sensor sensor) {
         return sensorRepository.save(sensor);
    }

    @Transactional
    public void delete(String id) {
        sensorRepository.deleteById(id);
    }

    @Transactional
    public Sensor update (String id, Sensor sensor) {
        sensor.setName(id);
        return sensorRepository.save(sensor);
    }
}
