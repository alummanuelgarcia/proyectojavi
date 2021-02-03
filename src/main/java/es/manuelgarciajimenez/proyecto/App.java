package es.manuelgarciajimenez.proyecto;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    Pane pantallaJuego = new Pane();
    final int escenaTamX = 800;
    final int escenaTamY = 500;
    int personajeX;
    int personajeY;
    Scene escena = new Scene(pantallaJuego, escenaTamX, escenaTamY);
    int personajeVelocidadY;
    int personajeVelocidadX;
    int fondoPosX = 0;
    int fondoPosX2 = 800;
    int escenaVelocidad;
    int enemigoX;
    int enemigoY;
    int enemigo2X;
    int enemigo2Y;
    int enemigo3X;
    int enemigo3Y;
    int enemigoVelocidad;
    Random random = new Random();
    boolean finPartida = false;
    Timeline animacionPersonaje;
    //Grupo Enemigo 1 
    Group grupoEnemigo = new Group();
    //Grupo Enemigo 2 
    Group grupoEnemigo2 = new Group();
    //Grupo Enemigo 3 
    Group grupoEnemigo3 = new Group();
    
    @Override
    public void start(Stage stage) {

        //ESCENA          
        stage.setTitle("ProyectoJuego");
        stage.setResizable(false);
        stage.setScene(escena);
        stage.show();
               

        //Fondo imagen
        Image imagenFondo = new Image(getClass().getResourceAsStream("/images/fondo-del-videojuego-horizonte-de-la-ciudad-105360575.jpg"));
        ImageView verImagen = new ImageView(imagenFondo);
        verImagen.setLayoutX(fondoPosX);
        Image imagenFondo2 = new Image(getClass().getResourceAsStream("/images/fondo-del-videojuego-horizonte-de-la-ciudad-105360575.jpg"));
        ImageView verImagen2 = new ImageView(imagenFondo2);
        verImagen2.setLayoutX(fondoPosX2);

        pantallaJuego.getChildren().add(verImagen);
        pantallaJuego.getChildren().add(verImagen2);
        
        
        reinicioJuego();
        
        
        //PERSONAJE PRINCIPAL
        //Cuerpo
        Rectangle cuerpo = new Rectangle(15, 6, 7, 35);

        //Cabeza
        Circle cabeza = new Circle(20, 10, 10);
        cabeza.setFill(Color.LIGHTCORAL);

        //Brazos
        Rectangle brazos = new Rectangle(20, 25, 10, 5);
        brazos.setFill(Color.LIGHTCORAL);

        //Ojos 
        Rectangle ojos = new Rectangle(24, 5, 3, 3);

        //Piernas
        Rectangle piernas = new Rectangle(15, 40, 20, 10);
        piernas.setFill(Color.BLUE);

        //Mochila
        Rectangle mochila = new Rectangle(0, 21, 15, 20);
        mochila.setFill(Color.DARKMAGENTA);

        //Zona de contacto
        Rectangle contactoPersonaje = new Rectangle(10, 5, 20, 40);
        contactoPersonaje.setVisible(false);

        //Agrupacion para el personaje principal
        Group grupoPersonaje = new Group();

        grupoPersonaje.getChildren().add(contactoPersonaje);
        grupoPersonaje.getChildren().add(brazos);
        grupoPersonaje.getChildren().add(piernas);
        grupoPersonaje.getChildren().add(cuerpo);
        grupoPersonaje.getChildren().add(cabeza);
        grupoPersonaje.getChildren().add(ojos);
        grupoPersonaje.getChildren().add(mochila);

        //Mover personaje en la escena
        grupoPersonaje.setLayoutX(personajeX);
        grupoPersonaje.setLayoutY(personajeY);

        //ENEMIGO 1 
        //Cuerpo
        Rectangle cuerpoEnemigo = new Rectangle(0, 0, 200, 100);

        //Ojos
        Rectangle ojosEnemigo = new Rectangle(1, 10, 30, 30);
        ojosEnemigo.setFill(Color.WHITE);

        //Boca
        Rectangle bocaEnemigo = new Rectangle(1, 70, 50, 15);
        bocaEnemigo.setFill(Color.RED);

        //Zona Contacto
        Rectangle contactoEnemigo = new Rectangle(0, 0, 200, 100);
        contactoEnemigo.setVisible(false);

        //Grupo Enemigo 1 
        
        grupoEnemigo.getChildren().add(contactoEnemigo);
        grupoEnemigo.getChildren().add(cuerpoEnemigo);
        grupoEnemigo.getChildren().add(ojosEnemigo);
        grupoEnemigo.getChildren().add(bocaEnemigo);

        //Mover enemigo en la escena
        grupoEnemigo.setLayoutX(enemigoX);
        grupoEnemigo.setLayoutY(enemigoY);

        //ENEMIGO 2
        //cuerpo
        Rectangle cuerpoEnemigo2 = new Rectangle(0, 0, 50, 300);
        cuerpoEnemigo2.setFill(Color.DIMGRAY);

        //ventana1
        Rectangle ventanas = new Rectangle(15, 10, 20, 20);
        ventanas.setFill(Color.BLUE);

        //ventana2
        Rectangle ventanas2 = new Rectangle(15, 60, 20, 20);
        ventanas2.setFill(Color.BLUE);

        //ventana3
        Rectangle ventanas3 = new Rectangle(15, 110, 20, 20);
        ventanas3.setFill(Color.BLUE);

        //ventanas4
        Rectangle ventanas4 = new Rectangle(15, 160, 20, 20);
        ventanas4.setFill(Color.BLUE);

        //ventanas5
        Rectangle ventanas5 = new Rectangle(15, 210, 20, 20);
        ventanas5.setFill(Color.BLUE);

        //Zona Contacto
        Rectangle contactoEnemigo2 = new Rectangle(0, 0, 50, 300);
        contactoEnemigo2.setVisible(false);

        //Grupo Enemigo 2 
        
        grupoEnemigo2.getChildren().add(contactoEnemigo2);
        grupoEnemigo2.getChildren().add(cuerpoEnemigo2);
        grupoEnemigo2.getChildren().add(ventanas);
        grupoEnemigo2.getChildren().add(ventanas2);
        grupoEnemigo2.getChildren().add(ventanas3);
        grupoEnemigo2.getChildren().add(ventanas4);
        grupoEnemigo2.getChildren().add(ventanas5);

        //mover enemigo 2 en la escena
        grupoEnemigo2.setLayoutX(enemigo2X);
        grupoEnemigo2.setLayoutY(enemigo2Y);

        //Enemigo 3
        //cuerpo
        Rectangle cuerpoEnemigo3 = new Rectangle(0, 0, 200, 90);
        cuerpoEnemigo3.setFill(Color.DARKOLIVEGREEN);

        //boca
        Rectangle bocaenemigo3 = new Rectangle(-30, 50, 40, 10);
        bocaenemigo3.setFill(Color.RED);

        //ojos
        Rectangle ojosenemigo3 = new Rectangle(10, -20, 20, 40);
        ojosenemigo3.setFill(Color.BLACK);

        //Zona de contacto 
        Rectangle contactoEnemigo3 = new Rectangle(0, 0, 200, 90);
        contactoEnemigo3.setVisible(false);

        //Grupo Enemigo 3 
        
        grupoEnemigo3.getChildren().add(contactoEnemigo3);
        grupoEnemigo3.getChildren().add(cuerpoEnemigo3);
        grupoEnemigo3.getChildren().add(bocaenemigo3);
        grupoEnemigo3.getChildren().add(ojosenemigo3);

        //mover enemigo 3 en la escena
        grupoEnemigo3.setLayoutX(enemigo3X);
        grupoEnemigo3.setLayoutY(enemigo3Y);

        //Mostrarlo en patalla
        pantallaJuego.getChildren().add(grupoPersonaje);
        pantallaJuego.getChildren().add(grupoEnemigo);
        pantallaJuego.getChildren().add(grupoEnemigo2);
        pantallaJuego.getChildren().add(grupoEnemigo3);

        //Distancia Random Enemigos
        enemigoX = random.nextInt(1600) + escenaTamX;
        enemigo2X = random.nextInt(1600) + escenaTamX;
        enemigo3X = random.nextInt(1600) + escenaTamX;

        //Movimiento del personaje
        //  timeline
                animacionPersonaje = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (ActionEvent ae) -> {
                    personajeY += personajeVelocidadY;
                    grupoPersonaje.setLayoutY(personajeY);
                    personajeX += personajeVelocidadX;
                    grupoPersonaje.setLayoutX(personajeX);
                    
                    //colision enemigos
                    Shape colisionEnemigo = Shape.intersect(contactoPersonaje, contactoEnemigo);                   
                    boolean colisionVacia = colisionEnemigo.getBoundsInLocal().isEmpty();
                    Shape colisionEnemigo2 = Shape.intersect(contactoPersonaje, contactoEnemigo2);
                    boolean colisionVacia2 = colisionEnemigo2.getBoundsInLocal().isEmpty();                   
                    Shape colisionEnemigo3 = Shape.intersect(contactoPersonaje, contactoEnemigo3);
                    boolean colisionVacia3 = colisionEnemigo3.getBoundsInLocal().isEmpty();
                    
                    
                    if (finPartida == false) {
                        if (colisionVacia == false) {
                            // System.out.println(finPartida);
                            finPartida = true;
                        }
                        if (colisionVacia2 == false){
                            finPartida = true;
                        }
                        if (colisionVacia3 == false){
                            finPartida = true;                          
                        }
                        //movimientos enemigos
                        //movimiento enemigo1
                        enemigoX += enemigoVelocidad;
                        grupoEnemigo.setLayoutX(enemigoX);

                        //aparicion aleatoria enemigo1
                        if (enemigoX < -200) {
                            enemigoX = random.nextInt(1600) + escenaTamX;
                            grupoEnemigo.setLayoutX(enemigoX);
                        }

                        //movimiento enemigo2
                        enemigo2X += enemigoVelocidad;
                        grupoEnemigo2.setLayoutX(enemigo2X);

                        //aparicion aleatoria enemigo2
                        if (enemigo2X < -60) {
                            enemigo2X = random.nextInt(1600) + escenaTamX;
                            grupoEnemigo2.setLayoutX(enemigo2X);
                        }

                        enemigo3X += enemigoVelocidad;
                        grupoEnemigo3.setLayoutX(enemigo3X);

                        if (enemigo3X < -200) {
                            enemigo3X = random.nextInt(1600) + escenaTamX;
                            grupoEnemigo2.setLayoutX(enemigo3X);
                        }

                        fondoPosX -= escenaVelocidad;
                        verImagen.setLayoutX(fondoPosX);
                        fondoPosX2 -= escenaVelocidad;
                        verImagen2.setLayoutX(fondoPosX2);

                        if (fondoPosX == -800) {
                            fondoPosX = 800;
                        }
                        if (fondoPosX2 == -800) {
                            fondoPosX2 = 800;
                        }

                        //Limite mapa superior
                        if (personajeY < 0) {
                            personajeY = 0;
                            personajeVelocidadY = 0;
                        }
                        //Limite mapa inferior

                        //450 porque es la diferencia entre el limite menos lo que mide el personaje
                        if (personajeY > 450) {
                            personajeY = 450;
                            personajeVelocidadY = 0;
                        }
                        //Limite mapa izquierda
                        if (personajeX < 0) {
                            personajeX = 0;
                            personajeVelocidadX = 0;
                        }

                        //Limite mapa derecha
                        if (personajeX > 755) {
                            personajeX = 755;
                            personajeVelocidadX = 755;
                        }

                        escena.setOnKeyPressed((KeyEvent event) -> {
                            switch (event.getCode()) {
                                //Tecla Arriba
                                case UP:
                                    personajeVelocidadY = -10;
                                    break;
                                //Tecla Abajo
                                case DOWN:
                                    personajeVelocidadY = 10;
                                    break;
                                //Tecla Derecha
                                case RIGHT:
                                    personajeVelocidadX = 10;
                                    break;
                                //Tecla Izquierda
                                case LEFT:
                                    personajeVelocidadX = -10;
                                    break;
                            }
                        });

                        animacionPersonaje.setCycleCount(Timeline.INDEFINITE);
                        animacionPersonaje.play();

                        //Boton suelto
                        escena.setOnKeyReleased((KeyEvent event) -> {
                            personajeVelocidadX = 0;
                            personajeVelocidadY = 0;
                        });

                    } //fin partida

                    if (finPartida == true) {
                        escena.setOnKeyPressed((KeyEvent event) -> {
                            switch (event.getCode()) {
                                //Tecla Arriba
                                case UP:
                                    personajeVelocidadY = 0;
                                    break;
                                //Tecla Abajo
                                case DOWN:
                                    personajeVelocidadY = 0;
                                    break;
                                //Tecla Derecha
                                case RIGHT:
                                    personajeVelocidadX = 0;
                                    break;
                                //Tecla Izquierda
                                case LEFT:
                                    personajeVelocidadX = 0;
                                    break;
                                case ESCAPE:
                                    reinicioJuego();
                            }
                            enemigoVelocidad = 0;
                            escenaVelocidad = 0;
                        });
                    }
                }

                ));
                animacionPersonaje.setCycleCount(Timeline.INDEFINITE);
                animacionPersonaje.play();
    }

    public void reinicioJuego() {        
        personajeX=70;
        personajeY=70;
        personajeVelocidadY = 0;
        personajeVelocidadX = 0;
        escenaVelocidad = 4;
        enemigoX = 800;
        enemigoY = 10;
        enemigo2X = 800;
        enemigo2Y = 200;
        enemigo3X = 800;
        enemigo3Y = 110;
        enemigoVelocidad = -5;
               
        //Mover enemigo en la escena
        grupoEnemigo.setLayoutX(enemigoX);
        grupoEnemigo.setLayoutY(enemigoY);
        
        //Distancia Random Enemigos
        enemigoX = random.nextInt(1600) + escenaTamX;
        enemigo2X = random.nextInt(1600) + escenaTamX;
        enemigo3X = random.nextInt(1600) + escenaTamX;

    }
    
    
    
    
    public static void main(String[] args) {
        launch();
    }
}