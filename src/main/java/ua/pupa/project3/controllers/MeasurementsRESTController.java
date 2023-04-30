package ua.pupa.project3.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ua.pupa.project3.DTO.MeasurementDTO;
import ua.pupa.project3.DTO.SensorDTO;
import ua.pupa.project3.models.Measurement;
import ua.pupa.project3.models.Sensor;
import ua.pupa.project3.services.MeasurementService;
import ua.pupa.project3.util.EntityNotCreatedException;
import ua.pupa.project3.util.EntityNotFoundException;
import ua.pupa.project3.util.ErrorResponse;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsRESTController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementsRESTController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add (@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage()).append("; ");
            }
            throw new EntityNotCreatedException(errorMessage.toString());
        }
        measurementService.save(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<MeasurementDTO> get() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO).toList();
    }

    @GetMapping("/rainyDaysCount")
    public long getRainyDaysCount () {
        return measurementService.countAllRainy();
    }

    private Measurement convertToMeasurement (MeasurementDTO measurementDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO (Measurement measurement) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(EntityNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Measurement was not found", System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(EntityNotCreatedException exception) {
        ErrorResponse errorResponse = new ErrorResponse("Measurement was not created", System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
