import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.util.ArrayList;

class Gate{
  public String name;
  public int X;
  public int Y;
  ArrayList<Point> inputs_e=new ArrayList<>();//入力端子の相対座標
  ArrayList<Point> outputs_e=new ArrayList<>();//出力端子の相対座標
  public ArrayList<Point> inputs=new ArrayList<>();//入力端子の絶対座標
  public ArrayList<Point> outputs=new ArrayList<>();//出力端子の絶対座標
  public Point begin;
  public Point end;
  public boolean truthValue;
  public int terminals;
  ArrayList<Point> over=new ArrayList<>();//重なってる端子を赤色にする
  Gate(Point point){
    this.X=point.x;
    this.Y=point.y;
  }
  public void update(){
    inputs=new ArrayList<>();
    outputs=new ArrayList<>();
    int x=X-Screen.interval*2;
    int y=Y-Screen.interval*2;
    for (Point p:inputs_e){
      inputs.add(new Point(p.x+x,p.y+y));
    }
    for (Point p:outputs_e){
      outputs.add(new Point(p.x+x,p.y+y));
    }
  }
  public void draw(Graphics g){
  }
  public void setBegin(Point point){
  }
  public void setEnd(Point point){
  }
  public Point getBegin(){
    return null;
  }
  public Point getEnd(){
    return null;
  }
  public void setPos(Point point){
    this.X=point.x;
    this.Y=point.y;
  }
  public void Over(){
  }
  void dot(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    boolean flag=false;
    for (Point point:over){
      if (point.equals(new Point(x,y))){
	flag=true;
	break;
      }
    }
    g.setColor((flag) ? Color.RED : Color.WHITE);
    g.fillOval(x-4,y-4,8,8);
    g.setColor(Color.BLACK);
    g.drawOval(x-4,y-4,8,8);
  }
}

class And extends Gate{
  public int terminals;
  And(Point point,int terminals){
    super(point);
    this.terminals=terminals;
    inputs_e.add(new Point(-20,10));
    inputs_e.add(new Point(-20,30));
    outputs_e.add(new Point(70,20));
    if (terminals==3){
      inputs_e.add(new Point(-20,20));
    }
    this.name="and";
  }
  public void draw(Graphics g){
    Graphics2D g2=(Graphics2D)g;
    int x=X-Screen.interval*2;
    int y=Y-Screen.interval*2;
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

    if (terminals==3){
      g.drawLine(x,y+20,x-16,y+20);
      dot(g,x-20,y+20);
    }
  }
}

class Or extends Gate{
  public int terminals;
  Or(Point point,int terminals){
    super(point);
    this.terminals=terminals;
    inputs_e.add(new Point(-20,10));
    inputs_e.add(new Point(-20,30));
    outputs_e.add(new Point(70,20));
    if (terminals==3){
      inputs_e.add(new Point(-20,20));
    }
    this.name="or";
  }
  public void draw(Graphics g){
    Graphics2D g2=(Graphics2D)g;
    int x=X-Screen.interval*2;
    int y=Y-Screen.interval*2;
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

    if (terminals==3){
      g.drawLine(x,y+20,x-16,y+20);
      dot(g,x-20,y+20);
    }
  }
}

class Nand extends And{
  Nand(Point point,int terminals){
    super(point,terminals);
    this.name="nand";
  }
  public void draw(Graphics g){
    super.draw(g);
    Graphics2D g2=(Graphics2D)g;
    int x=X-Screen.interval*2;
    int y=Y-Screen.interval*2;
    dot(g,x+54,y+20);
  }
}

class Nor extends Or{
  Nor(Point point,int terminals){
    super(point,terminals);
    this.name="nor";
  }
  public void draw(Graphics g){
    super.draw(g);
    Graphics2D g2=(Graphics2D)g;
    int x=X-Screen.interval*2;
    int y=Y-Screen.interval*2;
    dot(g,x+57,y+20);
  }
}

