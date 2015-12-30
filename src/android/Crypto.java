package org.plailopo.crypto;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Base64;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypto extends CordovaPlugin {

    public static final String TAG = "Crypto";

    final protected static char[] hexArray = "0123456789abcdef".toCharArray();
    private static final String AES_TRANSORMATION = "AES/CBC/PKCS7Padding";
    private static final String AES = "AES";
    private static final byte[] ZERO_KEY = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    public Crypto() {
    }

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        if ("AESencrypt".equals(action)) {

            JSONObject retObj = new JSONObject();
            String phrase = args != null && args.length() > 0 ? args.getString(0) : "";
            String chiave = args != null && args.length() > 1 ? args.getString(1) : "";
            String encryptedValue = "";
            byte[] decodedKey = Base64.decode(chiave, Base64.DEFAULT);

            try{
                Key skeySpec = new SecretKeySpec(decodedKey, AES);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(ZERO_KEY);
                Cipher ecipher = Cipher.getInstance(AES_TRANSORMATION);
                ecipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
                byte[] theByteArray = phrase.getBytes();
                byte[] dstBuff = ecipher.doFinal(theByteArray);
                encryptedValue = byteArrayToHexString(dstBuff);
            }catch(Exception e){
            }

            retObj.put("encrypted", encryptedValue);
            callbackContext.success(retObj);
			
            return true;
        }
 
        if ("init".equals(action)) {
            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }

    public static String byteArrayToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}


