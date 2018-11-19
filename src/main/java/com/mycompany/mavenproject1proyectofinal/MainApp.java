package com.mycompany.mavenproject1proyectofinal;

import Controller.LibroController;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Anio;
import model.Libros;
import model.Mes;

public class MainApp extends Application {

    private int id_libros;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //this.gc = new GeneradorController();
        primaryStage.setTitle("Biblioteca");

        // Create the registration form grid pane
        this.gridPane = createRegistrationGenerador();
        // Add UI controls to the registration form grid pane
        (new Libros()).setId_secuencial(1);//set 1 id libros
        
        biblioteca = new LibroController();
        
        biblioteca.llenarAnios();
        biblioteca.LLenarLibreria();
        addUIControls();
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private GridPane createRegistrationGenerador() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints
        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addInput( TextField campo_texto, String nombre, int posicion) {
        // Add Name Label
        Label nameLabel = new Label(nombre);
        gridPane.add(nameLabel, 0, posicion);

        // Add Name Text Field        
        campo_texto.setPrefHeight(40);
        gridPane.add(campo_texto, 1, posicion);
    }

    private boolean validate( TextField campo_texto, String nombre) {
        System.out.println("-------------->"+campo_texto.getText());
        if (campo_texto.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a " + nombre + ".");
            return true;
        }
        return false;
    }
    private GridPane gridPane;
    private LibroController biblioteca;
    private Libros libro_actual;
    private Anio anio_actual;//es el q se elige en campo anios
    private Mes mes_actual;//es el q se elige en campo anios
    private void addUIControls() {
        // Add Header
        Label headerLabel = new Label("Registration Generator");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // Add campos
        final TextField author = new TextField();
        this.addInput( author, "Autor", 1);
        final TextField genre = new TextField();
        this.addInput( genre, "Genero", 2);
        final TextField publisher = new TextField();
        this.addInput( publisher, "Publicadora", 3);
        final TextField title = new TextField();
        this.addInput( title, "Titulo", 4);
        final TextField numero = new TextField();
        this.addInput( numero, "Numero", 5);
        this.cargarAnios(gridPane,6);
        
        int posicion_boton = 9;
        // Add Submit Button
        Button submitButton = new Button("Crear");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, posicion_boton, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));
        final int posicion_tabla = 7;
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (validate( author, "Autor")) {                    
                    return;
                };
                if (validate( genre, "Genero")) {
                    return;
                };
                if (validate( publisher, "Publicadora")) {
                    return;
                };
                if (validate( title, "Titulo")) {
                    return;
                };
                if (validate(numero, "Numero")) {
                    return;
                };
                
                try {
                    Libros Generador = new Libros(author.getText(), genre.getText(), publisher.getText(), title.getText(), numero.getText(),mes_actual);
                    //(fabricante.getText(),modelo.getText(),codigo.getText());
                    
                    biblioteca.addLibro(Generador);
                    showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Se creo  el libro" + title.getText() + " de " + author.getText());
                    //System.out.println(gc.lista_generadores);
                    
                    cargarListaGeneradores( posicion_tabla);
                    /**/
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        cargarListaGeneradores( posicion_tabla);
    }
    
    private void cargarAnios(final GridPane gridPane, final int posicion) {
        Label GeneradorLabel = new Label("Anio : ");
        gridPane.add(GeneradorLabel, 0, posicion);
        
         List<Anio> anios = this.biblioteca.getAnios();                 
        
        ObservableList<Anio> observableList = FXCollections.observableList(anios);
        ListView<Anio> itemsz = new ListView<Anio>(observableList);
        itemsz.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Anio>() {
             @Override
             public void changed(ObservableValue<? extends Anio> observable, Anio oldValue, Anio newValue) {
                 anio_actual = observable.getValue();
                 cargarMes(gridPane,anio_actual,posicion+1);
             }
        });
        gridPane.add(itemsz, 1, posicion);
    }
    
    private void cargarMes(GridPane gridPane, Anio anio,int posicion) {
        Label GeneradorLabel = new Label("Mes : ");
        gridPane.add(GeneradorLabel, 0, posicion);
        
         List<Mes> mess = anio.getMess();                 
        
        ObservableList<Mes> observableList = FXCollections.observableList(mess);
        ListView<Mes> itemsz = new ListView<Mes>(observableList);
        itemsz.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mes>() {             
            @Override
            public void changed(ObservableValue<? extends Mes> observable, Mes oldValue, Mes newValue) {
                mes_actual = observable.getValue();
            }
        });
        gridPane.add(itemsz, 1, posicion);
    }

    private void cargarListaGeneradores(int posicionTabla) {
        Label GeneradorLabel = new Label("Biblioteca : ");
        gridPane.add(GeneradorLabel, 0, posicionTabla+1);

        
                ObservableList<Libros> observableList = FXCollections.observableList( biblioteca.getBiblioteca());
                ListView<Libros> itemsz = new ListView<Libros>(observableList);
                itemsz.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Libros>() {
                    
            @Override
            public void changed(ObservableValue<? extends Libros> observable, Libros oldValue, Libros newValue) {
                 //System.out.println("--->"+observable.getValue().getTitle());                        
                        libro_actual = observable.getValue();
                        gridPane.getChildren().clear();
                        addUIControlsParams(gridPane);//se vuelve a cargar la pantalla de generadores
            }
                });
                gridPane.add(itemsz, 1, posicionTabla+1);
         /**/
    }

    private void addUIControlsParams(final GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Registration Params");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // Add Name Label
        Label nameLabel = new Label("Nombre : ");
        gridPane.add(nameLabel, 0, 1);

        // Add Name Text Field
        final TextField nombre = new TextField();
        nombre.setPrefHeight(40);
        gridPane.add(nombre, 1, 1);

        // Add Email Label
        Label emailLabel = new Label("rango_minimo : ");
        gridPane.add(emailLabel, 0, 2);

        // Add Email Text Field
        final TextField rango_minimo = new TextField();
        rango_minimo.setPrefHeight(40);
        gridPane.add(rango_minimo, 1, 2);

        // Add Password Label
        Label passwordLabel = new Label("Rango_maximo : ");
        gridPane.add(passwordLabel, 0, 3);

        // Add Password Field
        final TextField rango_maximo = new TextField();
        rango_maximo.setPrefHeight(40);
        gridPane.add(rango_maximo, 1, 3);
        ///////////////////////////////////////////////////////
        // Add Password Label
        Label valor_actualLabel = new Label("Valor_actual : ");
        gridPane.add(valor_actualLabel, 0, 4);

        // Add Password Field
        final TextField valor_actual = new TextField();
        valor_actual.setPrefHeight(40);
        gridPane.add(valor_actual, 1, 4);
        ////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////
        cargarAlarmas(gridPane);
        ////////////////////////////////////////////////////////

        // Add Submit Button
        Button submitButton = new Button("Crear");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 1, 6);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nombre.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a nombre");
                    return;
                }
                if (rango_minimo.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a rango_minimo");
                    return;
                }
                if (rango_maximo.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a rango_maximo");
                    return;
                }
                if (valor_actual.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a valor_actual");
                    return;
                }
                if (!isInteger(rango_minimo.getText())) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Debe ser un numero rango_minimo");
                    return;
                }
                if (!isInteger(rango_maximo.getText())) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Debe ser un numero rango_maximo");
                    return;
                }
                if (!isInteger(valor_actual.getText())) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Debe ser un numero valor_actual");
                    return;
                }
                /*
                 if(alarma_actual == null) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Debe elegir Alarma");
                    return;                    
                }*/
 /*
                 //int id, String nombre, int rango_minimo, int rango_maximo, int valor_actual, Alarma tipo_alarma
                ParametroGenerador parametro = new ParametroGenerador(1,nombre.getText(),Integer.parseInt(rango_minimo.getText()),Integer.parseInt(rango_maximo.getText()),Integer.parseInt(valor_actual.getText()),alarma_actual);    
                generador_actual.addParametro(parametro);
                
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Se creo  el Parametro de" + generador_actual.getModelo()+ " de " + generador_actual.getFabricante());
                System.out.println(gc.lista_generadores);
                cargarlistaParametros(gridPane);
                 */
            }
        });
        cargarlistaParametros(gridPane);
        // Add regresa Button
        Button regresaButton = new Button("< Regresa");
        regresaButton.setPrefHeight(40);
        regresaButton.setDefaultButton(true);
        regresaButton.setPrefWidth(100);
        gridPane.add(regresaButton, 0, 6);
        GridPane.setHalignment(regresaButton, HPos.CENTER);
        GridPane.setMargin(regresaButton, new Insets(20, 0, 20, 0));
        regresaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();// para volver a lista de generadores
                addUIControls();
            }
        });
        // Add Calcula Button
        Button CalculaButton = new Button("Calcula");
        CalculaButton.setPrefHeight(40);
        CalculaButton.setDefaultButton(true);
        CalculaButton.setPrefWidth(100);
        gridPane.add(CalculaButton, 2, 6);
        GridPane.setHalignment(CalculaButton, HPos.CENTER);
        GridPane.setMargin(CalculaButton, new Insets(20, 0, 20, 0));
        /*
        CalculaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Calculo Realizado!", "El calculo de Media es:"+Parametro_actual.alteracionMedia());                        
            }
        });
         */
    }

    private void cargarlistaParametros(GridPane gridPane) {
        Label GeneradorLabel = new Label("Generadores : ");
        gridPane.add(GeneradorLabel, 0, 7);

        /*
                ObservableList<ParametroGenerador> observableList = FXCollections.observableList( generador_actual.getParametros());
                ListView<ParametroGenerador> itemsz = new ListView<ParametroGenerador>(observableList);
                itemsz.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ParametroGenerador>() {
                    
                    @Override
                    public void changed(ObservableValue<? extends ParametroGenerador> observable, ParametroGenerador oldValue, ParametroGenerador newValue) {
                        System.out.println("--->"+observable.getValue());                        
                        Parametro_actual = observable.getValue();
                        Parametro_actual.generarEventoParametro();
                        showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Iteraccion Creada!", "Se creo una iteraccion de generador");                        
                        if(Parametro_actual.getValor_actual()<= Parametro_actual.getRango_minimo() ||
                               Parametro_actual.getValor_actual()>= Parametro_actual.getRango_maximo() ){
                            showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Alarma ", "Evento fuera de rango: "+Parametro_actual.getValor_actual());                        
                        }
                        cargarlistaParametros(gridPane);
                    }
                });
                gridPane.add(itemsz, 1, 7);
         */
    }

    private void cargarAlarmas(GridPane gridPane) {
        Label GeneradorLabel = new Label("Alarmas : ");
        gridPane.add(GeneradorLabel, 0, 5);

        /*
        ObservableList<Alarma> observableList = FXCollections.observableList(listaAlarmas);
        ListView<Alarma> itemsz = new ListView<Alarma>(observableList);
        itemsz.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Alarma>() {
            @Override
            public void changed(ObservableValue<? extends Alarma> observable, Alarma oldValue, Alarma newValue) {
                alarma_actual = observable.getValue();
            }
        });
        gridPane.add(itemsz, 1, 5);
         */
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
