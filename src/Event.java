import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class SelectGate implements ActionListener{
  Screen screen;
  String name;
  SelectGate(Screen screen,String name){
    this.screen=screen;
    this.name=name;
  }
  @Override
  public void actionPerformed(ActionEvent e){
    switch (name){
      case "and2":
	break;
      case "and3":
	break;
      case "or2":
	break;
      case "or3":
	break;
      case "not":
	break;
      case "nand2":
	break;
      case "nand3":
	break;
      case "nor2":
	break;
      case "nor3":
	break;
      case "xor2":
	break;
      case "xor3":
	break;
      case "xnor2":
	break;
      case "xnor3":
	break;
      case "input":
	break;
      case "output":
	break;
      case "line":
	break;
    }
  }
}
