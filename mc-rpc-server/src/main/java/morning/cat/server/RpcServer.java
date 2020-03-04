package morning.cat.server;

import lombok.extern.slf4j.Slf4j;
import morning.cat.network.Request;
import morning.cat.network.RequestHandler;
import morning.cat.network.Response;
import morning.cat.network.TransportServer;
import morning.cat.serialization.SerializationService;
import morning.cat.server.config.RPCServerConfig;
import morning.cat.server.po.ServiceInstance;
import morning.cat.server.po.ServiceInvoker;
import morning.cat.server.po.ServiceManager;
import morning.cat.utils.ReflectionUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@Slf4j
public class RpcServer {
    private RPCServerConfig config;
    private TransportServer net;
    private SerializationService serializationService;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer() {

    }

    public RpcServer(RPCServerConfig config) {
        this.config = config;

        //net
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);

        //codec
        this.serializationService = ReflectionUtils.newInstance(config.getSerializationServiceClass());

        //service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }


    public <T> void register(Class<T> interfaceClasss, T bean) {
        serviceManager.register(interfaceClasss, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            Response resp = new Response();
            try {
                byte[] inBytes = new byte[recive.available()];

                IOUtils.readFully(recive, inBytes, 0, recive.available());
                // byte[] inBytes = IOUtils.readFully(recive,recive.available());
                Request request = serializationService.decode(inBytes, Request.class);//这里是从client传过来的

                log.info("get request: {}", request);

                /**
                 * 这里理解有点复杂
                 * 1.首先client传的是request对象 主要是 ServiceDescrptor决定是哪个方法 和 实参
                 * 2.对于serviceManager已经在register这个RpcService时已经通过class 和 实例将serviceDescriptor与实例bean 用map装配起来
                 */
                ServiceInstance sis = serviceManager.lookup(request);//request封装了ServiceDescriptor描述字符串
                /**
                 * 反射调用两个参数 方法+实例 刚好是serviceManager的存储了
                 */
                Object ret = serviceInvoker.invoke(sis, request);//执行返回结果

                resp.setData(ret);

            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                resp.setCode(1);
                resp.setMessage("RpcServer got error" + e.getClass().getName() + " : " + e.getMessage());

            } finally {
                try {
                    byte[] outBytes = serializationService.encode(resp);
                    toResp.write(outBytes);
                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }

            }

        }
    };
}
