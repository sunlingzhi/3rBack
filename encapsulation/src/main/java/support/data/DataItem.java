package support.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DataItem
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/7
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataItem{
    String id;
    String name;
    String storeKey;
}

