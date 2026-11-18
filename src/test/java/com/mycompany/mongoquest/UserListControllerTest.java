
package com.mycompany.mongoquest;
package com.mycompany.mongoquest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import javafx.fxml.FXMLLoader;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

public class UserListControllerTest {

    @Test
    public void testFXMLExists() {

        assertNotNull(
                getClass().getResource(
                        "/com/mycompany/mongoquest/UserListWindow.fxml"
                )
        );
    }

    @Test
    public void testFXMLLoads() throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/mycompany/mongoquest/UserListWindow.fxml"
                )
        );

        Parent root = loader.load();

        assertNotNull(root);
    }

    @Test
    public void testControllerLoads() throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/com/mycompany/mongoquest/UserListWindow.fxml"
                )
        );

        loader.load();

        UserListWindowController controller =
                loader.getController();

        assertNotNull(controller);
    }

    @Test
    public void testUserListExists() throws Exception {

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/mycompany/mongoquest/UserListWindow.fxml"
                )
        );

        assertNotNull(root.lookup("#userList"));
    }

    @Test
    public void testUserFieldExists() throws Exception {

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/mycompany/mongoquest/UserListWindow.fxml"
                )
        );

        assertNotNull(root.lookup("#userField"));
    }

    @Test
    public void testPasswordFieldExists() throws Exception {

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/mycompany/mongoquest/UserListWindow.fxml"
                )
        );

        assertNotNull(root.lookup("#passField"));
    }

    @Test
    public void testAddButtonExists() throws Exception {

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/mycompany/mongoquest/UserListWindow.fxml"
                )
        );

        assertNotNull(root.lookup("#addUserButton"));
    }

    @Test
    public void testModifyButtonExists() throws Exception {

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/mycompany/mongoquest/UserListWindow.fxml"
                )
        );

        assertNotNull(root.lookup("#modifyUser"));
    }

    @Test
    public void testCheckBoxesExist() throws Exception {

        Parent root = FXMLLoader.load(
                getClass().getResource(
                        "/com/mycompany/mongoquest/UserListWindow.fxml"
                )
        );

        assertNotNull(root.lookup("#verContra1"));
        assertNotNull(root.lookup("#verContra2"));
    }
}