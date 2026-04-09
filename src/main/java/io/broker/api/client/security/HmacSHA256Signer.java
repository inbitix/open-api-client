package io.broker.api.client.security;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utility class to sign messages using HMAC-SHA256.
 */
public class HmacSHA256Signer {

    /**
     * Sign the given message using the given secret.
     *
     * @param message message to sign
     * @param secret  secret key
     * @return a signed message
     */
    public static String sign1(String message, String secret) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secretKeySpec);
            return new String(Hex.encodeHex(sha256_HMAC.doFinal(message.getBytes())));
        } catch (Exception e) {
            throw new RuntimeException("Unable to sign message.", e);
        }
    }

    public static String sign(String message, String secret) {
        try {

            byte[] bytes = secret.getBytes();
            HashFunction hashFunction = Hashing.hmacSha256(bytes);

            HashCode hashCode = hashFunction.hashString(message, Charsets.UTF_8);

            String signature = hashCode.toString();
            return signature;
        } catch (Exception e) {
            throw new RuntimeException("Unable to sign message.", e);
        }
    }

    public static void main(String[] args) {
        String secretKey = "Ifa2AnOORWGSlvMDssaw8SmtUmd7GysrVw3KV5KzWRqlEZi0kdaRdR1TTAlluUnw";
        String originStr = "symbol=BTC-SWAP-USDT&side=BUY_OPEN&orderType=LIMIT&quantity=100&leverage=1&price=96220&priceType=INPUT&triggerPrice=&timeInForce=GTC&clientOrderId=dsfasv23csd&timestamp=1735897152574";


        String sign = sign(originStr, secretKey);
        System.out.println("sign:" + sign);
      /*  String sign = sign(originStr, secretKey);
        System.out.println("sign:" + sign);*/
    }
}
