import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

class Display extends JFrame implements KeyListener{
  public int width;
  public int height;
  Screen screen;
  Display(String title,int width,int height){
    super(title);
    this.width=width;
    this.height=height;
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(width,height);
    setResizable(false);
    JMenuBar menubar=new JMenuBar();
    JMenu menu1=new JMenu("ファイル");
    JMenuItem item1_1=new JMenuItem("終了");
    item1_1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	dispose();
	System.exit(0);
      }
    });
    menu1.add(item1_1);
    menubar.add(menu1);
    JMenu menu2=new JMenu("編集");
    JMenuItem item2_1=new JMenuItem("削除");
    item2_1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.state=1;
      }
    });
    menu2.add(item2_1);
    JMenuItem item2_2=new JMenuItem("入力切り替え");
    item2_2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.state=2;
      }
    });
    menu2.add(item2_2);
    JMenuItem item2_3=new JMenuItem("ブロック化");
    item2_3.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.state=3;
      }
    });
    menu2.add(item2_3);
    menubar.add(menu2);
    JMenu menu3=new JMenu("アイテム");
    JMenuItem item3_1=new JMenuItem("2入力ANDゲート");
    item3_1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new And(screen.mousePoint,2);
	screen.state=0;
      }
    });
    menu3.add(item3_1);
    JMenuItem item3_2=new JMenuItem("3入力ANDゲート");
    item3_2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new And(screen.mousePoint,3);
	screen.state=0;
      }
    });
    menu3.add(item3_2);
    JMenuItem item3_3=new JMenuItem("2入力ORゲート");
    item3_3.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Or(screen.mousePoint,2);
	screen.state=0;
      }
    });
    menu3.add(item3_3);
    JMenuItem item3_4=new JMenuItem("3入力ORゲート");
    item3_4.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Or(screen.mousePoint,3);
	screen.state=0;
      }
    });
    menu3.add(item3_4);
    JMenuItem item3_5=new JMenuItem("NOTゲート");
    item3_5.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Not(screen.mousePoint);
	screen.state=0;
      }
    });
    menu3.add(item3_5);
    JMenuItem item3_6=new JMenuItem("2入力NANDゲート");
    item3_6.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Nand(screen.mousePoint,2);
	screen.state=0;
      }
    });
    menu3.add(item3_6);
    JMenuItem item3_7=new JMenuItem("3入力NANDゲート");
    item3_7.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Nand(screen.mousePoint,3);
	screen.state=0;
      }
    });
    menu3.add(item3_7);
    JMenuItem item3_8=new JMenuItem("2入力NORゲート");
    item3_8.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Nor(screen.mousePoint,2);
	screen.state=0;
      }
    });
    menu3.add(item3_8);
    JMenuItem item3_9=new JMenuItem("3入力NORゲート");
    item3_9.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Nor(screen.mousePoint,3);
	screen.state=0;
      }
    });
    menu3.add(item3_9);
    JMenuItem item3_10=new JMenuItem("2入力XORゲート");
    item3_10.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Xor(screen.mousePoint,2);
	screen.state=0;
      }
    });
    menu3.add(item3_10);
    JMenuItem item3_11=new JMenuItem("3入力XORゲート");
    item3_11.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Xor(screen.mousePoint,3);
	screen.state=0;
      }
    });
    menu3.add(item3_11);
    JMenuItem item3_12=new JMenuItem("2入力XNORゲート");
    item3_12.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Xnor(screen.mousePoint,2);
	screen.state=0;
      }
    });
    menu3.add(item3_12);
    JMenuItem item3_13=new JMenuItem("3入力XNORゲート");
    item3_13.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Xnor(screen.mousePoint,3);
	screen.state=0;
      }
    });
    menu3.add(item3_13);
    JMenuItem item3_14=new JMenuItem("入力ノード");
    item3_14.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Input(screen.mousePoint,false);
	screen.state=0;
      }
    });
    menu3.add(item3_14);
    JMenuItem item3_15=new JMenuItem("出力ノード");
    item3_15.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Output(screen.mousePoint,false);
	screen.state=0;
      }
    });
    menu3.add(item3_15);
    JMenuItem item3_16=new JMenuItem("結線");
    item3_16.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	screen.gate=new Line(screen.mousePoint);
	screen.state=0;
      }
    });
    menu3.add(item3_16);
    menubar.add(menu3);
    setJMenuBar(menubar);

    this.screen=new Screen(this);
    add(screen);

    addKeyListener(this);
  }
  @Override
  public void keyTyped(KeyEvent e){
  }
  @Override
  public void keyPressed(KeyEvent e){
    if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
      dispose();
      System.exit(0);
    }else{
      screen.keyPressed(e);
    }
  }
  @Override
  public void keyReleased(KeyEvent e){
  }
  public void update(){
    screen.update();
  }
}

