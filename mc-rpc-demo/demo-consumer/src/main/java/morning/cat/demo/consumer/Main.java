package morning.cat.demo.consumer;

import morning.cat.client.RpcClient;
import morning.cat.client.config.RpcClientConfig;
import morning.cat.demo.api.HelloService;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/3/4 12:08 PM
 */
public class Main {

    public static void main(String[] args) {
        RpcClient client = new RpcClient(new RpcClientConfig());
        HelloService service = client.getProxy(HelloService.class);
        System.out.println(service.sayHello("MorningCat"));
    }
}
