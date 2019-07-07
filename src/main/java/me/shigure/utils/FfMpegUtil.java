package me.shigure.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengsiyuan
 * @date 2019-05-12 21:10
 */
public class FfMpegUtil {
	private static String inputPath = "";
	private static String outputPath = "";
	private static String ffmpegPath = "";

	/**
	 *
	 * 	视频转码为mp4
	 * @param oldVideoPath 原文件路径
	 * @param newVideoPath 新文件路径
	 * @param videoName 文件名
	 *
	 */
	public static int changeToMp4(String oldVideoPath,String newVideoPath, String videoName) {
		getPath(oldVideoPath,newVideoPath);
		if (checkFile(inputPath)) {
			//文件不存在
			return 1;
		}
		if (process(videoName)) {
			//转换成功
			return 0;
		}else{
			//不支持转换
			return 2;
		}
	}
	public static void getPath(String oldVideoPath,String newVideoPath){
		// 先获取当前项目路径，在获得源文件、目标文件、转换器的路径
		File diretory = new File("");
		try {
			String currPath = diretory.getAbsolutePath();
			//视频的地址
			inputPath = oldVideoPath;
			//视频转完格式存放地址
			outputPath = newVideoPath;
			//转换视频的插件
			ffmpegPath = "";
			System.out.println(currPath);
		}
		catch (Exception e) {
			System.out.println("getPath出错");
		}
	}

	public static boolean process(String vname ) {
		int type = checkContentType();
		boolean status = false;
		System.out.println("直接转成mp4格式");
		if(type == 0){
			// 直接转成mp4格式
			status = processMp4(inputPath, vname);
		}
		return status;
	}

	private static int checkContentType() {
		String type = inputPath.substring(inputPath.lastIndexOf(".") + 1, inputPath.length())
				.toLowerCase();
		// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
		switch (type) {
			case "avi":
				return 0;
			case "mpg":
				return 0;
			case "wmv":
				return 0;
			case "3gp":
				return 0;
			case "mov":
				return 0;
			case "mp4":
				return 0;
			case "asf":
				return 0;
			case "asx":
				return 0;
			case "flv":
				return 0;
			// 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
			// 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
			case "wmv9":
				return 1;
			case "rm":
				return 1;
			case "rmvb":
				return 1;
			default:
				return 9;
		}
	}
	//判断文件是否存在
	private static boolean checkFile(String path) {
		File file = new File(path);
		return !file.isFile();
	}



	//转换成h264编码的mp4
	private static boolean processMp4(String oldfilepath,String vname) {

		if (checkFile(inputPath)) {
			System.out.println(oldfilepath + " is not file");
			return false;
		}
		List<String> command = new ArrayList<String>();
		command.add(ffmpegPath + "ffmpeg");
		command.add("-i");
		command.add(oldfilepath);
		command.add("-y");
		command.add("-vcodec");
		command.add("copy");
		command.add("-acodec");
		command.add("copy");
		command.add(outputPath +"\\"+vname);
		try {
			Process videoProcess = new ProcessBuilder(command).redirectErrorStream(true).start();
			new PrintStream(videoProcess.getErrorStream()).start();
			new PrintStream(videoProcess.getInputStream()).start();
			videoProcess.waitFor();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
class PrintStream extends Thread {
	java.io.InputStream __is = null;

	public PrintStream(java.io.InputStream is) {
		__is = is;
	}

	@Override
	public void run() {
		try {
			while (this != null) {
				int _ch = __is.read();
				if (_ch != -1) {
					System.out.print((char) _ch);
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
