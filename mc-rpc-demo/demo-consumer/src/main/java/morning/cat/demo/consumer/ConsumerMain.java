package morning.cat.demo.consumer;

import morning.cat.client.RpcClient;
import morning.cat.client.config.RpcClientConfig;
import morning.cat.demo.api.HelloService;
import morning.cat.network.HTTPTransportClient;
import morning.cat.network.HttpTransportServer;
import morning.cat.network.Peer;
import morning.cat.serialization.fastjson.FastjsonSerializationService;

import java.util.Arrays;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/3/4 12:08 PM
 */
public class ConsumerMain {

    public static void main(String[] args) {

        RpcClientConfig clientConfig = new RpcClientConfig();

        clientConfig.setSerializationServiceClass(FastjsonSerializationService.class);
        clientConfig.setTransportClass(HTTPTransportClient.class);
        clientConfig.setServers(Arrays.asList(
                new Peer("127.0.0.1", 8999),
                new Peer("127.0.0.1", 9999),
                new Peer("127.0.0.1", 7777),
                new Peer("127.0.0.1", 6666)
        ));

        RpcClient client = new RpcClient(clientConfig);
        HelloService service = client.getProxy(HelloService.class);
        System.out.println(service.sayHello("MorningCat"));
    }
}
