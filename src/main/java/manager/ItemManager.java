package manager;

import exception.UserManagementException;

import java.io.IOException;

public interface ItemManager {
    void listItems() throws IOException;

    void showItem(int id) throws IOException;

    int createItem() throws UserManagementException;

    void updateItem(int id) throws UserManagementException;

    void deleteItem(int id) ;
}
