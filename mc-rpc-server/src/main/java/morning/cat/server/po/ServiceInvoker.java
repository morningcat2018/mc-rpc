package morning.cat.server.po;

import morning.cat.network.Request;
import morning.cat.utils.ReflectionUtils;

public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request) {
        return ReflectionUtils.invoke(service.getTarget(), service.getMethod(), request.getParameters());
    }
}
