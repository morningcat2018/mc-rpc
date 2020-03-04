package morning.cat.client.config;

import lombok.Data;
import morning.cat.client.selector.RandomTransportSelector;
import morning.cat.client.selector.TransportSelector;
import morning.cat.network.HTTPTransportClient;
import morning.cat.network.Peer;
import morning.cat.network.TransportClient;
import morning.cat.serialization.SerializationService;
import morning.cat.serialization.fastjson.FastjsonSerializationService;

import java.util.Arrays;
import java.util.List;

@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends SerializationService> serializationServiceClass = FastjsonSerializationService.class;
    private Class<? extends TransportSelector> selectorClass =
            RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1", 3000));
}
