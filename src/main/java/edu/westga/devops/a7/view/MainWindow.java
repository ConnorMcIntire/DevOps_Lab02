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
    private Button changeQuantityButton;
	
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
		this.changeQuantityButton.setOnAction(e -> this.handleChangeQuantity());
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
	
	private void handleChangeQuantity() {
        Item selectedItem = this.itemList.getSelectionModel().getSelectedItem();
        String quantityText = this.itemNameField.getText().trim();  

        if (selectedItem == null) {
            this.errorLabel.setText("You must select an item to change its quantity.");
            this.errorLabel.setVisible(true);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);

            if (quantity <= 0) {
                this.errorLabel.setText("You must provide a positive quantity.");
                this.errorLabel.setVisible(true);
                return;
            }

            selectedItem.setQuantity(quantity);
			this.itemList.refresh();
			
            this.errorLabel.setVisible(false);

        } catch (NumberFormatException e) {
            this.errorLabel.setText("Please enter a valid number for quantity.");
            this.errorLabel.setVisible(true);
        }
    }
}