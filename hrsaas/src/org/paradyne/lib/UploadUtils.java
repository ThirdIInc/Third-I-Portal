/**
 * 
 */
package org.paradyne.lib;

/**
 * @author lakkichand
 * @date 20/05/2008
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.struts2.interceptor.FileUploadInterceptor;

public class UploadUtils {
	public static final Boolean DEBUG = true;

	public static void debugPrint(String s) {
		if (DEBUG)
			System.out.println(s);
	}

		public static void makeDir(File f1) {
		Boolean success = f1.mkdir();
		System.out.println("\t" + f1.getName());
		if (!success) {
			System.exit(0);
		}
	}
	public static String getProjectName(String projectURL) {
		String items[] = projectURL.split("\\.");
		return items[0];
	}

		public static String getExtention(String projectURL) {
		int i= projectURL.lastIndexOf(".");
		String ext=projectURL.substring(i+1,projectURL.length());
		System.out.println("ext"+ext);
		return ext;
		
		
	}

	public static String getPackageName(String projectURL) {
		String items[] = projectURL.split("\\.");
		StringBuffer s = new StringBuffer("");
		for (int i = items.length - 1; i >= 0; i--) {
			s.append(items[i]);
			if (i > 0)
				s.append(".");
		}
		return s.toString();
	}

	public static String getFileContent(String filename) {
		File f = new File(filename);
		RandomAccessFile raf;
		byte[] fileContent = null;
		try {
			raf = new RandomAccessFile(f, "r");
			fileContent = new byte[(int) raf.length()];
			raf.read(fileContent);
			raf.close();
		} catch (Exception e) {
			System.out.println("Error in RandomAccessFile con "
					+ f.getAbsolutePath());
			System.exit(0);
		}
			String archivo = new String(fileContent);
		return archivo;
	}

	public static void createFile(String filename, String text) {
	
		File f = new File(filename);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			System.out.println("Error in  archive: "
					+ f.getAbsolutePath());
			System.exit(0);
		}
	}

	public static void copyFile(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(src);
			out = new FileOutputStream(dst);
			// Transfer bytes from in to out      
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			System.out.println("Error in path defined for upload in properties : "
					+ src.getAbsolutePath() + " a " + dst.getAbsolutePath());
			//System.exit(0);
		}
	}
	public static void uploadFiles(List<File> srcFiles ,List<String>file_names,String destPath) throws FileSizeLimitExceededException{
		File fullFile = null;
		long l=222297152;
		new FileUploadInterceptor().setMaximumSize(l);
		
		for (Iterator iterator = srcFiles.iterator(),iterator1=file_names.iterator(); iterator.hasNext();) {
			File name = (File) iterator.next();
			iterator1.hasNext();
			String file_name=(String) iterator1.next();
			System.out.println("name.getName()"+name.getName()+name.length());
					try {
						System.out.println("*** " + name+ "\t" + name.length());
						boolean yes = name.canWrite();
						System.out.println("yesyesyesyesyesyesyesyesyes"+ yes+file_name);
						String imageName = name.getName();
						// imageName = imageName.();2097152
						File f = new File(destPath);
						f.mkdirs();
						File savedFile = new File(destPath + "/", file_name);
						fullFile = uniqueFile(savedFile,destPath + "/");
						UploadUtils.copyFile(name, fullFile);
						String ext=UploadUtils.getExtention(destPath+"\\"+file_name);
						System.out.println("*********3a" + savedFile+savedFile.length());
						System.out.println("file uploaded successfully"+ext);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
		}
	}
	
	public static File uniqueFile(File f, String pa) {

		if (f.exists()) {
			String path = f.getParentFile().getPath();
			System.out
					.println("**********Path in upload.jsp=++++++++++++++++++++++++++++"
							+ path);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-HH:mm:ss");
			String sysDate = formater.format(date);
			String file =sysDate+ f.getName();
			return uniqueFile(new File(path + "\\" + file), pa);
		}
		return f;
	}
	
	public static String uploadFiles(BeanBase bean, String destPath,
			String type) {
		
		// TODO Auto-generated method stub
		String result="valid";
		String[] IMG_TYPES={"jpg","png","gif","jpeg"};
		String[] DOC_TYPES={"doc","txt","xls","rtf"};
		String[] ALL_TYPES={"jpg","png","gif","jpeg","doc","txt","xls","rtf"};
		
		File fullFile = null;
		List<File> list_files=bean.getUpload();
		List<String> file_names=bean.getUploadFileName();
		List<String> content_types=bean.getUploadContentType();
		for (Iterator iterator = list_files.iterator(),iterator1=file_names.iterator(); iterator.hasNext();) {
			File name = (File) iterator.next();
			iterator1.hasNext();
			String file_name=(String) iterator1.next();
			System.out.println("name.getName()"+name.getName()+name.length());
					try {
						System.out.println("*** " + name+ "\t" + name.length());
						boolean yes = name.canWrite();
						System.out.println("yesyesyesyesyesyesyesyesyes"+ yes+file_name);
						String imageName = name.getName();
						// imageName = imageName.();
						File f = new File(destPath);
						f.mkdirs();
						File savedFile = new File(destPath + "/", file_name);
						String ext=UploadUtils.getExtention(destPath+"\\"+file_name);
						System.out.println("Extention------------"+ext);
						if(type.equals("IMG")){
							System.out.println("if image");
						for (int i = 0; i < IMG_TYPES.length; i++) {
							System.out.println("count ...."+i);
							if(ext.equals(IMG_TYPES[i])){
									System.out.println("ext.equals");
									result="valid";
									break;
								}
								else result="invalid";
								
									
							}
						}
						else if(type.equals("DOC")){	
							System.out.println("if docs");
						for (int i = 0; i < DOC_TYPES.length; i++) {
							System.out.println("count ...."+i);
								if(ext.equals(DOC_TYPES[i])){
									System.out.println("ext.equals");
									result="valid";
									break;
								}
								else result="invalid";
									
							}
						}
						else if(type.equals("ALL")){	
							System.out.println("if docs");
						for (int i = 0; i < ALL_TYPES.length; i++) {
							System.out.println("count ...."+i);
								if(ext.equals(ALL_TYPES[i])){
									System.out.println("ext.equals");
									result="valid";
									break;
								}
								else result="invalid";
									
							}
						}
						else{
						
						fullFile = uniqueFile(savedFile,destPath + "/");
						UploadUtils.copyFile(name, fullFile);
						System.out.println("*********3a" + savedFile+savedFile.length());
					
						}
					
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						
					}
		}
		return result;
		
		
	}
}

