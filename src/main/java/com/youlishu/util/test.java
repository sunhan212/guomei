package com.youlishu.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author yujin
 * @Date 2021/8/19 16:30
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("offset", "1");
        jsonParam.put("size", "11833");
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type","application/json");

        String str = HttpClientUtil.post(HttpClientUtil.POST,"http://10.10.31.36:8009/v1/gb/access_device_list",map,jsonParam);

        JSONObject jsonObject = JSONObject.parseObject(str);
        String str1 = jsonObject.getString("data");
        JSONArray jsonArray =  JSONObject.parseArray(str1);
        JSONObject JSON = new JSONObject();
        for (Object obj:jsonArray){
//            System.out.println(obj);
            JSONObject json= JSONObject.parseObject(obj.toString());
            String id = json.getString("deviceId");
            JSONArray ja = new JSONArray();
            ja.add(id);
//            System.out.println(ja);


            String info = HttpClientUtil.post(HttpClientUtil.POST,"http://10.10.31.36:8009/v1/gb/device_detail",map,ja);
            JSONObject jo4 = JSONObject.parseObject(info);
            String str4 = jo4.getString("data").replace("[","").replace("]","");
            JSONObject jo5 = JSONObject.parseObject(str4);
            JSON.putAll(jo5);

        }
        System.out.println(JSON);


    }

}
