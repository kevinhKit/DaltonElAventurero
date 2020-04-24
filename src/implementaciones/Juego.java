package implementaciones;

import java.util.ArrayList;
import java.util.HashMap;

import clases.Fondo;
import clases.Item;
import clases.JugadorAnimado;
import clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Juego extends Application{
	//DECLARACION DE VARIABLES
	private int anchoventana = 1100 ;//ASIGNACION DE TAMA�O SIMULTANEO, TANTO PARA EL OBJETO SCENE Y PARA EL OBJETO CANVAS 
	private int altoventana = 700 ;
	public static boolean derecha;
	public static boolean izquierda;
	public static boolean arriba;
	public static boolean abajo;
	private Group root;
	private Scene escena;
	private Canvas lienzo;
	private GraphicsContext graficos;
	private JugadorAnimado jugadorAnimado;
	private Fondo fondo;
	private AnimationTimer animationTimer;
	private Item vidaParcial;
	private Item vidaTotal;
	private Item puntos;
	private Item escudo;
	private Item disparo;
	private ArrayList<Tile> tiles;
	private ArrayList<Tile> tile2;
//	private ArrayList<Item> items;
	public static HashMap< String, Image> imagenes;
//	private int escenarioItem[][]= {
//			{0,0,1,2,3,4,5,6,7,8,9,10},
//			{0,0,1,2,3,4,5,6,7,8,9,10},
//			{0,0,1,2,3,4,5,6,7,8,9,10},
//			{0,0,1,2,3,4,5,6,7,8,9,10},
//			{0,0,1,2,3,4,5,6,7,8,9,10},
//			{0,0,1,2,3,4,5,6,7,8,9,10},
//			{0,0,1,2,3,4,5,6,7,8,9,10},
//			{0,0,1,2,3,4,5,6,7,8,9,10},
//			{0,0,1,2,3,4,5,6,7,8,9,10},
//	};
	private int tilemap2[][] = {
			{0,0,0,0,1,0,2,0,3,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,1,0,0,1},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,1,0,0,1},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,1,0,0,1},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,1,0,0,1},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,1,0,0,1},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,1,0,0,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,0,0,1,0,0,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,1,0,0,1,0,0,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,1,0,0,1,0,0,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,1,0,0,1,0,0,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,1,0,0,1,0,0,1},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,3,0,0,0,0,0,3,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,1,0,0,1,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,1,0,0,1,0,0,0},
	};
	private int tilemaps[][] = {
			{3,1,1,13,13,13,13,13,13,13,13,13,13,13,13,13,13,4,4,4,4,3},
			{3,3,3,13,13,13,13,13,3,13,13,13,13,13,13,13,13,4,4,4,4,3},
			{3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,4,4,4,4,3},
			{3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,4,4,4,4,3},
			{3,13,13,3,13,13,13,13,3,13,13,13,13,13,13,13,13,8,8,8,8,3},
			{3,13,13,3,13,13,13,13,3,13,13,13,13,13,13,13,13,8,8,8,8,3},
			{3,13,13,3,13,13,13,13,3,13,13,13,13,13,13,13,13,7,7,7,7,3},
			{3,13,13,3,13,13,13,13,3,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,13,13,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,13,13,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,13,13,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,13,13,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,13,13,3,13,13,13,13,13,13,13,13,13,13,13,13,13,1,1,1,1,3},
			{3,13,13,3,13,13,13,13,13,13,13,13,13,13,13,13,13,1,1,1,1,3},
			{3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,1,1,1,1,3},
			{3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,1,1,1,1,3},
			{3,3,3,3,13,13,13,13,13,13,13,13,13,13,13,13,13,1,1,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,1,1,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,1,1,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,3,3,3,3,3},
			{3,1,1,3,13,13,7,13,13,7,7,13,13,7,7,13,13,11,11,11,11,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,11,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,1,1,3,7,7,13,13,13,13,13,7,7,13,13,7,7,13,13,13,13,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,1,3,3,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,3,3,0,3,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,3,0,0,0,3,3,13,13,13,13,13,13,13,3,3,13,13,13,13,13,3},
			{3,0,0,0,0,0,3,13,13,13,13,13,13,13,3,3,13,13,13,13,13,3},
			{3,3,0,0,0,3,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,3,3,0,3,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,3,3,3,3,13,7,7,7,13,13,13,13,7,7,13,13,7,7,13,13,3},
			{3,3,3,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
			{13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13},
			{3,3,3,3,7,7,13,13,13,7,7,13,13,13,13,7,7,7,13,13,13,3},
			{3,1,1,3,0,0,4,4,4,0,0,4,4,4,4,0,0,0,4,4,4,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,1,1,3,13,13,13,13,13,7,7,13,13,7,7,13,7,7,13,13,13,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3},
			{3,1,1,3,13,13,7,13,13,13,7,7,13,7,7,13,13,13,7,7,13,3},
			{3,1,1,3,3,13,13,13,13,13,13,13,13,13,13,13,3,3,1,1,1,3},
			{3,1,1,3,0,3,13,13,13,13,13,13,13,13,13,3,0,3,1,1,1,3},
			{3,1,1,3,0,0,3,13,13,13,13,13,13,13,3,0,0,3,1,1,1,3},
			{3,1,1,3,0,0,0,3,13,13,13,13,13,3,0,0,0,3,1,1,1,3},
			{3,1,1,3,0,0,0,3,13,13,13,13,13,3,0,0,0,3,1,1,1,3},
			{3,1,1,3,0,0,3,13,13,13,13,13,3,0,0,0,0,3,1,1,1,3},
			{3,1,1,3,0,3,13,13,13,13,13,3,0,0,0,0,0,3,3,1,1,3},
			{3,1,1,3,3,13,13,13,13,13,3,0,0,0,0,0,0,0,3,1,1,3},
			{3,1,1,3,3,13,13,13,13,13,3,0,0,0,0,0,0,0,3,1,1,3},
			{3,1,1,3,0,3,13,13,13,13,13,3,0,0,0,0,0,0,0,3,1,3},
			{3,1,1,3,0,0,3,13,13,13,13,13,3,0,0,0,0,0,0,3,1,3},
			{3,1,1,3,0,0,0,3,13,13,13,13,13,3,0,0,0,0,3,1,1,3},
			{3,1,1,3,0,0,0,3,13,13,13,13,13,3,0,0,3,3,1,1,1,3},
			{3,1,1,3,0,0,3,13,13,13,13,13,3,0,0,0,3,1,1,1,1,3},
			{3,1,1,3,0,3,13,13,13,13,13,3,0,0,0,0,3,1,1,1,1,3},
			{3,1,1,3,3,13,13,13,13,13,3,0,0,0,0,0,3,1,1,1,1,3},
			{3,1,1,3,3,13,13,13,13,13,3,0,0,0,0,0,3,1,1,1,1,3},
			{3,1,1,3,0,3,13,13,13,13,13,3,0,0,0,0,3,1,1,1,1,3},
			{3,1,1,3,0,0,3,13,13,13,13,13,3,0,0,0,3,1,1,1,1,3},
			{3,1,1,3,0,0,0,3,13,13,13,13,13,3,0,0,3,3,1,1,1,3},
			{3,1,1,3,0,0,0,3,13,13,13,13,13,3,0,0,0,3,3,1,1,3},
			{3,1,1,3,0,0,3,13,13,13,13,13,13,13,3,0,0,0,3,1,1,3},
			{3,1,1,3,0,3,13,13,13,13,13,13,13,13,13,3,0,0,3,1,1,3},
			{3,1,1,3,3,13,13,13,13,13,13,13,13,13,13,13,3,0,3,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,3,3,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,13,3,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,3,3,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,13,3,1,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,3,3,1,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,3,1,1,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,3,1,1,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,3,1,1,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,3,1,1,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,3,1,1,1,1,3},
			{3,1,1,3,13,13,13,13,13,13,13,13,13,13,13,13,3,1,1,1,1,3},
			{3,3,2,1,1,1,7,7,1,1,1,1,1,1,7,7,1,1,1,2,3,3},
			{3,3,2,1,1,1,7,7,1,1,1,1,1,1,7,7,1,1,1,2,3,3},
			{3,3,2,1,1,1,7,7,7,1,1,1,1,7,7,7,1,1,1,2,3,3},
			{3,3,2,1,1,1,1,7,7,7,7,7,7,7,7,1,1,1,1,2,3,3},
			{3,3,2,1,1,1,1,1,7,7,7,7,7,7,1,1,1,1,1,2,3,3},
			{3,3,2,1,1,1,1,1,1,1,7,7,1,1,1,1,1,1,1,2,3,3},
			{3,3,2,2,2,2,1,1,1,1,7,7,1,1,1,1,2,2,2,2,3,3},
			{3,3,3,3,3,2,1,1,1,1,7,7,1,1,1,1,2,3,3,3,3,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3},
			{3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3},
			{3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3},
			{3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3},
			{3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3},
			{3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3},
			{3,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,3},
			{3,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,3},
			{3,12,12,13,13,14,14,15,15,16,16,17,17,18,18,0,0,0,0,0,0,3},
			{3,12,12,13,13,14,14,15,15,16,16,17,17,18,18,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,19,19,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,19,19,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			{3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
			
	};
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage ventana) throws Exception {
		inicializarComponentes();
		gestionEvento();
		ventana.setScene(escena);
		ventana.setTitle("DALTON EL AVENTURERO");
		ventana.show();
		cicloJuego();
		
	}
	public void inicializarComponentes() {
		root = new Group();
		escena = new Scene( root , anchoventana , altoventana);
		lienzo = new Canvas( anchoventana, altoventana);
		root.getChildren().add(lienzo);
		graficos = lienzo.getGraphicsContext2D();//graficos.setGlobalAlpha(0.6); OPACIDAD DE INTERCEPCION ENTRE IMAGENES
		jugadorAnimado = new JugadorAnimado( 170 , 600 , 3 , "personaje" , 1 , "descanso1" );
		fondo = new Fondo( 50 , 0 , 2 , "fuego" , "fuego2");
		vidaTotal = new Item(0 , 976 , 103 , 2 , "vidat" , 0 , 1 );
		imagenes = new HashMap< String , Image>();
		cargarImagenes();
		inicializarTileMaps();
	}
	public void inicializarTileMaps() {
		tiles = new ArrayList<Tile>();
		for ( int i = 0 ; i < tilemaps.length ; i++ ) {
			for( int j = 0 ; j < tilemaps[i].length ; j++ ) {
				if(tilemaps[i][j]!=0) {
					this.tiles.add(new Tile ( tilemaps[i][j], j*50 ,i*50 , 2, "tile", 50, 50));//-5000
					
				}
			}
		}
		tile2 = new ArrayList<Tile>();
		for ( int i = 0 ; i < tilemap2.length ; i++ ) {
			for( int j = 0 ; j < tilemap2[i].length ; j++ ) {
				if(tilemap2[i][j]!=0) {
					this.tile2.add(new Tile ( tilemap2[i][j], j*80 ,i*100 , 2, "tile2", 80, 100));//-5000
				}
			}
		}
//		items = new ArrayList<Item>();
//		for ( int i = 0 ; i < escenarioItem.length ; i++ ) {
//			for( int j = 0 ; j < escenarioItem[i].length ; j++ ) {
//				if(escenarioItem[i][j]!=0) {
//					this.items.add(new Item ( escenarioItem[i][j], j*80 ,i*100 , 2, "vidat", 0, 0));
//				}
//			}
//		}
	}
	public void cargarImagenes() {
		imagenes.put( "fuego" , new Image("LIENZO1.png"));
		imagenes.put( "fuego2" , new Image("LIENZO2.png"));
		imagenes.put( "personaje", new Image("Dalton.png"));
		imagenes.put("tile", new Image("tilemaps.png"));
		imagenes.put( "tile2", new Image("arboles.png"));
		imagenes.put( "bola" , new Image("bola.png"));
		imagenes.put( "vidat", new Image("corazon.png"));
	}
	public void gestionEvento() {
		escena.setOnKeyPressed( new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evento) {
				switch (evento.getCode().toString()) {
				case "RIGHT":
					derecha = true;
					jugadorAnimado.setAnimacionActual("derecha");
					break;
				case "LEFT":
					izquierda = true;
					jugadorAnimado.setAnimacionActual("izquierda");
					break;
				case "UP":
					arriba = true;
					jugadorAnimado.setAnimacionActual("arriba");
					break;
				case "DOWN":
					abajo = true;
					jugadorAnimado.setAnimacionActual("abajo");
					break;
				case "SPACE":
					jugadorAnimado.setVelocidad(6);
					//DESDE AQUI ACCEDO A LA VELOCIDAD DEL USUARIO Y AL TIEMPO DE DURACION QUE CAMBIAN ESTRE FRAME
					break;
				}	
			}		
		});
		escena.setOnKeyReleased( new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evento) {
				switch (evento.getCode().toString()) {
				case "RIGHT":
					derecha = false;
					break;
				case "LEFT":
					izquierda = false;
					break;
				case "UP":
					arriba = false;
					break;
				case "DOWN":
					abajo = false;
					break;
				case "SPACE":
					jugadorAnimado.setVelocidad(3);
					break;
				}	
			}
			
		});
	}

	public void cicloJuego() {
		long tiempoInicial = System.nanoTime();
		animationTimer = new AnimationTimer() {
			@Override
			public void handle(long tiempoActual) {
				double t = ( tiempoActual - tiempoInicial ) / 1000000000.0 ;
				actualizarEstado(t);
				pintar();
			}	
		};
		animationTimer.start();
	}
	public void actualizarEstado(double t) {
		jugadorAnimado.verificarColisionesItem(vidaTotal);
		jugadorAnimado.verificarColisionesTile(tiles);
		jugadorAnimado.calcularFrame(t);
		jugadorAnimado.mover(tiles.get(tiles.size()-1).getY());
		fondo.mover(jugadorAnimado.getY());
		for(int i = 0 ; i < tiles.size() ; i++ ) {
			tiles.get(i).mover(jugadorAnimado.getY());
		}
		for(int i = 0 ; i < tile2.size() ; i++ ) {
			tile2.get(i).mover(jugadorAnimado.getY());
		}
		vidaTotal.mover(jugadorAnimado.getY());
		
	}
	public void pintar() {
		fondo.pintar(graficos);
		for(int i = 0 ; i < tiles.size() ; i++ ) {
			tiles.get(i).pintar(graficos);
		}
		for(int i = 0 ; i < tile2.size() ; i++ ) {
			tile2.get(i).pintar(graficos);
		}
		vidaTotal.pintar(graficos);
		jugadorAnimado.pintar(graficos);
		graficos.setFill(Color.AQUA);
		graficos.fillRect(7, 10, 100, 15);
		graficos.setFill(Color.BLACK);//
		graficos.fillText("VIDAS : " + jugadorAnimado.getVidas(), 32, 22);
	}

}
//DISPAROS ENCAPSULAR COORDENA X INCREMENTAR COORDENADA Y CADA 0.25 SEGUNDOS
//MOVIMIENTOS DE ENEMIGOS COMPARAR COORDENADA DEL PERSONADE CON LA DEL ENEMIGO
//condicionan compara de corrdenadas para pintar o invertir el orden de pintado.
//movimiento escenario solo en el centro