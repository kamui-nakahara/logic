import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.awt.Point;

class Load{
  public ArrayList<Gate> load(String filename){
    try(FileInputStream f=new FileInputStream(filename);
	BufferedInputStream b=new BufferedInputStream(f);
	ObjectInputStream in=new ObjectInputStream(b)){
      Gates gates=(Gates)in.readObject();
      ArrayList<Gate> g=new ArrayList<>();
      for (Gate gate:gates.gates){
	Gate ga;
	switch(gate.name){
	  case "and":
	    ga=new And(new Point(gate.X,gate.Y),gate.terminals);
	    ga.update();
	    g.add(ga);
	    break;
	  case "or":
	    ga=new Or(new Point(gate.X,gate.Y),gate.terminals);
	    ga.update();
	    g.add(ga);
	    break;
	  case "not":
	    ga=new Not(new Point(gate.X,gate.Y));
	    ga.update();
	    g.add(ga);
	    break;
	  case "nand":
	    ga=new Nand(new Point(gate.X,gate.Y),gate.terminals);
	    ga.update();
	    g.add(ga);
	    break;
	  case "nor":
	    ga=new Nor(new Point(gate.X,gate.Y),gate.terminals);
	    ga.update();
	    g.add(ga);
	    break;
	  case "xor":
	    ga=new Xor(new Point(gate.X,gate.Y),gate.terminals);
	    ga.update();
	    g.add(ga);
	    break;
	  case "xnor":
	    ga=new Xnor(new Point(gate.X,gate.Y),gate.terminals);
	    ga.update();
	    g.add(ga);
	    break;
	  case "input":
	    ga=new Input(new Point(gate.X,gate.Y),gate.truthValue);
	    ga.update();
	    g.add(ga);
	    break;
	  case "output":
	    ga=new Output(new Point(gate.X,gate.Y),gate.truthValue);
	    ga.update();
	    g.add(ga);
	    break;
	  case "sevenSegment":
	    ga=new SevenSegment(new Point(gate.X,gate.Y));
	    ga.update();
	    g.add(ga);
	    break;
	  case "line":
	    ga=new Line(new Point(gate.X,gate.Y));
	    ga.setBegin(gate.getBegin());
	    ga.setEnd(gate.getEnd());
	    ga.update();
	    g.add(ga);
	    break;
	}
      }
      return g;
    }catch(IOException e){
      e.printStackTrace();
    }catch(ClassNotFoundException e){
      e.printStackTrace();
    }
    return null;
  }
}
