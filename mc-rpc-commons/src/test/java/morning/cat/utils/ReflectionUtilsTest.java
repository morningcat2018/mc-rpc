package morning.cat.utils;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/3/3 7:11 PM
 */
public class ReflectionUtilsTest {

    @Test
    public void test1() {
        T t = ReflectionUtils.newInstance(T.class);
        assertEquals(t.add(1, 2), 3);
    }

    @Test
    public void test2() {
        Method[] methods = ReflectionUtils.getPublicMethods(T.class);
        Method method = methods[0];

        T t = new T();
        Object r = ReflectionUtils.invoke(t, method, 3, 2);

        assertEquals("5", r);
    }

}

class T {
    public int add(int a, int b) {
        return a + b;
    }

    private int sub(int a, int b) {
        return a - b;
    }
}
