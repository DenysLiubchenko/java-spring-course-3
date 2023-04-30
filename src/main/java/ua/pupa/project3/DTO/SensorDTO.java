package ua.pupa.project3.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "Name should be not empty")
    @Size(max = 30)
    private String name;

    public SensorDTO(String name) {
        this.name = name;
    }

    public SensorDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
