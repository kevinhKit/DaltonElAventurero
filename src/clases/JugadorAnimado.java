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
	public static int puntacion=0;
	private String animacionActual;
	private int direccion = 1;
	private boolean cron=false;
	private boolean vidaMenos=false;
	private int a;
	private boolean b=false;
	private int aa;
	private boolean bb=false;
	private int acu;
	private boolean veri;	
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
		@Override
		public void pintar(GraphicsContext graficos) {
			graficos.drawImage( Juego.imagenes.get(nombreimagen), xImagen, yImagen, anchoImagen, altoImagen, x, y, anchoImprimir, altoImprimir );
			//graficos.strokeRect( x + 29+10 , y + 10 + 10+10 , anchoImprimir -34 -29-10-3, altoImprimir -20 - 10 -10 -10);
		}
		@Override
		public void mover(int y3) {	
			if(y <= 500 && !Juego.colisionObtaculoFrente) {
				Juego.avance=true;
			}else {
				Juego.avance=false;
			}
			if( y <-68) {
				this.y = 630 ;
			}
			if( y >= 631) {
				this.y = 630 ;
			}
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
				this.animacionActual = "descanso2";
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
			return new Rectangle(x + 29+10 , y + 10 + 10 +10, anchoImprimir -34 -29-10-3, altoImprimir -20 - 10 -10 -10);
		}
		public void verificarColisionesItem(ArrayList<Item> item) {
			for(int i = 0 ; i < item.size() ; i++ ) {
				if(item.get(i).getNombreimagen().equals("vidat")) {
					if(!item.get(i).isCapturado() && this.obtenerRegtangulo().getBoundsInLocal().intersects(item.get(i).obtenerRegtangulo().getBoundsInLocal())) {
						item.get(i).setCapturado(true);
						this.vidas = this.vidas + item.get(i).getVidas();
					}
				}
				if(item.get(i).getNombreimagen().equals("escudo")) {
					if(!item.get(i).isCapturado() && this.obtenerRegtangulo().getBoundsInLocal().intersects(item.get(i).obtenerRegtangulo().getBoundsInLocal())) {
						item.get(i).setCapturado(true);
						JugadorAnimado.puntacion+=5;
						Tile.modoNormal=false;
						cron=true;
					}else {

						if(cron) {
							cronometro();
						}	
					}
				}
			}
		}
