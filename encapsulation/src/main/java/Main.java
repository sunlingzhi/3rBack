import support.utils.DataUtils;

import java.io.File;

/**
 * @ClassName Main
 * @Description ass we see
 * @Author sun_liber
 * @Date 2019/12/24
 * @Version 1.0.0
 */
public class Main {
    public static void main(String[] args) {
        File fileZip=new File("E:\\project\\sunlingzhi\\Instance\\5e01afdb2a4bec74736889c5\\INPUT_ELEVATION.zip");
        DataUtils.downloadDataItemStore("5e01afbd773ed432d604245c", fileZip);
    }
}
