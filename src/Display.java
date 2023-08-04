import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;

class Display extends JFrame implements KeyListener{
  Screen screen;
  Display(String title,int width,int height){
    super(title);
    setSize(width,height);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    screen=new Screen(this);
    screen.setPreferredSize(new Dimension(width,height));

    JScrollPane scrollpane=new JScrollPane(screen);
    scrollpane.setPreferredSize(new Dimension(width,height));

    add(scrollpane);

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
    switch(e.getKeyCode()){
      case KeyEvent.VK_ESCAPE:
	close();
	break;
    }
  }
  public void keyReleased(KeyEvent e){
  }
}
