package com.nytimesmostpopular.security;


import android.util.Base64;
import android.util.Log;

import com.nytimesmostpopular.utilites.Constants;
import com.nytimesmostpopular.utilites.LogUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Dilip Birajadar on 2019-12-14.
 */
public class AES {
    private static final String TAG = AES.class.getName();
    private static String AES_ALGORITHM = "AES";
    private static String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";


    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, AES_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /*This method is used for the encrypt data*/
    public static String encrypt(String strToEncrypt ) {
        try {
            setKey(Constants.yek);
            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] inputBytes = cipher.doFinal(strToEncrypt.getBytes());
            return Base64.encodeToString(inputBytes, Base64.DEFAULT);

        } catch (NoSuchAlgorithmException e) {
            LogUtils.errorLog ( TAG, Log.getStackTraceString( e));
        } catch (InvalidKeyException e) {
            LogUtils.errorLog (TAG, Log.getStackTraceString(e));
        } catch (NoSuchPaddingException e) {
            LogUtils.errorLog (TAG, Log.getStackTraceString(e));
        } catch (BadPaddingException e) {
            LogUtils.errorLog (TAG, Log.getStackTraceString(e));
        } catch (IllegalBlockSizeException e) {
            LogUtils.errorLog (TAG, Log.getStackTraceString(e));
        }

        return null;
    }
    /*This method is used for the decrypt data*/
    public static String decrypt(String strToDecrypt) {
        try {
            if(strToDecrypt == null) {
                return null;
            }
            setKey(Constants.yek); // this used to get encrypted key if doing data decryption without encrypted key knowledg
            // or if don't know encryption key used to encrypted data previously

            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] inputBytes = cipher.doFinal(Base64.decode(strToDecrypt, Base64.DEFAULT));
            return new String(inputBytes);

        } catch (NoSuchAlgorithmException e) {
            LogUtils.errorLog (TAG, Log.getStackTraceString(e));
        } catch (InvalidKeyException e) {
            LogUtils.errorLog (TAG, Log.getStackTraceString(e));
        } catch (NoSuchPaddingException e) {
            LogUtils.errorLog (TAG, Log.getStackTraceString(e));
        } catch (BadPaddingException e) {
            LogUtils.errorLog (TAG, Log.getStackTraceString(e));
        } catch (IllegalBlockSizeException e) {
            LogUtils.errorLog (TAG, Log.getStackTraceString(e));
        }

        return null;
    }

}
