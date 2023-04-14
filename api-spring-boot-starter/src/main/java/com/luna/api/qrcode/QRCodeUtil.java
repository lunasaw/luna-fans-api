package com.luna.api.qrcode;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.common.collect.Lists;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.BaseException;

/**
 * @author chenzhangyue@luna.com 2021/7/12
 */
public class QRCodeUtil {

    /**
     * 给二维码加上背景图
     *
     * @param qrImage 二维码图片
     * @param @param qrImage 二维码图片 背景图设置
     */
    public static BufferedImage setBackground(BufferedImage qrImage, BackGroundConfig backGroundConfig)
        throws IOException {
        BufferedImage backgroundImage = ImageIO.read(backGroundConfig.getFile());
        Graphics2D graphics = backgroundImage.createGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP));
        graphics.drawImage(qrImage, backGroundConfig.getX(), backGroundConfig.getY(), backGroundConfig.getWidth(),
            backGroundConfig.getHeight(), null);
        graphics.dispose();
        return backgroundImage;
    }

    public static BufferedImage setOtherImage(BufferedImage qrImage, BackGroundConfig backGroundConfig)
        throws IOException {
        BufferedImage logoFile = ImageIO.read(backGroundConfig.getFile());
        Graphics2D graphics = qrImage.createGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP));
        graphics.drawImage(logoFile, backGroundConfig.getX(), backGroundConfig.getY(), backGroundConfig.getWidth(),
            backGroundConfig.getHeight(), null);
        graphics.dispose();
        return qrImage;
    }

    /**
     * 将图片处理为圆形图片
     * 传入的图片必须是正方形的才会生成圆形 如果是长方形的比例则会变成椭圆的
     *
     * @param image
     * @return
     */
    public static BufferedImage transferImgForRoundImgage(BufferedImage image) {
        try {
            BufferedImage resultImg =
                new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resultImg.createGraphics();
            Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, image.getWidth(), image.getHeight());
            // 使用 setRenderingHint 设置抗锯齿
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            resultImg = g.getDeviceConfiguration().createCompatibleImage(image.getWidth(), image.getHeight(),
                Transparency.TRANSLUCENT);
            g = resultImg.createGraphics();
            // 使用 setRenderingHint 设置抗锯齿
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setClip(shape);
            g.drawImage(image, 0, 0, null);
            g.dispose();
            return resultImg;
        } catch (Exception e) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, e.getMessage());
        }
    }

    /**
     * 创建二维码
     *
     * @param qrConfig 二维码配置信息
     */
    public static BufferedImage createQr(QrConfig qrConfig) throws WriterException {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        // 参数顺序分别为: 编码内容,编码类型,生成图片宽度,生成图片高度,设置参数
        BitMatrix bm =
            multiFormatWriter.encode(qrConfig.getContent(), BarcodeFormat.QR_CODE, qrConfig.getWidth(),
                qrConfig.getHeight(), QrConfig.HINTS);
        BufferedImage image = new BufferedImage(qrConfig.getWidth(), qrConfig.getHeight(), BufferedImage.TYPE_INT_RGB);

        // 开始利用二维码数据创建Bitmap图片，分别设为黑(0xFFFFFFFF) 白(0xFF000000)两色
        for (int x = 0; x < qrConfig.getWidth(); x++) {
            for (int y = 0; y < qrConfig.getHeight(); y++) {
                image.setRGB(x, y, bm.get(x, y) ? QrConfig.QR_COLOR : QrConfig.BG_WHITE);
            }
        }
        return image;
    }

    /**
     * 文字设置
     *
     * @param image 图像
     * @param textConfig 文字设置
     */
    public static void setText(BufferedImage image, TextConfig textConfig) {
        Graphics graphics = image.getGraphics();
        // 设置字体颜色
        graphics.setColor(textConfig.getColor());
        graphics.setFont(textConfig.getFont());
        // 文字显示位置
        graphics.drawString(textConfig.getContent(), textConfig.getX(), textConfig.getY());
        graphics.dispose();
    }

    /**
     * 指定背景文件创建二维码并输出指定位置
     *
     * @param suffix 保存文件后缀
     * @param output 输出地址
     */
    public static void createAndSet(String suffix, String output, BackGroundConfig backGroundConfig, QrConfig qrConfig,
        Collection<TextConfig> textConfigs) {
        try {
            BufferedImage image = createQr(qrConfig);
            BufferedImage background =
                setBackground(image, backGroundConfig);
            for (TextConfig textConfig : textConfigs) {
                setText(image, textConfig);
            }
            setOutput(background, suffix, output);
        } catch (Exception e) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, e.getMessage());
        }
    }

    /**
     * 输出最终文件
     *
     * @param image
     * @param suffix
     * @param output
     * @throws IOException
     */
    public static void setOutput(BufferedImage image, String suffix, String output) throws IOException {
        ImageIO.write(image, suffix, new File(output));
    }

    /**
     * 根据指定模版创建二维码
     *
     * @param backImage
     * @param output
     * @param content QR内容
     * @param name 模版->姓名
     * @param phone 模版->电话
     * @param note 模版->备注
     */
    public static void createAndSet(String backImage, String output, String content, String name, String phone,
        String note) {
        BackGroundConfig backGroundConfig = new BackGroundConfig(new File(backImage), 139, 466, 472, 472);
        QrConfig qrConfig = new QrConfig(content, 472, 472);
        TextConfig nameText = new TextConfig(name, 242, 292);
        TextConfig phoneText = new TextConfig(phone, 242, 350);
        TextConfig noteText = new TextConfig(note, 242, 410);
        ArrayList<TextConfig> textConfigs = Lists.newArrayList(nameText, phoneText, noteText);
        int lastIndexOf = output.lastIndexOf(".");
        String suffix = output.substring(lastIndexOf + 1);
        createAndSet(suffix, output, backGroundConfig, qrConfig, textConfigs);
    }

    /**
     * 给二维码图片中间添加Logo
     *
     * @param image
     * @param logoConfig
     * @return
     */
    public static void setLogo(BufferedImage image, LogoConfig logoConfig) throws IOException {
        Graphics2D graphics2D = image.createGraphics();
        // 保持二维码是正方形的
        int widthLogo = image.getWidth() / logoConfig.getWidth();
        int heightLogo = image.getWidth() / logoConfig.getHeight();
        // 计算图片放置位置
        int x = (image.getWidth() - widthLogo) / 2;
        int y = (image.getHeight() - heightLogo) / 2;
        // 开始绘制图片
        BufferedImage logo = ImageIO.read(logoConfig.getFile());
        graphics2D.drawImage(logo, x, y, widthLogo, heightLogo, null);
        graphics2D.drawRoundRect(x, y, widthLogo, heightLogo, 10, 10);
        graphics2D.setStroke(new BasicStroke(logoConfig.getBorder()));
        graphics2D.setColor(logoConfig.getBorderColor());
        graphics2D.drawRect(x, y, widthLogo, heightLogo);
        graphics2D.dispose();
    }

    public static class QrConfig {
        /**
         * 二维码颜色:黑色
         */
        public static final int                         QR_COLOR = 0x201f1f;
        /**
         * 二维码背景颜色:白色
         */
        public static final int                         BG_WHITE = 0xFFFFFF;

        /**
         * 设置QR二维码参数信息
         */
        public static final Map<EncodeHintType, Object> HINTS    = new HashMap<EncodeHintType, Object>() {
                                                                     private static final long serialVersionUID = 1L;

                                                                     {
                                                                         // 设置QR二维码的纠错级别(H为最高级别)
                                                                         put(EncodeHintType.ERROR_CORRECTION,
                                                                             ErrorCorrectionLevel.M);
                                                                         // 设置编码方式
                                                                         put(EncodeHintType.CHARACTER_SET,
                                                                             StandardCharsets.UTF_8);
                                                                         // 白边
                                                                         put(EncodeHintType.MARGIN, 0);
                                                                     }
                                                                 };
        /**
         * Qr内容
         */
        private String                                  content;
        /**
         * Qr宽度
         */
        private int                                     width;
        /**
         * Qr高度
         */
        private int                                     height;

        public QrConfig(String content, int width, int height) {
            this.content = content;
            this.width = width;
            this.height = height;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static class LogoConfig {
        /**
         * logo默认边框颜色
         */
        public static final Color DEFAULT_BORDER_COLOR = Color.WHITE;
        /**
         * logo默认边框宽度
         */
        public static final int   DEFAULT_BORDER       = 1;
        /**
         * logo大小默认为照片的1/6
         */
        public static final int   DEFAULT_LOGO_PART    = 4;

        private Color             borderColor          = DEFAULT_BORDER_COLOR;

        private int               border               = DEFAULT_BORDER;

        private int               part                 = DEFAULT_LOGO_PART;

        /**
         * LOGO
         */
        private File              file;

        /**
         * x 偏移量
         */
        private int               x;
        /**
         * y 偏移量
         */
        private int               y;

        /**
         * LOGO宽度
         */
        private int               width;
        /**
         * LOGO高度
         */
        private int               height;

        public Color getBorderColor() {
            return borderColor;
        }

        public void setBorderColor(Color borderColor) {
            this.borderColor = borderColor;
        }

        public int getBorder() {
            return border;
        }

        public void setBorder(int border) {
            this.border = border;
        }

        public int getPart() {
            return part;
        }

        public void setPart(int part) {
            this.part = part;
        }

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static class TextConfig {
        /**
         * 文字默认边框颜色
         */
        public static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
        /**
         * 文字默认不加粗
         */
        public static final int   DEFAULT_FONT_STYLE   = Font.PLAIN;
        /**
         * 文字默认大小
         */
        public static final int   DEFAULT_FONT_SIZE    = 30;

        private Color             color                = DEFAULT_BORDER_COLOR;
        /**
         * 设置字体类型和大小(BOLD加粗/ PLAIN平常) 多格式用 '+' eg：Font.BOLD + Font.ITALIC
         */
        private Font              font                 = new Font(null, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE);

        /**
         * 文字内容
         */
        private String            content;
        /**
         * x 偏移量
         */
        private int               x;
        /**
         * y 偏移量
         */
        private int               y;

        public TextConfig(String content, int x, int y) {
            this.content = content;
            this.x = x;
            this.y = y;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Font getFont() {
            return font;
        }

        public void setFont(Font font) {
            this.font = font;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public static class BackGroundConfig {
        /**
         * 二维码背景图
         */
        private File file;
        /**
         * 二维码在背景图中 x偏移量
         */
        private int  x;
        /**
         * 二维码在背景图中 y偏移量
         */
        private int  y;
        /**
         * 二维码宽度
         */
        private int  width;
        /**
         * 二维码高度
         */
        private int  height;

        public BackGroundConfig(File file, int x, int y, int width, int height) {
            this.file = file;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public File getFile() {
            return file;
        }

        public void setFile(File file) {
            this.file = file;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
