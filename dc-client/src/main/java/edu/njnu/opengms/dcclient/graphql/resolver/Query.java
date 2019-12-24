package edu.njnu.opengms.dcclient.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import domain.dataitem.DataItem;
import edu.njnu.opengms.dcclient.domain.dataitem.DataItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName Query
 * @Description 这就是我们的映射方法，以Schema为模板，从映射后的实体中 抽取数据
 * @Author sun_liber
 * @Date 2019/7/6
 * @Version 1.0.0
 */
@Component
public class Query implements GraphQLQueryResolver {
    @Autowired
    DataItemRepository dataItemRepository;

    public List<DataItem> findAllDataItems(){
        return dataItemRepository.findAll();
    }

    public DataItem findOneDataItem(String id){
        return dataItemRepository.findById(id).orElse(null);
    }
}
