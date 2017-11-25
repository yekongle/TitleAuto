package util;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
public class OperateUtil extends JFrame implements ActionListener {
	private JLabel label1,label2,label3,label4;
	private JButton button1,button2,button3,button4,button5;
	private JComboBox jcomboBox;
	private static JProgressBar progressBar;
	private JTextField textField1,textField2,textField3;
	private String[] listData = {"Premiere CC","Premiere CS6","Premiere CS5","Premiere CS4"};
	private Color color = new Color(245,245,245);
	private Font font=new Font("微软雅黑",Font.PLAIN,18);
	private GetCaptionFile getCaptionFile = new GetCaptionFile();
	private String compareStr = "";
	public OperateUtil(){
		 
		try { // 使用Windows的界面风格
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			   initWedget();
			   initLayout();
			   init();
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
	}
	

	public void initWedget(){
		label1= new JLabel("软件版本");
		label2 = new JLabel("字幕模板");
		label3 = new JLabel("字幕文件");
		label4 = new JLabel("保存目录");
		button1 = new JButton("关于");
		button2 = new JButton("浏览");
		button3 = new JButton("浏览");
		button4 = new JButton("浏览");	
		button5 = new JButton("生成字幕");
		jcomboBox = new JComboBox(listData);
		jcomboBox.setEnabled(false);
		textField1 = new JTextField();
		textField2 = new JTextField();
		textField3 = new JTextField();
		progressBar = new JProgressBar();
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		jcomboBox.addActionListener(this);
	}
		
	public void initLayout(){
			Container container = this.getContentPane();
			container.setLayout(null);
			
	        BackgroundPanel backPanel = new BackgroundPanel();
	        backPanel.setBounds(0, 0, 650, 80);
 
	        label1.setBounds(30, 100, 80, 30);
	        label1.setFont(font);     
	        jcomboBox.setBounds(115, 100, 380, 30);
	        button1.setBounds(510, 100, 100, 30);
	        button1.setFont(font);
	        
	        label2.setBounds(30, 160, 80, 30);
	        label2.setFont(font);
	        textField1.setBounds(115, 160, 380, 30);
	        button2.setBounds(510, 160, 100, 30);
	        button2.setFont(font);
	        button2.setToolTipText("选择一个字幕模板");
	        
	        label3.setBounds(30, 220, 80, 30);
	        label3.setFont(font);
	        textField2.setBounds(115, 220, 380, 30);
	        button3.setBounds(510, 220, 100, 30);
	        button3.setFont(font);
	        button3.setToolTipText("选择一个字幕文件");
	        
	        label4.setBounds(30, 280, 80, 30);
	        label4.setFont(font);
	        textField3.setBounds(115, 280, 380, 30);
	        button4.setBounds(510, 280, 100, 30);
	        button4.setFont(font);
	        button4.setToolTipText("选择生成字幕目录");
	        
	        button5.setBounds(250, 330, 120, 30);
	        button5.setFont(font);
	        
	        progressBar.setBounds(30,380	, 580, 30);
	        progressBar.setMinimum(0);
	        progressBar.setMaximum(100);
	        progressBar.setFont(font);
	        progressBar.setStringPainted(true);
	        
	        container.setBackground(color);
	        container.add(backPanel);
	        container.add(label1);
	        container.add(jcomboBox);
	        container.add(button1);
	         
	        container.add(label2);
	        container.add(textField1);
	        container.add(button2);
	         
	        container.add(label3);
	        container.add(textField2);
	        container.add(button3);
	         
	        container.add(label4);
	        container.add(textField3);
	        container.add(button4);
	         
	        container.add(button5);
	        container.add(progressBar);
	}
	public void init(){
		Toolkit kit =Toolkit.getDefaultToolkit();
		Image image=kit.createImage(this.getClass().getResource("yekong.jpg"));
		this.setTitle("字幕生成工具V1.1 for Premiere CX");
		this.setIconImage(image);
		this.setSize(650,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFont(font);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		Object object = e.getSource();
		if(object == button1){
			 new AboutDialog();
		}else if(object == button2){
			if(progressBar.getValue() == 100){
				progressBar.setValue(0);
			}
			try{
			File file = this.getJFileChooser(".prtl");
			if(file != null){
				textField1.setText(file.getAbsolutePath());
			}
			}catch(Exception e1){
				//e1.printStackTrace();
			}
		}else if(object == button3){
			try{
				File file = this.getJFileChooser("");
				if(file != null){
					textField2.setText(file.getAbsolutePath());
				}
				}catch(Exception e1){
					//e1.printStackTrace();
				}
			
		}else if(object == button4){
			try{
				File file = this.getDirecChooser();
				if(file != null){
					textField3.setText(file.getAbsolutePath());
				}
				}catch(Exception e1){
					//e1.printStackTrace();
				}
		}else if(object == button5){
			if(progressBar.getValue() == 100){
				progressBar.setValue(0);
			}
			String str1 = textField1.getText().trim();
			String str2 = textField2.getText().trim();
			String str3 = textField3.getText();
			if(str1.equals(compareStr) || str2.equals(compareStr) || str3.equals(compareStr)){
				JOptionPane.showMessageDialog(this, "文本框不能为空!", "Attention", JOptionPane.ERROR_MESSAGE);
			}else{
				//ArrayList<String> arrayList = getCaptionFile.getJsonFile(new File(str1));
				try{
					getCaptionFile.generateSub_Titles(new File(str1), new File(str2), str3);
				}catch(Exception e2){
					//JOptionPane.showMessageDialog(this, "请检查输入路径或文本格式是否有误!", "Alert", JOptionPane.ERROR_MESSAGE);
					System.out.println(e2.getStackTrace());
				}
			}
		}	
	}
	
	public File getJFileChooser(String pattern){
		JFileChooser fileDialog = new JFileChooser();
	    fileDialog.setAcceptAllFileFilterUsed(false);
		FileFilterUtil filter = new FileFilterUtil(pattern);
		fileDialog.setFileFilter(filter);
		fileDialog.showOpenDialog(this);
        File file=fileDialog.getSelectedFile();
        return file;       
	}
	
	public File getDirecChooser(){
		JFileChooser fileDialog = new JFileChooser();
		fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileDialog.showOpenDialog(null);
		File file=fileDialog.getSelectedFile();
		return file;
	}
	public static void setValue(int value){
			progressBar.setValue(value);
	}
	public JProgressBar getprogressBar(){
		return progressBar;
	}
	public static void getOptionPane(){
		JOptionPane.showMessageDialog(null, "请检查输入路径或文本格式是否有误!", "Alert", JOptionPane.ERROR_MESSAGE);
	}
	
	public static  void main(String[] args){
		new OperateUtil();
	}

}
