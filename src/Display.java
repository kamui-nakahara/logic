import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

class Display extends JFrame implements KeyListener{
  public int width;
  public int height;
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
    menubar.add(menu2);
    JMenu menu3=new JMenu("アイテム");
    JMenuItem item3_1=new JMenuItem("2入力ANDゲート");
    item3_1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="and2";
      }
    });
    menu3.add(item3_1);
    JMenuItem item3_2=new JMenuItem("3入力ANDゲート");
    item3_2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="and3";
      }
    });
    menu3.add(item3_2);
    JMenuItem item3_3=new JMenuItem("2入力ORゲート");
    item3_3.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="or2";
      }
    });
    menu3.add(item3_3);
    JMenuItem item3_4=new JMenuItem("3入力ORゲート");
    item3_4.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="or3";
      }
    });
    menu3.add(item3_4);
    JMenuItem item3_5=new JMenuItem("NOTゲート");
    item3_5.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="not";
      }
    });
    menu3.add(item3_5);
    JMenuItem item3_6=new JMenuItem("2入力NANDゲート");
    item3_6.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="nand2";
      }
    });
    menu3.add(item3_6);
    JMenuItem item3_7=new JMenuItem("3入力NANDゲート");
    item3_7.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="nand3";
      }
    });
    menu3.add(item3_7);
    JMenuItem item3_8=new JMenuItem("2入力NORゲート");
    item3_8.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="nor2";
      }
    });
    menu3.add(item3_8);
    JMenuItem item3_9=new JMenuItem("3入力NORゲート");
    item3_9.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="nor3";
      }
    });
    menu3.add(item3_9);
    JMenuItem item3_10=new JMenuItem("2入力XORゲート");
    item3_10.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="xor2";
      }
    });
    menu3.add(item3_10);
    JMenuItem item3_11=new JMenuItem("3入力XORゲート");
    item3_11.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="xor3";
      }
    });
    menu3.add(item3_11);
    JMenuItem item3_12=new JMenuItem("2入力XNORゲート");
    item3_12.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="xnor2";
      }
    });
    menu3.add(item3_12);
    JMenuItem item3_13=new JMenuItem("3入力XNORゲート");
    item3_13.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="xnor3";
      }
    });
    menu3.add(item3_13);
    JMenuItem item3_14=new JMenuItem("入力ノード");
    item3_14.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="input";
      }
    });
    menu3.add(item3_14);
    JMenuItem item3_15=new JMenuItem("出力ノード");
    item3_15.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="output";
      }
    });
    menu3.add(item3_15);
    JMenuItem item3_16=new JMenuItem("結線");
    item3_16.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
	Gate.having="line";
      }
    });
    menu3.add(item3_16);
    menubar.add(menu3);
    setJMenuBar(menubar);

    Screen screen=new Screen(this);
    add(screen);

    addKeyListener(this);
  }
  @Override
  public void keyTyped(KeyEvent e){
  }
  @Override
  public void keyPressed(KeyEvent e){
    switch(e.getKeyCode()){
      case KeyEvent.VK_ESCAPE:
	dispose();
	System.exit(0);
	break;
    }
  }
  @Override
  public void keyReleased(KeyEvent e){
  }
}

class Screen extends JPanel implements MouseMotionListener,MouseListener{
  Point mousePoint=new Point(0,0);
  Display display;
  public static int interval=10;
  int state=0; //0:ゲート設置 1:削除
  Color mouseColor=new Color(128,128,128,128);
  Screen(Display display){
    super();
    this.display=display;
    addMouseMotionListener(this);
    addMouseListener(this);
  }
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    background(g);
    g.setColor(mouseColor);
    g.fillOval(mousePoint.x-8,mousePoint.y-8,16,16);
    switch(state){
      case 0:
	g.setColor(Color.BLACK);
	switch(Gate.having){
	  case "and2":
	    Gate.and2(g,mousePoint.x,mousePoint.y);
	    break;
	  case "and3":
	    Gate.and3(g,mousePoint.x,mousePoint.y);
	    break;
	  case "or2":
	    Gate.or2(g,mousePoint.x,mousePoint.y);
	    break;
	  case "or3":
	    Gate.or3(g,mousePoint.x,mousePoint.y);
	    break;
	  case "not":
	    Gate.not(g,mousePoint.x,mousePoint.y);
	    break;
	  case "nand2":
	    Gate.nand2(g,mousePoint.x,mousePoint.y);
	    break;
	  case "nand3":
	    Gate.nand3(g,mousePoint.x,mousePoint.y);
	    break;
	  case "nor2":
	    Gate.nor2(g,mousePoint.x,mousePoint.y);
	    break;
	  case "nor3":
	    Gate.nor3(g,mousePoint.x,mousePoint.y);
	    break;
	  case "xor2":
	    Gate.xor2(g,mousePoint.x,mousePoint.y);
	    break;
	  case "xor3":
	    Gate.xor3(g,mousePoint.x,mousePoint.y);
	    break;
	  case "xnor2":
	    Gate.xnor2(g,mousePoint.x,mousePoint.y);
	    break;
	  case "xnor3":
	    Gate.xnor3(g,mousePoint.x,mousePoint.y);
	    break;
	  case "input":
	    Gate.input(g,mousePoint.x,mousePoint.y,false);
	    break;
	  case "output":
	    Gate.output(g,mousePoint.x,mousePoint.y,false);
	    break;
	}
	break;
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
  }
  @Override
  public void mouseMoved(MouseEvent e){
    mousePoint.x=(int)((e.getPoint().x+interval/2)/interval)*interval;
    mousePoint.y=(int)((e.getPoint().y+interval/2)/interval)*interval;
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
  }
  @Override
  public void mouseReleased(MouseEvent e){
    mouseColor=new Color(128,128,128,128);
  }
}
