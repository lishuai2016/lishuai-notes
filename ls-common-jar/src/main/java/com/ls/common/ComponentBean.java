package com.ls.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: lishuai-notes
 * @author: lishuai
 * @create: 2018-12-14 11:00
 *
 * 通过@Data注解得到的get set方法，在使用的时候idea会检测不通过
 */
@Component
@Data
public class ComponentBean {
    @Value("${ls.name}")
    private String name;


}
