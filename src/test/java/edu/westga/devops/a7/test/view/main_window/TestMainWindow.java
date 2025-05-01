package edu.westga.devops.a4.test.view.main_window;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import edu.westga.devops.a7.model.Item;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;

public class TestMainWindow extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/westga/devops/a7/view/MainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testAddValidItem() {
        clickOn("#itemNameField").write("Apples");
        clickOn("#addItemButton");

        @SuppressWarnings("unchecked")
        ListView<Item> itemList = lookup("#itemList").query();
        assertEquals(1, itemList.getItems().size());
        assertTrue(itemList.getItems().get(0).toString().contains("Apples"));
    }

    @Test
    public void testAddInvalidItem_ShowsError() {
        clickOn("#itemNameField").write("  ");
        clickOn("#addItemButton");

        assertTrue(lookup("#errorLabel").queryLabeled().isVisible());
        assertEquals("Item name must not be empty", lookup("#errorLabel").queryLabeled().getText());
    }
}
