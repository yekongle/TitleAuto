package util;
import java.io.File;
import javax.swing.filechooser.FileFilter;
/**
 * @author yekongle
 * */
public class FileFilterUtil extends FileFilter{
	private String pattern = null;
	public FileFilterUtil(){}
	public FileFilterUtil(String pattern){
		this.pattern = pattern;
	}
	@Override
	public boolean accept(File file) {
		// TODO 自动生成的方法存根
		if (file.isDirectory())return true;
		else if(pattern.equals(".prtl")){
			return file.getName().endsWith(".prtl") ;
		}else{
		    return file.getName().endsWith(".txt") || file.getName().endsWith(".json");
		}

	}

	@Override
	public String getDescription() {
		// TODO 自动生成的方法存根
		if(pattern.equals(".prtl")){
			return "Premiere CC字幕文件(*.prtl)";
		} else{
			return "json,txt文件(*.json;*.txt)";
		}
		
	}

}
