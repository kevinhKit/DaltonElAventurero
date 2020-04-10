package clases;

import implementaciones.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Item extends ObjetoJuego{
private int puntos;
private int vidas;
private int tipoItem;
private boolean capturado = false;

	public Item(int tipoItem ,int x, int y, int velocidad, String nombreimagen, int puntos, int vidas) {
	super(x, y, velocidad, nombreimagen);
	this.puntos = puntos;
	this.vidas = vidas;
	this.tipoItem = tipoItem;
}

	@Override
	public void pintar(GraphicsContext graficos) {
		if(!capturado) {
			graficos.drawImage(Juego.imagenes.get(nombreimagen), x, y);
		}
		//graficos.strokeOval(x, y, 50, 45);
	}

	@Override
	public void mover(int y3) {
		if(Juego.arriba && y3 <= 500) {
			this.y += velocidad;
		}
		
	}
	@Override
	public Rectangle obtenerRegtangulo() {
		if( this.nombreimagen == "vidat") {
			return new Rectangle(x, y, 50, 45);
		}
		return null;
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
