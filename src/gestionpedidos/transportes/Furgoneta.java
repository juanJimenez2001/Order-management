package gestionpedidos.transportes;
import gestionpedidos.mapa.Mapa;

public abstract class Furgoneta extends Transporte{//Furgoneta
  
  private double tara;
  
  public Furgoneta(String codigo, Mapa mapa, double tara){//constructor
    super(codigo,mapa);
    this.tara=tara;
  }//constructor
  
  public double getTara(){//getter
    return tara;
  }//getter
  
  public void setTara(double tara){//set
    this.tara=tara;
  }//set
  
  public abstract double coste(String codPosOrigen, String codPosDestino);// de coste
  
}//Furgoneta