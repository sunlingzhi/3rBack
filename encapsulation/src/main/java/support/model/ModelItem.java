package support.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName ModelItem
 * @Description todo
 * @Author sun_liber
 * @Date 2019/7/5
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelItem{
    private String name;
    private String des;
    private List<State> stateList;
}
