package util;
import java.awt.*;
/**
 * @author yekongle
 * */
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
public class AboutDialog extends JDialog{
	private Color normalColor = new Color(0,191,255);
	private Color mouseoverColor = new Color(30,144,255);
	private Font font=new Font("微软雅黑",Font.PLAIN,16);
	private JLabel label1,label2,label3,label4;
	private static Desktop deskTop;
	private String uri = "www.yekongle.com/page/message";
	public AboutDialog(){
		initDeskTop();
		initWedget();
		initLayout();
		initFrame();
	}
	
	public void  initFrame(){
		this.setTitle("About");
		this.setSize(450, 300);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo( null);
		this.setVisible(true);
	}
	
	public void initWedget(){
		label1 = new JLabel("<html><body><center>目前仅支持Premiere CC字幕模板和JSON格式文本<br>更多版本,敬请关注.</center></body></html>");
		label1.setFont(font);
		label1.setBounds(30, 30,400, 60);
		
		label2 = new JLabel("发现问题?戳这里");
		label2.setFont(font);
		label2.setForeground(normalColor);
		label2.setBounds(150,100,200,30);
		
		label3 = new JLabel("Yekongle©2017");
		label3.setFont(font);
		label3.setBounds(150,220,200,30);
		
		label2.addMouseListener(new labelMouseAdapter());
		
	}
	
	public void initLayout(){
		this.setLayout(null);
		this.add(label1);
		this.add(label2);
		this.add(label3);
	}
	
	public void initDeskTop(){
		if (Desktop.isDesktopSupported()) {  
				deskTop = Desktop.getDesktop();
            }
	}
	
	public void bindURI(String str){
		try {
			deskTop.browse(new URI(str));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
 
/*	public static void main(String  []args){
		new AboutDialog();
	}*/

 
	class labelMouseAdapter extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO 自动生成的方法存根
			bindURI(uri);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO 自动生成的方法存根
			label2.setForeground(mouseoverColor);
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO 自动生成的方法存根
			label2.setForeground(normalColor);
		}
	 
	}
	
}