//		public void verificarColisionesTile2(ArrayList<Tile> tiles) {
//			for(int i = 0 ; i < tiles.size() ; i++ ) {
//				if( tiles.get(i).getTipotile() == 3 ) {
//					if(this.obtenerRegtangulo().getBoundsInLocal().intersects(tiles.get(i).obtenerRegtangulo().getBoundsInLocal())) {		
//						int jx = (int) this.obtenerRegtangulo().getX();
//						int jy = (int)this.obtenerRegtangulo().getY();
//						int tx = (int)tiles.get(i).obtenerRegtangulo().getX();
//						int ty = (int)tiles.get(i).obtenerRegtangulo().getY();
//						int tw = (int)tiles.get(i).obtenerRegtangulo().getWidth();
//						int th = (int)tiles.get(i).obtenerRegtangulo().getHeight();
//						System.out.println(jx);
//						System.out.println(jy);
//						System.out.println(tx);
//						System.out.println(ty);
//						if((jx <= tx + tw) &&
//								(jx >= tx + 25) &&
//								((jy >= ty - th) &&
//										jy <= ty + th)) {
//							this.x = this.x + velocidad;
//							//this.y = this.y;
//							System.out.println("LADO IZQUIERDO");
//						}
//						if((jx >= (tx - tw) &&
//								jx <= (tx - this.obtenerRegtangulo().getWidth() + 25))&&
//								((this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
//										this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight())) {
//							this.x = this.x- velocidad;
//							//this.y = this.y;
//							System.out.println("LADO DERECHO");
//						}
//						if(Juego.derecha) {
//							System.out.println("Jugador en la Derecha");
//						}
//						if(Juego.izquierda) {
//							System.out.println("Jugador en la izquierda");
//						}
//						if(Juego.arriba&&Tile.isModoNormal()) {
//							System.out.println("Jugador arriba");
//						}
//						if(Juego.abajo) {
//							System.out.println("Jugador abajo");
//						}
//					}
//				}
//			}
//			
//		}
		public void verificarColisionesTile(ArrayList<Tile> tiles) {
			for(int i = 0 ; i < tiles.size() ; i++ ) {
				if(tiles.get(i).getNombreimagen()=="tile") {
					if( tiles.get(i).getTipotile() == 3 ) {
//						if(!this.obtenerRegtangulo().getBoundsInLocal().intersects(tiles.get(i).obtenerRegtangulo().getBoundsInLocal())) {
//							tiles.get(i).setAvance(true);
//						}
						if(this.obtenerRegtangulo().getBoundsInLocal().intersects(tiles.get(i).obtenerRegtangulo().getBoundsInLocal())) {
							if((this.obtenerRegtangulo().getX() <= tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()) &&
									(this.obtenerRegtangulo().getX() >= tiles.get(i).obtenerRegtangulo().getX() + 25) &&
									((this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
											this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight())) {
								this.x = this.x + velocidad;
								//System.out.println("LADO IZQUIERDO");
							}
							if((this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
									this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth() + 25))&&
									((this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
											this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight())) {
								this.x = this.x- velocidad;
								//System.out.println("LADO DERECHO");
							}
							if((this.obtenerRegtangulo().getY() <= (tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight()) &&
									this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() + 25) &&
									(this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
											this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()))) {
								this.y = this.y + velocidad;
								Juego.colisionObtaculoFrente=true;//tiles.get(i).setAvance(false);
							}
							else {
							}
							if((this.obtenerRegtangulo().getY() >= (tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
									this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + 25) &&
									(this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
											this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()))) {
								this.y = this.y - velocidad;
							}
													}else {													
							if(Juego.colisionObtaculoFrente==true) {
								avanzar();
							}
						}
					}
				}
				//SEGUNDO ARREGLO DE TILE (TILE)
				else {
					if(this.obtenerRegtangulo().getBoundsInLocal().intersects(tiles.get(i).obtenerRegtangulo().getBoundsInLocal())) {

						if((this.obtenerRegtangulo().getX() <= tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()) &&
								(this.obtenerRegtangulo().getX() >= tiles.get(i).obtenerRegtangulo().getX() + 25) &&
								((this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
										this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight())) {
							this.x = this.x + velocidad;
							//this.y = this.y;
						}
						if((this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
								this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth() + 25))&&
								((this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
										this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight())) {
							this.x = this.x- velocidad;
						}
						if((this.obtenerRegtangulo().getY() <= (tiles.get(i).obtenerRegtangulo().getY() + tiles.get(i).obtenerRegtangulo().getHeight()) &&
								this.obtenerRegtangulo().getY() >= tiles.get(i).obtenerRegtangulo().getY() + 25) &&
								(this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
										this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()))) {
							this.y = this.y + velocidad;
							Juego.colisionObtaculoFrente=true;
						}
						if((this.obtenerRegtangulo().getY() >= (tiles.get(i).obtenerRegtangulo().getY() - this.obtenerRegtangulo().getHeight()) &&
								this.obtenerRegtangulo().getY() <= tiles.get(i).obtenerRegtangulo().getY() + 25) &&
								(this.obtenerRegtangulo().getX() >= (tiles.get(i).obtenerRegtangulo().getX() - this.obtenerRegtangulo().getWidth()) &&
										this.obtenerRegtangulo().getX() <= (tiles.get(i).obtenerRegtangulo().getX() + tiles.get(i).obtenerRegtangulo().getWidth()))) {
							this.y = this.y - velocidad;
						}		
					}	else {
						if(Juego.colisionObtaculoFrente==true) {
							avanzar();
						}
					}
				}
			}
		}
		public void cronometro() {
			if(!b) {
				a=1;
				//a+=1;
				b=true;
			}else {
				a+=1;
				if(a==516) {
					b=false;
					cron=false;
					Tile.modoNormal=true;
				}
			}
		}
		public void avanzar() {
			if(bb) {
				aa+=1;
				if(aa==2294) {
					bb=false;
					Juego.colisionObtaculoFrente=false;
					aa=0;
				}
			}
			if(!bb) {
				aa=1;
				bb=true;
			}
		}
		public void verificarColisionEnemigoAnimado(EnemigoAnimado e) {
				if(!e.isCapturado() && !vidaMenos && this.obtenerRegtangulo().getBoundsInLocal().intersects(e.obtenerRegtangulo().getBoundsInLocal())) {
					if(!cron) {
						this.vidas -= e.getVidas();
						vidaMenos=true;
						e.setY(-10);
						System.out.println("vida perdida");
					}
					if(cron) {
						e.setCapturado(true);
						JugadorAnimado.puntacion += 20;
						System.out.println("muerto");
					}
				}
				if(vidaMenos) {
					cronometroVidas();
				}
		}
		public void verificarColisionEnemigoAnimado2(ArrayList<EnemigoAnimado> e) {
			for(int i=0;i<e.size();i++) {
				if(!e.get(i).isCapturado() && !vidaMenos && this.obtenerRegtangulo().getBoundsInLocal().intersects(e.get(i).obtenerRegtangulo().getBoundsInLocal())) {
					if(!cron) {
						this.vidas -= e.get(i).getVidas();
						vidaMenos=true;
						int s = e.get(i).getY();
						e.get(i).setY(s-400);
						//System.out.println("vida perdida");
						//e.setCapturado(true);
					}
					if(cron) {
						//this.vidas -= e.getVidas();
						//vidaMenos=true;
						e.get(i).setCapturado(true);
						JugadorAnimado.puntacion += 20;
						System.out.println("muerto");
					}
				}
				if(vidaMenos) {
					cronometroVidas();
				}
			}
		}
		public void cronometroVidas() {
			if(!veri) {
				acu=1;
				//acu+=1;
				veri=true;
			}else {
				acu+=1;
				if(acu==100) {
					veri=false;
					vidaMenos=false;
				}
			}
		}
		public void verificarEstado(ArrayList<Tile> t1,ArrayList<Tile> t2) {
			if(vidas==0) {
			}
		}	
}