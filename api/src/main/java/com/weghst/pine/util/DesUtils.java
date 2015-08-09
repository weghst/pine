/**
 * Copyright (C) 2015 The Weghst Inc. (kevinz@weghst.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weghst.pine.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 *
 */
public final class DesUtils {

    private static final Key DEFAULT_KEY;
    private static final Cipher DEFAULT_ENCRYPT;
    private static final Cipher DEFAULT_DECRYPT;

    static {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            DEFAULT_KEY = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }

        DEFAULT_ENCRYPT = getCipher(Cipher.ENCRYPT_MODE, DEFAULT_KEY);
        DEFAULT_DECRYPT = getCipher(Cipher.DECRYPT_MODE, DEFAULT_KEY);
    }

    /**
     * @param mode
     * @param key
     * @return
     */
    public static Cipher getCipher(int mode, Key key) {
        try {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(mode, key);
            return cipher;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        return encrypt(DEFAULT_ENCRYPT, str);
    }

    /**
     * @param cipher
     * @param str
     * @return
     */
    public static String encrypt(Cipher cipher, String str) {
        byte[] bytes = invoke(cipher, StringUtils.getBytesUtf8(str));
        return Hex.encodeHexString(bytes);
    }

    /**
     * @param str
     * @return
     */
    public static String decrypt(String str) {
        return decrypt(DEFAULT_DECRYPT, str);
    }

    /**
     * @param cipher
     * @param str
     * @return
     */
    public static String decrypt(Cipher cipher, String str) {
        try {
            byte[] bytes = invoke(cipher, Hex.decodeHex(str.toCharArray()));
            return StringUtils.newStringUtf8(bytes);
        } catch (DecoderException e) {
            throw new CipherException(e);
        }
    }

    private static byte[] invoke(Cipher cipher, byte[] bytes) {
        try {
            return cipher.doFinal(bytes);
        } catch (Throwable e) {
            throw new CipherException(e);
        }
    }

}
