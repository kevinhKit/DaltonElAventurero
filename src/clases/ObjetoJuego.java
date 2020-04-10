package clases;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public abstract class ObjetoJuego {
	protected int x;
	protected int y;
	protected int velocidad;
	protected String nombreimagen;
	protected int ancho;
	protected int alto;

		public ObjetoJuego(int x, int y, int velocidad, String nombreimagen) {
			super();
			this.x = x;
			this.y = y;
			this.velocidad = velocidad;
			this.nombreimagen = nombreimagen;
		}

		public abstract void pintar(GraphicsContext graficos);
		
		public abstract void mover(int y3);
		
		public abstract Rectangle obtenerRegtangulo();
		
		
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getVelocidad() {
			return velocidad;
		}
		public void setVelocidad(int velocidad) {
			this.velocidad = velocidad;
		}
		public String getNombreimagen() {
			return nombreimagen;
		}
		public void setNombreimagen(String nombreimagen) {
			this.nombreimagen = nombreimagen;
		} 
}
