package clases;

import java.util.ArrayList;

import implementaciones.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Ataque extends ObjetoJuego{
	private int puntos;
	private int vidas;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	public static String tipoDisparo="a";
	private boolean capturado = false;
	public Ataque(int x, int y, int velocidad, String nombreimagen, int puntos, int vidas) {
		super(x, y, velocidad, nombreimagen);
		this.puntos = puntos;
		this.vidas = vidas;
										//		switch(tipoDisparo) {
										//		case "a":
										//			 xImagen=0;
										//			 yImagen=0;
										//			 anchoImagen=900;
										//			 altoImagen=-800;
										//			 ancho=50;
										//			 alto=50;
										//			break;
										//		case "b":
										//			 xImagen=0;
										//			 yImagen=46;
										//			 anchoImagen=50;
										//			 altoImagen=-46;
										//			 ancho=50;
										//			 alto=46;
										//			break;
										//		}
		 this.xImagen=0;
		 this.yImagen=0;
		 this.anchoImagen=900;
		 this.altoImagen=800;
		 ancho=30;
		 alto=30;
	}
	@Override
	public void pintar(GraphicsContext graficos) {
		if(!capturado) {
//		graficos.drawImage(Juego.imagenes.get(nombreimagen), xImagen, yImagen, anchoImagen, altoImagen, x, y , ancho, alto);
//		System.out.println("pintando");
		graficos.drawImage(Juego.imagenes.get(nombreimagen),xImagen,yImagen,anchoImagen,altoImagen,x,y,ancho,alto);
		graficos.strokeRect(x+3, y+3,ancho-6,alto-4);
		}
	}
	@Override
	public void mover(int y3) {
		this.y-=this.velocidad;
		
	}
	public void verificarColisionesEnemigoAnimado(EnemigoAnimado e1,int i,ArrayList<Ataque> ataques) {
		if(!e1.isCapturado()&& !capturado && this.obtenerRegtangulo().getBoundsInLocal().intersects(e1.obtenerRegtangulo().getBoundsInLocal())) {
			e1.setCapturado(true);
			capturado=true;
			JugadorAnimado.puntacion = this.puntos;
			ataques.remove(i);
		}else {
		}
		
	}
//	@Override
//	public void pintar(GraphicsContext graficos) {
////		graficos.drawImage(Juego.imagenes.get(nombreimagen), xImagen, yImagen, anchoImagen, altoImagen, x, y , ancho, alto);
////		System.out.println("pintando");
//		graficos.drawImage(Juego.imagenes.get(nombreimagen), x-420, y-700);
//	}
//	@Override
//	public void mover(int y3) {
//		//this.y+=this.velocidad;
//		
//	}
	@Override
	public Rectangle obtenerRegtangulo() {
		if( this.nombreimagen == "bola") {
			return new Rectangle(x+3, y+3,ancho-6,alto-4);
		}
		return null;
	}
	public boolean isCapturado() {
		return capturado;
	}
	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getVidas() {
		return vidas;
	}
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
	
}
