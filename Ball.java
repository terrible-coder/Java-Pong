import java.awt.*;
import java.awt.geom.*;
public class Ball {
	static final double BALL_SIZE = 10;
	
	private Dimension screen;
	PVector pos;
	PVector vel;
	Ball(double x, double y){
		this.pos = new PVector(x, y);
		this.vel = new PVector(1, 0);//PVector.random2D();
	}
	
	void setContainer(Container c){
		screen = c.getPreferredSize();
	}
	
	void update(){
		pos.add(vel);
		double 	top = pos.y - BALL_SIZE/2,
				bottom = pos.y + BALL_SIZE/2,
				left = pos.x - BALL_SIZE/2,
				right = pos.x + BALL_SIZE/2;
		if(top<0){
			pos.y = BALL_SIZE/2;
			vel.y *= -1;
		}else if(bottom>screen.getHeight()){
			pos.y = screen.getHeight() - BALL_SIZE/2;
			vel.y *= -1;
		}
		if(left<0){
			pos.x = BALL_SIZE/2;
			vel.x *= -1;
		}else if(right>screen.getWidth()){
			pos.x = screen.getWidth() - BALL_SIZE/2;
			vel.x *= -1;
		}
	}
	
	void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setColor(Color.WHITE);
		g2d.fill(new Ellipse2D.Double(pos.x - BALL_SIZE/2, pos.y - BALL_SIZE/2,
										BALL_SIZE, BALL_SIZE));
	}
}
