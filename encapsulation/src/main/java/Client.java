import cn.hutool.json.JSONUtil;
import support.ModelEncapsulation;
import support.model.ModelItem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @ClassName njnu.opengms.modelclient.Client
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/5
 * @Version 1.0.0
 */
public class Client {
    private static final int BUFFERSIZE = 1024*32;
    private ByteBuffer readBuffer ;
    private ByteBuffer writeBuffer;


    public Client() {
        this.readBuffer=ByteBuffer.allocate(BUFFERSIZE);
        this.writeBuffer=ByteBuffer.allocate(BUFFERSIZE);
    }






    public static void main(String[] args){









        if(args.length!=3){
            System.out.println("参数长度不等于3");
            return;
        }
        SocketAddress socketAddress = new InetSocketAddress(args[0],Integer.valueOf(args[1]));
        new Client().start(socketAddress, args[2]);
    }



    public void sendMessage(SocketChannel socketChannel,String value) throws IOException {
        writeBuffer.clear();
        writeBuffer.put(value.getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
    }


    public String  receiveMessage(SocketChannel socketChannel) throws IOException {
        int len=socketChannel.read(readBuffer);
        readBuffer.flip();
        byte[] bytes = new byte[len];
        readBuffer.get(bytes);
        readBuffer.clear();
        return new String(bytes,"UTF-8");
    }


    public void start(SocketAddress socketAddress,String instanceId){
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(socketAddress);
            System.out.println("传输模型实例ID");
            sendMessage(socketChannel,"instanceId:"+instanceId);
            System.out.println("获取模型信息");
            ModelItem modelItem = JSONUtil.parseObj(receiveMessage(socketChannel)).toBean(ModelItem.class);
            ModelEncapsulation modelEncapsulation=new ModelEncapsulation(modelItem);
            System.out.println("运行模型");
            modelEncapsulation.run(instanceId);
            sendMessage(socketChannel, JSONUtil.toJsonStr(modelItem));
            System.out.println("传输模型结果");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

