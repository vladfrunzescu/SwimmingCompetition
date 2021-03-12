package socialnetwork.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import socialnetwork.domain.Concurs;
import socialnetwork.domain.DoiDTO;
import socialnetwork.domain.Participant;
import socialnetwork.domain.Unu;
import socialnetwork.service.Service;
import socialnetwork.utils.Stil;
import socialnetwork.utils.observer.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MainController implements Observer {

    private Service service;

    private ObservableList<Concurs> concursModel = FXCollections.observableArrayList();
    private ObservableList<Concurs> stilModel = FXCollections.observableArrayList();

    @FXML
    private TableView<Concurs> tableViewStil;
    @FXML
    private TableColumn<Concurs,String> tableColumnStil;
    @FXML
    private TableColumn<Concurs,String> tableColumnParticipanti;

    @FXML
    private TableView<Concurs> tableViewParticipant;
    @FXML
    private TableColumn<Concurs,String> tableColumnStilParticipant;


    @FXML
    private TextField textFieldNume;
    @FXML
    private Label labelLocatie;

    @FXML
    private TextField textFieldNumeInput;
    @FXML
    private TextField textFieldVarstaInput;
    @FXML
    private TextField textFieldStiluriInput;


    @Override
    public void update() {
        this.initModel();
    }

    public void setPage(Service service, String locatie) {
        this.service = service;
        labelLocatie.setText(locatie);
        service.addObserver(this);
        initModel();
    }

    @FXML
    public void initialize() {
        tableColumnStil.setCellValueFactory(new PropertyValueFactory<Concurs, String>("Id"));
        tableColumnParticipanti.setCellValueFactory(new PropertyValueFactory<Concurs, String>("Participanti"));

        tableViewStil.setItems(concursModel);

        tableColumnStilParticipant.setCellValueFactory(new PropertyValueFactory<Concurs, String>("Id"));

        tableViewParticipant.setItems(stilModel);
    }

    private void initModel() {
        concursModel.setAll(service.getAllConcurs());

        //labelNume.setText(unu.getSecond_string());
    }

    @FXML
    public void handleCautaButton(){
        if(textFieldNume.getText() != null){
        String nume = textFieldNume.getText();
        List<Concurs> list = service.getAllStiluriByParticipant(nume);
        stilModel.setAll(list);
        }else{
            MessageAlert.showErrorMessage(null, "Completati campul nume!");
        }
    }

    @FXML
    public void handleSignUpButton(){
        try {
            String nume = textFieldNumeInput.getText();
            Integer varsta = Integer.parseInt(textFieldVarstaInput.getText());
            String stiluri = textFieldStiluriInput.getText();

            List<String> attr = Arrays.asList(stiluri.split(","));
            List<Stil> list = new ArrayList<>();
            for (String a : attr) {
                list.add(Stil.valueOf(a));
            }
            if (service.addParticipant(nume, varsta, list) == null) {
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Participant inscris!", "Succes!");
            } else {
                MessageAlert.showErrorMessage(null, "Nu s-a putut inscrie participantul! Introduceti datele din nou.");
            }
        }catch(Exception e){
            MessageAlert.showErrorMessage(null, e.getMessage());
        }
    }
    /*
    @FXML
    public void handleTrimiteButton() {
        try {
            String text_message = textField.getText();
            List<Long> all_entities = new ArrayList<>();
            for(Unu e : service.getAllUnu()){
                if(!e.equals(unu)){
                    all_entities.add(e.getId());
                }
            }

            //service.addEntity();
            //MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Mesaj", "Mesajul a fost trimis cu succes!");
            textField.setText("");
        }
        catch (Exception ex) {
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    public void handleRetragButton(){
        try{
            //if(service.action() == null){
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "", "!");
            //}
        } catch(Exception ex){
                MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

    @FXML
    public void handleRevinButton(){
        try{
            //if(service.action() == null){
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "", "!");
            //}
        } catch(Exception ex){
            MessageAlert.showErrorMessage(null, ex.getMessage());
        }
    }

     */
}
