package gestionpedidos.transportes;
import gestionpedidos.mapa.Mapa;

public class FurgonetaSubcontratada extends Furgoneta{//FurgonetaSubcontratada
  
  private double eurosPKm=1;
  
  public FurgonetaSubcontratada (String codigo, Mapa mapa, double tara){//constructor
    super(codigo, mapa, tara);
  }//constructor
  
  public double getEurosPKm(){//getter
    return eurosPKm;
  }//getter
  
  public void setEurosPKm(double eurosPKm){//set
    this.eurosPKm=eurosPKm;
  }//set
  
  public double coste (String codOrigen, String codDestino){//coste
    double coste=0;
    Mapa m= super.getMapa();
    if (super.getTara()<1000)
      coste=m.distancia(codOrigen,codDestino)*eurosPKm;
    else
      coste=m.distancia(codOrigen,codDestino)*eurosPKm*1.1;
    return coste;
  }//coste
}//FurgonetaSubcontratada