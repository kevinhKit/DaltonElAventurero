package clases;

import implementaciones.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends ObjetoJuego {
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private int tipotile;

	public Tile(int tipotile, int x, int y, int velocidad, String nombreimagen, int ancho, int alto) {
		super(x, y, velocidad, nombreimagen);
		this.ancho = ancho;
		this.alto = alto;
		this.tipotile = tipotile;
		if(this.nombreimagen == "tile") {
			switch(tipotile){
			case 1:
				xImagen=557;
				yImagen=59;
				anchoImagen = 40;
				altoImagen = 40;
				break;
			case 13:
				xImagen=557;
				yImagen=19;
				anchoImagen = 40;
				altoImagen = 40;
				break;
			case 2:
				xImagen=319;
				yImagen=60;
				anchoImagen = 40;
				altoImagen = 40;
				break;
			case 3:
				xImagen=477;
				yImagen=60;
				anchoImagen = 40;
				altoImagen = 40;
				break;
			case 4:
				xImagen=398;
				yImagen=60;
				anchoImagen = 40;
				altoImagen = 40;
				break;
			case 5:
				xImagen=318;
				yImagen=20;
				anchoImagen = 40;
				altoImagen = 40;
				break;
			case 6:
				xImagen=358;
				yImagen=20;
				anchoImagen = 40;
				altoImagen = 40;
				break;
			case 7:		//CAMBIAR POR UN TILE CORRECTO
				xImagen=557;
				yImagen=179;
				anchoImagen = 40;
				altoImagen = -40;
				break;	
			case 8:		//CAMBIAR POR UN TILE CORRECTO
				xImagen=555;
				yImagen=99;
				anchoImagen = 40;
				altoImagen = 40;
				break;
			case 9:
				xImagen=557;
				yImagen=119;
				anchoImagen = 40;
				altoImagen = 40;
				break;
			case 10:		//CAMBIAR POR UN TILE CORRECTO
				xImagen=557;
				yImagen=139;
				anchoImagen = 40;
				altoImagen = 40;
				break;
			case 11:		//CAMBIAR POR UN TILE CORRECTO
				xImagen=557;
				yImagen=159;
				anchoImagen = 40;
				altoImagen = 40;
				break;
			case 12:		//CAMBIAR POR UN TILE CORRECTO
				xImagen=479;//519,792
				yImagen=763+30;
				anchoImagen = 40;
				altoImagen = -30;
				break;

//			case 11:		//CAMBIAR POR UN TILE CORRECTO
//				xImagen=597;
//				yImagen=99;
//				anchoImagen = 40;
//				altoImagen = -40;
//				break;
//			case 11:		//CAMBIAR POR UN TILE CORRECTO
//				xImagen=597;
//				yImagen=199;
//				anchoImagen = 40;
//				altoImagen = -40;
//				break;
			}
		}
		if(this.nombreimagen == "tile2") {
			switch(tipotile){
			case 1:
				xImagen=0;
				yImagen=50;
				anchoImagen = 48;
				altoImagen = -50;
				break;
			case 2:
				xImagen=48;
				yImagen=50;
				anchoImagen = 48;
				altoImagen = -50;
				break;
			case 3:
				xImagen=96;
				yImagen=50;
				anchoImagen = 48;
				altoImagen = -50;
				break;
			}
		}

	}
	@Override
	public Rectangle obtenerRegtangulo() {
		
		return null;
	}
	

	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(nombreimagen), xImagen, yImagen, anchoImagen, altoImagen, x, -y + 700 , ancho, -alto);
		graficos.setStroke(Color.WHITE);
//		if( nombreimagen == "tile") {
//			graficos.strokeRect( x , -y + 700 -alto, ancho , alto );
//		}
//		if( nombreimagen == "tile2" ) {
//			graficos.strokeRect( x , -y + 700 -alto, ancho , alto );
//			//graficos.strokeOval(400, 400, 80, 100);
//		}
	}

	@Override
	public void mover(int y3) {
		if(Juego.arriba && y3 <= 500) {
			y-= velocidad;
		}
//		if(Juego.abajo) {
//			y+=velocidad;
//		}
	}

	public final int getxImagen() {
		return xImagen;
	}

	public final void setxImagen(int xImagen) {
		this.xImagen = xImagen;
	}

	public final int getyImagen() {
		return yImagen;
	}

	public final void setyImagen(int yImagen) {
		this.yImagen = yImagen;
	}

	public final int getTipotile() {
		return tipotile;
	}

	public final void setTipotile(int tipotile) {
		this.tipotile = tipotile;
	}
	
}
