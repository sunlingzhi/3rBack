package domain.modelitem.support;

import lombok.Data;

import java.util.List;

/**
 * @ClassName State
 * @Description todo
 * @Author sun_liber
 * @Date 2019/11/6
 * @Version 1.0.0
 */
@Data
public class State {
    private String name;
    private String des;
    private List<Event> eventList;
}
