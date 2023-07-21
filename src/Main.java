public class Main{
  public static void main(String[] args){
    System.setProperty("apple.laf.useScreenMenuBar","true");
    Display display=new Display("論理回路",1000,800);
    display.setVisible(true);
    while (true){
      display.update();
      display.repaint();
    }
  }
}
