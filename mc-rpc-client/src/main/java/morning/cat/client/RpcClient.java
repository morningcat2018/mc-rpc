package morning.cat.client;

import morning.cat.client.config.RpcClientConfig;
import morning.cat.client.selector.TransportSelector;
import morning.cat.serialization.SerializationService;
import morning.cat.utils.ReflectionUtils;

import java.lang.reflect.Proxy;


public class RpcClient {
    private RpcClientConfig config;
    private SerializationService serializationService;
    private TransportSelector selector;

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.serializationService = ReflectionUtils.newInstance(this.config.getSerializationServiceClass());
        this.selector = ReflectionUtils.newInstance(this.config.getSelectorClass());
        this.selector.init(this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass());

    }

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz, serializationService, selector)
        );
    }
}
