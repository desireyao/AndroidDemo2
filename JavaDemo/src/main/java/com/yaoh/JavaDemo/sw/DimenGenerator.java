package com.yaoh.JavaDemo.sw;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;

/**
 * @author yaoh
 * @date Create in 2020-01-09 11:06
 * @description TODO
 */
public class DimenGenerator {

    private static final int MAX_SIZE = 426;

    private static final int DESIGN_WIDTH = 426;

    public static void main(String[] args) {
        int[] arr = new int[]{300, 320, 360, 384, 392, 400, 410,426, 432, 450, 480, 533,540, 592, 600, 640};
        for (int swdp : arr) {
            makeAll(DESIGN_WIDTH, swdp, "./sw/res/values");
        }
    }

    public static float px2dip(float pxValue, int sw, int designWidth) {
        float dpValue = (pxValue / (float) designWidth) * sw;
        BigDecimal bigDecimal = new BigDecimal(dpValue);
        float finDp = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return finDp;
    }

    private static String makeAllDimens(int swdp, int designWidth) {
        float dpValue;
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
            sb.append("<resources>\r\n");
            sb.append(String.format("<dimen name=\"base_dpi\">%ddp</dimen>\r\n", swdp));
            for (int i = 0; i <= MAX_SIZE; i++) {
                dpValue = px2dip((float) i, swdp, designWidth);
                // qb_px_%1$d
                sb.append(String.format("<dimen name=\"sdp_%1$d\">%2$.2fdp</dimen>\r\n", i, dpValue));
            }
            sb.append("</resources>\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void makeAll(int designWidth, int swdp, String buildDir) {
        try {
            if (swdp < 0) {
                return;
            }
            String folderName = "values-sw" + swdp + "dp";
            File file = new File(buildDir + File.separator + folderName);
            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + "/dimens.xml");
            fos.write(makeAllDimens(swdp, designWidth).getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
