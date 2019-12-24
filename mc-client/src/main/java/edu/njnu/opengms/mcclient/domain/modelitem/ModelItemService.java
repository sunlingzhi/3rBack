package edu.njnu.opengms.mcclient.domain.modelitem;

import base.CopyUtils;
import base.exception.MyException;
import domain.modelitem.ModelItem;
import domain.modelitem.dto.AddModelItemDTO;
import domain.modelitem.dto.FindModelItemDTO;
import domain.modelitem.dto.UpdateModelItemDTO;
import domain.modelitem.vo.ModelItemVO;
import edu.njnu.opengms.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @ClassName ModelService
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/22
 * @Version 1.0.0
 */
@Service
public class ModelItemService implements BaseService<ModelItem, ModelItemVO, AddModelItemDTO,FindModelItemDTO, UpdateModelItemDTO> {

    @Autowired
    ModelItemRepository modelItemRepository;

    @Override
    public ModelItem add(AddModelItemDTO addDTO) {
        ModelItem modelItem =new ModelItem();
        addDTO.convertTo(modelItem);
        return modelItemRepository.insert(modelItem);
    }

    @Override
    public void delete(String id) {
        modelItemRepository.deleteById(id);
    }

    @Override
    public Page<ModelItemVO> list(FindModelItemDTO findDTO) {
        Page<ModelItem> modelItems = modelItemRepository.findAll(findDTO.getPageable());
        return modelItems.map(modelItem -> {
            ModelItemVO vo = new ModelItemVO();
            CopyUtils.copyProperties(modelItem,vo);
            return vo;
        });
    }

    @Override
    public ModelItem get(String s) {
        ModelItem modelItem=modelItemRepository.findById(s).orElseGet(null);
        return modelItem;
    }

    @Override
    public long count() {
        return modelItemRepository.count();
    }

    @Override
    public ModelItem update(String id, UpdateModelItemDTO updateDTO) {
        ModelItem modelItem = modelItemRepository.findById(id).orElseThrow(MyException::noObject);
        updateDTO.updateTo(modelItem);
        return modelItemRepository.save(modelItem);
    }
}
