package com.tourism.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;

import org.apache.http.HttpEntity;

/**
 * 입출력과 관련된 프로그램
 * 
 * @author jungs (dnachiu@gmail.com)
 * 
 */
public class IOUtil {

	/**
	 * 모든 메소드는 static한 방법으로 접근하게 하기위해 default constructor를 private으로 막음.
	 */
	private IOUtil() {
		// 사용안함
	}

	/**
	 * InputStream 객체를 읽어서 String으로 리턴함.
	 * 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 * 
	 * @param is
	 *            읽어들일 InputStream객체
	 * @return InputStream으로부터 읽은 데이터의 내용
	 * @throws IOException
	 *             InputStream에 문제가 있거나, Stream으로부터 읽는과정중 문제가 발생하면 IOException을
	 *             던진다.
	 */
	public static String readInputStream(InputStream is) throws IOException {
		byte[] readBytes = readInputStreamAsByteArray(is);
		return new String(readBytes, "UTF-8");
	}

	/**
	 * InputStream으로부터 데이터를 읽어들여 charset이 null이 아니면 인코딩 하여 리턴한다.
	 * 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 * 
	 * @param is
	 *            읽어들일 InputStream 객체
	 * @param charset
	 *            인코딩할 캐릭터 셋(예: euc-kr, utf-8)
	 * @return 입력 스트림의 데이터.
	 * @throws IOException
	 *             읽는 중 오류가 생기면 예외발생
	 */
	public static String readInputStream(InputStream is, String charset)
			throws IOException {
		byte[] readBytes = readInputStreamAsByteArray(is);
		String encodedStr = null;
		try {
			if (charset == null || charset.equalsIgnoreCase("iso-8859-1")) {
				encodedStr = new String(readBytes);
			} else {
				encodedStr = new String(readBytes, charset);
			}
		} catch (UnsupportedEncodingException uee) {
			encodedStr = new String(readBytes);
		}
		return encodedStr;
	}

	/**
	 * InputStream으로부터 데이터를 읽어들여 byte의 배열로 리턴한다.
	 * 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 * 
	 * @param is
	 *            읽어들일 InputStream 객체
	 * @return InputStream으로부터 읽은 byte의 배열
	 * @throws IOException
	 *             읽는 중 오류가 생기면 예외발생
	 */
	public static byte[] readInputStreamAsByteArray(InputStream is)
			throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(1024);

		byte[] buffer = new byte[1024];

		int len;
		while ((len = is.read(buffer)) > 0) {
			outStream.write(buffer, 0, len);
		}

