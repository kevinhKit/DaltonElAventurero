package clases;

import implementaciones.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Fondo extends ObjetoJuego{
	private String nombreimagen2;
	private int y2 = -700;

		public Fondo(int x, int y, int velocidad, String nombreimagen,String nombreimagen2) {
			super(x, y, velocidad, nombreimagen);
			this.nombreimagen2 = nombreimagen2;
		}

		//METODOS SOBREESCRITOS POR HERENCIA Y ABSTRACCION
		@Override
		public void pintar(GraphicsContext graficos) {
			graficos.drawImage(Juego.imagenes.get(nombreimagen), x, y);
			graficos.drawImage(Juego.imagenes.get(nombreimagen2), x, y2);	

		}

		@Override
		public void mover(int y3) {
			if( y >= 700) {
				y = -700;
			}
			if( y2 >= 700) {
				y2 = -700;
			}
			if(Juego.arriba && y3 <= 500) {
				y += velocidad;
				y2 += velocidad;
			}
//			if(Juego.abajo) {
//				y -= velocidad;
//				y2 -= velocidad;
//			}
		}

		@Override
		public Rectangle obtenerRegtangulo() {
			// TODO Auto-generated method stub
			return null;
		}
}
