package edu.njnu.opengms.dcclient.domain.store;


import base.exception.MyException;
import domain.store.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * @ClassName LocalStorage
 * @Description todo
 * @Author sun_liber
 * @Date 2019/7/11
 * @Version 1.0.0
 */
@Component
public class LocalStorageImpl implements Storage {

    private Path path;

    @Value ("${storages.local.path}")
    String pathString;

    @Value ("${storages.local.address}")
    String address;

    @PostConstruct
    private  void init(){
        this.path= Paths.get(pathString);
    }

    @Override
    public void store(InputStream inputStream, long contentLength, String contentType, String key) {
        try {
            Files.copy(inputStream,path.resolve(key), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw  new MyException("存储文件出错");
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(path,1).filter(el->!el.equals(path)).map(el->path.relativize(el));
        } catch (IOException e) {
            throw new MyException("读取存储的文件出错");
        }
    }

    @Override
    public Path load(String key) {
        return path.resolve(key);
    }

    @Override
    public Resource loadAsResource(String key) {
        Path file=this.load(key);
        Resource resource;
        try {
            resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Override
    public void delete(String key) {
        Path file=this.load(key);
        try {
            Files.delete(file);
        } catch (IOException e) {
            throw new MyException("删除文件出错");
        }
    }

    @Override
    public String generateUrl(String key) {
        return address+key;
    }


}
