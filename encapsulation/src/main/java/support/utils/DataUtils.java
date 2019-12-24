package support.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import support.data.DataItem;

import java.io.File;
import java.util.HashMap;

/**
 * @ClassName DataUtils
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/7
 * @Version 1.0.0
 */
public class DataUtils {

    private static final String REMOTE_URL="http://localhost:8081/";

    public static DataItem getDataItem(String id){
        String url=REMOTE_URL+"/dataitem/"+id;
        return JSONUtil.parseObj(HttpUtil.get(url)).get("data", DataItem.class);
    }

    public static DataItem addDataItem(File file){
        String url=REMOTE_URL+"/dataitem/addByStorage";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("file", file);
        return JSONUtil.parseObj(HttpUtil.post(url, paramMap)).get("data", DataItem.class);
    }

    public static void  downloadDataItemStore(String dataItemId, File file){
        String url=REMOTE_URL+"/storage/downloadByDataItemId/"+dataItemId;
        HttpUtil.downloadFile(url, file);
    }

}
