package clases;
import javafx.scene.shape.Rectangle;

public class Animacion {
	private double duracion;
	private Rectangle coordenadas[];
	private String frameActual;

		public Animacion( double duracion, Rectangle[] coordenadas) {
			this.duracion = duracion;
			this.coordenadas = coordenadas;
		}

		public double getDuracion() {
			return duracion;
		}
		
		public void setDuracion(double duracion) {
			this.duracion = duracion;
		}
		
		public Rectangle[] getCoordenadas() {
			return coordenadas;
		}
		
		public void setCoordenadas(Rectangle[] coordenadas) {
			this.coordenadas = coordenadas;
		}
		
		public String getFrameActual() {
			return frameActual;
		}
		
		public void setFrameActual(String frameActual) {
			this.frameActual = frameActual;
		}
		
		
		public Rectangle calcularFrameActual(double t) {
			int cantidadFrames = this.coordenadas.length;
			int indiceFrameActual = (int)(t%(cantidadFrames*duracion)/duracion);
			return coordenadas[indiceFrameActual];
		}
}
