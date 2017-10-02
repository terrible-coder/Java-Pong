
public class PlayerAI extends Player{
	PlayerAI(double x, double y){
		super(x, y);
	}
	
	@Override
	void update(Ball b){
		double ydiff_percent = (b.pos.y - pos.y)/
				(screen.getHeight()-Ball.BALL_SIZE/2-PLAYER_BOUNDS.getHeight()/2);
		vel.y = 5*ydiff_percent;
		super.update(b);
	}
}
