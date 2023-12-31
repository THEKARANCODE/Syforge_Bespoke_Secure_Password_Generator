package com.javafx.passwordgenerator_1_0;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;

public class Controller {

    String securePassword;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="c2CButton"
    private Button c2CButton; // Value injected by FXMLLoader

    @FXML // fx:id="generatePasswordButton"
    private Button generatePasswordButton; // Value injected by FXMLLoader

    @FXML // fx:id="lowerCaseCkbxa"
    private CheckBox lowerCaseCkbxa; // Value injected by FXMLLoader

    @FXML // fx:id="mainPasswordLabel"
    private Label mainPasswordLabel; // Value injected by FXMLLoader

    @FXML // fx:id="numericalCkbxa"
    private CheckBox numericalCkbxa; // Value injected by FXMLLoader

    @FXML // fx:id="passwordLengthSlider"
    private Slider passwordLengthSlider; // Value injected by FXMLLoader

    @FXML // fx:id="sliderLabel"
    private Label sliderLabel; // Value injected by FXMLLoader

    @FXML // fx:id="specialCkbxa"
    private CheckBox specialCkbxa; // Value injected by FXMLLoader

    @FXML // fx:id="upperCaseCkbxa"
    private CheckBox upperCaseCkbxa; // Value injected by FXMLLoader

    @FXML
    void handleCopy2Clipboard(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(securePassword);
        clipboard.setContent(content);
        System.out.println("\"Copy To Clipboard\" button pressed, " + securePassword + " coppied to Clipboard.");
    }

    @FXML
    void handleGenerate(ActionEvent event) {
        securePassword = SecurePasswordGenerator.generateSecurePassword((int) passwordLengthSlider.getValue(), lowerCaseCkbxa.isSelected(), upperCaseCkbxa.isSelected(), numericalCkbxa.isSelected(), specialCkbxa.isSelected(), false);
        mainPasswordLabel.setText(securePassword);
        System.out.println("\"Generate\" button pressed, " + securePassword + " generated");
    }

    @FXML
    void handleSlider(MouseEvent event) {
        int intsliderValue = (int) passwordLengthSlider.getValue();
        String stringSliderValue = String.valueOf(intsliderValue);
        if (intsliderValue < 100) {
            sliderLabel.setText("Password Length : 0" + stringSliderValue);
        } else {
            sliderLabel.setText("Password Length : " + stringSliderValue);
        }
    }

    @FXML
    void handleSliderReleased(MouseEvent event) {
        int intsliderValue = (int) passwordLengthSlider.getValue();
        String stringSliderValue = String.valueOf(intsliderValue);
        if (intsliderValue < 100) {
            sliderLabel.setText("Password Length : 0" + stringSliderValue);
        } else {
            sliderLabel.setText("Password Length : " + stringSliderValue);
        }
        System.out.println("Slider released, Value updated Successfully.");
    }

    //        This method is called by the FXMLLoader when initialization is complete
    @FXML
    void initialize() {
        String securePassword = SecurePasswordGenerator.generateSecurePassword((int) passwordLengthSlider.getValue(), false);
        mainPasswordLabel.setText(securePassword);
        System.out.println("Application initiated successfully.");
    }

}
