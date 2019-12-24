package edu.njnu.opengms.dcclient.domain.dataitem;


import base.CopyUtils;
import base.exception.MyException;
import domain.dataitem.DataItem;
import domain.dataitem.dto.AddDataItemDTO;
import domain.dataitem.dto.FindDataItemDTO;
import domain.dataitem.dto.UpdateDataItemDTO;
import domain.dataitem.vo.DataItemVO;
import edu.njnu.opengms.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;



/**
 * @ClassName DataItemService
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/22
 * @Version 1.0.0
 */
@Service
public class DataItemService implements BaseService<DataItem,DataItemVO, AddDataItemDTO,FindDataItemDTO,UpdateDataItemDTO> {

    @Autowired
    DataItemRepository dataItemRepository;

    @Override
    public DataItem add(AddDataItemDTO addDTO) {
        DataItem dataItem =new DataItem();
        addDTO.convertTo(dataItem);
        return dataItemRepository.insert(dataItem);
    }

    @Override
    public void delete(String id) {
        dataItemRepository.deleteById(id);
    }

    @Override
    public Page<DataItemVO> list(FindDataItemDTO findDTO) {
        Page<DataItem> dataItems = dataItemRepository.findAll(findDTO.getPageable());
        return dataItems.map(dataItem -> {
            DataItemVO vo =new DataItemVO();
            CopyUtils.copyProperties(dataItem,vo);
            return vo;
        });
    }

    @Override
    public DataItem get(String s) {
        return dataItemRepository.findById(s).orElseGet(null);
    }

    @Override
    public long count() {
        return dataItemRepository.count();
    }

    @Override
    public DataItem update(String id, UpdateDataItemDTO updateDTO) {
        DataItem dataItem = dataItemRepository.findById(id).orElseThrow(MyException::noObject);
        updateDTO.updateTo(dataItem);
        return dataItemRepository.save(dataItem);
    }
}
