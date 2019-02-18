package com.yitian.sq.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RestTest {
    /*public static void main(String[] args) {
        String address="http://localhost:8080/carpartsShiro/rest/user/admin.shtml";
        CloseableHttpClient client= HttpClients.createDefault();
        HttpGet get= new HttpGet(address);
        //请求发送之后对方的响应对象
        CloseableHttpResponse response=null;
        //内容
        HttpEntity entity=null;
        try {
            response=client.execute(get);
            //内容对象
            entity=response.getEntity();
            //将内容对象转字符串
            System.out.println(EntityUtils.toString(entity,"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
