package com.luna.tencent.pay.nortify;

import com.alibaba.fastjson.JSON;
import com.luna.common.http.HttpUtils;
import com.luna.common.utils.text.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Package: com.luna.tencent.pay.nortify
 * @ClassName: TencentPayNotify
 * @Author: luna
 * @CreateTime: 2020/8/16 15:32
 * @Description:
 */
@RestController
@RequestMapping("/pay")
public class TencentPayNotifyController {

    @Autowired
    private TencentPayNotifyService tencentPayNotifyService;

    /**
     * 支付回调同步接口
     * 
     * @param request
     */
    @PostMapping("/notify")
    public String notify(HttpServletRequest request) throws Exception {
        String data = HttpUtils.getRequest(request);
        Map<String, String> map = ConvertUtil.xmlToMap(data);
        String s = tencentPayNotifyService.analysisNotify(data);
        System.out.println(JSON.toJSONString(map));
        return s;
    }

}
