package edu.njnu.opengms.dcclient.domain.store;

import domain.store.FileItemStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName FileItemStorageService
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/22
 * @Version 1.0.0
 */
@Service
public class FileItemStorageService {
    @Autowired
    FileItemStorageRepository fileItemStorageRepository;

    @Autowired
    LocalStorageImpl localStorageImpl;

    public FileItemStorage store(InputStream inputStream, long contentLength, String contentType, String name) {
        String key= UUID.randomUUID().toString();
        String url = localStorageImpl.generateUrl(key);
        localStorageImpl.store(inputStream,contentLength,contentType,key);
        FileItemStorage fileItemStorage=  FileItemStorage
                .builder()
                .key(key)
                .name(name)
                .size(contentLength)
                .contentType(contentType)
                .url(url)
                .build();
        fileItemStorage.beforeInsert();
        return fileItemStorageRepository.insert(fileItemStorage);
    }


    public FileItemStorage findByKey(String key){
        return fileItemStorageRepository.findByKey(key).orElseGet(null);
    }

    public Resource loadAsResource(String key) {
        return localStorageImpl.loadAsResource(key);
    }
}
