package com.trashCan.service;

public interface WechatService {
    
	public boolean checkSignature(String signature, String timestamp, String nonce);

}