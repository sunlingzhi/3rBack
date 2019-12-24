package domain.store;

import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @InterfaceName Storage
 * @Description todo
 * @Author sun_liber
 * @Date 2019/7/11
 * @Version 1.0.0
 */
public interface Storage {
    /**
     * 将实体进行存储
     * @param inputStream
     * @param contentLength
     * @param contentType
     * @param key
     */
    void store(InputStream inputStream, long contentLength, String contentType, String key);

    /**
     * 加载存储的所有实体
     * @return
     */
    Stream<Path> loadAll();

    /**
     * 获取指定key的Path
     * @param key
     * @return
     */
    Path load(String key);

    /**
     * 获取指定key的Resource
     * @param key
     * @return
     */
    Resource loadAsResource(String key);

    /**
     * 根据key，删除存储的实体
     * @param key
     */
    void delete(String key);

    /**
     * 根据key，获取实体的访问url
     * @param key
     * @return
     */
    String generateUrl(String key);
}
