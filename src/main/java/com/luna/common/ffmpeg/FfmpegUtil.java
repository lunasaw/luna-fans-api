package com.luna.common.ffmpeg;

import com.luna.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Luna@win10
 * @date 2020/4/28 20:33
 */
public class FfmpegUtil {
    private final static Logger LOG      = LoggerFactory.getLogger(FfmpegUtil.class);

    /** 设置图片大小 */
    private final static String IMG_SIZE = "1920x1080";

    /**
     * 截取某一时刻图片
     * 
     * @param videoPath 视频路径
     * @param imagePath 图片保存路径
     * @param timePoint 截取视频多少秒时的画面
     * @return
     */
    public static boolean ffmpegToImage(@NotEmpty String ffmpegPaht, String videoPath, String imagePath, int timePoint,
        String size) {
        List<String> commands = new ArrayList<String>();
        commands.add(ffmpegPaht);
        commands.add("-ss");
        commands.add(timePoint + "");
        commands.add("-i");
        commands.add(videoPath);
        commands.add("-y");
        commands.add("-f");
        commands.add("image2");
        commands.add("-t");
        commands.add("0.001");
        commands.add("-s");
        // 这个参数是设置截取图片的大小
        if (StringUtils.isNoneEmpty(size)) {
            commands.add(size);
        } else {
            commands.add(IMG_SIZE);
        }
        commands.add(imagePath);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            builder.start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Description 文件是否能被ffmpeg解析
     */
    public static int checkFileType(String fileName) {
        String type = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
            .toLowerCase();
        if (type.equals("avi")) {
            return 0;
        } else if (type.equals("mov")) {
            return 0;
        } else if (type.equals("mp4")) {
            return 0;
        } else if (type.equals("flv")) {
            return 0;
        } else if (type.equals("png")) {
            return 1;
        } else if (type.equals("jpg")) {
            return 1;
        } else if (type.equals("jpeg")) {
            return 1;
        }
        return 9;
    }

    /**
     * @Description 获取视频时长
     */
    static int getVideoTime(@NotEmpty String ffmpegPath, String video_path) {
        List<String> commands = new ArrayList<String>();
        commands.add(ffmpegPath);
        commands.add("-i");
        commands.add(video_path);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            final Process p = builder.start();

            // 从输入流中读取视频信息
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            // 从视频信息中解析时长
            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
            Pattern pattern = Pattern.compile(regexDuration);
            Matcher m = pattern.matcher(sb.toString());
            if (m.find()) {
                int time = getTimelen(m.group(1));
                LOG.info(video_path + ",视频时长：" + time + ", 开始时间：" + m.group(2) + ",比特率：" + m.group(3) + "kb/s");
                return time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 获取时长 格式:"00:00:10.68"
     * 
     * @param timelen
     * @return
     */
    private static int getTimelen(String timelen) {
        int min = 0;
        String strs[] = timelen.split(":");
        if (strs[0].compareTo("0") > 0) {
            min += Integer.valueOf(strs[0]) * 60 * 60;
            // 秒
        }
        if (strs[1].compareTo("0") > 0) {
            min += Integer.valueOf(strs[1]) * 60;
        }
        if (strs[2].compareTo("0") > 0) {
            min += Math.round(Float.valueOf(strs[2]));
        }
        return min;
    }

    /**
     * 截取视频或者音频
     * 命令 ffmpeg -i source.mp3 -vn -acodec copy -ss 00:03:21.36 -t 00:00:41 output.mp3
     * 
     * @param ffmpegPath ffmpeg安装目录
     * @param inputpath 输入
     * @param output 输出
     * @param start 开始
     * @param end 结束
     * @return
     */
    public static boolean getSubMovie2Command(String ffmpegPath, String inputpath, String output, String start,
        String end) {
        List<String> command = new ArrayList<String>();
        command.add(ffmpegPath);
        command.add("-i");
        command.add(inputpath);
        command.add("-vcodec");
        command.add("copy");
        command.add("-acodec");
        command.add("copy");
        command.add("-ss");
        /** 开始时间 */
        command.add(start);
        command.add("-to");
        /** 结束时间 */
        command.add(end);
        command.add(output);
        command.add("-y");
        boolean process = DealMedia.process(command);
        if (!process) {
            LOG.error("Coding error, command={}", command);
        }
        return process == true;
    }

    /**
     * mp3 转wav
     * 
     * 
     * @param ffmpegPath
     * @param inputpath
     * @param output
     * @return
     */
    public static boolean mp3ToWav(String ffmpegPath, String inputpath, String output) {
        String s =
            "D:\\ffmpeg\\ffmpeg\\bin\\ffmpeg.exe -i C:\\Users\\improve\\Pictures\\音乐\\output.mp3 -acodec pcm_s16le -ac 2 -ar 44100 C:\\Users\\improve\\Pictures\\音乐\\english.wav";
        List<String> command = new ArrayList<String>();
        command.add(ffmpegPath);
        command.add("-i");
        command.add(inputpath);
        command.add("-acodec");
        command.add("pcm_s16le");
        command.add("-ac");
        command.add("2");
        command.add("-ar");
        command.add("44100");
        command.add(output);
        command.add("-y");
        boolean process = DealMedia.process(command);
        if (!process) {
            LOG.error("Coding error, command={}", command);
        }
        return process == true;
    }

    /**
     * 秒转化成 hh:mm:ss
     * 
     * @param duration
     * @return
     */
    public static String convertInt2Date(long duration) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(cal.getTimeInMillis() + duration * 1000);
    }

    /**
     * 视频抽取音频文件
     * 
     * @param videoPath 视频路径
     * @param type 音频类型
     * @param audioPath 音频保存路径
     * @return
     */
    public static boolean ffmpegToAudio(String ffmpegPath, String videoPath, String type, String audioPath) {
        List<String> commands = new java.util.ArrayList<String>();
        commands.add(ffmpegPath);
        commands.add("-i");
        commands.add(videoPath);
        commands.add("-f");
        commands.add(type);
        commands.add("-vn");
        commands.add("-y");
        commands.add("-acodec");
        if ("wav".equals(type)) {
            commands.add("pcm_s16le");
        } else if ("mp3".equals(type)) {
            commands.add("mp3");
        }
        commands.add("-ar");
        commands.add("16000");
        commands.add("-ac");
        commands.add("1");
        commands.add(audioPath);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            Process p = builder.start();
            LOG.info("抽离成功:" + audioPath);

            // 1. start
            BufferedReader buf = null;
            // 保存ffmpeg的输出结果流
            String line = null;

            buf = new BufferedReader(new InputStreamReader(p.getInputStream()));

            StringBuffer sb = new StringBuffer();
            while ((line = buf.readLine()) != null) {
                LOG.info(line);
                sb.append(line);
                continue;
            }
            p.waitFor();// 这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
            // 1. end
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * wav 转 mp3
     * wav转mp3命令：ffmpeg -i test.wav -f mp3 -acodec libmp3lame -y wav2mp3.mp3
     * 
     * @param wavPath
     * @param mp3Path
     * @return
     */
    public static boolean ffmpegOfwavTomp3(String ffmpegPath, String wavPath, String mp3Path) {
        List<String> commands = new java.util.ArrayList<String>();
        commands.add(ffmpegPath);
        commands.add("-i");
        commands.add(wavPath);
        commands.add("-f");
        commands.add("mp3");
        commands.add("-acodec");
        commands.add("libmp3lame");
        commands.add("-y");
        commands.add(mp3Path);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            Process p = builder.start();
            System.out.println("转换成功:" + mp3Path);

            // 1. start
            BufferedReader buf = null;
            // 保存ffmpeg的输出结果流
            String line = null;

            buf = new BufferedReader(new InputStreamReader(p.getInputStream()));

            StringBuffer sb = new StringBuffer();
            while ((line = buf.readLine()) != null) {
                System.out.println(line);
                sb.append(line);
                continue;
            }
            p.waitFor();// 这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
            // 1. end
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}