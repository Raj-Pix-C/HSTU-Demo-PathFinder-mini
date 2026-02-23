package com.example.demo_hstu_pathfinder;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;





public class Controller implements Initializable {

    public Button goButton;
    public AnchorPane mapArea;
    public AnchorPane actualBackground;
    public Separator separator;
    public Label dist;


    // creating instance
    ShortestPath pathfinder = new ShortestPath();



    // all nodes go here
    @FXML
    public Circle SecondGate;
    @FXML
    public Circle DVM;
    @FXML
    public Circle Aud2;
    @FXML
    public Circle Wajed;
    @FXML
    public Circle Admin;
    @FXML
    public Circle Ten;
    @FXML
    public Circle Gym;
    @FXML
    public Circle MainGate;
    @FXML
    public Circle Bus;
    @FXML
    public Circle Agri;
    @FXML
    public Circle Aud1;
    @FXML
    public Circle TSC;
    @FXML
    public Circle Lib;
    @FXML
    public Circle FirstGate;



    // all edges go here
    @FXML
    public Line dvm_aud2;
    @FXML
    public Line mg_gym;
    @FXML
    public Line w_10;
    @FXML
    public Line admin_10;
    @FXML
    public Line mg_bus;
    @FXML
    public Line g2_w;
    @FXML
    public Line dvm_w;
    @FXML
    public Line mg_w;
    @FXML
    public Line g2_mg;
    @FXML
    public Line g2_dvm;
    @FXML
    public Line aud2_admin;
    @FXML
    public Line mg_tsc;
    @FXML
    public Line tsc_g1;
    @FXML
    public Line tsc_agri;
    @FXML
    public Line aud1_lib;
    @FXML
    public Line agri_aud1;
    @FXML
    public Line mg_agri;
    @FXML
    public Line bus_10;
    @FXML
    public Line aud2_w;




    // choice box section //

    // enlisting all nodes
    public ObservableList<String> items = FXCollections.observableArrayList(
            "Second Gate", "DVM Building", "Auditorium-2", "Kazi Nazrul Islam Building",
            "Administrative Building", "Dr. Kudrat-E-Khuda Building", "Gymnasium", "Main Gate",
            "Bus Stand", "Agriculture Building", "Auditorium-1", "TSC", "Central Library",
            "First Gate"
    );

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


    @FXML
    public Circle[] nodesList = new Circle[14];
    public Line[][] edgesList = new Line[14][14];





    // things like choice box require this method to function
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // choicebox section // begin //

        // 'start' choicebox items initialize and capture section // begin //
        start.setItems(items);
        start.setOnAction(this::getSelectedStart);
        // 'start' choicebox items initialize and capture section // finish //


        // 'end' choicebox items initialize and capture section // begin //
        end.setItems(items);
        end.setOnAction(this::getSelectedEnd);
        // 'end' choicebox items initialize and capture section // finish //

        // choicebox section // finish //





        // Initialize Symmetric Access section // begin //

        // nodes initialize section // begin //

        nodesList[0] = SecondGate;
        nodesList[3] = Wajed;
        nodesList[7] = MainGate;
        nodesList[1] = DVM;
        nodesList[2] = Aud2;
        nodesList[5] = Ten;
        nodesList[4] = Admin;
        nodesList[6] = Gym;
        nodesList[8] = Bus;
        nodesList[9] = Agri;
        nodesList[12] = Lib;
        nodesList[11] = TSC;
        nodesList[10] = Aud1;
        nodesList[13] = FirstGate;

        // nodes initialize section // finish //



        // edges initialize section // begin //

        // set all edge as null first // will come in handy later
        for (Line[] lines : edgesList) {
            for (Line line : lines) {
                line = null;
            }
        }

        // now set the only required edges and leave unused ones as null

        // DVM - Aud2
        edgesList[1][2] = dvm_aud2;
        edgesList[2][1] = dvm_aud2;

        // MainGate - Gym
        edgesList[7][6] = mg_gym;
        edgesList[6][7] = mg_gym;

        // Wajed - Ten
        edgesList[5][3] = w_10;
        edgesList[3][5] = w_10;

