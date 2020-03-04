package morning.cat.client;

import lombok.extern.slf4j.Slf4j;
import morning.cat.client.selector.TransportSelector;
import morning.cat.network.Request;
import morning.cat.network.Response;
import morning.cat.network.TransportClient;
import morning.cat.protocol.ServiceDescriptor;
import morning.cat.serialization.SerializationService;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName RemoteInvoker
 * @Description 调用远程服务的代理类
 * @Author 逝风无言
 * @Data 2020/2/26 17:37
 * @Version 1.0
 **/
@Slf4j
public class RemoteInvoker implements InvocationHandler {

    private Class clazz;
    private SerializationService serializationService;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz , SerializationService serializationService, TransportSelector selector) {
        this.clazz = clazz;
        this.serializationService = serializationService;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);

        Response response = invokeRemote(request);
        if(response==null || response.getCode()!=0){
            throw new IllegalStateException("fail to invoke remote: "+response);
        }

        return response.getData();
    }

    private Response invokeRemote(Request request){
        TransportClient client = null;
        Response response = null;
        try{
            client = selector.select();

            byte[] outBytes = serializationService.encode(request);
            InputStream revice = client.write(new ByteArrayInputStream(outBytes));

            byte[] inBytes = new byte[revice.available()];
            IOUtils.readFully(revice,inBytes,0,revice.available());

           // byte[] inBytes = IOUtils.readFully(revice , revice.available());

             response = serializationService.decode(inBytes,Response.class);

        }catch (IOException e) {
            log.warn(e.getMessage(),e);
            response = new Response();
            response.setCode(1);
            response.setMessage("RpcClient got error:"+e.getClass()+" : "+e.getMessage());
        }finally {
            if(client!=null){
                selector.release(client);
            }
        }
        return response;
    }
}
