package gestionpedidos.transportes;
import gestionpedidos.mapa.Mapa;
import java.util.HashMap;


public abstract class Transporte{
  
  private String codigo;
  private Mapa mapa;
  
  
  public Transporte(String codigo, Mapa mapa){
    this.codigo=codigo;
    this.mapa=mapa;
  } // de constructor
  
  protected Mapa getMapa (){//getter
    return mapa;
  }//getter
  
  public String getCodigo(){// de getCod
    return codigo;
  } // de getCod
  
  public void setCodigo(String codigo){// de setCod
    this.codigo=codigo;
  } // de setCod
  
  
  public double coste(String posDestino){// de coste
    return coste(this.codigo, posDestino);
  } // de coste
  
  public abstract double coste(String codPosOrigen, String codPosDestino);// de coste
  
} // de class transporte







