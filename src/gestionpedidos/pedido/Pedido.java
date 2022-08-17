package gestionpedidos.pedido;
import gestionpedidos.transportes.Transporte;
import gestionpedidos.mapa.Mapa;
import gestionpedidos.pedido.Cliente;
import gestionpedidos.pedido.PlatoComida;
import gestionpedidos.pedido.Restaurante;

public class Pedido{//Pedido
  private double importe;
  private Cliente cliente;
  private PlatoComida[] comidas;
  private Restaurante restaurante;
  private Transporte transporte;
  private double peso;
  
  public Pedido(Cliente cliente, PlatoComida[] comidas, Restaurante restaurante){//constructor
    this.cliente=cliente;
    this.restaurante=restaurante;
    this.comidas = new PlatoComida[comidas.length];
    for(int i=0;i<comidas.length;i++){
      this.comidas[i]=comidas[i];
    } // de for
    for(int i=0;i<comidas.length;i++){//for
      if(comidas[i] != null){
        this.peso = peso + comidas[i].getPeso();
        this.importe = importe + comidas[i].getPrecio();
      } // de if 
    }//for    
  }//constructor
  
  public double getPeso(){//getPeso
    return peso;
  }//getPeso
  
  public Cliente getCliente(){//getCliente
    return cliente;
  }//getCliente
  
  public Restaurante getRestaurante(){//getRestaurante
    return restaurante;
  }//getRestaurante
  
  public Transporte getTransporte(){//getTransporte
    return transporte;
  }//getTransporte
  
  public void setTransporte(Transporte transporte){//getTransporte
    this.transporte=transporte;
  }//getTransporte
  
  public double getImporte(){//getTransporte
    return importe;
  }//getTransporte
  
  public double coste(Transporte transporte){//coste
    double coste=0;
    if (importe<100)
      coste= transporte.coste(restaurante.getCodigo())+transporte.coste(restaurante.getCodigo(),cliente.getCodigo())+importe;
    else
      coste= (transporte.coste(restaurante.getCodigo())+transporte.coste(restaurante.getCodigo(),cliente.getCodigo()))*1.1+importe;
    return coste;
  }//coste
}//Pedido