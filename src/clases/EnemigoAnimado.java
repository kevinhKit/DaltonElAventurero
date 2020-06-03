package clases;

import java.util.ArrayList;
import java.util.HashMap;

import implementaciones.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class EnemigoAnimado extends ObjetoJuego{
	private int vidas;
	private HashMap< String , Animacion > animaciones;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private int anchoImprimir = 100 ;
	private int altoImprimir = 110 ;
	private String animacionActual;
	private int direccion = 1;
	//private boolean cron=false;
	private boolean capturado = false;
	private int a;
	boolean b=false;
		public EnemigoAnimado(int x, int y, int velocidad, String nombreimagen, int vidas, String animacionActual) {
			super(x, y, velocidad, nombreimagen);
			this.vidas = vidas;
			this.animacionActual = animacionActual;
			animaciones = new HashMap< String , Animacion >();
			inicializarAnimaciones();
		}
		public void inicializarAnimaciones(){
			Rectangle coordenadasIzquierda[] = {
					new Rectangle( 5 , 64 , 53 , 64 ),
					new Rectangle( 67+3 , 64 , 53 , 64 ),
					new Rectangle( 131+3 , 64 , 53 , 64 ),
			};
			Animacion animacionIzquierda = new Animacion( 0.2 , coordenadasIzquierda);
			animaciones.put("izquierda", animacionIzquierda);
			//NEXT
			Rectangle coordenadasDerecha[] = {
					new Rectangle( 5 , 128 , 55 , 64 ),
					new Rectangle( 67+3 , 128 , 55 , 64 ),
					new Rectangle( 131+3 , 128 , 55 , 64 ),
			};
			Animacion animacionDerecha = new Animacion( 0.2 , coordenadasDerecha);
			animaciones.put("derecha", animacionDerecha);
				//NEXT
			Rectangle coordenadasAbajo[] = {
				new Rectangle( 5 , 0 , 53 , 64 ),
				new Rectangle( 67+3 , 0 , 53 , 64 ),
				new Rectangle( 131+3, 0 , 53 , 64 ),

			};
			Animacion animacionAbajo = new Animacion( 0.2 , coordenadasAbajo);
			animaciones.put("abajo", animacionAbajo);
			//NEXT
			Rectangle coordenadasArriba[] = {
					new Rectangle( 5 , 192 , 55 , 65 ),
					new Rectangle( 67+3 , 192 , 55 , 65 ),
					new Rectangle( 131+3 , 192 , 55 , 65 ),
			};
			Animacion animacionArriba = new Animacion( 0.2 , coordenadasArriba);
			animaciones.put("arriba", animacionArriba);
		}
		public void calcularFrame(double t) {
			Rectangle coordenadas = animaciones.get(animacionActual).calcularFrameActual(t);
			this.xImagen = (int)coordenadas.getX();
			this.yImagen = (int)coordenadas.getY();
			this.anchoImagen = (int)coordenadas.getWidth();
			this.altoImagen = (int)coordenadas.getHeight();
		}
		
		//METODOS SOBREESCRITOS POR HERENCIA Y ABSTRACCION
		@Override
		public void pintar(GraphicsContext graficos) {
			if(!capturado) {
			graficos.drawImage( Juego.imagenes.get(nombreimagen), xImagen, yImagen, anchoImagen, altoImagen, x, y, anchoImprimir, altoImprimir );
			graficos.strokeRect(x+10+10, y+5, anchoImprimir-25-10-7, altoImprimir-20-5);
			//graficos.strokeRect( x + 29 , y + 10 + 10 , anchoImprimir -34 -29, altoImprimir -20 - 10 -10 );
			}
		}
		@Override
		public void mover(int y3) {	
		}
		public int getVidas() {
			return vidas;
		}
		public void setVidas(int vidas) {
			this.vidas = vidas;
		}
		public String getAnimacionActual() {
			return animacionActual;
		}
		public void setAnimacionActual(String animacionActual) {
			this.animacionActual = animacionActual;
		}
		public int getDireccion() {
			return direccion;
		}
		public void setDireccion(int direccion) {
			this.direccion = direccion;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
		@Override
		public Rectangle obtenerRegtangulo() {			
			return new Rectangle(x+10+10, y+5, anchoImprimir-25-10-7, altoImprimir-20-5);//x+10, y, anchoImprimir-25, altoImprimir-20);
		}
		
		public void verificarColisionesItem(ArrayList<Item> item) {
		}
		public void verificarColisionesTile2(ArrayList<Tile> tiles) {
		}
		public void verificarColisionesTile(ArrayList<Tile> tiles) {
			if(!capturado) {
				for(int i = 0 ; i < tiles.size() ; i++ ) {
					if(tiles.get(i).getNombreimagen()=="tile") {
						if( tiles.get(i).getTipotile() == 3 ) {
							if(this.obtenerRegtangulo().getBoundsInLocal().intersects(tiles.get(i).obtenerRegtangulo().getBoundsInLocal())) {		
								int jx = (int) this.obtenerRegtangulo().getX();
								int jy = (int)this.obtenerRegtangulo().getY();
								int jw = (int)this.obtenerRegtangulo().getWidth();
								int jh = (int)this.obtenerRegtangulo().getHeight();
								int tx = (int)tiles.get(i).obtenerRegtangulo().getX();
								int ty = (int)tiles.get(i).obtenerRegtangulo().getY();
								int tw = (int)tiles.get(i).obtenerRegtangulo().getWidth();
								int th = (int)tiles.get(i).obtenerRegtangulo().getHeight();
//								System.out.println(jx);
//								System.out.println(jy);
//								System.out.println(tx);
//								System.out.println(ty);
								if((jx <= tx + tw) &&
										(jx >= tx + 25) &&
										((jy >= ty - th) &&
												jy <= ty + th)) {
									this.x = this.x + velocidad;
									//this.y = this.y;
									//System.out.println("LADO IZQUIERDO");
								}
								if((jx >= (tx - tw) &&
										jx <= (tx - jw + 25))&&
										((jy >= ty - jh) &&
												jy <= ty + th)) {
									this.x = this.x- velocidad;
									//this.y = this.y;
									//System.out.println("LADO DERECHO");
								}
//								if((this.obtenerRegtangulo().getY() <= (tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight()) &&
//										this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() + 25) &&
//										(this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
//												this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()))) {
//									this.y = this.y + velocidad;
//								}
//								if((this.obtenerRegtangulo().getY() >= (tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
//										this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + 25) &&
//										(this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
//												this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()))) {
//									this.y = this.y - velocidad;
//								}	
							}
						}
					}
					else{
						if(this.obtenerRegtangulo().getBoundsInLocal().intersects(tiles.get(i).obtenerRegtangulo().getBoundsInLocal())) {
							tiles.remove(i);
						}
						
					}
				}
			}
		}
//		public void cronometro() {
//			if(!b) {
//				a=1;
//				a+=1;
//				b=true;
//			}else {
//				a+=1;
//				if(a==133546) {
//					System.out.println("hora");
//					b=false;
//					cron=false;
//					Tile.modoNormal=true;
//				}
//			}
//
//			System.out.println(a);
//		}
		public void perseguir(JugadorAnimado p1) {
			if(!capturado) {
//				if(p1.obtenerRegtangulo().getX()>this.obtenerRegtangulo().getX()) {
//				this.x+=velocidad;
//				animacionActual="derecha";
//			}else if(this.obtenerRegtangulo().getX()>p1.obtenerRegtangulo().getX()) {
//				this.x-=velocidad;
//				animacionActual="izquierda";
//			}else {
//				if(p1.obtenerRegtangulo().getY()>this.obtenerRegtangulo().getY()) {
//					this.y+=velocidad;
//					animacionActual="abajo";
//				}
//				if(this.obtenerRegtangulo().getY()>p1.obtenerRegtangulo().getY()) {
//					this.y-=velocidad;
//					animacionActual="arriba";
//				}
//			}
			
			//NUEVA LOGICA
			
			if(this.obtenerRegtangulo().getX()>(p1.obtenerRegtangulo().getX()+p1.obtenerRegtangulo().getWidth()-20)) {
				this.x-=velocidad;
				animacionActual="izquierda";
			}
			if((this.obtenerRegtangulo().getX()+this.obtenerRegtangulo().getWidth())<p1.obtenerRegtangulo().getX()+20) {
				this.x+=velocidad;
				animacionActual="derecha";
			}
			if((this.obtenerRegtangulo().getX()<=(p1.obtenerRegtangulo().getX()+p1.obtenerRegtangulo().getWidth()-20))&&
					((this.obtenerRegtangulo().getX()+this.obtenerRegtangulo().getWidth())>=p1.obtenerRegtangulo().getX()+20)) {
				if(this.obtenerRegtangulo().getY()<p1.obtenerRegtangulo().getY()) {
					this.y+=velocidad;
					animacionActual="abajo";
				}
				if(this.obtenerRegtangulo().getY()>p1.obtenerRegtangulo().getY()) {
					this.y-=velocidad;
					animacionActual="arriba";
				}
			}
			
			}
		}
		public boolean isCapturado() {
			return capturado;
		}
		public void setCapturado(boolean capturado) {
			this.capturado = capturado;
		}

		
}
//if(p1.obtenerRegtangulo().getX()>this.obtenerRegtangulo().getX()) {
//this.x+=velocidad;
//animacionActual="derecha";
//}else {
//this.y+=velocidad;
//animacionActual="abajo";
//}
//
//if(p1.obtenerRegtangulo().getY()>this.obtenerRegtangulo().getY()) {
//this.y+=velocidad;
//animacionActual="abajo";
//}else {
//this.y-=velocidad;
//animacionActual="arriba";
//}