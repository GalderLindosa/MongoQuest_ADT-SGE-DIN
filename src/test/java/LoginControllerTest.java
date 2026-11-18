/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.*;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author Usuario
 */
public class LoginControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/mycompany/mongoquest/LoginWindow.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }



    @Test
    public void testCorrectLogin() {
        clickOn("#fieldUser").write("user01");
        clickOn("#fieldPass").write("1234");
        clickOn("#btnLogin");

    }

}
