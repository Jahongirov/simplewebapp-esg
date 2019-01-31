package uz.pdp.esg.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
    //httprequestdan olish uchun qo'llanilgan (ssettings)
    public static HashMap<String, String> getRequestMap(HttpServletRequest request) {
        Map<String, String[]> reqParam = request.getParameterMap();
        HashMap<String, String> params = new HashMap<String, String>();
        for (String key : reqParam.keySet()) {
            if (reqParam.get(key).length > 0) {
                params.put(key, reqParam.get(key)[0]);
            }
        }
        return params;
    }


}
