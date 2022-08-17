package gestionpedidos;

import gestionpedidos.excepciones.PedidoSinTransporteAsignado;
import gestionpedidos.mapa.Mapa;
import gestionpedidos.mapa.PosicionXY;
import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Moto;
import gestionpedidos.transportes.Transporte;
import anotacion.Programacion2; 
@Programacion2 (
                nombreAutor1 = "Juan",
                apellidoAutor1 = "Jimenez Perez",
                emailUPMAutor1 = "juan.jimenez.perez@alumnos.upm.es",
                nombreAutor2 = "Jesus",
                apellidoAutor2 = "Hernandez Perez", 
                emailUPMAutor2 = "jesus.hernandezp@alumnos.upm.es"
               )
  
  public class GestionReparto {
  
  
  
  // CÓDIGO DE APOYO
  private GestionRepartoLocal[] gestoresLocales;
  private Mapa mapa;
  
  //CÓDIGO DE APOYO
  public Mapa getMapa() {
    return mapa;
  }
  
  // CÓDIGO DE APOYO
  public String getEstadoGestorLocal(int i){
    return this.gestoresLocales[i].getDisponibles() + this.gestoresLocales[i].getEsperando();
  }
  
  // CÓDIGO DE APOYO
  public String getEstadoGestorLocalNum(int i){
    return this.gestoresLocales[i].getCodMotosDisponibles().size() + ";" +
      this.gestoresLocales[i].getCodFurgoDisponibles().size() + ";" +
      this.gestoresLocales[i].getCodEsperandoMoto().size() + ";" +
      this.gestoresLocales[i].getCodEsperandoFurgo().size() ;
  }
  
  public GestionReparto(Mapa mapa){
    this.mapa = mapa;
    gestoresLocales = new GestionRepartoLocal[4];
    gestoresLocales[0] = new GestionRepartoLocal();
    gestoresLocales[1] = new GestionRepartoLocal();
    gestoresLocales[2] = new GestionRepartoLocal();
    gestoresLocales[3] = new GestionRepartoLocal();
  }
  
  public void addTransporteLocalidad(Transporte transporte) {//addTransporteLocalidad
    int localidad = seleccionarLocalidad(getMapa().getPosicion(transporte.getCodigo()));
    gestoresLocales[localidad].add(transporte); 
  }//addTransporteLocalidad
  
  private int seleccionarLocalidad(PosicionXY pos){//seleccionarLocalidad
    int localidad=-1;
    int x = pos.getX();
    int y = pos.getY();
    int maxCoordX = this.mapa.getMaxCoordX();
    int maxCoordY = this.mapa.getMaxCoordY();
    
    if(x>=0 && x<=maxCoordX/2) {//if
      if(y>=0 && pos.getY()<=maxCoordY/2)
        localidad= 0;
      else
        localidad=2;
    }//if
    if(x>=maxCoordX/2+1 && x<=maxCoordX) {//if
      if(y>=0 && y<=maxCoordY/2)
        localidad=1;
      else
        localidad=3;
    }//if
    return localidad;
    }//seleccionarLocalidad
  
  
  public void asignarPedido(Pedido pedido) {//asignarPedido
    int localidad = seleccionarLocalidad(getMapa().getPosicion(pedido.getCliente().getCodigo()));
    gestoresLocales[localidad].asignarPedido(pedido);
  }//asignarPedido
  
  public void notificarEntregaPedido(Pedido pedido) throws PedidoSinTransporteAsignado{//  de notificarEntregaPedido
	  
    int localidad = seleccionarLocalidad(getMapa().getPosicion(pedido.getTransporte().getCodigo()));
    gestoresLocales[localidad].notificarEntregaPedido(pedido);

  } //  de notificarEntregaPedido
  
  }//gestionpedidos
