package com.tourism.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.List;

/**
 * Created by ppm on 2017/10/24.
 */
public class ImageMarkUtil {
    /** 水印透明度 */
    private static float alpha = 0.2f;
    /** 水印图片旋转角度 */
    private static double degree = 0f;
    private static int interval = 0;

    // 水印透明度
  //.  private static float alpha = 0.5f;
    // 水印横向位置
    private static int positionWidth = 40;
    // 水印纵向位置
    private static int positionHeight = 40;
    // 水印文字字体
    private static Font font = new Font("宋体", Font.BOLD, 12);
    // 水印文字颜色
    private static Color color = Color.black;

    /**
     * 设置水印参数，不设置就使用默认值
     *
     * @param alpha
     *            水印透明度
     * @param degree
     *            水印图片旋转角度 *
     * @param interval
     *            水印图片间隔
     */
    public static void setImageMarkOptions(float alpha, int degree,
                                           int interval) {
        if (alpha != 0.0f) {
            ImageMarkUtil.alpha = alpha;
        }
        if (degree != 0f) {
            ImageMarkUtil.degree = degree;
        }
        if (interval != 0f) {
            ImageMarkUtil.interval = interval;
        }

    }

    /**
     * 给图片添加水印图片
     *
     * @param waterImgPath
     *            水印图片路径
     * @param srcImgPath
     *            源图片路径
     * @param targerPath
     *            目标图片路径
     */
    public static void waterMarkByImg(String waterImgPath, String srcImgPath,
                                      String targerPath) throws Exception {
        waterMarkByImg(waterImgPath, srcImgPath, targerPath, 0);
    }

    /**
     * 给图片添加水印图片
     *
     * @param waterImgPath
     *            水印图片路径
     * @param srcImgPath
     *            源图片路径
     * @param targerPath
     *            目标图片路径
     */
    public static void waterMarkByImg(List<String> watermarkPaths, String srcImgPath,String longText) {
        try {
            //加入平铺水印
            waterMarkByImg(watermarkPaths.get(0), srcImgPath, srcImgPath, 0);
            //加入查询章水印
            markImageByIcon(watermarkPaths.get(1),srcImgPath,srcImgPath,0);
            //加入底部文字水印
            markImageByText(longText,srcImgPath,srcImgPath,null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 给图片添加水印图片、可设置水印图片旋转角度
     *
     * @param waterImgPath
     *            水印图片路径
     * @param srcImgPath
     *            源图片路径
     * @param targerPath
     *            目标图片路径
     * @param degree
     *            水印图片旋转角度
     */
    public static void waterMarkByImg(String waterImgPath, String srcImgPath,
                                      String targerPath, double degree) throws Exception {
        OutputStream os = null;
        try {

            Image srcImg = ImageIO.read(new File(srcImgPath));

            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 1、得到画笔对象
            Graphics2D g = buffImg.createGraphics();

            // 2、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
            // 3、设置水印旋转
            if (0 != degree) {
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2, (double) buffImg
                                .getHeight() / 2);
            }

            // 4、水印图片的路径 水印图片一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(waterImgPath);

            // 5、得到Image对象。
            Image img = imgIcon.getImage();

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));

            // 6、水印图片的位置
            for (int height = interval + imgIcon.getIconHeight(); height < buffImg
                    .getHeight(); height = height +interval+ imgIcon.getIconHeight()) {
                for (int weight = interval + imgIcon.getIconWidth(); weight < buffImg
                        .getWidth(); weight = weight +interval+ imgIcon.getIconWidth()) {
                    g.drawImage(img, weight - imgIcon.getIconWidth(), height
                            - imgIcon.getIconHeight(), null);
                }
            }
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            // 7、释放资源
            g.dispose();

            // 8、生成图片
            os = new FileOutputStream(targerPath);
            ImageIO.write(buffImg, "JPEG", os);

            System.out.println("图片完成添加水印图片");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
  /*      markImageByText("我是你爸爸啊.............小儿子", "C:\\Users\\ppm\\Desktop\\6.jpg",
                "C:\\Users\\ppm\\Desktop\\7.jpg",null);
        System.out.println("添加水印图片开始");
*/
        ImageMarkUtil.setImageMarkOptions(0.0f, 0,
                20);
        String watermarkPath = "d:/2.png";  //????????
        String watermarkPath1 = "d:/3.png";  //????????
        String imgPath = "d:/6.jpg";//???????????
        java.util.List<String> watermarkPaths=new ArrayList<String>();
        watermarkPaths.add(0,watermarkPath);
        watermarkPaths.add(1,watermarkPath1);
        ImageMarkUtil.waterMarkByImg(watermarkPaths, imgPath,"本档案供参考或本企业办理业务使用；登记相关内容可登录国家企业信用信息公示系统查询比对。");
        System.out.println("..???????????...");

    }
    /**
     * 给图片添加水印、可设置水印图片旋转角度
     * @param iconPath 水印图片路径
     * @param srcImgPath 源图片路径
     * @param targerPath 目标图片路径
     * @param degree 水印图片旋转角度
     */
    public static void markImageByIcon(String iconPath, String srcImgPath,
                                       String targerPath, Integer degree) {
        OutputStream os = null;
        try {
            Image srcImg = ImageIO.read(new File(srcImgPath));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 得到画笔对象
            // Graphics g= buffImg.getGraphics();
            Graphics2D g = buffImg.createGraphics();

            // 设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);

            if (null != degree) {
                // 设置水印旋转
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2, (double) buffImg
                                .getHeight() / 2);
            }
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(iconPath);
            // 得到Image对象
            Image img = imgIcon.getImage();
            float alpha = 1f; // 透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            // 表示水印图片的位置
            g.drawImage(img, 60, 40, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            g.dispose();
            os = new FileOutputStream(targerPath);
            // 生成图片
            ImageIO.write(buffImg, "JPEG", os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 给图片添加水印文字、可设置水印文字的旋转角度
     *
     * @param logoText
     * @param srcImgPath
     * @param targerPath
     * @param degree
     */
    public static void markImageByText(String logoText, String srcImgPath,
                                       String targerPath, Integer degree) {

        InputStream is = null;
        OutputStream os = null;
        try {
            // 1、源图片
            Image srcImg = ImageIO.read(new File(srcImgPath));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            // 2、得到画笔对象
            Graphics2D g = buffImg.createGraphics();
            // 3、设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(
                    srcImg.getScaledInstance(srcImg.getWidth(null),
                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
                    null);
            // 4、设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2,
                        (double) buffImg.getHeight() / 2);
            }
            // 5、设置水印文字颜色
            g.setColor(color);
            // 6、设置水印文字Font
            g.setFont(font);
            // 7、设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    1f));
            // 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
            g.drawString(logoText, 60,  buffImg.getHeight()-50);
            // 9、释放资源
            g.dispose();
            // 10、生成图片
            os = new FileOutputStream(targerPath);
            ImageIO.write(buffImg, "JPEG", os);

            System.out.println("图片完成添加水印文字");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
