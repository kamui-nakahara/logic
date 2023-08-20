import java.io.Serializable;
import java.util.ArrayList;

class Gates implements Serializable{
  public ArrayList<Gate> gates=new ArrayList<>();
  Gates(ArrayList<Gate> gates){
    this.gates=gates;
  }
}
