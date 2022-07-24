package com.yap.yapcommon;

import org.apache.commons.collections.MapUtils;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class NormalTest {

    @Test
    public void intTest() {
        Map map = new HashMap();
        int a = MapUtils.getInteger(map,"a");
        System.out.println(a);
    }

    @Test
    public void readerTest() throws Exception{
        Reader reader = new FileReader("/Users/zhangxianbiao/test.txt");
        // char 是两个字节，但是一个汉字字符，在utf8编码下，是3个字节，写入的文件大小也是3字节？
        char[] chars = new char[4];
        reader.read(chars);
        System.out.println(chars);
        reader.close();

        Writer writer = new FileWriter("/Users/zhangxianbiao/test2.txt");
        writer.write("你好碶匼");
        writer.flush();
        writer.close();
    }
}

