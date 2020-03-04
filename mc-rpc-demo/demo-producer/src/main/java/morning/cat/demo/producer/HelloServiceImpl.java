package morning.cat.demo.producer;

import morning.cat.demo.api.HelloService;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/3/4 11:49 AM
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String value) {
        return "HelloServiceImpl response : hello " + value;
    }
}
