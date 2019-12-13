/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir_project;

import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;
import com.sun.xml.internal.ws.dump.LoggingDumpTube;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.omg.PortableServer.POA;

/**
 *
 * @author assem
 */
public class GUI extends Application {
    FlowPane flow = new FlowPane(20, 20);
    Button b1 = new Button("Go");
    TextField t = new TextField("search");
        TextArea area = new TextArea();
         VBox vbox = new VBox();
          Label label = new Label("INDEX TYPE");
           RadioButton radio = new RadioButton("INVERTED INDEX");
            ToggleGroup toggel = new ToggleGroup();
             RadioButton radio1 = new RadioButton("MATRIX");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("IR_project");
         Font font = new Font("tahoma", 15);
        
        flow.setPadding(new Insets(5));
        
        b1.setPrefWidth(150);
        b1.setFont(font);
        b1.setOnAction((event) -> {
            if(toggel.getSelectedToggle().equals(radio)){
                String s = t.getText();
                inverted_index inverted = new inverted_index();
                
                try {
                    inverted.invertedindex(s);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            if(toggel.getSelectedToggle().equals(radio1)){
                String s = t.getText();
                matrix m = new matrix();
                try {
                    m.searching(s);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        

        t.setPrefWidth(500);

        flow.getChildren().addAll(t, b1);
       
        vbox.setPadding(new Insets(22));
       
        label.setPadding(new Insets(22));
       
        label.setFont(font);
        Color color = Color.web("#4527a0");
       
        radio.setPadding(new Insets(22));
       
        radio1.setPadding(new Insets(22));
       

        radio.setToggleGroup(toggel);
        radio1.setToggleGroup(toggel);
        vbox.getChildren().addAll(label,radio,radio1);

        BorderPane porder = new BorderPane();
        porder.setTop(flow);
        porder.setCenter(area);
        porder.setRight(vbox);
        Scene s = new Scene(porder, 700, 500);

        primaryStage.setScene(s);

        primaryStage.setWidth(700);

        primaryStage.setHeight(500);

        primaryStage.show();

    }

   
        
        



    }


