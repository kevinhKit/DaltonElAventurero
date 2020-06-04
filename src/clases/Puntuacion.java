package clases;

public class Puntuacion {
	private String jugador;
	private long puntuacion;
	public Puntuacion(String jugador, long puntuacion) {
		super();
		this.jugador = jugador;
		this.puntuacion = puntuacion;
	}
	public String getJugador() {
		return jugador;
	}
	public void setJugador(String jugador) {
		this.jugador = jugador;
	}
	public long getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(long puntuacion) {
		this.puntuacion = puntuacion;
	}
	@Override
	public String toString() {
		return "Puntuacion [jugador=" + jugador + ", puntuacion=p" + puntuacion + "]";
	}
	
	
}
