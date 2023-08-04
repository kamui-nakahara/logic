import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Display extends JFrame implements KeyListener{
  Display(String title,int width,int height){
    super(title);
    setSize(width,height);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    addKeyListener(this);
  }
  void close(){
    dispose();
    System.exit(0);
  }
  public void update(){
  }
  public void keyTyped(KeyEvent e){
  }
  public void keyPressed(KeyEvent e){
    switch (e.getKeyCode()){
      case KeyEvent.VK_ESCAPE:
	close();
	break;
    }
  }
  public void keyReleased(KeyEvent e){
  }
}
