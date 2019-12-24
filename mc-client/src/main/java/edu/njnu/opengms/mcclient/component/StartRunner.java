package edu.njnu.opengms.mcclient.component;


import edu.njnu.opengms.mcclient.socket.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName StartRunner
 * @Description 该类在程序运行之初，可以做一些初始化的工作
 * @Author sun_liber
 * @Date 2018/11/28
 * @Version 1.0.0
 */
@Component
public class StartRunner implements CommandLineRunner {
    @Override
    public void run(String... args){
        System.out.println("初始化操作，命令行参数为：");
        for (String arg : args) {
            System.out.println(arg);
        }
        new Server().start();
    }
}
