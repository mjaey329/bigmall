package com.dingmj.bigmall.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * <h1>Json 转换工具类</h1>
 * @author DMJ
 * @date 2019-07-09 17:02
 */
public class JacksonUtil {

    /**
     * <h2>根据json name 获取 值</h2>
     * @param body
     * @param field
     * @return
     */
    public static String parseString(String body,String field){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(body);
            JsonNode leaf = node.get(field);
            if (leaf != null){
                return leaf.asText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
