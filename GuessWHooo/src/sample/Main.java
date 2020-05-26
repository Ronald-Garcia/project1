//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sample;

import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    // INSTANCE VARIABLES
    public static Stage stage = new Stage();
    public static final char[] LETTERS = {'p', 'i', 'u', 'f', 'g', 'h', 'j', 'v', 'a', 'q'};
    public static String[][] map = getMap();
    public static int count = 0;
    public static String answer;

    // Stores the display text for the game
    @FXML
    public Text mapText;

    //Stores each one of the string of letters to guess
    @FXML
    public ComboBox<String> comboBox;

    /*
     *  This method sets up the window where the game takes place with the preloaded FXML file
     */
    public void start(Stage defaultStageIgnored) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);

        stage.setWidth(640);
        stage.setHeight(520);
        //stage.resizableProperty().setValue(false);
        stage.setTitle("Guess Who!");
        stage.setScene(scene);
        stage.show();
    }

    /*
     *  This method launches the program
     */
    public static void main(String[] args) { launch(args); }

    /*
     *  This method generates a new member of the comboBox by selecting 2 random letters from the LETTERS array
     */
    public static String getGuess(){
        String value = "";
        Random r = new Random();
        for(int i = 0; i < r.nextInt(1) + 2; i++){
            value += LETTERS[r.nextInt(LETTERS.length)];
        }
        return value;
    }
    /*
     *  This method sets up the board, made up of 9 guesses without any duplicates in a 2D array
     */
    public static String[][] getMap(){
        String[][] value = new String[3][3];
        for(int i = 0; i < 3; i++){
            for(int x = 0; x < 3; x++){
                String temp = getGuess();
                while(temp == null) temp = getGuess();
                if(!getStringMap(value).contains(temp)) value[i][x] = temp;
            }
        }
        return value;
    }
    /*
     *  This method sets a map into string format, each guess on its own line
     */
    public static String getStringMap(String[][] map){
        String value = "";
        for(int x = 0; x < map.length; x++){
            value += String.join(" ", map[x]);
            value += "\n";
        }
        return value;
    }
    /*
     * This method is used to update the map after a guess has been made by the player, replacing guesses without what the player guessed with a --
     */
    public void result() {

            for(int x = 0; x < 3; x++){
                for(int y = 0; y < 3; y++){
                    if(!answer.equals(map[x][y]) && !map[x][y].contains(Character.toString(comboBox.getValue().charAt(comboBox.getValue().length()-2)))){
                        map[x][y] = "--";
                    }

                }
            }
        mapText.setText(getStringMap(map));
    }

    /*
     * This method handles the closing of the window
     */
    @FXML
    public void onExitAction(ActionEvent event) {
        stage.close();
    }
    /*
     * This method generates a map after the text has been clicked on
     */
    @FXML
    public void onTextClicked() {
        mapText.setText(getStringMap(map));
        answer = map[new Random().nextInt(3)][new Random().nextInt(3)];
    }

    /*
     * This method handles the submissions, after 3 submissions the answer is revealed and the game ends.
     */
    @FXML
    public void onSubmitPressed() {
        if (count >= 3) {
            comboBox.setVisible(false);
            mapText.setText("Answer: " + answer);
        } else {
            result();
            System.out.println("result");
            count++;
        }
    }
}
