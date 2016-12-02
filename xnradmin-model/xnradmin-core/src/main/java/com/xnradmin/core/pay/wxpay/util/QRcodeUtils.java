package com.xnradmin.core.pay.wxpay.util;





import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.mozilla.javascript.tools.shell.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * 利用zxing开源工具生成二维码QRCode
 * 
 * @date 2012-10-26
 * @author xhw
 * 
 */
public class QRcodeUtils {

    private final static Logger LOG = LoggerFactory.getLogger(QRcodeUtils.class);
    
    private static final int BLACK = 0xff000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static boolean getQRCode(String codeUrl,String imageName) {
        QRcodeUtils test = new QRcodeUtils();
        String filePostfix = "jpg";
        String fileUrl = ServletActionContext.getServletContext()
                .getRealPath("/images") + "/"+imageName+".jpg";
        File file = new File(fileUrl);
        test.encode(codeUrl, file, filePostfix, BarcodeFormat.QR_CODE, 300, 300, null);
        String decodeUrl = test.decode(file);
        if (decodeUrl.equals(codeUrl)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 生成QRCode二维码<br>
     * 在编码时需要将com.google.zxing.qrcode.encoder.Encoder.java中的<br>
     * static final String DEFAULT_BYTE_MODE_ENCODING = "ISO8859-1";<br>
     * 修改为UTF-8，否则中文编译后解析不了<br>
     * 
     * @param contents
     *            二维码的内容
     * @param file
     *            二维码保存的路径，如：C://test_QR_CODE.png
     * @param filePostfix
     *            生成二维码图片的格式：png,jpeg,gif等格式
     * @param format
     *            qrcode码的生成格式
     * @param width
     *            图片宽度
     * @param height
     *            图片高度
     * @param hints
     */
    private void encode(String contents, File file, String filePostfix, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, format, width, height);
            writeToFile(bitMatrix, filePostfix, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成二维码图片<br>
     * 
     * @param matrix
     * @param format
     *            图片格式
     * @param file
     *            生成二维码图片位置
     * @throws IOException
     */
    private static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        ImageIO.write(image, format, file);
    }

    /**
     * 生成二维码内容<br>
     * 
     * @param matrix
     * @return
     */
    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
            }
        }
        return image;
    }

    /**
     * 解析QRCode二维码
     */
    @SuppressWarnings("unchecked")
    private String decode(File file) {
        try {
            BufferedImage image;
            try {
                image = ImageIO.read(file);
                if (image == null) {
                    LOG.info("Could not decode image");
                }
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result;
                @SuppressWarnings("rawtypes")
                Hashtable hints = new Hashtable();
                // 解码设置编码方式为：utf-8
                hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
                result = new MultiFormatReader().decode(bitmap, hints);
                String resultStr = result.getText();
                LOG.info("解析后内容：" + resultStr);
                return resultStr;
            } catch (IOException ioe) {
                LOG.error(ioe.toString());
            } catch (ReaderException re) {
                LOG.error(re.toString());
            }
        } catch (Exception ex) {
            LOG.error(ex.toString());
        }
        return null;
    }

}
