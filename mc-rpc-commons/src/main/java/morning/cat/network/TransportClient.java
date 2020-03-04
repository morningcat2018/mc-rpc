package morning.cat.network;


import java.io.InputStream;

/**
 * <p>1.创建连接 2.发送数据，并且等待响应3，关闭连接</p>
 **/
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
