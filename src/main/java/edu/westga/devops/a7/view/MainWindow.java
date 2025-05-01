package edu.westga.devops.a7.view;

import edu.westga.devops.a7.model.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainWindow {

    @FXML
    private TextField itemNameField;

    @FXML
    private Button addItemButton;

    @FXML
    private Label errorLabel;

    @FXML
    private ListView<Item> itemList;
	
	@FXML
	private Button removeItemButton;

    private ObservableList<Item> shoppingList;

    @FXML
    public void initialize() {
        this.shoppingList = FXCollections.observableArrayList();
        this.itemList.setItems(this.shoppingList);
        this.errorLabel.setVisible(false); 
		

        this.addItemButton.setOnAction(e -> this.handleAddItem());
		this.removeItemButton.setOnAction(e -> this.handleRemoveItem());
    }

    private void handleAddItem() {
        String itemName = this.itemNameField.getText().trim();

        if (itemName.isEmpty()) {
            this.errorLabel.setText("Item name must not be empty");
            this.errorLabel.setVisible(true);
        } else {
            Item newItem = new Item(itemName);
            this.shoppingList.add(newItem);
            this.itemNameField.clear();
            this.errorLabel.setVisible(false);
        }
    }
	
	private void handleRemoveItem() {
		Item selectedItem = this.itemList.getSelectionModel().getSelectedItem(); 
    
		if (selectedItem == null) {
			this.errorLabel.setText("You must select an item to remove.");
			this.errorLabel.setVisible(true);
			return;
		}

		this.shoppingList.remove(selectedItem);
		this.errorLabel.setVisible(false);
	}
}