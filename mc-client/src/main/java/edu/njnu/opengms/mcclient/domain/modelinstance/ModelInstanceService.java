package edu.njnu.opengms.mcclient.domain.modelinstance;


import base.exception.MyException;
import domain.modelinstance.ModelInstance;
import domain.modelinstance.dto.AddModelInstanceDTO;
import domain.modelinstance.dto.UpdateModelInstanceDTO;
import domain.modelinstance.support.StatusEnum;
import edu.njnu.opengms.mcclient.component.AsyncTaskComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ModelInstanceService
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/7
 * @Version 1.0.0
 */
@Service
public class ModelInstanceService{

    @Autowired
    ModelInstanceRepository modelInstanceRepository;

    @Autowired
    AsyncTaskComponent asyncTaskComponent;




    public ModelInstance get(String id) {
        return modelInstanceRepository.findById(id).orElseGet(null);
    }


    public ModelInstance add(AddModelInstanceDTO addModelInstanceDTO) {
        ModelInstance modelInstance=new ModelInstance();
        addModelInstanceDTO.convertTo(modelInstance);
        modelInstance.setStatusEnum(StatusEnum.READY);
        return modelInstanceRepository.insert(modelInstance);
    }


    public ModelInstance update(String id, UpdateModelInstanceDTO updateModelInstanceDTO) {
        ModelInstance modelInstance = modelInstanceRepository.findById(id).orElseThrow(MyException::noObject);
        updateModelInstanceDTO.convertTo(modelInstance);
        return modelInstanceRepository.save(modelInstance);
    }



    public ModelInstance invoke(String id){
        UpdateModelInstanceDTO updateModelInstanceDTO=new UpdateModelInstanceDTO();
        updateModelInstanceDTO.setStatusEnum(StatusEnum.RUN);
        ModelInstance update = this.update(id, updateModelInstanceDTO);
        asyncTaskComponent.executeAsyncModelInstance(update);
        return update;
    }
}
