package com.tourism.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiexingbao
 * 文件夹拷贝(文件内含有文件和文件夹)
 *
 */
public class CopyDirectory {
	
	private static String regex = ".+\\.[j][a][v][a]";//正则表达式 用来判读是否是图片格式的文件
	
	private static Logger logger = LoggerFactory.getLogger(CopyDirectory.class);
	
	public static void main(String[] args) {
		String src = "C:\\mysoftware\\test\\record\\91440400086804199M";
		String temp = "C:\\mysoftware\\apache-tomcat-7.0.70-hq-fx\\webapps\\temp\\91440400086804199M";
		
		copyPhoto(src,temp);
		System.out.println("文件拷贝完成!");
	}

	// 将一个文件的东西全部复制到另一个文件夹
	public static boolean copyPhoto(String savePath, String copyToPath) {
		logger.info("源文件路径："+savePath);
		logger.info("目标路径："+copyToPath);
		try {
			while (true) {
				System.out.println("复制 " + savePath + " 目录开始");
				long t1 = System.currentTimeMillis();
				if (copyFolder(savePath, copyToPath)) {
					long t2 = System.currentTimeMillis();
					System.out.println("复制目录结束，用时：" + (t2 - t1) + "ms");
					break;
				} else {
					return false;
				}
			}
			// }
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}

	public static boolean copyFolder(String savePath, String copyToPath) {

		try {
			File mFile = new File(copyToPath);
			if (!mFile.exists()) {
				(new File(copyToPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			}
			File a = new File(savePath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (savePath.endsWith(File.separator)) {
					temp = new File(savePath + file[i]);
				} else {
					temp = new File(savePath + File.separator + file[i]);
				}

				if (temp.isFile()) {
					String fileName = copyToPath + "/" + (temp.getName()).toString();
					File testFile = new File(fileName);
					if (!testFile.exists()) {
						FileInputStream input = new FileInputStream(temp);
						FileOutputStream output = new FileOutputStream(fileName);
						byte[] b = new byte[1024 * 5];
						int len;
						while ((len = input.read(b)) != -1) {
							output.write(b, 0, len);
						}
						output.flush();
						output.close();
						input.close();
					}
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(savePath + "/" + file[i], copyToPath + "/" + file[i]);
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();
			return false;
		}
	}

	private String getTimeString(String time) {
		if (time.length() < 2) {
			return "0" + time;
		} else {
			return time;
		}
	}
	
	
	  /**
     * 删除空目录
     * @param dir 将要删除的目录路径
     */
    private static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

}