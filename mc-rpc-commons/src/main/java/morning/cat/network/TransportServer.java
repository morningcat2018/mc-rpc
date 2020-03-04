package morning.cat.network;

/**
 * <p>
 * 1. 启动监听端口
 * 2，接收请求
 * 3，关闭监听
 * </p>
 **/
public interface TransportServer {
    void init(int port, RequestHandler handler);

    void start();

    void stop();
}
