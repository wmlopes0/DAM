package com.clase.calculadora;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PrimaryController {

    @FXML
    private TextField operando1;
    @FXML
    private TextField operando2;
    @FXML
    private TextField resultado;
    @FXML
    private Button operar;
    @FXML
    private RadioButton multiplicar;
    @FXML
    private ToggleGroup grupo;
    @FXML
    private RadioButton dividir;
    @FXML
    private RadioButton restar;
    @FXML
    private RadioButton sumar;

    @FXML
    private void calcular(ActionEvent event) {
        float num1 = Float.parseFloat(operando1.getText());
        float num2 = Float.parseFloat(operando2.getText());
        float resultadoOperacion = 0f;
        if (sumar.isSelected()) {
            resultadoOperacion = num1 + num2;
            resultado.setText(String.valueOf(resultadoOperacion));
        } else {
            if (restar.isSelected()) {
                resultadoOperacion = num1 - num2;
                resultado.setText(String.valueOf(resultadoOperacion));
            } else {
                if (multiplicar.isSelected()) {
                    resultadoOperacion = num1 * num2;
                    resultado.setText(String.valueOf(resultadoOperacion));
                } else {
                    resultadoOperacion = num1 / num2;
                    resultado.setText(String.valueOf(resultadoOperacion));
                }
            }
        }

    }
}
