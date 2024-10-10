package com.example.maquinaexpendedora;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class HelloController {

    @FXML
    private Button botonCancelar;
    @FXML
    private Button moneda6;
    @FXML
    private Button moneda4;
    @FXML
    private Button moneda5;
    @FXML
    private Button botonComprar;
    @FXML
    private Button moneda2;
    @FXML
    private Button moneda3;
    @FXML
    private Button moneda1;
    @FXML
    private Button producto6;
    @FXML
    private Button producto4;
    @FXML
    private Button producto5;
    @FXML
    private Button producto2;
    @FXML
    private Button producto3;
    @FXML
    private Button producto1;
    @FXML
    private Label pantallaProducto;
    @FXML
    private GridPane panelProductos;
    @FXML
    private Label pantallaMonedero;
    private double totalImporte = 0.0;
    private double precioProductoSeleccionado = 0.0;
    private String productoSeleccionado = "";
    private double cambio= 0;
    @FXML
    // Inicialización de eventos
    public void initialize() {
        // Filtro para resaltar el producto seleccionado
        producto1.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> seleccionarProducto("Agua", 1.5, producto1));
        producto2.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> seleccionarProducto("Fanta", 1.5, producto2));
        producto3.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> seleccionarProducto("Coca-Cola", 1.5, producto3));
        producto4.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> seleccionarProducto("Aquarius", 1.5, producto4));
        producto5.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> seleccionarProducto("Caña", 1.8, producto5));
        producto6.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> seleccionarProducto("Palmera", 2.0, producto6));

        // Manejador para añadir el valor de la moneda al importe total
        moneda1.setOnAction(this::manejarMoneda);
        moneda2.setOnAction(this::manejarMoneda);
        moneda3.setOnAction(this::manejarMoneda);
        moneda4.setOnAction(this::manejarMoneda);
        moneda5.setOnAction(this::manejarMoneda);
        moneda6.setOnAction(this::manejarMoneda);

        // Evento usando setOn... para el botón de cancelar
        botonCancelar.setOnAction(event -> {
            resetearOperacion();
            pantallaMonedero.setText("Operación cancelada");
        });

        // Evento extendido para el botón de compra
        botonComprar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                realizarCompra();
            }
        });
    }
    @FXML
    // Método para manejar la selección de un producto
    private void seleccionarProducto(String nombre, double precio, Button boton) {
        productoSeleccionado = nombre;
        precioProductoSeleccionado = precio;
        pantallaProducto.setText("Producto: " + nombre);
        boton.setStyle("-fx-background-color: #2273b7");
    }

    @FXML
    // Método para manejar el clic de las monedas
    private void manejarMoneda(ActionEvent event) {
        Button boton = (Button) event.getSource();
        double valorMoneda = 0.0;

        switch (boton.getText()) {
            case "0,05€":
                valorMoneda = 0.05;
                break;
            case "0,10€":
                valorMoneda = 0.10;
                break;
            case "0,20€":
                valorMoneda = 0.20;
                break;
            case "0,50€":
                valorMoneda = 0.50;
                break;
            case "1€":
                valorMoneda = 1.0;
                break;
            case "2€":
                valorMoneda = 2.0;
                break;
        }

        totalImporte += valorMoneda;
        pantallaMonedero.setText("Monedero: " + String.format("%.2f", totalImporte) + "€");
    }
    @FXML
    // Método para resetear la operación
    private void resetearOperacion() {
        productoSeleccionado = "";
        precioProductoSeleccionado = 0.0;
        totalImporte = 0.0;
        pantallaProducto.setText("Producto");
        pantallaMonedero.setText("Monedero");
    }
    @FXML
    // Método para realizar la compra
    private void realizarCompra() {
        if (productoSeleccionado.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("Producto no seleccionado.");
            // Mostrar la alerta
            alert.showAndWait();
            return;
        }

        if (totalImporte < precioProductoSeleccionado) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ha ocurrido un error");
            alert.setContentText("Saldo insuficiente.");
            // Mostrar la alerta
            alert.showAndWait();
            resetearOperacion();
        }

        if (totalImporte >= precioProductoSeleccionado){
            cambio = totalImporte - precioProductoSeleccionado;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText("Gracias por su compra");
            alert.setContentText("Su cambio es: " + String.format("%.2f", cambio) + "€.");
            // Mostrar la alerta
            alert.showAndWait();
            resetearOperacion();
        }
    }

}