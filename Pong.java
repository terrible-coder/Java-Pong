import javax.swing.JFrame;
public class Pong {
	public static void main(String args[]){
		JFrame f = new JFrame("PONG");
		f.setLayout(null);
		f.setSize(614, 640);
		f.setVisible(true);
		
		f.add(new Pong_Screen(600, 600));
	}
}