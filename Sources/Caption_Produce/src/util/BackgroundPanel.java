package util;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.*;
public class BackgroundPanel extends JPanel{
	Image img;
	public BackgroundPanel(){
		Toolkit kit =Toolkit.getDefaultToolkit();
		 img=kit.createImage(this.getClass().getResource("bg3.jpg"));
	}
	public BackgroundPanel(String url){
		Toolkit kit =Toolkit.getDefaultToolkit();
		 img=kit.createImage(this.getClass().getResource(url));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);
	}
}