        // Admin - Ten
        edgesList[4][5] = admin_10;
        edgesList[5][4] = admin_10;

        // MainGate - Bus
        edgesList[7][8] = mg_bus;
        edgesList[8][7] = mg_bus;

        // SecondGate - Wajed
        edgesList[0][3] = g2_w;
        edgesList[3][0] = g2_w;

        // DVM - Wajed
        edgesList[1][3] = dvm_w;
        edgesList[3][1] = dvm_w;

        // MainGate - Wajed
        edgesList[7][3] = mg_w;
        edgesList[3][7] = mg_w;

        // SecondGate - MainGate
        edgesList[0][7] = g2_mg;
        edgesList[7][0] = g2_mg;

        // SecondGate - DVM
        edgesList[0][1] = g2_dvm;
        edgesList[1][0] = g2_dvm;

        // Aud2 - Admin
        edgesList[2][4] = aud2_admin;
        edgesList[4][2] = aud2_admin;

        // MainGate - TSC
        edgesList[7][11] = mg_tsc;
        edgesList[11][7] = mg_tsc;

        // TSC - FirstGate
        edgesList[11][13] = tsc_g1;
        edgesList[13][11] = tsc_g1;

        // TSC - Agriculture
        edgesList[11][9] = tsc_agri;
        edgesList[9][11] = tsc_agri;

        // Aud1 - Library
        edgesList[10][12] = aud1_lib;
        edgesList[12][10] = aud1_lib;

        // Agriculture - Aud1
        edgesList[9][10] = agri_aud1;
        edgesList[10][9] = agri_aud1;

        // MainGate - Agriculture
        edgesList[7][9] = mg_agri;
        edgesList[9][7] = mg_agri;

        // Bus - Ten
        edgesList[8][5] = bus_10;
        edgesList[5][8] = bus_10;

        // Aud2 - Wajed
        edgesList[2][3] = aud2_w;
        edgesList[3][2] = aud2_w;


        // edges initialize section // finish //

        // Initialize Symmetric Access section // finish //


        // separator line //
        separator.setPrefWidth(4);

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
    public void onGoClicked(ActionEvent ae) throws IOException {
        StringBuilder sb = new StringBuilder();

        // testing code

        System.out.println("Clicked on " + selectedStart + " to " + selectedEnd + "\ndistance: " + pathfinder.getDistance(selectedStart, selectedEnd));
        System.out.print("path picked: ");
        LinkedList<Integer> path = (LinkedList<Integer>) pathfinder.pathConstruct(selectedStart, selectedEnd);
        for(int i = 0; i < path.size(); i++) {
            sb.append(items.get(path.get(i)));
            System.out.print(sb);

            if(i < path.size() - 1) {
                sb.append(" --( ");
                sb.append(pathfinder.shortestPath[path.get(i)][path.get(i+1)]);
                sb.append(" )--> ");
                System.out.print(" --> ");
            }
        }



        // actual code

        /* the logic is to highlight all intermediate nodes including the
        start and end node as well as all edges and showing calculated distance
        */

        // reset each node to dodgerblue and edges to black before each time path is printed
        resetNodes();
        resetEdges();

        // highlight node code
        for (int index : path) {
            nodesList[index].setFill(Color.ORANGE);
        }

        // highlight edges code
        for (int i = 1; i < path.size(); i++) {
            Line edge = edgesList[path.get(i)][path.get(i-1)];
            if (edge != null) {
                edge.setStroke(Color.ORANGE);
                edge.setStrokeWidth(4);
            }
        }


        // path print on screen code
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Shortest Distance: ");
        sb2.append(pathfinder.getDistance(selectedStart, selectedEnd));
        sb2.append(" meters");

        label1.setText(sb.toString());
        dist.setText(sb2.toString());

    }


    private void resetNodes(){

        for(Circle c : nodesList){
            c.setFill(Color.DODGERBLUE);
        }
    }

    private void resetEdges(){

        for (Line[] lines : edgesList) {
            for (Line line : lines) {
                if (line != null) {
                    line.setStroke(Color.BLACK);
                    line.setStrokeWidth(1);
                }
            }
        }

    }

}
