package model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "homes")
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Id")
    private int id;

    @Column(name = "name")
    @JsonProperty("Name")
    private String name;

    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("Devices")
    @JsonManagedReference
    private List<Device> devices;

    public Home(){}

    public Home(String name) {
        this.name = name;
        devices = new ArrayList<Device>();
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

    public List<Device> getDevices() {
        return devices;
    }

    public List<Device> addDevice(Device device) {
        if (!this.devices.contains(device)) {
            this.devices.add(device);
            device.setHome(this);
        }
        return this.devices;
    }

    public List<Device> removeDevice(Device device) {
        this.devices.remove(device);
        return this.devices;
    }
}
