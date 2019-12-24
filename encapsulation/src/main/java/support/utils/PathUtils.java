package support.utils;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName njnu.opengms.modelclient.support.utils.PathUtils
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/5
 * @Version 1.0.0
 */
public class PathUtils {
    public static final String ASSEMBLY = System.getProperty("user.dir")+ "\\Assembly";
    public static final String INSTANCE = System.getProperty("user.dir")+ "\\Instance";

    /**
     * 将相关模型拷贝到实例文件夹下
     * @return
     */
    public static Path copy(String instanceId){
        Path assembly= Paths.get(PathUtils.ASSEMBLY);
        Path instance= Paths.get(PathUtils.INSTANCE, instanceId);
        FileUtil.copyContent(assembly.toFile(),instance.toFile(),true);
        return instance;
    }

    public static Path getInstance(String instanceId){
        Path instance= Paths.get(PathUtils.INSTANCE, instanceId);
        return instance;
    }



    public static Path getPublicExe(String type){
        Path currentPath= Paths.get(System.getProperty("user.dir"),"..","..","lib").normalize();
        if("saga".equals(type)){
            Path resolve = currentPath.resolve("saga_vc_x64" + File.separator + "saga_cmd.exe");
            return resolve;
        }else if("gdal".equals(type)){
            return null;
        }else{
            return null;
        }
    }

}
