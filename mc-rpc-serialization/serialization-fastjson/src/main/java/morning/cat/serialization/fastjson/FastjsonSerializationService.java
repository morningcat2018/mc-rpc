package morning.cat.serialization.fastjson;

import com.alibaba.fastjson.JSON;
import morning.cat.serialization.SerializationService;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/3/3 7:39 PM
 */
public class FastjsonSerializationService implements SerializationService {

    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T decode(byte[] bytes, Class<T> tClass) {
        return JSON.parseObject(bytes, tClass);
    }
}
