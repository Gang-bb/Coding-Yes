package rsa;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * RSA测试
 *
 * @author lyx
 * @date 2022/6/8
 **/
public class Test {
    public static void main(String[] args) {
        //获取当前系统RSA加密的公钥
        RSA rsa = new RSA();
        String publicKey = rsa.getPublicKeyBase64();
        String privateKey = rsa.getPrivateKeyBase64();

        System.out.println(publicKey);
        System.out.println(privateKey);

        String spk = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAllsP/q0VJIzpPbYDIVCIP0Jz22z7SStd0boE+9h+lilrPWmbjZPQvhLD+1uon7XFc6GUi6UEGQyW7QknIl7np/bOT7zcp9KlQREYj1dqG5ECGihq6IU+Z6gDlu0p842AbZA89cflRQnxbWl3DajEUxBa0UMMQo4aU7iHa0527ktl0en45IHc97i7RQEDjOXseEG3MXIjbyOPY3eaLbOQhQASRkOw9w2w51zPYp0dPbBVnd/H5pu6sRO45iE6uFol6PHFEH9Wjp+nO1nOpgR1D2M8q4VQuXOhLWe0hfefr5hfTbc8UbmqNdKLZHV7248y2IMiMTX7dqCSHoTvJ2iPhwIDAQAB";
        String secret = "fa348269-2500-43f7-b755-2d4d6436e00e";

        RSA rsa2 = new RSA(null,spk);
        //对秘钥进行加密传输，防止篡改数据
        String encryptSecret = rsa2.encryptBase64(secret, CharsetUtil.CHARSET_UTF_8, KeyType.PublicKey);
        System.out.println(encryptSecret);
    }
}
