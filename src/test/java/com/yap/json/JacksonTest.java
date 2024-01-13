package com.yap.json;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

public class JacksonTest {

    // {"name":"ZhangSan","age":18}
    @Test
    public void test1() throws IOException {
        JsonFactory factory = new JsonFactory();

        try (JsonGenerator jsonGenerator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.writeStartObject();// 写 { 符号

            jsonGenerator.writeStringField("name", "ZhangSan");
            jsonGenerator.writeNumberField("age", 18);

            jsonGenerator.writeEndObject();// 写 } 符号
        }
    }

    // {"zhName"}
    @Test
    public void test2() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeFieldName("zhName");

            jsonGenerator.writeEndObject();
        }
    }

    // {"zhName":"A哥","enName":"YourBatman"}
    @Test
    public void test3() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeFieldName("zhName");
            jsonGenerator.writeString("A哥");

            jsonGenerator.writeFieldName("enName");
            jsonGenerator.writeString("YourBatman");

            jsonGenerator.writeEndObject();
        }
    }

    // {"zhName":"A哥","person":{"enName":"YourBatman","age":18}}
    @Test
    public void test4() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeFieldName("zhName");
            jsonGenerator.writeString("A哥");

            // 写对象（记得先写key 否则无效）
            jsonGenerator.writeFieldName("person");
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("enName");
            jsonGenerator.writeString("YourBatman");
            jsonGenerator.writeFieldName("age");
            jsonGenerator.writeNumber(18);
            jsonGenerator.writeEndObject();

            jsonGenerator.writeEndObject();
        }
    }

    // {"zhName":"A哥","objects":["YourBatman",{"enName":"YourBatman"},18]}
    @Test
    public void test5() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeFieldName("zhName");
            jsonGenerator.writeString("A哥");

            // 写数组（记得先写key 否则无效）
            jsonGenerator.writeFieldName("objects");
            jsonGenerator.writeStartArray();
            // 1、写字符串
            jsonGenerator.writeString("YourBatman");
            // 2、写对象
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("enName", "YourBatman");
            jsonGenerator.writeEndObject();
            // 3、写数字
            jsonGenerator.writeNumber(18);
            jsonGenerator.writeEndArray();

            jsonGenerator.writeEndObject();
        }
    }

    // {"zhName":"A哥","values":[3,4,5]}
    @Test
    public void test6() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeFieldName("zhName");
            jsonGenerator.writeString("A哥");

            // 快捷写入数组（从第index = 2位开始，取3个）
            jsonGenerator.writeFieldName("values");
            jsonGenerator.writeArray(new int[]{1, 2, 3, 4, 5, 6}, 2, 3);

            jsonGenerator.writeEndObject();
        }
    }

    // {"success":true,"myName":null}
    @Test
    public void test7() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeFieldName("success");
            jsonGenerator.writeBoolean(true);
            jsonGenerator.writeFieldName("myName");
            jsonGenerator.writeNull();

            jsonGenerator.writeEndObject();
        }
    }


    // 推荐使用这些组合方法去简化书写
    // 组合写JSON Key和Value
    // {"zhName":"A哥","success":true,"myName":null}
    @Test
    public void test8() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeStringField("zhName","A哥");
            jsonGenerator.writeBooleanField("success",true);
            jsonGenerator.writeNullField("myName");
            // jsonGenerator.writeObjectFieldStart();
            // jsonGenerator.writeArrayFieldStart();

            jsonGenerator.writeEndObject();
        }
    }

    // 「writeRaw()和writeRawValue()」
    // {'name':'YourBatman'}
    @Test
    public void test9() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            jsonGenerator.writeRaw("{'name':'YourBatman'}");
        }
    }

    // {"name":"YourBatman","age":18}
    @Test
    public void test11() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.err, JsonEncoding.UTF8)) {
            jsonGenerator.setCodec(new UserObjectCodec());

            jsonGenerator.writeObject(new User());
        }
    }

    @Test
    public void test12() throws IOException {
        JsonFactory factory = new JsonFactory();
        try (JsonGenerator jsonGenerator = factory.createGenerator(System.err, JsonEncoding.UTF8)) {
            jsonGenerator.setCodec(new UserObjectCodec());
            jsonGenerator.writeObject(new UserTreeNode(new User()));
        }
    }

    public class UserTreeNode implements TreeNode {
        private User user;

        public User getUser() {
            return user;
        }

        public UserTreeNode(User user) {
            this.user = user;
        }

        @Override
        public JsonToken asToken() {
            return null;
        }

        @Override
        public JsonParser.NumberType numberType() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isValueNode() {
            return false;
        }

        @Override
        public boolean isContainerNode() {
            return false;
        }

        @Override
        public boolean isMissingNode() {
            return false;
        }

        @Override
        public boolean isArray() {
            return false;
        }

        @Override
        public boolean isObject() {
            return false;
        }

        @Override
        public TreeNode get(String fieldName) {
            return null;
        }

        @Override
        public TreeNode get(int index) {
            return null;
        }

        @Override
        public TreeNode path(String fieldName) {
            return null;
        }

        @Override
        public TreeNode path(int index) {
            return null;
        }

        @Override
        public Iterator<String> fieldNames() {
            return null;
        }

        @Override
        public TreeNode at(JsonPointer ptr) {
            return null;
        }

        @Override
        public TreeNode at(String jsonPointerExpression) throws IllegalArgumentException {
            return null;
        }

        @Override
        public JsonParser traverse() {
            return null;
        }

        @Override
        public JsonParser traverse(ObjectCodec codec) {
            return null;
        }
    }

    @Data
    public class User {
        private String name = "YourBatman";
        private Integer age = 18;
    }

    // 自定义ObjectCodec解码器 用于把User写为JSON
    // 因为本例只关注write写，因此只需要实现此这一个方法即可
    public class UserObjectCodec extends ObjectCodec {
        @Override
        public Version version() {
            return null;
        }

        @Override
        public <T> T readValue(JsonParser p, Class<T> valueType) throws IOException {
            return null;
        }

        @Override
        public <T> T readValue(JsonParser p, TypeReference<T> valueTypeRef) throws IOException {
            return null;
        }

        @Override
        public <T> T readValue(JsonParser p, ResolvedType valueType) throws IOException {
            return null;
        }

        @Override
        public <T> Iterator<T> readValues(JsonParser p, Class<T> valueType) throws IOException {
            return null;
        }

        @Override
        public <T> Iterator<T> readValues(JsonParser p, TypeReference<T> valueTypeRef) throws IOException {
            return null;
        }

        @Override
        public <T> Iterator<T> readValues(JsonParser p, ResolvedType valueType) throws IOException {
            return null;
        }

        @Override
        public void writeValue(JsonGenerator gen, Object value) throws IOException {
            User user = User.class.cast(value);

            gen.writeStartObject();
            gen.writeStringField("name",user.getName());
            gen.writeNumberField("age",user.getAge());
            gen.writeEndObject();
        }

        @Override
        public <T extends TreeNode> T readTree(JsonParser p) throws IOException {
            return null;
        }

        @Override
        public void writeTree(JsonGenerator gen, TreeNode tree) throws IOException {

        }

        @Override
        public TreeNode createObjectNode() {
            return null;
        }

        @Override
        public TreeNode createArrayNode() {
            return null;
        }

        @Override
        public JsonParser treeAsTokens(TreeNode n) {
            return null;
        }

        @Override
        public <T> T treeToValue(TreeNode n, Class<T> valueType) throws JsonProcessingException {
            return null;
        }
    }
}
