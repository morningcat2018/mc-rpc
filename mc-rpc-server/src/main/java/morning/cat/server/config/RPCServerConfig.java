package morning.cat.server.config;

import lombok.Data;
import morning.cat.network.HttpTransportServer;
import morning.cat.network.TransportServer;
import morning.cat.serialization.SerializationService;
import morning.cat.serialization.fastjson.FastjsonSerializationService;


@Data
public class RPCServerConfig {
    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;
    private Class<? extends SerializationService> serializationServiceClass = FastjsonSerializationService.class;

    private int port = 3000;

}
