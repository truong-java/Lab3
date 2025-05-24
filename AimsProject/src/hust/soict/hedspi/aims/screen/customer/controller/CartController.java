package hust.soict.hedspi.aims.screen.customer.controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.store.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;


public class CartController {
	
    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnViewStore;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private Label costLabel;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private TableView<Media> tblMedia;
    
    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;
    
    @FXML
    private TextField tfFilter;
    
    @FXML
    private Button btnPlaceOrder;

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
    	if (media instanceof DigitalVideoDisc) {
    		DigitalVideoDisc dvd = (DigitalVideoDisc) media;
            Alert playAlert = new Alert(Alert.AlertType.INFORMATION);
            playAlert.setTitle("Playing...");
            playAlert.setHeaderText(null);
            playAlert.setContentText("Title: " + dvd.getTitle() + "\nDirector: " + dvd.getDirector() + "\nLength: " + dvd.getLength() + " min");
            playAlert.showAndWait();
    	} else {
    		CompactDisc cd = (CompactDisc) media;
    		int count = cd.getTracks().size();
    		String noti = "Title: " + cd.getTitle() + "\nDirector: " + cd.getDirector() + "\nArtist: " + cd.getArtist()+ "\nNumber of tracks: " + count + "\nList of tracks:";
    		for (Track track : cd.getTracks()) {
    			noti = noti + "\n+ " + track.getTitle() + " - " + track.getLength() + " min";
    		}
            Alert playAlert = new Alert(Alert.AlertType.INFORMATION);
            playAlert.setTitle("Playing...");
            playAlert.setHeaderText(null);
            playAlert.setContentText(noti);
            playAlert.showAndWait();
    	}
    }
    
    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart == null || cart.getItemsOrdered().isEmpty()) {
            Alert emptyAlert = new Alert(Alert.AlertType.WARNING);
            emptyAlert.setTitle("Warning!");
            emptyAlert.setHeaderText(null);
            emptyAlert.setContentText("Your cart is empty. Please add items before placing an order.");
            emptyAlert.showAndWait();
            return;
        }
        
        StringBuilder message = new StringBuilder("=== Order Summary ===\n");

        for (Media media : cart.getItemsOrdered()) {
            message.append("ID: ").append(media.getId())
                   .append(", Title: ").append(media.getTitle())
                   .append(", Category: ").append(media.getCategory())
                   .append(", Cost: $").append(String.format("%.2f", media.getCost()))
                   .append("\n");
        }

        message.append("\n----------------------\n")
               .append("Total cost: $").append(String.format("%.2f", cart.totalCost()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText("Thank you for your order!");
        alert.setContentText(message.toString());
        alert.showAndWait();

        cart.getItemsOrdered().clear(); 
        updateCost();
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        cart.removeMedia(media);
        updateCost();
    }

    private Cart cart;
    private Store store;

    public CartController(Store store, Cart cart) {
        this.cart = cart;
        this.store = store;
    }
    
    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
            fxmlLoader.setController(new ViewStoreController(store, cart));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Store");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   public CartController() {
   }

    public void setCart(Cart cart) {
        this.cart = cart;
        if (filteredData != null) {
            filteredData = new FilteredList<>(cart.getItemsOrdered(), p -> true);
            tblMedia.setItems(filteredData);
        }
    }

    private void updateButtonBar(Media media) {
        if (media == null) {
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        } else {
            btnRemove.setVisible(true);
            if (media instanceof Playable) {
                btnPlay.setVisible(true);
            } else {
                btnPlay.setVisible(false);
            }
        }
    }
    
    private FilteredList<Media> filteredData;
    
    private void showFilteredMedia(String filterText) {
        if (filterText == null || filterText.isBlank()) {
            filteredData.setPredicate(p -> true); // không lọc
            return;
        }

        filteredData.setPredicate(media -> {
            if (radioBtnFilterId.isSelected()) {
                return String.valueOf(media.getId()).contains(filterText);
            } else if (radioBtnFilterTitle.isSelected()) {
                return media.getTitle().toLowerCase().contains(filterText.toLowerCase());
            }
            return true;
        });
    }
    
    public void updateCost() {
    	costLabel.setText(String.format("%.2f",cart.totalCost()));
    }
    
    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(
                new PropertyValueFactory<Media, Integer>("id"));
        colMediaTitle.setCellValueFactory(
                new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(
                new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(
                new PropertyValueFactory<Media, Float>("cost"));
        if(cart.getItemsOrdered() != null )
            tblMedia.setItems(cart.getItemsOrdered());

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                updateButtonBar(newValue);
            }
        });
        
        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                showFilteredMedia(newValue);
            }
        });
        
        filteredData = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        tblMedia.setItems(filteredData);
        
        updateCost();

    }
}