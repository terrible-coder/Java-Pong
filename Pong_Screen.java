import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Pong_Screen extends JPanel{
	static final long serialVersionUID = JPanel.FRAMEBITS;
	
	Ball b;
	Player p;
	PlayerAI pai;
	boolean gameOn;
	Pong_Screen(int width, int height){
		super();
		setSize(width, height);
		setLayout(null);
		setVisible(true);
		setFocusable(true);
		setPreferredSize(new Dimension(width, height));
		
		b = new Ball(width/2.0, height/2.0);
		b.setContainer(this);
		p = new Player(0.9*width, height/2.0);
		p.setContainer(this);
		pai = new PlayerAI(0.1*width, height/2.0);
		pai.setContainer(this);
		gameOn = false;

		KeyListener kListener = new KeyListener(){
			public void keyPressed(KeyEvent e){
				int key = e.getKeyCode();
				switch(key){
				case KeyEvent.VK_UP:
					p.vel.y = -1;
					break;
				case KeyEvent.VK_DOWN:
					p.vel.y = 1;
					break;
				default:
					gameOn = !gameOn;
				}
			}
			public void keyTyped(KeyEvent e){
				
			}
			public void keyReleased(KeyEvent e){
				p.vel.y = 0;
			}
		};
		addKeyListener(kListener);
		
	}
	
	void resetGame(){
		p.pos.set(0.9*getWidth(), getHeight()/2.0);
		pai.pos.set(0.1*getWidth(), getHeight()/2.0);
		b.pos.set(getWidth()/2.0, getHeight()/2.0);
		gameOn = false;
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.fillRect(0, 0, getWidth(), getHeight());
		if(gameOn){
			b.update();
			p.update(b);
			pai.update(b);
		}
		b.paint(g);
		p.paint(g);
		pai.paint(g);
		repaint();
	}
}
