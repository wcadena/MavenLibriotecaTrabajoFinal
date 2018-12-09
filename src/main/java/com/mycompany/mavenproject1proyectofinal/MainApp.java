package com.mycompany.mavenproject1proyectofinal;

import Controller.LibroController;
import javafx.scene.image.Image;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import model.Anio;
import model.Libros;
import model.Mes;
import model.UtilLibrosNumero;

public class MainApp extends Application {

    private int id_libros;
    MenuBar menuBar = new MenuBar();
    
    private Stage primaryStage_master;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //this.gc = new GeneradorController();
        primaryStage.setTitle("Biblioteca");

        // Create the registration form grid pane
        this.gridPane = inicializarPanelGrid();
        // Add UI controls to the registration form grid pane
        (new Libros()).setId_secuencial(1);//set 1 id libros
        
        biblioteca = new LibroController();
        
        biblioteca.llenarAnios();
        biblioteca.LLenarLibreria();
        //addUIControls();
        this.addMenu();
        addMenuReporte();
        VBox root = new VBox(menuBar,gridPane);
    

        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(root, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);

        primaryStage.show();
        
        bienvenida();
        this.primaryStage_master = primaryStage;
    }
   

    private GridPane inicializarPanelGrid() {
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

     private void bienvenida() throws FileNotFoundException{        
        
        Image image1 = new Image("File:Hemeroteca.gif");
        ImageView ima1 = new ImageView(image1);
        GridPane.setHalignment(ima1, HPos.CENTER);
        gridPane.add(ima1, 1, 1);
       
        Image image2 = new Image("File:Stanlee.gif");
        ImageView ima2 = new ImageView(image2);
        GridPane.setHalignment(ima2, HPos.CENTER);
        gridPane.add(ima2, 1, 2);
        
        Label headerLabel = new Label("Instricciones:");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 1, 3, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));
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
    
    private void addMenu(){
        //Where the GUI is created:
        
        Menu menu = new Menu("Menu");
        MenuItem menuItem1 = new MenuItem("Lista de Libros");
        MenuItem menuItem2 = new MenuItem("Nuevo");

        menu.getItems().add(menuItem1);
        menu.getItems().add(menuItem2);
        
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
                cargarListaGeneradores( 2, biblioteca.getBiblioteca());
            }
        });
        
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
                addUIControls() ;
            }
        });
        
        menuBar.getMenus().add(menu);
    }
    private void addMenuReporte(){
        //Where the GUI is created:
        
        Menu menuR = new Menu("Reportes");
        MenuItem menuItem1 = new MenuItem("Reporte de meses que no se registro compras de revistas");
        MenuItem menuItem2 = new MenuItem("Reporte por rango trimestre mostrar promedio de revistas recibidas");
        MenuItem menuItem3 = new MenuItem("Reporte de continuidad, mas meses (Mas Numeros) , soportar duplicidad de datos.");
        MenuItem menuItem4 = new MenuItem("Reporte ordenado por numero de cada revista que se recibio en el anio");
        MenuItem menuItem5 = new MenuItem("Revistas disponibles por mes");        
        MenuItem menuItem6 = new MenuItem("Reportes revistas ordenado");        
        
        menuR.getItems().add(menuItem5);
        menuR.getItems().add(menuItem1);
        menuR.getItems().add(menuItem2);
        menuR.getItems().add(menuItem3);
        menuR.getItems().add(menuItem4);
        
        menuItem5.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
                reporteTotal_5() ;
            }
        });
        
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
                reporteTotal_1();
            }
        });
        
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
                reporteTotal_2() ;
            }
        });
        menuItem3.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
                reporteTotal_3() ;
            }
        });
        
        menuItem4.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
                reporteTotal_4() ;
            }
        });
        
        menuBar.getMenus().add(menuR);
    }
    private void addUIControls() {
        // Add Header
        Label headerLabel = new Label("Registration Libro");
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
        this.cargarAnios(gridPane,6,"");
        
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
                    
                    cargarListaGeneradores( posicion_tabla, biblioteca.getBiblioteca() );
                    /**/
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        cargarListaGeneradores( posicion_tabla, biblioteca.getBiblioteca());
    }
    
    private void cargarAnios(final GridPane gridPane, final int posicion,final String titulo) {
        Label GeneradorLabel = new Label("Anio "+titulo+": ");
        gridPane.add(GeneradorLabel, 0, posicion);
        
         List<Anio> anios = this.biblioteca.getAnios();                 
        
        ObservableList<Anio> observableList = FXCollections.observableList(anios);
        ListView<Anio> itemsz = new ListView<Anio>(observableList);
        itemsz.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Anio>() {
             @Override
             public void changed(ObservableValue<? extends Anio> observable, Anio oldValue, Anio newValue) {
                 anio_actual = observable.getValue();
                 cargarMes(gridPane,anio_actual,posicion+1,titulo);
             }
        });
        gridPane.add(itemsz, 1, posicion);
    }
    
    private void cargarMes(GridPane gridPane, Anio anio,int posicion,String titulo) {
        Label GeneradorLabel = new Label("Mes "+titulo+": ");
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
    private Anio anio_actual2;
    private void cargarAnios2(final GridPane gridPane, final int posicion, final String titulo) {
        Label GeneradorLabel = new Label("Anio "+titulo+": ");
        gridPane.add(GeneradorLabel, 0, posicion);
        
         List<Anio> anios = this.biblioteca.getAnios();                 
        
        ObservableList<Anio> observableList = FXCollections.observableList(anios);
        ListView<Anio> itemsz = new ListView<Anio>(observableList);
        itemsz.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Anio>() {
             @Override
             public void changed(ObservableValue<? extends Anio> observable, Anio oldValue, Anio newValue) {
                 anio_actual2 = observable.getValue();
                 cargarMes2(gridPane,anio_actual,posicion+1,titulo);
             }
        });
        gridPane.add(itemsz, 1, posicion);
    }
    private Mes mes_actual2;
    private void cargarMes2(GridPane gridPane, Anio anio,int posicion, String titulo) {
        Label GeneradorLabel = new Label("Mes "+titulo+": ");
        gridPane.add(GeneradorLabel, 0, posicion);
        
         List<Mes> mess = anio.getMess();                 
        
        ObservableList<Mes> observableList = FXCollections.observableList(mess);
        ListView<Mes> itemsz = new ListView<Mes>(observableList);
        itemsz.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Mes>() {             
            @Override
            public void changed(ObservableValue<? extends Mes> observable, Mes oldValue, Mes newValue) {
                mes_actual2 = observable.getValue();
            }
        });
        gridPane.add(itemsz, 1, posicion);
    }
    private Anio solo_anio_actual;
    private void cargarSoloAnios(final GridPane gridPane, final int posicion,final String titulo) {
        Label GeneradorLabel = new Label("Anio "+titulo+": ");
        gridPane.add(GeneradorLabel, 0, posicion);
        
         List<Anio> anios = this.biblioteca.getAnios();                 
        
        ObservableList<Anio> observableList = FXCollections.observableList(anios);
        ListView<Anio> itemsz = new ListView<Anio>(observableList);
        itemsz.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Anio>() {
             @Override
             public void changed(ObservableValue<? extends Anio> observable, Anio oldValue, Anio newValue) {
                 solo_anio_actual = observable.getValue();
                 
             }
        });
        gridPane.add(itemsz, 1, posicion);
    }
    
