package com.scrimet.dslist.utils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenProvider {
    
    private static final Map<String, String> tokenStore = new HashMap<>();
    
    public static String generateToken(String userId) {
        String token = Base64.getEncoder().encodeToString(
            (userId + ":" + System.currentTimeMillis() + ":" + UUID.randomUUID()).getBytes()
        );
        tokenStore.put(token, userId);
        return token;
    }
    
    public static String validateToken(String token) {
        return tokenStore.get(token);
    }
    
    public static void revokeToken(String token) {
        tokenStore.remove(token);
    }
}
