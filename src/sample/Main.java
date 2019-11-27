package sample;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

// Orginalnie
//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}


//zajecia z JavaFX 20.11.2019
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,400,400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            // zadanie 1
            Label label = new Label("Hello world!!");
            //root.setCenter(label);
            //cd zad 3
            label.setLayoutY(120);

            //zadanie 2
            Label label1 = new Label("Label 1");
            Label label2 = new Label("Label 2");

            VBox leftVbox = new VBox(10);
            leftVbox.getChildren().add(label1);
            leftVbox.getChildren().add(label2);

            root.setLeft(leftVbox);

            //zadanie 3
            TextField text1 = new TextField("Example text 1");
            //dodawanie CSSa do pola textowego
            text1.getStyleClass().add("my-field");
            text1.setLayoutX(10);
            text1.setLayoutY(0);
            TextField text2 = new TextField();
            //dodawanie CSSa
            text2.getStyleClass().add("my-field");
            text2.setLayoutX(0);
            text2.setLayoutY(60);
            Group centerGroup = new Group();
            //centerGroup.getChildren().addAll(text1,text2, label);
            root.setCenter(centerGroup);

            //zadanie 4 obrazek
            // obrazek z internetu po URL
            //Image image = new Image("http://galera.ii.pw.edu.pl/~zsz/javafx/img/lenna256px.png");

            //obrazek z komputera (dodalismy obrazek prawym na paczce programu i Import
            Image image = new Image(getClass().getResourceAsStream("lenna256px.png"));


            ImageView imageView = new ImageView(image);
            centerGroup.getChildren().add(imageView);
            centerGroup.getChildren().addAll(text1,text2, label);

            //po dodaniu 3 przyciskow tu dalej robimy!!!
            //funkcj maja byc przed przycyskami bo beda mowic co robia przyciski
            RadioButton radio1 = new RadioButton("Radio 1");
            RadioButton radio2 = new RadioButton("Radio 2");
            RadioButton radio3 = new RadioButton("Radio 3");

            radio1.setUserData("User Data przycisk 1 ");
            radio2.setUserData("User Data przycisk 2 ");
            radio3.setUserData("User Data przycisk 3 ");

            ToggleGroup tgGrup = new ToggleGroup();
            radio1.setToggleGroup(tgGrup);
            radio2.setToggleGroup(tgGrup);
            radio3.setToggleGroup(tgGrup);

            //zastosowanie nasluchiwacza!!
            tgGrup.selectedToggleProperty().addListener(
                    (observableValue, oldToggle, newToggle)->{
                        System.out.println(newToggle.getUserData().toString());
                    }
            );

            HBox bottomHbox = new HBox(10);
            bottomHbox.getChildren().addAll(radio1,radio2,radio3);
            root.setBottom(bottomHbox);

            // dodac przycisk // potem dodac  3 przyciski obok siebie
            Button button1 = new Button("Click me1!");
            //dodawanie CSSa do przyciskow
            button1.setId("button1");
            Button button2 = new Button("Click me2!");

            Button button3 = new Button("Click me3!");


            //tu powiazanie  przycisku z akcja uzycie lambda
            button1.setOnAction(event ->  {
                System.out.println("Button 1 - clicked");
                if(radio1.isSelected())
                    System.out.println("Radio 1 selected");
                if(radio2.isSelected())
                    System.out.println("Radio 2 selected");
                if(radio3.isSelected())
                    System.out.println("Radio 3 selected");

                if(tgGrup.getSelectedToggle() != null)
                {
                    System.out.println(tgGrup.getSelectedToggle().getUserData());
                }

                //pobieranie watrosci z pola txt
                System.out.println(text1.getText());

                //czy spełnia zasady kodowania grup!!!

                String wpisanyStr = text1.getText();
                //moje
                //String wyrRegularne = "[A-Z]{2}[1-9]{2}[A-Za-z][0-9]{2}[a-z]";
                //prowadzacego
                String wyrRegularne = "[JA|BD|DS][0-9]{2}[A-Za-z][0-9]{2}[abc]";

                if (wpisanyStr.matches(wyrRegularne)) {
                    // etap 2 // miedzy rokiem 17 z 25
                    String przycietyString = wpisanyStr.substring(2,4);
                    Integer rok = Integer.parseInt(przycietyString);
                    if(rok>=17 && rok<=25)
                        System.out.println("dobrze");}
                else {System.out.println("zle");}
            });


            //tu dodanie okna z komunikatem po nacisnieciu przycisku 2
            button2.setOnAction(event ->  {
                System.out.println("Button 2 - clicked");
                Alert alert = new Alert(AlertType.CONFIRMATION, "Kliknieto przycisk 2");
                //alert.show();
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && result.get() == ButtonType.OK) {
                    System.out.println("Wcisnieto OK w oknie alert.");
                }

            });



            //tu pokazane okno dialogowe po nacisnieciu przyciski 3
            button3.setOnAction(event ->  {
                System.out.println("Button 3 - clicked");
                Stage stageDlg = new Stage();
                stageDlg.setTitle("Okno dialogowe");
                BorderPane rootDlg = new BorderPane();
                stageDlg.setScene(new Scene(rootDlg, 300,100));

                Label msg = new Label("To jest okno dialogowe");
                rootDlg.setCenter(msg);

                stageDlg.initOwner(primaryStage);
                stageDlg.initModality(Modality.WINDOW_MODAL);

                stageDlg.show();

            });

            HBox topHbox = new HBox(10);
            topHbox.getChildren().addAll(button1, button2, button3);
            root.setTop(topHbox);


            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}