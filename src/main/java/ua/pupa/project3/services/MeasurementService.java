package ua.pupa.project3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pupa.project3.models.Measurement;
import ua.pupa.project3.models.Sensor;
import ua.pupa.project3.reposiotories.MeasurementRepository;
import ua.pupa.project3.reposiotories.SensorRepository;
import ua.pupa.project3.util.EntityNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public Measurement findOne(int id) {
        return measurementRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public long countAllRainy () {
        return findAll().stream().filter(Measurement::isRaining).count();
    }

    @Transactional
    public Measurement save(Measurement measurement) {
         return measurementRepository.save(measurement);
    }

    @Transactional
    public void delete(int id) {
        measurementRepository.deleteById(id);
    }

    @Transactional
    public Measurement update (int id, Measurement measurement) {
        measurement.setId(id);
        return measurementRepository.save(measurement);
    }
}
