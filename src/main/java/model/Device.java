package model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;

@Entity
@Table (name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Id")
    private int id;

    @Column(name = "name")
    @JsonProperty("Name")
    private String name;

    @Column(name = "value")
    @JsonProperty("Value")
    private int value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_home")
    @JsonBackReference
    private Home home;

    public Device() {}

    public Device(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}