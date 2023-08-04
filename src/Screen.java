import javax.swing.JPanel;
import java.awt.Graphics;

class Screen extends JPanel{
  Display display;
  Screen(Display display){
    this.display=display;
  }
  public void paintComponent(Graphics g){
    super.paintComponent(g);
  }
}
