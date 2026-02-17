package com.example.demo_hstu_pathfinder;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Button goButton;
    // creating instance
    ShortestPath pathfinder = new ShortestPath();



    // choice box section //

    // enlisting all nodes
    public ObservableList<String> items = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

    // start choice box(dropdown)
    @FXML
    public ChoiceBox<String> start;

    // end choice box(dropdown)
    @FXML
    public ChoiceBox<String> end;

    // to keep track of chosen items
    public int selectedStart, selectedEnd;

    @FXML
    private Label label1 = new Label();


    // things like choice box require this method to function
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start.setItems(items);
        start.setOnAction(this::getSelectedStart);

        end.setItems(items);
        end.setOnAction(this::getSelectedEnd);
    }


    // helper method to visualize start choicebox items
    public void getSelectedStart(ActionEvent ae) {
        String selected = start.getValue();
        selectedStart = items.indexOf(selected);
    }

    // helper method to visualize end choicebox items
    public void getSelectedEnd(ActionEvent ae) {
        String selected = end.getValue();
        selectedEnd = items.indexOf(selected);
    }


    // method to find path(which is precalculated) between selected items from star and end choiceboxes
    @FXML
    public void onGoClicked() {
        System.out.println("Clicked on " + selectedStart + " to " + selectedEnd + "\ndistance: " + pathfinder.getDistance(selectedStart, selectedEnd));
        System.out.print("path picked: ");
        LinkedList<Integer> path = (LinkedList<Integer>) pathfinder.pathConstruct(selectedStart, selectedEnd);
        for(int i = 0; i < path.size(); i++) {
            System.out.print(items.get(path.get(i)) + " ");
        }
        label1.setText(selectedStart + " to " + selectedEnd);
        label1.setVisible(true);
    }
}
