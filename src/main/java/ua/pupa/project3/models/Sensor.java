package ua.pupa.project3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Sensor1")
public class Sensor {
    @Id
    @Column(name = "name")
    @NotEmpty(message = "Name should be not empty")
    @Size(max = 30)
    private String name;

    public Sensor(String name) {
        this.name = name;
    }

    public Sensor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
