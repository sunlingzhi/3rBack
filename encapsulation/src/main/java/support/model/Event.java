package support.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Event
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/6
 * @Version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    private String name;
    private String des;
    private boolean isOptional;
    private IOFlagEnum ioFlagEnum;
    private DataTemplate dataTemplate;
}