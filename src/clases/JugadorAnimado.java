package clases;

import java.util.ArrayList;
import java.util.HashMap;

import implementaciones.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class JugadorAnimado extends ObjetoJuego {
	private int vidas;
	private HashMap< String , Animacion > animaciones;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private int anchoImprimir = 125 ;
	private int altoImprimir = 128 ;
	private String animacionActual;
	private int direccion = 1;

		public JugadorAnimado(int x, int y, int velocidad, String nombreimagen, int vidas, String animacionActual) {
			super(x, y, velocidad, nombreimagen);
			this.vidas = vidas;
			this.animacionActual = animacionActual;
			animaciones = new HashMap< String , Animacion >();
			inicializarAnimaciones();
		}
		public void inicializarAnimaciones(){
			Rectangle coordenadasDescanso1[] = {
					new Rectangle( 64 , 134 , 65 , 68 )
			};
			Animacion animacionDescanso1 = new Animacion( 0 , coordenadasDescanso1);
			animaciones.put("descanso1" , animacionDescanso1 );
			//NEXT
			Rectangle coordenadasDescanso2[] = {
					new Rectangle( 64 , 0 , 65 , 68 )
			};
			Animacion animacionDescanso2 = new Animacion( 0 , coordenadasDescanso2);
			animaciones.put("descanso2" , animacionDescanso2 );
			//NEXT
			Rectangle coordenadasIzquierda[] = {
				new Rectangle( 0 , 72 , 65 , 68 ),
				new Rectangle( 64 , 72 , 65 , 68 ),
				new Rectangle( 128 , 72 , 65 , 68 ),
				new Rectangle( 192 , 72 , 65 , 68 ),
				new Rectangle( 256 , 72 , 65 , 68 ),
				new Rectangle( 320 , 72 , 65 , 68 ),
				new Rectangle( 384 , 72 , 65 , 68 ),
				new Rectangle( 448 , 72 , 65 , 68 ),
				new Rectangle( 512 , 72 , 65 , 68 ),
			};
			Animacion animacionIzquierda = new Animacion( 0.1 , coordenadasIzquierda);
			animaciones.put("izquierda", animacionIzquierda);
			//NEXT
			Rectangle coordenadasDerecha[] = {
				new Rectangle( 0 , 200 , 65 , 68 ),
				new Rectangle( 64 , 200 , 65 , 68 ),//+1
				new Rectangle( 128 , 200 , 65 , 68 ),
				new Rectangle( 192 , 200 , 65 , 68 ),
				new Rectangle( 256 , 200 , 65 , 68 ),
				new Rectangle( 320 , 200 , 65 , 68 ),//+1
				new Rectangle( 384 , 200 , 65 , 68 ),
				new Rectangle( 448 , 200 , 65 , 68 ),
				new Rectangle( 512 , 200 , 65 , 68 ),
			};
			Animacion animacionDerecha = new Animacion( 0.1 , coordenadasDerecha);
			animaciones.put("derecha", animacionDerecha);
				//NEXT
			Rectangle coordenadasAbajo[] = {
				new Rectangle( 0 , 134 , 65 , 68 ),
				new Rectangle( 64 , 134 , 65 , 68 ),//+1
				new Rectangle( 128 , 134 , 65 , 68 ),
				new Rectangle( 192 , 134 , 65 , 68 ),
				new Rectangle( 256 , 134 , 65 , 68 ),
				new Rectangle( 320 , 134 , 65 , 68 ),//+1
				new Rectangle( 384 , 134 , 65 , 68 ),
				new Rectangle( 448 , 134 , 65 , 68 ),
				new Rectangle( 512 , 134 , 65 , 68 ),
			};
			Animacion animacionAbajo = new Animacion( 0.1 , coordenadasAbajo);
			animaciones.put("abajo", animacionAbajo);
			//NEXT
			Rectangle coordenadasArriba[] = {
				new Rectangle( 0 , 0 , 65 , 68 ),
				new Rectangle( 64 , 0 , 65 , 68 ),//+1
				new Rectangle( 128 , 0 , 65 , 68 ),
				new Rectangle( 192 , 0 , 65 , 68 ),
				new Rectangle( 256 , 0 , 65 , 68 ),
				new Rectangle( 320 ,  0 , 65 , 68 ),//+1
				new Rectangle( 384 , 0 , 65 , 68 ),
				new Rectangle( 448 , 0 , 65 , 68 ),
				new Rectangle( 512 , 0 , 65 , 68 ),
			};
			Animacion animacionArriba = new Animacion( 0.1 , coordenadasArriba);
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
			graficos.drawImage( Juego.imagenes.get(nombreimagen), xImagen, yImagen, anchoImagen, altoImagen, x, y, anchoImprimir, altoImprimir );
			graficos.strokeRect( x + 29 , y + 10 + 10 , anchoImprimir -34 -29, altoImprimir -20 - 10 -10 );
		}
		@Override
		public void mover(int y3) {	
			if( y <-68) {
				this.y = 630 ;
			}
			if( y >= 631) {
				this.y = 630 ;
			}/////////////COLOCAR QUE NO SALGA DE LA PANTALLA EL PERSONAJE SINO QUE SOLO DEL CENTRO DEL ESCENARIO PUEDA AVANZAR
//												if( x <= 32 ) {
//													this.x = 32;
//												}
//			if(y3<=500) {
//				if( x >= 1050 ) {
//					this.x = -32;
//				}
//			}////////////////////////////////////////
//			if( x >= 956 ) {
//				this.x = 955;
//			}////////////////////
			if( x >= 1000 ) {
				this.x = -100;
			}
			if(Juego.derecha) {
				this.x += velocidad ;
			}
			if(Juego.izquierda) {
				this.x -= velocidad;
			}
			if(Juego.arriba) {
				this.y -= velocidad;
			}
			if(Juego.abajo) {
				this.y += velocidad;
			}
			if(!Juego.derecha && !Juego.izquierda && !Juego.arriba && !Juego.abajo) {
				this.animacionActual = "descanso1";
			}
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
			return new Rectangle(x + 29 , y + 10 + 10 , anchoImprimir -34 -29, altoImprimir -20 - 10 -10 );
		}
		
		public void verificarColisionesItem(Item item) {
			if(!item.isCapturado() && this.obtenerRegtangulo().getBoundsInLocal().intersects(item.obtenerRegtangulo().getBoundsInLocal())) {
				item.setCapturado(true);
				this.vidas = this.vidas + item.getVidas();
			}
		}
		
		public void verificarColisionesTile(ArrayList<Tile> tiles) {
			for(int i = 0 ; i < tiles.size() ; i++ ) {
				if( tiles.get(i).getTipotile() == 3 ) {
//					if((this.obtenerRegtangulo().getY() <= (tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight()))){
//						if(this.obtenerRegtangulo().getBoundsInLocal().intersects(tiles.get(i).obtenerRegtangulo().getBoundsInLocal())) {
//							tiles.get(i).setAvance(false);
//							tiles.get(i).setAnulacion(2);
//							this.x = this.x;
//							this.y = this.y + velocidad;
//						}else {
//							//tiles.get(i).setAvance(true);
//							tiles.get(i).setAvance(true);
//							tiles.get(i).setAnulacion(0);
//						}
//					}
					if(this.obtenerRegtangulo().getBoundsInLocal().intersects(tiles.get(i).obtenerRegtangulo().getBoundsInLocal())) {
						//this.vidas = this.vidas + 1;
						System.out.println("evento colision obtaculo");
						if((this.obtenerRegtangulo().getX() <= tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()) &&
								(this.obtenerRegtangulo().getX() >= tiles.get(i).obtenerRegtangulo().getX() + 25) &&
								((this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
										this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight())) {
							this.x = this.x + velocidad;
							this.y = this.y;
						}
						if((this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
								this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth() + 25))&&
								((this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
										this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight())) {
							this.x = this.x- velocidad;
							this.y = this.y;
						}
//AQUI SE PRESENTA EL ERROR CUANDO EL PERSONAJE ESTA DEBAJO DE UN TILE
						if((this.obtenerRegtangulo().getY() <= (tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight()) &&
								this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() + 25) &&
								(this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
										this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()))) {
							this.x = this.x;
							this.y = this.y + velocidad;
							tiles.get(i).setAvance(false);
							tiles.get(i).setAnulacion(2);
						}
						if((this.obtenerRegtangulo().getY() >= (tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
								this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + 25) &&
								(this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
										this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()))) {
							this.x = this.x;
							this.y = this.y - velocidad;
						}
						//EL CODIGO ANTERIOR VERIFICA COLISION CON UN SOLO TILE, TAMBIEN FUNCIONA CON VARIOS TILES A LAS VEZ PERO PODRIA DAR ERROR, POR ELLO SE CREARAN LAS SIGUIENTES CONDICIONES.
						
//						if( ((this.obtenerRegtangulo().getX() <= tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()) &&
//								(this.obtenerRegtangulo().getX() >= tiles.get(i).obtenerRegtangulo().getX() + 25) &&
//								((this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
//										this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight())) &&
//								((this.obtenerRegtangulo().getY() <= (tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight()) &&
//										this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() + 25) &&
//										(this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
//												this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()))) ) {
//							this.x = this.x + velocidad;
//							this.y = this.y + velocidad;
//							tiles.get(i).setAvance(false);
//							tiles.get(i).setAnulacion(2);
//						}
//						if( ((this.obtenerRegtangulo().getX() <= tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()) &&
//								(this.obtenerRegtangulo().getX() >= tiles.get(i).obtenerRegtangulo().getX() + 25) &&
//								((this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
//										this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight())) &&
//								((this.obtenerRegtangulo().getY() >= (tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
//										this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + 25) &&
//										(this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
//												this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()))) ) {
//							this.x = this.x + velocidad;
//							this.y = this.y - velocidad;
//							tiles.get(i).setAvance(false);
//							tiles.get(i).setAnulacion(2);
//						}
//						if( ((this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
//								this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth() + 25))&&
//								((this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
//										this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight())) &&
//								((this.obtenerRegtangulo().getY() <= (tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight()) &&
//										this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() + 25) &&
//										(this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
//												this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()))) ) {
//							this.x = this.x - velocidad;
//							this.y = this.y + velocidad;
//							tiles.get(i).setAvance(false);
//							tiles.get(i).setAnulacion(2);
//						}
//						if( ((this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
//								this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth() + 25))&&
//								((this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
//										this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight())) &&
//								((this.obtenerRegtangulo().getY() >= (tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
//										this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + 25) &&
//										(this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
//												this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()))) ) {
//							this.x = this.x - velocidad;
//							this.y = this.y - velocidad;
//							tiles.get(i).setAvance(false);
//							tiles.get(i).setAnulacion(2);
//						}
						
					}else {
						tiles.get(i).setAvance(true);
						tiles.get(i).setAnulacion(0);
					}
				}
			}
		}

		
}