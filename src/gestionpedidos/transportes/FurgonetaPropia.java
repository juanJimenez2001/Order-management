package gestionpedidos.transportes;
import gestionpedidos.mapa.Mapa;

public class FurgonetaPropia extends Furgoneta{//FurgonetaPropia
  
  private double velocidadMedia = 30;
  private double EUROS_P_HORA = 40;
  
  public FurgonetaPropia(String codigo, Mapa mapa, double tara){//constructor
    super(codigo, mapa, tara);
  }//constructor
  
  public double getVelocidadMedia(){//getter
    return velocidadMedia;
  }//getter
  
  public void setVelocidadMedia(double velocidadMedia){//set
    this.velocidadMedia=velocidadMedia;
  }//set
  
  public double coste (String codOrigen, String codDestino){//coste
    double coste=0;
    Mapa m= super.getMapa();
    if (super.getTara()<500)
      coste=m.distancia(codOrigen,codDestino)*EUROS_P_HORA/velocidadMedia;
    else
      coste=m.distancia(codOrigen,codDestino)*EUROS_P_HORA/velocidadMedia*1.1;
    return coste;
  }//coste
}//FurgonetaPropia