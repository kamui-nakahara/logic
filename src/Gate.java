import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.Color;
import java.awt.geom.Path2D;

class Gate{
  public static String having="and2";
  public static void and2(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    g.drawLine(x+30,y,x,y);
    g.drawLine(x,y,x,y+40);
    g.drawLine(x,y+40,x+30,y+40);
    g.drawLine(x+50,y+20,x+70,y+20);
    g.drawLine(x,y+10,x-16,y+10);
    g.drawLine(x,y+30,x-16,y+30);
    g2.draw(new Arc2D.Double(x+10,y,40,40,-90,180,Arc2D.OPEN));

    dot(g,x-20,y+10);
    dot(g,x-20,y+30);
    dot(g,x+70,y+20);

    //return
  }
  public static void and3(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    g.drawLine(x+30,y,x,y);
    g.drawLine(x,y,x,y+40);
    g.drawLine(x,y+40,x+30,y+40);
    g.drawLine(x+50,y+20,x+66,y+20);
    g.drawLine(x,y+10,x-16,y+10);
    g.drawLine(x,y+30,x-16,y+30);
    g.drawLine(x,y+20,x-16,y+20);
    g2.draw(new Arc2D.Double(x+10,y,40,40,-90,180,Arc2D.OPEN));

    dot(g,x-20,y+10);
    dot(g,x-20,y+20);
    dot(g,x-20,y+30);
    dot(g,x+70,y+20);
  }
  public static void or2(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    g.drawLine(x,y,x+20,y);
    g.drawLine(x,y+40,x+20,y+40);
    g.drawLine(x+55,y+20,x+66,y+20);
    g.drawLine(x+3,y+10,x-16,y+10);
    g.drawLine(x+3,y+30,x-16,y+30);
    g2.draw(new Arc2D.Double(x-75,y-20,80,80,-30,60,Arc2D.OPEN));
    g2.draw(new Arc2D.Double(x-20,y,80,80,90,-60,Arc2D.OPEN));
    g2.draw(new Arc2D.Double(x-20,y-40,80,80,-90,60,Arc2D.OPEN));

    dot(g,x-20,y+10);
    dot(g,x-20,y+30);
    dot(g,x+70,y+20);
  }
  public static void or3(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    g.drawLine(x,y,x+20,y);
    g.drawLine(x,y+40,x+20,y+40);
    g.drawLine(x+55,y+20,x+66,y+20);
    g.drawLine(x+3,y+10,x-16,y+10);
    g.drawLine(x+3,y+30,x-16,y+30);
    g.drawLine(x+3,y+20,x-16,y+20);
    g2.draw(new Arc2D.Double(x-75,y-20,80,80,-30,60,Arc2D.OPEN));
    g2.draw(new Arc2D.Double(x-20,y,80,80,90,-60,Arc2D.OPEN));
    g2.draw(new Arc2D.Double(x-20,y-40,80,80,-90,60,Arc2D.OPEN));

    dot(g,x-20,y+10);
    dot(g,x-20,y+20);
    dot(g,x-20,y+30);
    dot(g,x+70,y+20);
  }
  public static void nand2(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    and2(g,x,y);
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    dot(g,x+54,y+20);
  }
  public static void nand3(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    and3(g,x,y);
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    dot(g,x+54,y+20);
  }
  public static void nor2(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    or2(g,x,y);
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    dot(g,x+57,y+20);
  }
  public static void nor3(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    or3(g,x,y);
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    dot(g,x+57,y+20);
  }
  public static void not(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    g.drawLine(x,y,x,y+40);
    g.drawLine(x,y+40,x+28,y+20);
    g.drawLine(x+28,y+20,x,y);
    g.drawLine(x+36,y+20,x+66,y+20);
    g.drawLine(x,y+20,x-16,y+20);
    dot(g,x+30,y+20);

    dot(g,x-20,y+20);
    dot(g,x+70,y+20);
  }
  public static void xor2(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    or2(g,x,y);
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    g2.draw(new Arc2D.Double(x-80,y-20,80,80,-30,60,Arc2D.OPEN));
  }
  public static void xor3(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    or3(g,x,y);
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    g2.draw(new Arc2D.Double(x-80,y-20,80,80,-30,60,Arc2D.OPEN));
  }
  public static void xnor2(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    g2.draw(new Arc2D.Double(x-80,y-20,80,80,-30,60,Arc2D.OPEN));
    x+=Screen.interval*2;
    y+=Screen.interval*2;
    nor2(g,x,y);
  }
  public static void xnor3(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    g2.draw(new Arc2D.Double(x-80,y-20,80,80,-30,60,Arc2D.OPEN));
    x+=Screen.interval*2;
    y+=Screen.interval*2;
    nor3(g,x,y);
  }
  public static void input(Graphics g,int x,int y,boolean value){
    Graphics2D g2=(Graphics2D)g;
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    Path2D.Double p=new Path2D.Double();
    p.moveTo(x+50,y+10);
    p.lineTo(x+40,y);
    p.lineTo(x,y);
    p.lineTo(x,y+20);
    p.lineTo(x+40,y+20);
    p.lineTo(x+50,y+10);
    p.lineTo(x+66,y+10);
    if (value)
      g2.fill(p);
    else
      g2.draw(p);
    dot(g,x+70,y+10);
  }
  public static void output(Graphics g,int x,int y,boolean value){
    Graphics2D g2=(Graphics2D)g;
    x-=Screen.interval*2;
    y-=Screen.interval*2;
    Path2D.Double p=new Path2D.Double();
    p.moveTo(x+40,y+20);
    p.lineTo(x+40,y);
    p.lineTo(x,y);
    p.lineTo(x-10,y+10);
    p.lineTo(x,y+20);
    p.lineTo(x+40,y+20);
    p.moveTo(x-10,y+10);
    p.lineTo(x-26,y+10);
    if (value)
      g2.fill(p);
    else
      g2.draw(p);
    dot(g,x-30,y+10);
  }
  public static void dot(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    g.setColor(Color.WHITE);
    g.fillOval(x-4,y-4,8,8);
    g.setColor(Color.BLACK);
    g.drawOval(x-4,y-4,8,8);
  }
}
