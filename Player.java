import java.awt.*;
import java.awt.geom.*;
public class Player {
	static Dimension PLAYER_BOUNDS = new Dimension(10, 80);
	PVector pos;
	PVector vel;
	Dimension screen;
	int score;
	
	Player(double x, double y){
		pos = new PVector(x, y);
		vel = new PVector();
		score = 0;
	}
	
	void setContainer(Container c){
		screen = c.getPreferredSize();
	}
	
	void update(Ball b){
		pos.add(vel);
		checkInScreen();
		checkCollision(b);
	}
	
	private void checkCollision(Ball b){
		double 	top = pos.y - PLAYER_BOUNDS.getHeight()/2,
				bottom = pos.y + PLAYER_BOUNDS.getHeight()/2,
				left = pos.x - PLAYER_BOUNDS.getWidth()/2,
				right = pos.x + PLAYER_BOUNDS.getWidth()/2;
		if(b.pos.y+Ball.BALL_SIZE/2<top || b.pos.y-Ball.BALL_SIZE/2>bottom){
			return;
		}
		double d = pos.x - b.pos.x;
		if(Math.abs(d) < (Ball.BALL_SIZE + PLAYER_BOUNDS.getWidth())/2){
			if(d < 0){
				b.pos.x = right + Ball.BALL_SIZE/2;
			}else{
				b.pos.x = left - Ball.BALL_SIZE/2;
			}
			b.vel.x *= -1;
			double ydiff = b.pos.y - pos.y,
					diff_percent = ydiff/PLAYER_BOUNDS.getHeight();
			b.vel.y = diff_percent;
		}
	}
	
	private void checkInScreen(){
		double 	top = pos.y - PLAYER_BOUNDS.getHeight()/2,
				bottom = pos.y + PLAYER_BOUNDS.getHeight()/2,
				left = pos.x - PLAYER_BOUNDS.getWidth()/2,
				right = pos.x + PLAYER_BOUNDS.getWidth()/2;
		if(top<=0){
			pos.y = PLAYER_BOUNDS.getHeight()/2;
		}else if(bottom>=screen.getHeight()){
			pos.y = screen.getHeight() - PLAYER_BOUNDS.getHeight()/2;
		}else if(left<=0){
			pos.x = PLAYER_BOUNDS.getWidth()/2;
		}else if(right>=screen.getWidth()){
			pos.x = screen.getWidth() - PLAYER_BOUNDS.getHeight()/2;
		}
	}
	
	void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setColor(Color.WHITE);
		g2d.fill(new Rectangle2D.Double(
								pos.x - PLAYER_BOUNDS.getWidth()/2,
								pos.y - PLAYER_BOUNDS.getHeight()/2,
								PLAYER_BOUNDS.getWidth(),
								PLAYER_BOUNDS.getHeight()
								));
	}
}
