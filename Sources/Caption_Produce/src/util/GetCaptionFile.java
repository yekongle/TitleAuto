package util;
import java.util.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;   
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.json.JSONException;  
import org.json.JSONObject;  
import org.json.JSONArray;  
	/**
	 * @author yekongle
	 * */
 public class GetCaptionFile {
	//private ArrayList<String> arrayList = new ArrayList<String>();
	/**
	 * Get the captions from a json file.
	 * */
	 //private int n = 0;
	public ArrayList<String> getJsonFile(File file){
		 ArrayList<String> arrayList = new ArrayList<String>();
		 try {
 
		//	 InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8"); 
			
			 //InputStreamReader isr = new InputStreamReader(new  BOMInputStream(file), "UTF-8"); 
			//去掉Windows为utf-8格式文件添上的BOM头
			 UnicodeReader ur = new UnicodeReader(new FileInputStream(file),"UTF-8");
			 BufferedReader br = new BufferedReader(ur);
			
			String line=null;String str = "";
		 
				while((line=br.readLine())!=null){
			 		str+=line;
				}
				br.close();
				JSONObject jsonData=new JSONObject(str);
			
			//	System.out.println(str);   
			 /*System.out.println(str);   
				buffer.setLength(0);
				Iterator<String>  iter=jsonData.keys();	 
				while(iter.hasNext()){
					System.out.println(iter.next());
 					arrayList.add(jsonData.getString(iter.next()));
				}*/	
				for(int i = 0; i<jsonData.length();i++){
					/*arrayList.add(jsonData.get("录音-"+(i+1)+".wav").toString());*/
					arrayList.add(jsonData.get(String.valueOf(i)).toString());
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				//e.printStackTrace();	 
				 OperateUtil.getOptionPane();
			}
		  
		 return arrayList;
	}
	
	/**
	 * Get a caption template from a prtl file.
	 * */
	public Element  getCaptionTemplate(File file){
		 
	/*		FileInputStream fInputStream = new FileInputStream(new File("G:/字幕模板.prtl"));
			InputStreamReader inputStreamReader = new InputStreamReader(fInputStream, "utf-16");  
			BufferedReader br = new BufferedReader(inputStreamReader); 
			//BufferedReader br = new BufferedReader(new FileReader(new File("G:/字幕模板.prtl")));
			String str =""; StringBuffer buffer=new StringBuffer("");
			while((str=br.readLine())!=null){
 			System.out.println(str);
				buffer.append(str);
			}
			br.close();
			System.out.println(buffer.toString());
			buffer.setLength(0);*/
			 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			 DocumentBuilder builder;
			 Element  root = null;
			try {
				builder = factory.newDocumentBuilder();
				Document doc = builder.parse(file);
				  root = doc.getDocumentElement();
			} catch (ParserConfigurationException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			 //File file = new File("G:/字幕模板.prtl");
			catch (SAXException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		return root;
	}
	
 
	
	/**
	 * Generate caption files to a directory.
	 * */
	public void generateSub_Titles(File templateFile,File captionFile,String saveDir){
			try{
				ArrayList<String> list = this.getJsonFile(captionFile);
				Element root = this.getCaptionTemplate(templateFile);
				Element textNode =(Element)root.getElementsByTagName("TRString").item(0);
				Element countNode =(Element)root.getElementsByTagName("CharacterAttributes").item(0);        
				
				Transformer t =TransformerFactory.newInstance().newTransformer();	
				t.setOutputProperty(OutputKeys.ENCODING,"UTF-16");
				for(int i = 0;i<list.size();i++){
					 
					String str = list.get(i); 
				//	byte[] decodeStr= str.getBytes("gb2312") ; //解码  
					//String encodeStr = new String(decodeStr,"UTF-16");
					countNode.setAttribute("RunCount",String.valueOf((str.length()+1)));
					textNode.setTextContent(str);
					DOMSource source = new DOMSource(root);
					// File subFile=  new File("G:/workspace/字幕1.prtl");
					String fileName = (saveDir+"/Titles/"+"字幕-"+(i+1)+".prtl").replace("\\", "/");
					File subFile=  new File(fileName);
					File parentFile = subFile.getParentFile();
					if(!parentFile.exists()){
						parentFile.mkdirs();
					}
				     StreamResult result = new StreamResult(subFile);
				     t.transform(source, result);
				    OperateUtil.setValue((i+1)*100/list.size());
				}
			
			}catch(Exception e){
						//e.printStackTrace();	 
					OperateUtil.getOptionPane();
				}
	}
	
	
	/**
	 * Transfrom the doc.
	 * */
	public void transform(){
		
	}
	
	/** 
	 * 判断文件的编码格式 
	 * @param fileName :file 
	 * @return 文件编码格式 
	 * @throws Exception 
	 */  
	public static String codeString(String fileName) throws Exception{  
	    BufferedInputStream bin = new BufferedInputStream(  
	    new FileInputStream(fileName));  
	    int p = (bin.read() << 8) + bin.read();  
	    String code = null;  
	      
	    switch (p) {  
	        case 0xefbb:  
	            code = "UTF-8";  
	            break;  
	        case 0xfffe:  
	            code = "Unicode";  
	            break;  
	        case 0xfeff:  
	            code = "UTF-16BE";  
	            break;  
	        default:  
	            code = "GBK";  
	    }        
	    return code;  
	}  
/* public static void main(String[] args){
 
	} */
}