class Screen extends JPanel implements MouseMotionListener,MouseListener{
  public Point mousePoint=new Point(0,0);
  Display display;
  public static int interval=10;
  public int state=0; //0:ゲート設置 1:削除 2:入力切り替え
  Color mouseColor=new Color(128,128,128,128);
  //public Gate gate=new And(mousePoint,2);
  public Gate gate=new Block(mousePoint,new HashMap<String,String>(),"a",2,2);
  ArrayList<Gate> gates=new ArrayList<>();
  boolean moved=false;
  boolean released=false;
  boolean pressed=false;
  Point select=null;
  Screen(Display display){
    super();
    this.display=display;
    addMouseMotionListener(this);
    addMouseListener(this);
  }
  public void update(){
    if (moved){
      if (state==0){
	gate.setPos(mousePoint);
	gate.over=new ArrayList<>();
	for (Gate g:gates){
	  for (Point input:gate.inputs){
	    for (Point output:g.outputs){
	      if (output.equals(input)){
		gate.over.add(input);
		break;
	      }
	    }
	  }
	  for (Point output:gate.outputs){
	    for (Point input:g.inputs){
	      if (input.equals(output)){
		gate.over.add(output);
		break;
	      }
	    }
	  }
	}
      }else if (state==1){
	for (Gate gate:gates){
	  if (gate.deleteRect().contains(mousePoint)){
	    gate.rectColor=Color.RED;
	    break;
	  }else{
	    gate.rectColor=Color.BLUE;
	  }
	}
      }else if (state==2){
	for (Gate gate:gates){
	  if (gate.name=="input"){
	    if (gate.deleteRect().contains(mousePoint)){
	      gate.strokeColor=new Color(128,128,128);
	      break;
	    }else{
	      gate.strokeColor=Color.BLACK;
	    }
	  }
	}
      }
      moved=false;
    }
    if (released){
      if (state==0){
	if (gate.name=="line"){
	  if (gate.getBegin()==null){
	    gate.setBegin(new Point(mousePoint.x,mousePoint.y));
	  }else if (gate.getEnd()==null){
	    gate.setEnd(new Point(mousePoint.x,mousePoint.y));
	    if (!gate.getBegin().equals(gate.getEnd()))gates.add(gate);
	    gate=new Line(new Point(gate.X,gate.Y));
	  }
	}else{
	  gate.over=new ArrayList<>();
	  gates.add(gate);
	  switch(gate.name){
	    case "and":
	      gate=new And(new Point(gate.X,gate.Y),gate.terminals);
	      break;
	    case "or":
	      gate=new Or(new Point(gate.X,gate.Y),gate.terminals);
	      break;
	    case "not":
	      gate=new Not(new Point(gate.X,gate.Y));
	      break;
	    case "nand":
	      gate=new Nand(new Point(gate.X,gate.Y),gate.terminals);
	      break;
	    case "nor":
	      gate=new Nor(new Point(gate.X,gate.Y),gate.terminals);
	      break;
	    case "xor":
	      gate=new Xor(new Point(gate.X,gate.Y),gate.terminals);
	      break;
	    case "xnor":
	      gate=new Xnor(new Point(gate.X,gate.Y),gate.terminals);
	      break;
	    case "input":
	      gate=new Input(new Point(gate.X,gate.Y),gate.truthValue);
	      break;
	    case "output":
	      gate=new Output(new Point(gate.X,gate.Y),gate.truthValue);
	      break;
	  }
	}
      }else if (state==1){
	for (Gate gate:gates){
	  if (gate.deleteRect().contains(mousePoint)){
	    gates.remove(gates.indexOf(gate));
	    break;
	  }
	}
	if (select!=null){
	  Rectangle rect=new Rectangle(Math.min(select.x,mousePoint.x),Math.min(select.y,mousePoint.y),Math.abs(select.x-mousePoint.x),Math.abs(select.y-mousePoint.y));
	  ArrayList<Gate> removeGates=new ArrayList<>();
	  for (Gate gate:gates){
	    if (rect.contains(gate.getCenter())){
	      removeGates.add(gate);
	    }
	  }
	  for (Gate gate:removeGates){
	    gates.remove(gates.indexOf(gate));
	  }
	  select=null;
	}
      }else if (state==2){
	for (Gate gate:gates){
	  if (gate.name=="input" && gate.deleteRect().contains(mousePoint)){
	    gate.setTruthValue();
	  }
	}
      }else if (state==3){
	  Rectangle rect=new Rectangle(Math.min(select.x,mousePoint.x),Math.min(select.y,mousePoint.y),Math.abs(select.x-mousePoint.x),Math.abs(select.y-mousePoint.y));
	  ArrayList<Gate> removeGates=new ArrayList<>();
	  ArrayList<Gate> inputs=new ArrayList<>();
	  ArrayList<Gate> outputs=new ArrayList<>();
	  for (Gate gate:gates){
	    if (rect.contains(gate.getCenter())){
	      removeGates.add(gate);
	      if (gate.name=="input"){
		inputs.add(gate);
	      }
	      if (gate.name=="output"){
		outputs.add(gate);
	      }
	    }
	  }
	  if (removeGates.size()>0 && inputs.size()>0 && outputs.size()>0){
	    String name=JOptionPane.showInputDialog(display,"ブロックの名前を定義");
	    HashMap<String,String> truthTable=new HashMap<>();
	    if (!name.equals("")){
	      for (int i=0;i<Math.pow(2,inputs.size());i++){
		int b=i;
		String inputSide="";
		for (int j=inputs.size()-1;j>=0;j--){
		  int c=0;
		  if (b>=Math.pow(2,j)){
		    c=1;
		    b-=Math.pow(2,j);
		  }
		  inputSide+=c;
		  inputs.get(j).setTruthValue(c);
		  run(inputs.get(j));
		}
		String outputSide="";
		for (Gate gate:outputs){
		  outputSide+=((gate.truthValue) ? 1 : 0);
		}
		truthTable.put(inputSide,outputSide);
	      }
	      for (Gate gate:removeGates){
		gates.remove(gates.indexOf(gate));
	      }
	      System.out.println("");
	    }
	  }
	  select=null;
      }
      released=false;
    }
    if (pressed){
      if (gates.size()>0){
	gates.remove(gates.size()-1);
      }
      pressed=false;
    }
    for (Gate gate:gates){
      if (gate.name=="input"){
	run(gate);
      }
    }
    gate.update();
    for (Gate gate:gates){
      gate.update();
    }
  }
  public void run(Gate gate){//実行 再帰関数
    if (gate.name=="output"){
      gate.truthValue=gate.input_terminal.get(0);
    }else{
      for (Gate sub:gates){
	if (gate!=sub){
	  if (sub.name=="line"){
	    if (gate.name=="line"){
	      if (sub.getBegin().equals(gate.getEnd())){
	      }else if (sub.getEnd().equals(gate.getEnd())){
		sub.reverse();
	      }else{
		continue;
	      }
	      sub.input_terminal.set(0,gate.truthValue);
	      sub.operation();
	      run(sub);
	    }else{
	      if (sub.getBegin().equals(gate.outputs.get(0))){
	      }else if (sub.getEnd().equals(gate.outputs.get(0))){
		sub.reverse();
	      }else{
		continue;
	      }
	      sub.input_terminal.set(0,gate.truthValue);
	      sub.operation();
	      run(sub);
	    }
	  }else{
	    for (int i=0;i<sub.terminals;i++){
	      if (gate.name=="line"){
		if (!sub.inputs.get(i).equals(gate.getEnd())){
		  continue;
		}
	      }else{
		if (!sub.inputs.get(i).equals(gate.outputs.get(0))){
		  continue;
		}
	      }
	      sub.input_terminal.set(i,gate.truthValue);
	      sub.operation();
	      run(sub);
	    }
	  }
	}
      }
    }
  }
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    background(g);
    Graphics2D g2=(Graphics2D)g;
    g2.setStroke(new BasicStroke(2));
    g.setColor(mouseColor);
    g.fillOval(mousePoint.x-8,mousePoint.y-8,16,16);
    switch(state){
      case 0:
	g.setColor(Color.BLACK);
	gate.draw(g);
	break;
    }
    for (Gate gate:gates){
      g.setColor(Color.BLACK);
      gate.draw(g);
      if (state==1)gate.deleteRectDraw(g);
    }
    if (select!=null){
      g.setColor(new Color(255,0,0));
      g2.setStroke(new BasicStroke(5));
      Rectangle rect=new Rectangle(Math.min(select.x,mousePoint.x),Math.min(select.y,mousePoint.y),Math.abs(select.x-mousePoint.x),Math.abs(select.y-mousePoint.y));
      g2.draw(rect);
    }
  }
  void background(Graphics g){
    g.setColor(Color.WHITE);
    g.fillRect(0,0,display.width,display.height);
    g.setColor(new Color(128,128,128,128));
    for (int x=0;x<display.width;x+=interval){
      g.drawLine(x,0,x,display.height);
    }
    for (int y=0;y<display.height;y+=interval){
      g.drawLine(0,y,display.width,y);
    }
  }
  @Override
  public void mouseDragged(MouseEvent e){
    mousePoint.x=(int)((e.getPoint().x+interval/2)/interval)*interval;
    mousePoint.y=(int)((e.getPoint().y+interval/2)/interval)*interval;
  }
  @Override
  public void mouseMoved(MouseEvent e){
    mousePoint.x=(int)((e.getPoint().x+interval/2)/interval)*interval;
    mousePoint.y=(int)((e.getPoint().y+interval/2)/interval)*interval;
    moved=true;
  }
  @Override
  public void mouseExited(MouseEvent e){
  }
  @Override
  public void mouseEntered(MouseEvent e){
  }
  @Override
  public void mouseClicked(MouseEvent e){
  }
  @Override
  public void mousePressed(MouseEvent e){
    mouseColor=new Color(128,128,128,255);
    if (state==1 || state==3){
      select=e.getPoint();
    }
  }
  @Override
  public void mouseReleased(MouseEvent e){
    mousePoint.x=(int)((e.getPoint().x+interval/2)/interval)*interval;
    mousePoint.y=(int)((e.getPoint().y+interval/2)/interval)*interval;
    mouseColor=new Color(128,128,128,128);
    released=true;
  }
  public void keyPressed(KeyEvent e){
    switch(e.getKeyCode()){
      case KeyEvent.VK_ENTER:
	released=true;
	select=mousePoint;
	break;
      case KeyEvent.VK_UP:
	mousePoint.y-=interval;
	moved=true;
	break;
      case KeyEvent.VK_DOWN:
	mousePoint.y+=interval;
	moved=true;
	break;
      case KeyEvent.VK_LEFT:
	mousePoint.x-=interval;
	moved=true;
	break;
      case KeyEvent.VK_RIGHT:
	mousePoint.x+=interval;
	moved=true;
	break;
      case KeyEvent.VK_Z:
	pressed=true;
	break;
      case KeyEvent.VK_SPACE:
	state=(state+1)%4;
	break;
      case KeyEvent.VK_0:
	gate=new And(mousePoint,2);
	state=0;
	break;
      case KeyEvent.VK_1:
	gate=new And(mousePoint,3);
	state=0;
	break;
      case KeyEvent.VK_2:
	gate=new Or(mousePoint,2);
	state=0;
	break;
      case KeyEvent.VK_3:
	gate=new Or(mousePoint,3);
	state=0;
	break;
      case KeyEvent.VK_4:
	gate=new Not(mousePoint);
	state=0;
	break;
      case KeyEvent.VK_5:
	gate=new Nand(mousePoint,2);
	state=0;
	break;
      case KeyEvent.VK_6:
	gate=new Nand(mousePoint,3);
	state=0;
	break;
      case KeyEvent.VK_7:
	gate=new Nor(mousePoint,2);
	state=0;
	break;
      case KeyEvent.VK_8:
	gate=new Nor(mousePoint,3);
	state=0;
	break;
      case KeyEvent.VK_9:
	gate=new Xor(mousePoint,2);
	state=0;
	break;
      case KeyEvent.VK_A:
	gate=new Xor(mousePoint,3);
	state=0;
	break;
      case KeyEvent.VK_B:
	gate=new Xnor(mousePoint,2);
	state=0;
	break;
      case KeyEvent.VK_C:
	gate=new Xnor(mousePoint,3);
	state=0;
	break;
      case KeyEvent.VK_D:
	gate=new Input(mousePoint,false);
	state=0;
	break;
      case KeyEvent.VK_E:
	gate=new Output(mousePoint,false);
	state=0;
	break;
      case KeyEvent.VK_F:
	gate=new Line(mousePoint);
	state=0;
	break;
    }
  }
}
