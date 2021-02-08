package es.manuelgarciajimenez.proyecto;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    boolean finPartida;
    Timeline animacionPersonaje;
    int puntuacion=0;
    int puntuacionMaxima=0;
    int tamañoTexto=50;
    
    //ImageView
    Image imagenFondo = new Image(getClass().getResourceAsStream("/images/fondo-del-videojuego-horizonte-de-la-ciudad-105360575.jpg"));
    ImageView verImagen = new ImageView(imagenFondo);
    Image imagenFondo2 = new Image(getClass().getResourceAsStream("/images/fondo-del-videojuego-horizonte-de-la-ciudad-105360575.jpg"));
    ImageView verImagen2 = new ImageView(imagenFondo2);
    
    //Grupo Enemigo 1 
    Group grupoEnemigo = new Group();
    //Grupo Enemigo 2 
    Group grupoEnemigo2 = new Group();
    //Grupo Enemigo 3 
    Group grupoEnemigo3 = new Group();
    
    //TEXTO DERROTA
    HBox paneDerrota = new HBox();
    Text textoPerdido = new Text ("Has perdido!!!. ESCAPE para reiniciar");
    //TEXTO PUNTUACION
    Text textoPuntuacionActual = new Text ("0");
    
    
    @Override
    public void start(Stage stage) {

        //ESCENA          
        stage.setTitle("ProyectoJuego");
        stage.setResizable(false);
        stage.setScene(escena);
        stage.show();
               
        
        
        
        //Fondo imagen
        //Image imagenFondo = new Image(getClass().getResourceAsStream("/images/fondo-del-videojuego-horizonte-de-la-ciudad-105360575.jpg"));
        //ImageView verImagen = new ImageView(imagenFondo);
        verImagen.setLayoutX(fondoPosX);
        //Image imagenFondo2 = new Image(getClass().getResourceAsStream("/images/fondo-del-videojuego-horizonte-de-la-ciudad-105360575.jpg"));
        //ImageView verImagen2 = new ImageView(imagenFondo2);
        verImagen2.setLayoutX(fondoPosX2);

        pantallaJuego.getChildren().add(verImagen);
        pantallaJuego.getChildren().add(verImagen2);       
        
        reinicioJuego();
        
        
        //Layouts Pantalla
        
        //Layout principal
        HBox panePuntuacion = new HBox();        
        panePuntuacion.setTranslateY(10);
        panePuntuacion.setMinWidth(escenaTamX);
        panePuntuacion.setAlignment(Pos.CENTER);
        panePuntuacion.setSpacing(100);
        pantallaJuego.getChildren().add(panePuntuacion);
        
        //Layout puntuacion actual
        HBox panePuntuacionActual = new HBox();
        panePuntuacionActual.setTranslateX(150);
        panePuntuacionActual.setTranslateY(12);
        panePuntuacionActual.setSpacing(10);
        pantallaJuego.getChildren().add(panePuntuacionActual);
        
        //Texto puntuacion actual
        Text textoPuntuacion = new Text ("Puntuación:");
        textoPuntuacion.setLayoutX(30);
        textoPuntuacion.setFont(Font.font(40));
        textoPuntuacion.setFill(Color.GREEN);
        
        panePuntuacionActual.getChildren().add(textoPuntuacion);
        
        //Texto Puntuacion
        
        textoPuntuacionActual.setFont(Font.font(tamañoTexto));
        textoPuntuacionActual.setFill(Color.GREEN);
                        
        panePuntuacion.getChildren().add(textoPuntuacionActual);
        
        //PUNTUACION MAXIMA
        HBox paneMaxima = new HBox();        
        paneMaxima.setTranslateY(400);
        paneMaxima.setTranslateX(150);
        paneMaxima.setSpacing(10);
        pantallaJuego.getChildren().add(paneMaxima);
        
        Text textoMaxima = new Text ("Puntuación Máxima:");
        textoMaxima.setLayoutX(10);
        textoMaxima.setFont(Font.font(30));
        textoMaxima.setFill(Color.GREEN);
        
        paneMaxima.getChildren().add(textoMaxima);
        
        HBox panePuntuacionMaxima = new HBox();        
        panePuntuacionMaxima.setTranslateY(400);
        panePuntuacionMaxima.setTranslateX(100);
        panePuntuacionMaxima.setSpacing(10);
        pantallaJuego.getChildren().add(panePuntuacionMaxima);
        
        Text textoPuntuacionMaxima = new Text ("0");
        textoPuntuacionMaxima.setLayoutX(10);
        textoPuntuacionMaxima.setFont(Font.font(30));
        textoPuntuacionMaxima.setFill(Color.GREEN);
        
        paneMaxima.getChildren().add(textoPuntuacionMaxima);
                               
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
        enemigo2X = random.nextInt(1600) + enemigo3X + 400;
        enemigo3X = random.nextInt(1600) + enemigoX + 350;

        //Movimiento del personaje
                //timeline
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
                        
                        //FIN PARTIDA
                        if (colisionVacia == false) {                           
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
                            enemigo2X = random.nextInt(1600) + enemigo3X + 400;
                            grupoEnemigo2.setLayoutX(enemigo2X);
                        }
                        //movimiento enemigo3
                        enemigo3X += enemigoVelocidad;
                        grupoEnemigo3.setLayoutX(enemigo3X);
                        
                        //aparicion aleatoria enemigo3
                        if (enemigo3X < -200) {
                            enemigo3X = random.nextInt(1600) + enemigoX + 350;
                            grupoEnemigo2.setLayoutX(enemigo3X);
                        }
                        //Puntuacion enemigoX
                        if(personajeX>=enemigoX && personajeX<enemigoX-enemigoVelocidad){                            
                            puntuacion++;
                            textoPuntuacionActual.setText(String.valueOf(puntuacion));
                        }                        
                        //Puntuacion enemigo2X
                        if(personajeX>=enemigo2X && personajeX<enemigo2X-enemigoVelocidad){                            
                            puntuacion++;
                            textoPuntuacionActual.setText(String.valueOf(puntuacion));
                        }
                        //Puntuacion enemigo3x
                        if(personajeX>=enemigo3X && personajeX<enemigo3X-enemigoVelocidad){                            
                            puntuacion++;
                            textoPuntuacionActual.setText(String.valueOf(puntuacion));
                        }
                        
                        //INCREMENTO VELOCIDAD ENEMIGOS
                        if(puntuacion==5){
                            enemigoVelocidad=-7;
                        }
                        if (puntuacion == 10){
                            enemigoVelocidad = -9;                          
                        }                        
                        if (puntuacion == 15){
                            enemigoVelocidad = -11;
                        }
                        if (puntuacion == 20){
                            enemigoVelocidad=-15;
                        }
                        if (puntuacion == 30){
                            enemigoVelocidad=-20;
                        }
                        if (puntuacion == 40){
                            enemigoVelocidad=-25;
                        }
                        if (puntuacion == 50){
                            enemigoVelocidad=-40;
                        }
                        
                        //poner en pantalla puntuacion maxima
                        
                        if (puntuacion > puntuacionMaxima){
                            puntuacionMaxima = puntuacion;
                            textoPuntuacionMaxima.setText(String.valueOf(puntuacionMaxima));
                        }                                                                                                                
                        //Movimiento Escena
                        
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
                        textoDerrota();
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
                                    pantallaJuego.getChildren().remove(paneDerrota);
                                    paneDerrota.getChildren().remove(textoPerdido);
                                    
                                    
                            }
                            
                        });
                    }
                }

                ));
                animacionPersonaje.setCycleCount(Timeline.INDEFINITE);
                animacionPersonaje.play();
    }

    public void reinicioJuego() {               
        //Variables 
        personajeX=70;
        personajeY=70;
        personajeVelocidadY = 0;
        personajeVelocidadX = 0;
        escenaVelocidad = 4;
        fondoPosX = 0;
        fondoPosX2 = 800;
        enemigoX = 800;
        enemigoY = 10;
        enemigo2X = 800;
        enemigo2Y = 200;
        enemigo3X = 800;
        enemigo3Y = 110;
        enemigoVelocidad = -5;
        puntuacion=0;
        textoPuntuacionActual.setText(String.valueOf(puntuacion));
                                       
        //Escena
        verImagen.setLayoutX(fondoPosX);
        verImagen2.setLayoutX(fondoPosX2);
        
        //Mover enemigo en la escena
        grupoEnemigo.setLayoutX(enemigoX);
        grupoEnemigo.setLayoutY(enemigoY);
        
        //Mover enemigo 2 en la escena
        grupoEnemigo2.setLayoutX(enemigo2X);
        grupoEnemigo2.setLayoutY(enemigo2Y);
        
        //Mover enemigo 3 en la escena
        grupoEnemigo3.setLayoutX(enemigo3X);
        grupoEnemigo3.setLayoutY(enemigo3Y);
        
        //Distancia Random Enemigos
        enemigoX = random.nextInt(1600) + escenaTamX;
        enemigo2X = random.nextInt(1600) + enemigo3X + 400;
        enemigo3X = random.nextInt(1600) + enemigoX + 350;
                                                         
        finPartida = false;                                      
    }
    
    public void textoDerrota(){
        
        //Layout Perdido
                
        paneDerrota.setTranslateY(200);
        paneDerrota.setMinWidth(escenaTamX);
        paneDerrota.setAlignment(Pos.CENTER);
        paneDerrota.setSpacing(100);
        pantallaJuego.getChildren().add(paneDerrota);
        
        //Texto Perdido
        
        textoPerdido.setLayoutX(30);
        textoPerdido.setFont(Font.font(40));
        textoPerdido.setFill(Color.RED);       
        paneDerrota.getChildren().add(textoPerdido);
    }
    
    
    
    public static void main(String[] args) {
        launch();
    }
}