private void cargarSoloMes(GridPane gridPane, ArrayList<Mes> meses,int posicion, String titulo) {
        Label GeneradorLabel = new Label("Meses "+titulo+": ");
        gridPane.add(GeneradorLabel, 0, posicion);
        
         List<Mes> mess = meses;                 
        
        ObservableList<Mes> observableList = FXCollections.observableList(mess);
        ListView<Mes> itemsz = new ListView<Mes>(observableList);
        
        gridPane.add(itemsz, 1, posicion);
    }

    

    private void cargarListaGeneradores(int posicionTabla, List<Libros> libros) {

        
        Label GeneradorLabel = new Label("Biblioteca : ");
        gridPane.add(GeneradorLabel, 0, posicionTabla + 1);

        //Collections.sort(libros);//para ordenar segun titulo
        ObservableList<Libros> observableList = FXCollections.observableList(libros);

        TableView<Libros> table = new TableView<Libros>();
        table.setEditable(true);

        TableColumn title = new TableColumn("Titulo");
        title.setCellValueFactory(
                new PropertyValueFactory<Libros, String>("title"));

        TableColumn author = new TableColumn("Autor");
        author.setCellValueFactory(
                new PropertyValueFactory<Libros, String>("author"));

        TableColumn genre = new TableColumn("Genero");
        genre.setCellValueFactory(
                new PropertyValueFactory<Libros, String>("genre"));

        TableColumn publisher = new TableColumn("Publicadora");
        publisher.setCellValueFactory(
                new PropertyValueFactory<Libros, String>("publisher"));

        TableColumn numero = new TableColumn("Número");
        numero.setCellValueFactory(
                new PropertyValueFactory<Libros, String>("numero"));

        TableColumn mes = new TableColumn("Fecha");
        mes.setCellValueFactory(
                new PropertyValueFactory<Libros, Mes>("mes"));

        table.setItems(observableList);
        table.getColumns().addAll(title, author, genre, publisher, numero, mes);

        gridPane.add(table, 1, posicionTabla + 1);
        

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Libros>() {

            @Override
            public void changed(ObservableValue<? extends Libros> observable, Libros oldValue, Libros newValue) {
                //System.out.println("--->"+observable.getValue().getTitle());                        
                libro_actual = observable.getValue();
                gridPane.getChildren().clear();
                addUIControlsParams(gridPane, libro_actual);//se vuelve a cargar la pantalla de generadores
            }
        });
    }
    
    private void reporteTotal_5(){
        
        // Add Header
        Label headerLabel = new Label("Revistas disponibles por mes");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));
        
        
        this.cargarAnios(gridPane,2,"");
            
            
            int posicion_boton = 5;
        // Add Submit Button
        Button submitButton = new Button("Buscar");
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
                Mes mes_reporte_actual = mes_actual;
                
                cargarListaGeneradores(posicion_tabla+1,mes_reporte_actual.getBiblioteca_mes());

            }
        });
        
        
            
    }
    
    private void reporteTotal_1(){
        
        // Add Header
        Label headerLabel = new Label("Reporte de meses que no se registro compras de revistas");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));
        
        
        this.cargarSoloAnios(gridPane,1,"");
            
            
            int posicion_boton = 5;
        // Add Submit Button
        Button submitButton = new Button("Buscar");
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
                ArrayList<Mes> meses =solo_anio_actual.getMess();
                ArrayList<Mes> meses_rep1 =new ArrayList<Mes>();
                for (int i = 0; i < meses.size(); i++) {
                    Mes auxMes1 = meses.get(i);
                    if(auxMes1.getBiblioteca_mes().size() ==0 ){
                        meses_rep1.add(auxMes1);
                    }
                }
                cargarSoloMes(gridPane, meses_rep1,posicion_tabla+1,"Sin Libros" );

            }
        });
        
        
            
    }
    
    private void reporteTotal_2(){
        
        // Add Header
        Label headerLabel = new Label("Reporte por rango trimestre mostrar promedio de revistas recibidas");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));
        
        
        this.cargarSoloAnios(gridPane,1,"");
            
            
            int posicion_boton = 5;
        // Add Submit Button
        Button submitButton = new Button("Buscar");
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
                ArrayList<Mes> meses =solo_anio_actual.getMess();
                ArrayList<Mes> meses_rep1 =new ArrayList<Mes>();
                double primTrim=0;
                double secTrim=0;
                double tercTrim=0;
                double cuartTrim=0;
                for (int i = 0; i < meses.size(); i++) {
                    Mes auxMes1 = meses.get(i);
                    if(i == 0 || i == 1 || i == 2){
                        primTrim += auxMes1.getBiblioteca_mes().size();
                    }
                    if(i == 3 || i == 4 || i == 5){
                        secTrim += auxMes1.getBiblioteca_mes().size();
                    }
                    if(i == 6 || i == 7 || i == 8){
                        tercTrim += auxMes1.getBiblioteca_mes().size();
                    }
                    if(i == 9 || i == 10 || i == 11){
                        cuartTrim += auxMes1.getBiblioteca_mes().size();
                    }
                    
                }
                TextArea textField = new TextArea();  
                String r2 ="";
                r2 += "\t Reporte: "+solo_anio_actual.getAnio()+"\n";
                r2 += "Primer\tSegundo\tTercero\tCuarta\t\n";
                r2 += String.format( "%.2f",primTrim/3)+"\t\t" +String.format( "%.2f",secTrim/3)+ "\t\t" +
                        String.format( "%.2f",tercTrim/3)+ "\t\t" +String.format( "%.2f",cuartTrim/3)+ "\t\n";
                textField.setText(r2);
                
                gridPane.add(textField, 0, posicion_tabla+1,2,4);                
            }
        });                            
    }
    private void reporteTotal_3(){
        
        // Add Header
        Label headerLabel = new Label("Reporte de continuidad, mas meses (Mas Numeros) , soportar duplicidad de datos.");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));
        
        
           
            
            int posicion_boton = 5;
        // Add Submit Button
        Button submitButton = new Button("Buscar");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, posicion_boton, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));
        final int posicion_tabla = 7;
        
        final List<UtilLibrosNumero> re3 = new ArrayList();
        
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Libros> aux_lib = biblioteca.getBiblioteca();
                for (int i = 0; i < aux_lib.size(); i++) {
                    Libros auxMes1 = aux_lib.get(i);    
                    int posicion = UtilLibrosNumero.findCustomerByid(re3, auxMes1.getTitle());
                    
                   
                    if(posicion >=0){
                        UtilLibrosNumero auxR3 = re3.get(posicion);
                        List<String> auxNumx1= auxR3.getNumeros();
                        auxNumx1.add(auxMes1.getNumero());
                        auxR3.setNumeros(auxNumx1);
                        re3.set(posicion,auxR3);
                    }else{
                        UtilLibrosNumero auxR33 = new UtilLibrosNumero(auxMes1.getTitle());
                        List<String> auxNumx12= auxR33.getNumeros();
                        auxNumx12.add(auxMes1.getNumero());
                        auxR33.setNumeros(auxNumx12);                        
                        re3.add(auxR33);
                    }
                }          
                
                TextArea textField = new TextArea();  
                String r2 ="";
                r2 += "\t Reporte: Por Titulo \n";
                r2 += "Titulo\t\t\t\t\tNumero\n";
                
                Collections.sort(re3);                
                for (int j = 0; j < re3.size(); j++) {
                    UtilLibrosNumero auxrep34 = re3.get(j);  
                    r2 += auxrep34.getTitulo()+"\t\t\t\t\t"+auxrep34.getNumeros()+"\n";
                }
                textField.setText(r2);
                
                gridPane.add(textField, 0, posicion_tabla+1,2,4);
                

            }
        });
        
        
            
    }

    private void reporteTotal_4(){
        
        // Add Header
        Label headerLabel = new Label("Reporte ordenado por numero de cada revista que se recibio en el anio.");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));                           
            
            int posicion_boton = 5;
        // Add Submit Button
        Button submitButton = new Button("Buscar");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, posicion_boton, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));
        final int posicion_tabla = 7;
        
        final List<UtilLibrosNumero> re3 = new ArrayList();
        
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Libros> aux_lib = biblioteca.getBiblioteca();
                Collections.sort(aux_lib,Libros.PorNumero);//para ordenar segun titulo                
                
                cargarListaGeneradores(posicion_tabla+1,aux_lib);
            }
        });
        
        
            
    }
    
    private void addUIControlsParams(final GridPane gridPane,Libros libro) {
        // Add Header
        Label headerLabel = new Label("Detalle Libro");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        // Add Name Label
        final TextField author = new TextField();
        this.addInput( author, "Autor", 1);
        author.setText(libro.getAuthor());
        final TextField genre = new TextField();
        this.addInput( genre, "Genero", 2);
        genre.setText(libro.getGenre());
        final TextField publisher = new TextField();
        this.addInput( publisher, "Publicadora", 3);
        publisher.setText(libro.getPublisher());
        final TextField title = new TextField();
        this.addInput( title, "Titulo", 4);
        title.setText(libro.getTitle());
        final TextField numero = new TextField();
        this.addInput( numero, "Numero", 5);
        numero.setText(libro.getNumero());
        ////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////
        //cargarAlarmas(gridPane);
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
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Mensage!", "No es parte de Tarea");
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
        /*Button CalculaButton = new Button("Calcula");
        CalculaButton.setPrefHeight(40);
        CalculaButton.setDefaultButton(true);
        CalculaButton.setPrefWidth(100);
        gridPane.add(CalculaButton, 2, 6);
        GridPane.setHalignment(CalculaButton, HPos.CENTER);
        GridPane.setMargin(CalculaButton, new Insets(20, 0, 20, 0));*/
        /*
        CalculaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Calculo Realizado!", "El calculo de Media es:"+Parametro_actual.alteracionMedia());                        
            }
        });
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
