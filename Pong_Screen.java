import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Pong_Screen extends JPanel implements KeyListener{
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
		
		addKeyListener(this);
		
	}
	
	void resetGame(){
		p.pos.set(0.9*getWidth(), getHeight()/2.0);
		pai.pos.set(0.1*getWidth(), getHeight()/2.0);
		b.pos.set(getWidth()/2.0, getHeight()/2.0);
		gameOn = false;
	}
	
	void paintScores(Graphics g){
		Graphics2D g2d = (Graphics2D)g.create();
		String scores = pai.score + " - " + p.score;
		Font font = new Font(Font.SERIF, Font.PLAIN, 30);
		Rectangle2D rect = font.getStringBounds(scores, g2d.getFontRenderContext());
		double sx = (getWidth() - rect.getWidth())/2;
		g2d.setColor(Color.WHITE);
		g2d.setFont(font);
		g2d.drawString(scores, (float)sx, (float)rect.getHeight());
		g2d.dispose();
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.fillRect(0, 0, getWidth(), getHeight());
		if(gameOn){
			b.update();
			if(b.pos.x<0){
				p.score++;
				b.vel = new PVector(-1, 0);
				resetGame();
			}else if(b.pos.x>getWidth()){
				pai.score++;
				b.vel = new PVector(1, 0);
				resetGame();
			}
			p.update(b);
			pai.update(b);
		}
		b.paint(g);
		p.paint(g);
		pai.paint(g);
		repaint();
	}
	
	@Override
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
	
	@Override
	public void keyReleased(KeyEvent e){
		p.vel.y = 0;
	}
	
	@Override
	public void keyTyped(KeyEvent e){
		
	}
}
