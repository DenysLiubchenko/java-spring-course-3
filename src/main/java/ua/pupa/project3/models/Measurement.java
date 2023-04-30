package ua.pupa.project3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "Measurement1")
public class Measurement {
    @Id
    @Column(name = "measurement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Sensor should be not empty")
    @NotNull(message = "Sensor should be not null")
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

    @Column(name = "tvalue")
    @NotEmpty(message = "Value should be not empty")
    @Size(min = -100, max = 100)
    private double tvalue;

    @Column(name="raining")
    @NotEmpty(message = "Raining should be not empty")
    @NotNull(message = "Raining should not be null")
    private boolean raining;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
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
