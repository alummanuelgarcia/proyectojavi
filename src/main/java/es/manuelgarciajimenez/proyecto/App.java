package es.manuelgarciajimenez.proyecto;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        
            

            //PERSONAJE
            
            //GRUPO
            //Cabeza
            Circle cabeza = new Circle(15,10,10);
            //root.getChildren().add(cabeza);
            
            //Cuerpo
            Rectangle cuerpo = new Rectangle (10,10,7,35);
            //root.getChildren().add(cuerpo);
        
            //Piernas
            Polygon piernas = new Polygon();
            piernas.getPoints().addAll(new Double[]{
                0.0, 0.0,
                40.0, 20.0,
                40.0, 50.0 });
                //root.getChildren().add(piernas);
            
            
            //Agrupacion para el personaje
            Group grupoPersonaje = new Group();
            grupoPersonaje.getChildren().add(cabeza);
            grupoPersonaje.getChildren().add(cuerpo);
            grupoPersonaje.getChildren().add(piernas);
            
            //Mover personaje en la escena
            grupoPersonaje.setLayoutX(50);
            grupoPersonaje.setLayoutY(50);
            
            //Mostrarlo en pantalla
            
            Pane pantallaPersonaje = new Pane();
            pantallaPersonaje.getChildren().add(grupoPersonaje);
            
            //ESCENA          
            Scene Escena = new Scene(pantallaPersonaje,800,600);
            stage.setTitle ("ProyectoJuego");
            stage.setScene(Escena);
            stage.show();
            
            
    }

    public static void main(String[] args) {
        launch();
    }

}