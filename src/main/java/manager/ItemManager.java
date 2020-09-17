package manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.UserManagementException;

import java.io.IOException;
import java.io.StringWriter;

public abstract class ItemManager {
    public abstract void listItems() throws IOException;

    public abstract void showItem(int id) throws IOException;

    public abstract int createItem() throws UserManagementException;

    public abstract void updateItem(int id) throws UserManagementException;

    public abstract void deleteItem(int id) ;

    protected void writeToConsole(Object items) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, items);
        System.out.println(writer.toString());
    }

}
