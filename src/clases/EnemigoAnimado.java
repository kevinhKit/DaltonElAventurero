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
	private int anchoImprimir = 125 ;
	private int altoImprimir = 128 ;
	private String animacionActual;
	private int direccion = 1;
	private boolean cron=false;
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
					new Rectangle( 5 , 0 , 53 , 68 ),
					new Rectangle( 67 , 0 , 53 , 68 ),
					new Rectangle( 131 , 0 , 53 , 68 ),
			};
			Animacion animacionIzquierda = new Animacion( 0.1 , coordenadasIzquierda);
			animaciones.put("izquierda", animacionIzquierda);
			//NEXT
			Rectangle coordenadasDerecha[] = {
					new Rectangle( 5 , 0 , 53 , 68 ),
					new Rectangle( 67 , 0 , 53 , 68 ),
					new Rectangle( 131 , 0 , 53 , 68 ),
			};
			Animacion animacionDerecha = new Animacion( 0.1 , coordenadasDerecha);
			animaciones.put("derecha", animacionDerecha);
				//NEXT
			Rectangle coordenadasAbajo[] = {
				new Rectangle( 5 , 0 , 53 , 68 ),
				new Rectangle( 67 , 0 , 53 , 68 ),
				new Rectangle( 131 , 0 , 53 , 68 ),

			};
			Animacion animacionAbajo = new Animacion( 0.1 , coordenadasAbajo);
			animaciones.put("abajo", animacionAbajo);
			//NEXT
			Rectangle coordenadasArriba[] = {
					new Rectangle( 5 , 192 , 53 , 64 ),
					new Rectangle( 67 , 0 , 53 , 64 ),
					new Rectangle( 131 , 0 , 53 , 64 ),
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
		
		public void verificarColisionesItem(ArrayList<Item> item) {
		}
		public void verificarColisionesTile2(ArrayList<Tile> tiles) {
		}
		public void verificarColisionesTile(ArrayList<Tile> tiles) {
		}
		public void cronometro() {
			if(!b) {
				a=1;
				a+=1;
				b=true;
			}else {
				a+=1;
				if(a==133546) {
					System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
					b=false;
					cron=false;
					Tile.modoNormal=true;
				}
			}

			System.out.println(a);
		}

		
}
