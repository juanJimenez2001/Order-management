package gestionpedidos.transportes;
import gestionpedidos.mapa.Mapa;


public class Moto extends Transporte{//Moto
  
  private double eurosPKm = 2;
  public static final double TARIFA_MIN = 10;
  
  public Moto(String codigo, Mapa mapa){// de constructor
    super(codigo,mapa);
  } // de constructor
  
  public double getEurosPKm(){// de getEurosPKm
    return eurosPKm;
  } // de getEurosPKm
  
  public void setEurosPKm(double eur){// de setEurosPKm
    eurosPKm=eur;
  } // de setEurosPKm
  
  public double coste(String codOrigen, String codDestino){// de coste
    Mapa m= super.getMapa();
    return m.distancia(codOrigen, codDestino)*eurosPKm + TARIFA_MIN;
  }// de coste
}//Moto   
  
    

  
  
  
  
  
  
  
  
  
  
  
  
  
  


