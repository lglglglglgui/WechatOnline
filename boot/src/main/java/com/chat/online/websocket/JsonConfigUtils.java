package com.chat.online.websocket;

import com.chat.online.pojo.OnlineUser;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import java.util.List;

public class JsonConfigUtils {


    public static JsonConfig getJsonConfig(final List<String> propertys){

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object object, String property, Object value) {
                if(object instanceof OnlineUser){
                    if (propertys.contains(property)){
                        //返回true属性不会被序列化
                        return true;
                    }
                }
                return false;
            }
        });
        return jsonConfig;
    }

}
