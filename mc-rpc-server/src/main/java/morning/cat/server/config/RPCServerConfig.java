package morning.cat.server.config;

import lombok.Data;
import morning.cat.network.HttpTransportServer;
import morning.cat.network.TransportServer;
import morning.cat.serialization.SerializationService;
import morning.cat.serialization.fastjson.FastjsonSerializationService;


@Data
public class RPCServerConfig {
    /**
     * 网络通讯协议
     */
    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;

    /**
     * 序列化协议
     */
    private Class<? extends SerializationService> serializationServiceClass = FastjsonSerializationService.class;

    /**
     * 通讯端口
     */
    private int port = 3000;
}
