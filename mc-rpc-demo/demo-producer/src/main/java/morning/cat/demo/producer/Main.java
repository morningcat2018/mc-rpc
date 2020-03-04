package morning.cat.demo.producer;

import morning.cat.demo.api.HelloService;
import morning.cat.server.RpcServer;
import morning.cat.server.config.RPCServerConfig;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/3/4 11:52 AM
 */
public class Main {

    public static void main(String[] args) {

        RpcServer server = new RpcServer(new RPCServerConfig());
        server.register(HelloService.class, new HelloServiceImpl());
        server.start();
    }
}
