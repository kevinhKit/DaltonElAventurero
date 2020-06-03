package clases;

import implementaciones.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Item extends ObjetoJuego{
private int puntos;
private int vidas;
private int xImagen;
private int yImagen;
private int anchoImagen;
private int altoImagen;
private boolean capturado = false;

//private int tipoItem;

	public Item(int tipoItem ,int x, int y, int velocidad, String nombreimagen, int puntos, int vidas) {
	super(x, y, velocidad, nombreimagen);
	this.puntos = puntos;
	this.vidas = vidas;
	//this.tipoItem = tipoItem;
	if(nombreimagen=="vidat") {
		switch(tipoItem) {
			case 1:
				 xImagen=0;
				 yImagen=46;
				 anchoImagen=50;
				 altoImagen=-46;
				 ancho=50;
				 alto=46;
				break;
		}
	}
	if(nombreimagen=="escudo") {
		switch(tipoItem) {
		case 2:
			 xImagen=0;
			 yImagen=500;
			 anchoImagen=444;
			 altoImagen=-460;
			 ancho=50;
			 alto=46;
			break;
		}
	}
}

	@Override
	public void pintar(GraphicsContext graficos) {
		if(!capturado) {
			graficos.drawImage(Juego.imagenes.get(nombreimagen), xImagen, yImagen,anchoImagen,altoImagen,x,-y+700,ancho,-alto);
		}
		//graficos.strokeOval(x, -y+700-alto, ancho, alto);
	}

	@Override
	public void mover(int y3) {
		if(Juego.arriba && Juego.avance) {
			this.y -= velocidad;
		}
		
	}
	@Override
	public Rectangle obtenerRegtangulo() {
		if( this.nombreimagen == "vidat") {
			return new Rectangle(x, -y+700-alto, ancho, alto);
		}
		return new Rectangle(x, -y+700-alto, ancho, alto);
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

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
	
	
}
