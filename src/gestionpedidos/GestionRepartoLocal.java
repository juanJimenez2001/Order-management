package gestionpedidos;

import gestionpedidos.excepciones.PedidoSinTransporteAsignado;
import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Furgoneta;
import gestionpedidos.transportes.Moto;
import gestionpedidos.transportes.Transporte;
import list.ArrayList;
import list.IList;
import queues.CircularQueue;
import queues.IQueue;
import queues.exceptions.EmptyQueueException;
import anotacion.Programacion2; 
@Programacion2 (
                nombreAutor1 = "Juan",
                apellidoAutor1 = "Jimenez Perez",
                emailUPMAutor1 = "juan.jimenez.perez@alumnos.upm.es",
                nombreAutor2 = "Jesus",
                apellidoAutor2 = "Hernandez Perez", 
                emailUPMAutor2 = "jesus.hernandezp@alumnos.upm.es"
               )
  
  public class GestionRepartoLocal { //Gestion reparto local
  // CÓDIGO DE APOYO
  private IList<Moto> motosDisponibles;
  private IList<Furgoneta> furgonetasDisponibles;
  
  private IQueue<Pedido> pedidosEsperandoMoto;
  private IQueue<Pedido> pedidosEsperandoFurgoneta; 
  
  
  // CÓDIGO DE APOYO
  public String getDisponibles(){
    return "Motos Disponibles:" + GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
                                                                                             GestionRepartoLocalFuncionesAuxiliares.getCodList(motosDisponibles)) + System.lineSeparator()  +
      "Furgonetas Disponibles:" + GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
                                                                                             GestionRepartoLocalFuncionesAuxiliares.getCodList(furgonetasDisponibles)) + System.lineSeparator();
    
  }
  
  // CÓDIGO DE APOYO
  public String getEsperando(){
    return "Pedidos esperando moto:" + GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
                                                                                                  GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoMoto)) + System.lineSeparator() +
      "Pedidos esperando furgoneta:" + GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
                                                                                                  GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoFurgoneta)) + System.lineSeparator();
  }
  
  // CÓDIGO DE APOYO
  public IList<String> getCodMotosDisponibles(){
    return GestionRepartoLocalFuncionesAuxiliares.getCodList(motosDisponibles);
  } 
  
  // CÓDIGO DE APOYO
  public IList<String> getCodFurgoDisponibles(){
    return GestionRepartoLocalFuncionesAuxiliares.getCodList(furgonetasDisponibles);
    
  }
  
  // CÓDIGO DE APOYO
  public IList<String[]> getCodEsperandoMoto(){
    return GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoMoto);
  }
  
  public IList<String[]> getCodEsperandoFurgo(){
    return GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoFurgoneta);
  }
  
  private static final double PESO_MAX_MOTO = 20;
  
  // CÓDIGO DE APOYO
  public GestionRepartoLocal(){  
    
    this.motosDisponibles = new ArrayList<>();
    this.furgonetasDisponibles = new ArrayList<>();
    
    this.pedidosEsperandoFurgoneta = new CircularQueue<>();
    this.pedidosEsperandoMoto = new CircularQueue<>();
  }
  
  public void add(Transporte transporte){
    
    if(transporte instanceof Moto){
      Moto moto = (Moto) transporte;
      motosDisponibles.add(motosDisponibles.size(),moto);
    } // de if moto
    
    if(transporte instanceof Furgoneta){
      Furgoneta furgoneta = (Furgoneta) transporte;
      furgonetasDisponibles.add(furgonetasDisponibles.size(),furgoneta);
    } // de if furgonetas
  } // de add
  
  
  public void asignarPedido(Pedido pedido) {
    if(pedido.getPeso() <= PESO_MAX_MOTO){
      if(motosDisponibles.size() > 0){
        int e = 0;
        double coste = pedido.coste(motosDisponibles.get(0));
        for(int i=0; i+1 < motosDisponibles.size(); i++){
          if (coste > pedido.coste(motosDisponibles.get(i+1))){
            e = i+1;
            coste = pedido.coste(motosDisponibles.get(e));
          } // de if        
        } // de for
        pedido.setTransporte(motosDisponibles.get(e));
        motosDisponibles.remove(motosDisponibles.get(e));
      } // hay moto disponible
      else
        pedidosEsperandoMoto.add(pedido);    
    } // menor PESO_MAX_MOTO
    else{
      if(furgonetasDisponibles.size() > 0){
        int e = 0;
        double coste = pedido.coste(furgonetasDisponibles.get(0));
        for(int i=0; i+1 < furgonetasDisponibles.size(); i++){
          if (coste > pedido.coste(furgonetasDisponibles.get(i+1))){
            e = i+1;
            coste = pedido.coste(furgonetasDisponibles.get(e));
          } // de if        
        } // de for
        pedido.setTransporte(furgonetasDisponibles.get(e));
        furgonetasDisponibles.remove(furgonetasDisponibles.get(e));
      } // hay furgoneta disponible
      else
        pedidosEsperandoFurgoneta.add(pedido);    
      
    } // mayor PESO_MAX_MOTO
  } // asignar pedido
  
  //PRE: el pedido tiene asignado un transporte
  public void notificarEntregaPedido(Pedido pedido) throws PedidoSinTransporteAsignado { // de notificarEntregaPedido
    try {//try
      if(pedido.getTransporte() instanceof Moto) {//if moto
        Moto moto=(Moto)pedido.getTransporte();
        if(!pedidosEsperandoMoto.isEmpty())
          pedidosEsperandoMoto.poll().setTransporte(moto);
        else 
          motosDisponibles.add(motosDisponibles.size(), moto);
      }//if moto
      else if(pedido.getTransporte() instanceof Furgoneta) {//else if furgoneta
        Furgoneta furgoneta=(Furgoneta) pedido.getTransporte();
        if(!pedidosEsperandoFurgoneta.isEmpty())
          pedidosEsperandoFurgoneta.poll().setTransporte(furgoneta); 
        else 
          furgonetasDisponibles.add(furgonetasDisponibles.size(), furgoneta);
      }//else if furgoneta
      else
        throw new PedidoSinTransporteAsignado();
    }//try 
    catch (EmptyQueueException error) {//catch moto
      System.out.println (error.getMessage());
      error.printStackTrace(System.out);
    }//catch moto
  } // de notificarEntregaPedido
}//Gestion reparto local