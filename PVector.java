public class PVector {
	double x, y;
	PVector(){
		this(0, 0);
	}
	PVector(double x){
		this(x, 0);
	}
	PVector(double x, double y){
		this.x = x;
		this.y = y;
	}
	void set(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	double mag(){
		return Math.sqrt(x*x + y*y);
	}
	double angle(){
		return Math.atan2(y, x);
	}
	
	void add(PVector b){
		this.x+=b.x;
		this.y+=b.y;
	}
	void mult(double scalar){
		this.x*=scalar;
		this.y*=scalar;
	}
	
	static PVector random2D(){
		double angle = Math.random()*Math.PI*2;
		return new PVector(Math.cos(angle), Math.sin(angle));
	}
}