class Not extends Gate{
  Not(Point point){
    super(point);
    inputs_e.add(new Point(-20,20));
    outputs_e.add(new Point(70,20));
    this.name="not";
  }
  public void draw(Graphics g){
    Graphics2D g2=(Graphics2D)g;
    int x=X-Screen.interval*2;
    int y=Y-Screen.interval*2;
    g.drawLine(x,y,x,y+40);
    g.drawLine(x,y+40,x+28,y+20);
    g.drawLine(x+28,y+20,x,y);
    g.drawLine(x+36,y+20,x+66,y+20);
    g.drawLine(x,y+20,x-16,y+20);
    dot(g,x+30,y+20);

    dot(g,x-20,y+20);
    dot(g,x+70,y+20);
  }
}

class Xor extends Or{
  Xor(Point point,int terminals){
    super(point,terminals);
    this.name="xor";
  }
  public void draw(Graphics g){
    super.draw(g);
    Graphics2D g2=(Graphics2D)g;
    int x=X-Screen.interval*2;
    int y=Y-Screen.interval*2;
    g2.draw(new Arc2D.Double(x-80,y-20,80,80,-30,60,Arc2D.OPEN));
  }
}

class Xnor extends Nor{
  Xnor(Point point,int terminals){
    super(point,terminals);
    this.name="xnor";
  }
  public void draw(Graphics g){
    super.draw(g);
    Graphics2D g2=(Graphics2D)g;
    int x=X-Screen.interval*2;
    int y=Y-Screen.interval*2;
    g2.draw(new Arc2D.Double(x-80,y-20,80,80,-30,60,Arc2D.OPEN));
  }
}

class Input extends Gate{
  Input(Point point,boolean truthValue){
    super(point);
    outputs_e.add(new Point(70,10));
    this.name="input";
    this.truthValue=truthValue;
  }
  public void draw(Graphics g){
    Graphics2D g2=(Graphics2D)g;
    int x=X-Screen.interval*2;
    int y=Y-Screen.interval*2;
    Path2D.Double p=new Path2D.Double();
    p.moveTo(x+50,y+10);
    p.lineTo(x+40,y);
    p.lineTo(x,y);
    p.lineTo(x,y+20);
    p.lineTo(x+40,y+20);
    p.lineTo(x+50,y+10);
    p.lineTo(x+66,y+10);
    if (truthValue)
      g2.fill(p);
    else
      g2.draw(p);
    dot(g,x+70,y+10);
  }
}

class Output extends Gate{
  Output(Point point,boolean truthValue){
    super(point);
    inputs_e.add(new Point(-30,10));
    this.name="output";
    this.truthValue=truthValue;
  }
  public void draw(Graphics g){
    Graphics2D g2=(Graphics2D)g;
    int x=X-Screen.interval*2;
    int y=Y-Screen.interval*2;
    Path2D.Double p=new Path2D.Double();
    p.moveTo(x+40,y+20);
    p.lineTo(x+40,y);
    p.lineTo(x,y);
    p.lineTo(x-10,y+10);
    p.lineTo(x,y+20);
    p.lineTo(x+40,y+20);
    p.moveTo(x-10,y+10);
    p.lineTo(x-26,y+10);
    if (truthValue)
      g2.fill(p);
    else
      g2.draw(p);
    dot(g,x-30,y+10);
  }
}

class Line extends Gate{
  public Point begin;
  public Point end;
  Line(Point point){
    super(point);
    this.name="line";
  }
  public void setBegin(Point point){
    begin=point;
  }
  public void setEnd(Point point){
    end=point;
  }
  public Point getBegin(){
    return begin;
  }
  public Point getEnd(){
    return end;
  }
  public void draw(Graphics g){
    Graphics2D g2=(Graphics2D)g;
    if (begin==null){
      dot(g,X,Y);
    }else{
      dot(g,begin.x,begin.y);
      Path2D.Double p=new Path2D.Double();
      p.moveTo(begin.x,begin.y);
      if (end==null){
	dot(g,X,Y);
	p.lineTo(X,Y);
      }else{
	dot(g,end.x,end.y);
	p.lineTo(end.x,end.y);
      }
      g2.draw(p);
    }
  }
}