		outStream.close();
		return outStream.toByteArray();
	}

	public static char[] readInputSteamAsCharArray(InputStream inputStream,
			String charSet) throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, charSet);

		CharArrayWriter outStream = new CharArrayWriter(1024);

		char[] buffer = new char[1024];

		int len;
		while ((len = inputStreamReader.read(buffer)) > 0) {
			outStream.write(buffer, 0, len);
		}

		outStream.close();
		return outStream.toCharArray();

	}

	public static String readZipInputStream(InputStream is) throws Exception {
		String requestXML = null;
		InflaterInputStream iis = null;

		try {
			iis = new InflaterInputStream(is);
			requestXML = readInputStream(iis);
		} catch (EOFException eofExp) {
			requestXML = "[ERROR]" + eofExp.toString();
		} catch (ZipException zipExp) {
			requestXML = "[ERROR]" + zipExp.toString();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (Exception ioe) {
			}
		}

		if (requestXML == null)
			requestXML = "[ERROR]The number of bytes that can be read is 0.";

		return requestXML;
	}

	/**
	 * 파일을 읽어 바이트의 배열로 리턴함
	 * 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 * 
	 * @param filePath
	 *            읽어들일 파일의 경로
	 * @return 파일내용의 바이트 배열
	 * @throws IOException
	 *             파일을 읽는도중 문제 발생하면 IOException을 던짐
	 */
	public static byte[] readFileAsByteArray(String filePath)
			throws IOException {
		// RandomAccessFile raf = new RandomAccessFile(filePath, "r");
		// byte [] b = new byte[(int)raf.length()];
		// raf.read(b);
		// raf.close();

		ByteArrayOutputStream outStream = new ByteArrayOutputStream(1024);
		FileInputStream fis = new FileInputStream(filePath);
		byte[] buffer = new byte[1024];

		int len;
		while ((len = fis.read(buffer)) > 0) {
			outStream.write(buffer, 0, len);
		}

		fis.close();
		outStream.close();

		return outStream.toByteArray();
	}

	/**
	 * Reader 객체를 읽어서 String으로 리턴함.
	 * 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 * 
	 * @param reader
	 *            읽어들일 Reader객체
	 * @return Reader로부터 읽은 데이터의 내용
	 * @throws IOException
	 *             Reader 에 문제가 있거나, Reader로부터 읽는과정중 문제가 발생하면 IOException을 던진다.
	 */
	public static String readInputStream(Reader reader) throws IOException {
		StringBuffer sb = new StringBuffer();

		char[] c = new char[1024];
		int count = 0;
		while (true) {
			count = reader.read(c);

			if (count <= -1) { // end of the stream
				break;
			}

			sb.append(new String(c, 0, count));
		}

		return sb.toString();
	}

	/**
	 * 테스트를 위해 사용됨.
	 * 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static ByteBuffer readFileToBuffer(String filePath)
			throws IOException {
		// System.gc(); // 가비지 콜렉터 실행시켜 메모리 확보

		RandomAccessFile randomFile = null;
		ByteBuffer mapBuf = null;

		randomFile = new RandomAccessFile(filePath, "r");
		mapBuf = randomFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0,
				randomFile.length());

		return mapBuf;
	}

	/**
	 * 테스트를 위해 사용됨.
	 * 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 * 
	 * @deprecated Don't use this method.
	 * @param filePath
	 * @return
	 * @throws IOException
	 * 
	 */
	public static String readFile(String filePath) throws IOException {
		Charset charset = Charset.forName("UTF-8");
		return new String(charset.decode(readFileToBuffer(filePath)).toString());
	}

	/**
	 * write to file
	 * 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 * 
	 * @param fileName
	 *            기록할 파일명
	 * @param b
	 *            기록할 byte의 배열
	 * @throws Exception
	 */
	public static void writeFile(String fileName, byte[] b) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		fos.write(b);
		fos.close();
	}

	/**
	 * write to file.
	 * 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 * 
	 * @param fileName
	 *            기록할 파일명
	 * @param c
	 *            기록할 char의 배열
	 * @throws Exception
	 */
	public static void writeFile(String fileName, char[] c) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream(fileName));
		writer.write(c);
		writer.close();
	}

	/**
	 * write to file
	 * 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 * 
	 * @param filePath
	 *            기록할 파일패스
	 * @param content
	 * @throws IOException
	 */
	// public static void writeFile(String filePath, String content) throws
	// IOException {
	// System.gc(); // 가비지 콜렉터 실행시켜 메모리 확보
	//
	// RandomAccessFile randomFile = null;
	// ByteBuffer mapBuf = null;
	//
	// randomFile = new RandomAccessFile(filePath, "rw");
	// mapBuf = randomFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0,
	// content.getBytes().length);
	// mapBuf.put(content.getBytes());
	// randomFile.close();
	// }

	/**
	 * write to file
	 * 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 * 
	 * @param filePath
	 *            기록할 파일패스
	 * @param ByteBuffer
	 *            inBuf 버퍼
	 * @throws IOException
	 */
	public static void writeFile(String filePath, ByteBuffer inBuf)
			throws IOException {
		// System.gc(); // 가비지 콜렉터 실행시켜 메모리 확보

		RandomAccessFile randomFile = null;
		ByteBuffer mapBuf = null;

		randomFile = new RandomAccessFile(filePath, "rw");
		mapBuf = randomFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0,
				inBuf.capacity());
		mapBuf.put(inBuf);
		randomFile.close();
	}

	/**
	 * 파일 이어서 쓸 경우 사용
	 * 
	 * @param filePath
	 * @param contents
	 * @throws IOException
	 */
	public static void writeFileContinuing(String filePath, String contents)
			throws IOException {
		if (contents == null) {
			throw new IOException("file contents does not exist");
		}
		RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
		raf.seek(raf.length());
		raf.writeBytes(contents);
	}

	/**
	 * 파일 이어서 쓸 경우 사용
	 * 
	 * @param filePath
	 * @param contents
	 * @throws IOException
	 */
	public static void writeFileContinuing(String filePath, byte[] contents)
			throws IOException {
		if (contents == null) {
			throw new IOException("file contents does not exist");
		}
		RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
		raf.seek(raf.length());
		raf.write(contents);
	}

	// public static void writeFile(String homePath, String path, byte[]
	// b)throws IOException {
	// int lastIndex = path.lastIndexOf("/");
	// if (lastIndex == -1){
	// throw new IOException("파일 경로 포맷이 잘못됐습니다: " + path);
	// }
	//
	// String dirPath = path.substring(0, lastIndex);
	// File dirFile = new File(homePath + dirPath);
	// if (!dirFile.exists()){
	// if (!dirFile.mkdirs()){
	// throw new IOException("디렉토리 생성 실패: " + dirPath);
	// }
	// }
	//
	// String filePath = homePath + path;
	//
	// }

	/**
	 * 디렉토리 경로를 인자로 받아서 디렉토리가 없을 경우 디렉토리를 생성한다.
	 * 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 * 
	 * @param homePath
	 *            기본 경로
	 * @param path
	 *            상대 경로
	 * @param input
	 *            file 내용을 담고있는 InputStream 객체
	 * @throws IOException
	 */
	public static void writeFile(String homePath, String path, InputStream input)
			throws IOException {
		int lastIndex = path.lastIndexOf("/");
		if (lastIndex == -1) {
			throw new IOException("파일 경로 포맷이 잘못됐습니다: " + path);
		}

		String dirPath = path.substring(0, lastIndex);
		File dirFile = new File(homePath + dirPath);
		if (!dirFile.exists()) {
			if (!dirFile.mkdirs()) {
				throw new IOException("디렉토리 생성 실패: " + dirPath);
			}
		}

		String filePath = homePath + path;

		try {
			writeFile(filePath, input);
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 
	 * <pre>
	 * Dependencies : None
	 * Side Effects : None
	 * </pre>
	 * 
	 * @param path
	 * @param input
	 * @throws IOException
	 */
	public static void writeFile(String path, InputStream input)
			throws IOException {
		FileOutputStream out = new FileOutputStream(path);
		int c;
		byte[] buf = new byte[1024];
		while ((c = input.read(buf)) != -1) {
			out.write(buf, 0, c);
		}

		input.close();
		out.close();
	}

	public static void writeOutputStream(InputStream input, OutputStream output)
			throws IOException {
		BufferedInputStream in = new BufferedInputStream(input);
		BufferedOutputStream os = new BufferedOutputStream(output);

		int read = 0;
		while ((read = in.read()) != -1) {
			os.write(read);
		}

		in.close();
		os.close();
	}

	/**
	 * 파일이 존재할 경우 덮어쓴다.
	 * 
	 * @param fromPath
	 * @param toPath
	 * @return
	 * @throws IOException
	 */
	public static boolean copyOverWrite(String fromPath, String toPath)
			throws IOException {
		File fromFile = new File(fromPath);
		if (!fromFile.exists())
			return false;

		writeFile(toPath, readFileAsByteArray(fromPath));

		return true;
	}

	/**
	 * 폴더는 삭제하지 않고 폴더안의 파일들을 삭제한다.
	 * 
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (!file.exists())
			return;
		if (file.isFile()) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFile(files[i]);
				files[i].delete();
			}
		}
	}

	public static void deleteFile(String path) {
		if (!StringUtil.isset(path))
			return;
		File file = new File(path);
		if (!file.exists())
			return;
		if (file.isFile()) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFile(files[i]);
				files[i].delete();
			}
		}

	}

	/**
	 * 폴더를 삭제한다.
	 * 
	 * @param file
	 */
	public static void deleteFolder(File file) {
		if (!file.exists())
			return;
		if (file.isFile()) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFile(files[i]);
				files[i].delete();
			}
		}
		file.delete();
	}

	public static void devideFile(String filePath, int n) throws IOException {
		// File file = new File(filePath);
		// long size = file.length();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(1024 * 2);
		FileInputStream fis = new FileInputStream(filePath);
		byte[] buffer = new byte[1024 * 2];

		int len;
		while ((len = fis.read(buffer)) > 0) {
			outStream.write(buffer, 0, len);
		}

		fis.close();
		outStream.close();

		// return outStream.toByteArray();
	}

	/**
	 * 下载文件
	 * 
	 * @param loadUrl
	 * @param fileName
	 * @throws IOException
	 */
	public static void downloadFile(HttpEntity entity, String fileUrl)
			throws IOException {
		File storeFile = new File(fileUrl);
		FileOutputStream output = new FileOutputStream(storeFile);
		InputStream input = entity.getContent();
		byte b[] = new byte[1024];
		int j = 0;
		while ((j = input.read(b)) != -1) {
			output.write(b, 0, j);
		}
		output.flush();
		output.close();
	}
}
