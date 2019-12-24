package edu.njnu.opengms.mcclient.socket;


import cn.hutool.json.JSONUtil;
import domain.modelinstance.ModelInstance;
import domain.modelinstance.dto.UpdateModelInstanceDTO;
import domain.modelinstance.support.StatusEnum;
import domain.modelitem.ModelItem;
import domain.modelitem.support.Event;
import domain.modelitem.support.IOFlagEnum;
import edu.njnu.opengms.common.utils.SpringApplicationContextHolder;
import edu.njnu.opengms.mcclient.domain.modelinstance.ModelInstanceService;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * @ClassName ServerHandlerImpl
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/5
 * @Version 1.0.0
 */
public class ServerHandlerImpl implements ServerHandler {
    private static final int BUFFERSIZE = 1024*32;
    private static final String INSTANCEID="instanceId:";

    private String value;
    private ByteBuffer readBuffer ;
    private ByteBuffer writeBuffer;
    private String instanceId;
    private ModelInstance modelInstance;

    public ServerHandlerImpl() {
        this.readBuffer=ByteBuffer.allocate(BUFFERSIZE);
        this.writeBuffer=ByteBuffer.allocate(BUFFERSIZE);
    }




    @Override
    public void handleAccept(SelectionKey selectionKey) throws IOException {

        SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();

        socketChannel.configureBlocking(false);

        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);

        System.out.println("建立请求......");

    }

    @Override
    public void handleRead(SelectionKey selectionKey) throws IOException {
        readBuffer.clear();
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        int num;

        try {
            if ((num=socketChannel.read(readBuffer)) == -1) {
                System.out.println("无信息接入");
                handleError(socketChannel);
            } else {
                socketChannel.register(selectionKey.selector(), SelectionKey.OP_WRITE);
                value=new String(readBuffer.array(),0,num);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            handleError(socketChannel);
        }

    }

    @Override
    public void handleWrite(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        channel.configureBlocking(false);
        Selector selector = selectionKey.selector();
        writeBuffer.clear();
        if(JSONUtil.isJson(value)){
            ModelItem modelItem = modelInstance.getModelItem();
            ModelItem modelItemFinish = JSONUtil.toBean(value, ModelItem.class);
            for (int i = 0; i < modelItem.getStateList().size(); i++) {
                List<Event> eventList = modelItem.getStateList().get(i).getEventList();
                for (int i1 = 0; i1 < eventList.size(); i1++) {
                    Event event = eventList.get(i1);
                    if(event.getIoFlagEnum().equals(IOFlagEnum.OUTPUT)){
                        String value = modelItemFinish.getStateList().get(i).getEventList().get(i1).getDataTemplate().getValue();
                        event.getDataTemplate().setValue(value);
                    }
                }
            }
            UpdateModelInstanceDTO updateModelInstanceDTO=new UpdateModelInstanceDTO();
            updateModelInstanceDTO.setModelItem(modelItem);
            updateModelInstanceDTO.setStatusEnum(StatusEnum.FINISH);
            SpringApplicationContextHolder.getBean(ModelInstanceService.class).update(instanceId,updateModelInstanceDTO);
            System.out.println("请求完成");
            //TODO 在之后的版本中应该加入pushlishEvent，将实例完成的消息传递给用户
            channel.close();
            return ;
        }else if(value.startsWith(INSTANCEID)){
            instanceId=value.split(":")[1];
            modelInstance= SpringApplicationContextHolder.getBean(ModelInstanceService.class).get(instanceId);
            ModelItem modelItem = modelInstance.getModelItem();
            writeBuffer.put(JSONUtil.toJsonStr(modelItem).getBytes());
            writeBuffer.flip();
            channel.write(writeBuffer);
            channel.register(selector, SelectionKey.OP_READ);
        }else{
            System.out.println("value: "+value);
            handleError(channel);
            return ;
        }
    }

    public void handleError(SocketChannel socketChannel) throws IOException {
        System.out.println("模型运行出错");
        modelInstance.setStatusEnum(StatusEnum.ERROR);
        UpdateModelInstanceDTO updateModelInstanceDTO=new UpdateModelInstanceDTO();
        updateModelInstanceDTO.setStatusEnum(StatusEnum.ERROR);
        updateModelInstanceDTO.setModelItem(modelInstance.getModelItem());
        SpringApplicationContextHolder.getBean(ModelInstanceService.class).update(modelInstance.getId(),updateModelInstanceDTO);
        socketChannel.close();
    }


}
