package manager;

import exception.UserManagementException;
import model.Device;
import service.DeviceService;
import service.HomeService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

public class DeviceManager extends ItemManager {
    private Scanner in = new Scanner(System.in);
    private DeviceService deviceService = new DeviceService();
    private HomeService homeService = new HomeService();

    public DeviceManager() {}

    @Override
    public void listItems() throws IOException {
        writeToConsole(deviceService.findAll());
    }

    @Override
    public void showItem(int id) throws IOException {
        writeToConsole(deviceService.findById(id));
    }

    @Override
    public int createItem() throws UserManagementException {
        HashMap<String, String> deviceFields = deviceForm();
        checkValidDeviceFields(deviceFields);
        Device device = new Device(deviceFields.get("name"), Integer.parseInt(deviceFields.get("value")));
        device.setHome(homeService.findById(Integer.parseInt(deviceFields.get("home"))));
        deviceService.create(device);
        return device.getId();
    }

    @Override
    public void updateItem(int id) throws UserManagementException {
        Device device = deviceService.findById(id);
        HashMap<String, String> deviceFields = deviceForm();
        checkValidDeviceFields(deviceFields);
        device.setName(deviceFields.get("name"));
        device.setValue(Integer.parseInt(deviceFields.get("value")));
        device.setHome(homeService.findById(Integer.parseInt(deviceFields.get("home"))));
        deviceService.update(device);
    }

    @Override
    public void deleteItem(int id) {
        Device device = deviceService.findById(id);
        deviceService.delete(device);
    }

    private HashMap<String, String> deviceForm() {
        HashMap<String, String> deviceFields = new HashMap<>();
        System.out.print("Enter name: ");
        deviceFields.put("name", in.nextLine());
        System.out.print("Enter value: ");
        deviceFields.put("value", in.nextLine());
        System.out.println("Enter home id: ");
        deviceFields.put("home", in.nextLine());
        return deviceFields;
    }

    private void checkValidDeviceFields(HashMap<String, String> _deviceFields) throws UserManagementException{
        if (Stream.of(_deviceFields.get("name"), _deviceFields.get("home")).anyMatch(""::equals)) {
            throw new UserManagementException("'Name', 'home id' fields cannot be blank!");
        }
        if (!_deviceFields.get("value").isEmpty() && !_deviceFields.get("value").matches("-?\\d+(\\.\\d+)?"))
            throw new UserManagementException("'Value' field is numeric!");
        if (_deviceFields.get("value").isEmpty()) {
            _deviceFields.put("value", "0");
        }
        if (!_deviceFields.get("home").matches("-?\\d+(\\.\\d+)?")) {
            throw new UserManagementException("'Home id' field is numeric!");
        }
        if (homeService.findById(Integer.parseInt(_deviceFields.get("home"))) == null) {
            throw new UserManagementException("Home with such id doesn't exist!");
        }
    }
}
