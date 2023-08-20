import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
 
class Save{
  public void save(String filename,ArrayList<Gate> g){
    try(FileOutputStream f=new FileOutputStream(filename);
      BufferedOutputStream b=new BufferedOutputStream(f);
      ObjectOutputStream out=new ObjectOutputStream(b)){

      Gates gates=new Gates(g);

      out.writeObject(gates);
    }catch(IOException e) {
      e.printStackTrace();
    }
  }
}
