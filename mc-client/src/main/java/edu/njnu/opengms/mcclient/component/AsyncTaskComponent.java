package edu.njnu.opengms.mcclient.component;

import cn.hutool.core.util.RuntimeUtil;
import domain.modelinstance.ModelInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * @ClassName AsyncTaskComponent
 * @Description 使用了@Async注解，就会生成子线程，实现异步
 * @Author sun_liber
 * @Date 2018/11/15
 * @Version 1.0.0
 */
@Component
public class AsyncTaskComponent {

    @Value ("${web.upload-path}")
    String storePath;


    /**
     * 异步任务，在主线程中调用异步任务，需要返回值的话，
     * 需要主线程去一直请求Future.isDone()，判断子线程任务是否完成，然后利用Future.get()获取返回值。
     * 如果不需要返回值的话，主线程会直接结束。
     * @return
     */
    @Async
    public Future<String> executeAsyncTaskPlus() {
        System.out.println("执行异步任务Plus：");
        IntStream.range(0,5).forEach(el->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return new AsyncResult<>("异步方法返回值");
    }

    @Async
    public void executeAsyncModelInstance(ModelInstance modelInstance) {
        System.out.println("启动modelInstance");
        Path path= Paths.get(storePath,"model_entity",modelInstance.getModelItem().getId());
        Process exec = RuntimeUtil.exec(null, path.toFile(), "java -jar main.jar localhost 8083 " + modelInstance.getId());
        //封装的模型的打印信息
        RuntimeUtil.getResult(exec);
    }
}
