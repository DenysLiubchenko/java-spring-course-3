package ua.pupa.project3.DTO;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.modelmapper.ModelMapper;
import ua.pupa.project3.models.Sensor;

public class MeasurementDTO {
    @NotEmpty(message = "Sensor should be not empty")
    @NotNull
    private SensorDTO sensorDTO;

    @NotEmpty(message = "Value should be not empty")
    @NotNull
    @Size(min = -100, max = 100)
    private double tvalue;

    @NotEmpty(message = "Raining should be not empty")
    @NotNull(message = "Raining should not be null")
    private boolean raining;

    public MeasurementDTO(SensorDTO sensorDTO, double tvalue, boolean raining) {
        this.sensorDTO = sensorDTO;
        System.out.println(sensorDTO.getName());
        this.tvalue = tvalue;
        this.raining = raining;
    }

    public MeasurementDTO() {

    }
    private SensorDTO convertToSensorDTO (Sensor sensor) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(sensor, SensorDTO.class);
    }
    public SensorDTO getSensor() {
        return sensorDTO;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensorDTO = sensor;
    }

    public double getTvalue() {
        return tvalue;
    }

    public void setTvalue(double tvalue) {
        this.tvalue = tvalue;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }
}
