package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;



public class Main extends Application {
public  static  Stage primaryStage;
public  void setStage(Stage stage){
    Main.primaryStage = stage;
}
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Авторизация");
        primaryStage.setScene(new Scene(root, 600, 375));
        primaryStage.getIcons().add(new Image(new File("C:\\Программы\\Kursach\\Klient\\src\\Image\\unnamed.jpg").toURL().toString()));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException{

        launch(args);
    }
}
