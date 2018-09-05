package com.trashCan.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.trashCan.service.WechatService;
import com.trashCan.utils.WechatConfigUtils;
import com.trashCan.utils.WechatShalUtils;

@Service
public class WechatServiceImpl implements WechatService {

	WechatShalUtils shalUtil = new WechatShalUtils();
    WechatConfigUtils config = new WechatConfigUtils();
    
    //微信公众平台登录的签名校验
    private final String token = config.GetWxConfig("token");
    @Override
    public boolean checkSignature(String signature,String timestamp,String nonce) {
        //1、排序
        String [] arr = new String[]{token,timestamp,nonce};
        Arrays.sort(arr);
        //2、生成新的字符串
        StringBuffer content = new StringBuffer();
        for(int i=0;i<arr.length;i++){
            content.append(arr[i]);
        }
        //3、shal加密
        String temp = shalUtil.getSha1(content.toString());
        return temp.equals(signature);
    }
}
