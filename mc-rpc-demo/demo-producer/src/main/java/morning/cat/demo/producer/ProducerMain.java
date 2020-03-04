package morning.cat.demo.producer;

import morning.cat.demo.api.HelloService;
import morning.cat.server.RpcServer;
import morning.cat.server.config.RPCServerConfig;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/3/4 11:52 AM
 */
public class ProducerMain {

    public static void main(String[] args) {

        // 配置服务器
        RpcServer server = new RpcServer(new RPCServerConfig());
        // 注册服务
        // 最原始的方式：API方式（类似 guice 的用法）
        server.register(HelloService.class, new HelloServiceImpl());
        // 启动 rpc 服务器
        server.start();
    }
}
