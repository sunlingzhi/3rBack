package domain.modelitem.support;

import lombok.Data;

import java.util.HashMap;

/**
 * @ClassName ParameterSelectDataTemplate
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/15
 * @Version 1.0.0
 */
@Data
public class ParameterSelectDataTemplate  extends DataTemplate{
    private String defaultValue;
    private HashMap<String,String> keyValue;
}
