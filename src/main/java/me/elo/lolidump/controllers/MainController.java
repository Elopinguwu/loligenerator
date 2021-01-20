package me.elo.lolidump.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import me.elo.lolidump.utils.Scrapper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public ImageView img;
    public Button gen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            img.setImage(Scrapper.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newImg(MouseEvent mouseEvent) {
        try {
            img.setImage(Scrapper.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
