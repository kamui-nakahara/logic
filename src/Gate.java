import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.BasicStroke;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.HashMap;

class Gate{
  HashMap<String,String> truthTable=new HashMap<>();
  public Color strokeColor=Color.BLACK;
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
  int output_terminals;
  Point center;
  ArrayList<Point> over=new ArrayList<>();//重なってる端子を赤色にする
  Color rectColor=Color.BLUE;
  public ArrayList<Boolean> input_terminal=new ArrayList<>();
  Gate(Point point,int terminals){
    this.X=point.x;
    this.Y=point.y;
    this.terminals=terminals;
    for (int i=0;i<terminals;i++){
      input_terminal.add(false);
    }
  }
  public Point getCenter(){
    return center;
  }
  public void reverse(){
  }
  public void operation(){
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
    int cx;
    int cy;
    if (name=="line"){
      if (getEnd()==null){
	cx=0;
	cy=0;
      }else{
	Point p1=getBegin();
	Point p2=getEnd();
	cx=(p1.x+p2.x)/2;
	cy=(p1.y+p2.y)/2;
      }
    }else{
      if (inputs.size()==0){
	cy=outputs.get(0).y;
	cx=outputs.get(0).x-50;
      }else if (outputs.size()==0){
	cy=inputs.get(0).y;
	cx=inputs.get(0).x+50;
      }else{
	cy=outputs.get(0).y;
	cx=(inputs.get(0).x+outputs.get(0).x)/2;
      }
    }
    center=new Point(cx,cy);
  }
  public void draw(Graphics g){
  }
  public Rectangle deleteRect(){
    return new Rectangle(center.x-Screen.interval,center.y-Screen.interval,Screen.interval*2,Screen.interval*2);
  }
  public void rectDraw(Graphics g){
    Graphics2D g2=(Graphics2D)g;
    g.setColor(rectColor);
    g2.setStroke(new BasicStroke(5));
    g2.draw(deleteRect());
    g.setColor(Color.BLACK);
    g2.setStroke(new BasicStroke(2));
  }
  public void deleteRectDraw(Graphics g){
    rectDraw(g);
    Graphics2D g2=(Graphics2D)g;
    Rectangle r=deleteRect();
    g.setColor(rectColor);
    g2.setStroke(new BasicStroke(5));
    g.drawLine(r.x+Screen.interval/2,r.y+Screen.interval/2,r.x+Screen.interval*3/2,r.y+Screen.interval*3/2);
    g.drawLine(r.x+Screen.interval/2,r.y+Screen.interval*3/2,r.x+Screen.interval*3/2,r.y+Screen.interval/2);
    g.setColor(Color.BLACK);
    g2.setStroke(new BasicStroke(2));
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
  public void setTruthValue(){
    truthValue=!truthValue;
  }
  public void setTruthValue(int a){
    if (a==0){
      truthValue=false;
    }else{
      truthValue=true;
    }
  }
  public void Over(){
  }
  void dot(Graphics g,int x,int y){
    Graphics2D g2=(Graphics2D)g;
    boolean flag=false;
    int a=4;
    for (Point point:over){
      if (point.equals(new Point(x,y))){
	flag=true;
	a=7;
	break;
      }
    }
    g.setColor((flag) ? Color.RED : Color.WHITE);
    g.fillOval(x-a,y-a,a*2,a*2);
    g.setColor(Color.BLACK);
    g.drawOval(x-4,y-4,8,8);
  }
}

class And extends Gate{
  public int terminals;
  And(Point point,int terminals){
    super(point,terminals);
    this.terminals=terminals;
    inputs_e.add(new Point(-20,10));
    inputs_e.add(new Point(-20,30));
    outputs_e.add(new Point(70,20));
    if (terminals==3){
      inputs_e.add(new Point(-20,20));
    }
    this.name="and";
  }
  public void operation(){
    int out=1;
    for (int i=0;i<terminals;i++){
      if (!input_terminal.get(i))
	out*=0;
    }
    if (out==0){
      truthValue=false;
    }else{
      truthValue=true;
    }
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
    super(point,terminals);
    this.terminals=terminals;
    inputs_e.add(new Point(-20,10));
    inputs_e.add(new Point(-20,30));
    outputs_e.add(new Point(70,20));
    if (terminals==3){
      inputs_e.add(new Point(-20,20));
    }
    this.name="or";
  }
  public void operation(){
    int out=0;
    for (int i=0;i<terminals;i++){
      if (input_terminal.get(i))
	out+=1;
    }
    if (out==0){
      truthValue=false;
    }else{
      truthValue=true;
    }
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
  public void operation(){
    int out=1;
    for (int i=0;i<terminals;i++){
      if (!input_terminal.get(i))
	out*=0;
    }
    if (out==0){
      truthValue=true;
    }else{
      truthValue=false;
    }
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
  public void operation(){
    int out=0;
    for (int i=0;i<terminals;i++){
      if (input_terminal.get(i))
	out+=1;
    }
    if (out==0){
      truthValue=true;
    }else{
      truthValue=false;
    }
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
    super(point,1);
    inputs_e.add(new Point(-20,20));
    outputs_e.add(new Point(70,20));
    this.name="not";
  }
  public void operation(){
    truthValue=!input_terminal.get(0);
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
  public void operation(){
    int out=0;
    for (int i=0;i<terminals;i++){
      if (input_terminal.get(i))
	out+=1;
    }
    if (out%2==0)
      truthValue=false;
    else
      truthValue=true;
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
  public void operation(){
    int out=0;
    for (int i=0;i<terminals;i++){
      if (input_terminal.get(i))
	out+=1;
    }
    if (out%2==0)
      truthValue=true;
    else
      truthValue=false;
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
  Font font=new Font("",Font.PLAIN,Screen.interval*2);
  Input(Point point,boolean truthValue){
    super(point,0);
    outputs_e.add(new Point(70,10));
    this.name="input";
    this.truthValue=truthValue;
  }
  public void draw(Graphics g){
    g.setColor(strokeColor);
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
    g2.setFont(font);
    if (truthValue)
      drawStringCenter(g,"H");
    else
      drawStringCenter(g,"L");
    g2.draw(p);
    dot(g,x+70,y+10);
  }
  public void drawStringCenter(Graphics g,String text){
    FontMetrics fm=g.getFontMetrics();
    Rectangle rectText=fm.getStringBounds(text,g).getBounds();
    int x=center.x-rectText.width/2;
    int y=center.y-rectText.height/2+fm.getMaxAscent();
    g.drawString(text,x,y);
  }
}

class Output extends Gate{
  Font font=new Font("",Font.PLAIN,Screen.interval*2);
  Output(Point point,boolean truthValue){
    super(point,1);
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
    g2.setFont(font);
    if (truthValue)
      drawStringCenter(g,"H");
    else
      drawStringCenter(g,"L");
    g2.draw(p);
    dot(g,x-30,y+10);
  }
  public void drawStringCenter(Graphics g,String text){
    FontMetrics fm=g.getFontMetrics();
    Rectangle rectText=fm.getStringBounds(text,g).getBounds();
    int x=center.x-rectText.width/2;
    int y=center.y-rectText.height/2+fm.getMaxAscent();
    g.drawString(text,x,y);
  }
}

class Line extends Gate{
  public Point begin;
  public Point end;
  public boolean reverse=false;
  Line(Point point){
    super(point,1);
    this.name="line";
    this.terminals=1;
  }
  public void setBegin(Point point){
    begin=point;
  }
  public void setEnd(Point point){
    end=point;
  }
  public void reverse(){
    reverse=!reverse;
  }
  public Point getBegin(){
    if (reverse)
      return end;
    else
      return begin;
  }
  public Point getEnd(){
    if (reverse)
      return begin;
    else
      return end;
  }
  public void operation(){
    truthValue=input_terminal.get(0);
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

class Block extends Gate{
  Block(Point point,HashMap<String,String> truthTable,String name,int input_terminals,int output_terminals){
    super(point,input_terminals);
    this.truthTable=truthTable;
    this.name=name;
    this.output_terminals=output_terminals;
    int x=X-Screen.interval*2;
    int y=Y-Screen.interval*2;
    for (int i=0;i<terminals/2;i++){
      inputs_e.add(new Point(x-Screen.interval*2,y+Screen.interval*2-(i+1)*Screen.interval));
      inputs_e.add(new Point(x-Screen.interval*2,y+Screen.interval*2+(i+1)*Screen.interval));
    }
    if (terminals%2==1){
      inputs_e.add(new Point(x-Screen.interval*2,y+Screen.interval*2));
    }
    for (int i=0;i<output_terminals/2;i++){
      outputs_e.add(new Point(x+Screen.interval*6,y+Screen.interval*2-(i+1)*Screen.interval));
      outputs_e.add(new Point(x+Screen.interval*6,y+Screen.interval*2+(i+1)*Screen.interval));
    }
    if (output_terminals%2==1){
      outputs_e.add(new Point(x-Screen.interval*2,y+Screen.interval*2));
    }
  }
  public void operation(){
  }
  public void draw(Graphics g){
    int x=X-Screen.interval*2;
    int y=Y-Screen.interval*2;
    g.drawRect(x,y,Screen.interval*4,Screen.interval*4);
    g.drawLine(x,y+Screen.interval*2-(terminals/2+1)*Screen.interval,x,y+Screen.interval*2+(terminals/2+1)*Screen.interval);
    g.drawLine(x+Screen.interval*4,y+Screen.interval*2-(output_terminals/2+1)*Screen.interval,x+Screen.interval*4,y+Screen.interval*2+(output_terminals/2+1)*Screen.interval);
    for (int i=0;i<terminals/2;i++){
      g.drawLine(x,y+Screen.interval*2-(i+1)*Screen.interval,x-Screen.interval*2,y+Screen.interval*2-(i+1)*Screen.interval);
      g.drawLine(x,y+Screen.interval*2+(i+1)*Screen.interval,x-Screen.interval*2,y+Screen.interval*2+(i+1)*Screen.interval);
      dot(g,x-Screen.interval*2,y+Screen.interval*2-(i+1)*Screen.interval);
      dot(g,x-Screen.interval*2,y+Screen.interval*2+(i+1)*Screen.interval);
    }
    if (terminals%2==1){
      g.drawLine(x,y+Screen.interval*2,x-Screen.interval*2,y+Screen.interval*2);
      dot(g,x-Screen.interval*2,y+Screen.interval*2);
    }
    for (int i=0;i<output_terminals/2;i++){
      g.drawLine(x+Screen.interval*4,y+Screen.interval*2-(i+1)*Screen.interval,x+Screen.interval*6,y+Screen.interval*2-(i+1)*Screen.interval);
      g.drawLine(x+Screen.interval*4,y+Screen.interval*2+(i+1)*Screen.interval,x+Screen.interval*6,y+Screen.interval*2+(i+1)*Screen.interval);
      dot(g,x+Screen.interval*6,y+Screen.interval*2-(i+1)*Screen.interval);
      dot(g,x+Screen.interval*6,y+Screen.interval*2+(i+1)*Screen.interval);
    }
    if (output_terminals%2==1){
      g.drawLine(x+Screen.interval*4,y+Screen.interval*2,x+Screen.interval*6,y+Screen.interval*2);
      dot(g,x+Screen.interval*6,y+Screen.interval*2);
    }
  }
}
