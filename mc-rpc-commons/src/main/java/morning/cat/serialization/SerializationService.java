package morning.cat.serialization;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/3/3 6:56 PM
 */
public interface SerializationService {

    byte[] encode(Object obj);

    <T> T decode(byte[] data, Class<T> tClass);
}
