package com.example.convertidoralura;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioController implements Initializable {

    public TextField txtMoneyTo;
    public TextField txtMoneyOf;
    public Label lblResultado;
    @FXML
    private ChoiceBox<String> choiceMoneyOf;
    @FXML
    private ChoiceBox<String> choiceMoneyTo;
    public String[] monedas={"Euro","Dólar","Sol peruano","Yen Japonés","Libra esterlina","Won sur-coreano"};
    public double[] changeMoney={0.910590 ,1,3.66017 ,144.707 ,0.788702 ,1317.71 };
    @FXML
    private void changeText(){
        String montoTxt = txtMoneyOf.getText();
        int indexOf = choiceMoneyOf.getSelectionModel().getSelectedIndex();
        int indexTo = choiceMoneyTo.getSelectionModel().getSelectedIndex();

        if(!montoTxt.isEmpty()){
            BigDecimal cambio = calculateChange(montoTxt,indexOf,indexTo);
            String montoConvert = cambio.toString();
            txtMoneyTo.setText(montoConvert);
            return;
        }
        txtMoneyTo.setText(montoTxt);
    }
    public BigDecimal calculateChange(String montoTxt, int indexOf, int indexTo) {
        BigDecimal monto = new BigDecimal(montoTxt);
        BigDecimal montoInDollar = BigDecimal.ONE.divide(BigDecimal.valueOf(changeMoney[indexOf]), 5, RoundingMode.HALF_UP);
        BigDecimal montoConvert = monto.multiply(montoInDollar).multiply(BigDecimal.valueOf(changeMoney[indexTo])).setScale(4, RoundingMode.HALF_UP);
        lblResultado.setText(monto +" "+monedas[indexOf] + " es igual a " +montoConvert+" "+ monedas[indexTo]);
        return montoConvert;
    }
    @Override
    public void initialize(URL url, ResourceBundle arg){
        choiceMoneyOf.getItems().addAll(monedas);
        choiceMoneyTo.getItems().addAll(monedas);
        choiceMoneyOf.getSelectionModel().selectFirst();
        choiceMoneyTo.getSelectionModel().selectFirst();
        txtMoneyOf.textProperty().addListener((observable, oldValue, newValue) -> {
            // Esta función se ejecutará cada vez que cambie el texto
            changeText();
        });
        choiceMoneyOf.valueProperty().addListener((observable, oldValue, newValue) -> {
            changeText();
        });
        choiceMoneyTo.valueProperty().addListener((observable, oldValue, newValue) -> {
            changeText();
        });
    }

}