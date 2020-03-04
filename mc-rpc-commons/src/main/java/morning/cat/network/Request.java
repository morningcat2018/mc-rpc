package morning.cat.network;

import lombok.Data;
import morning.cat.protocol.ServiceDescriptor;

/**
 * @ClassName Request
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/2/25 16:17
 * @Version 1.0
 **/
@Data
public class Request {
    private ServiceDescriptor serviceDescriptor;
    private Object[] parameters;
}
