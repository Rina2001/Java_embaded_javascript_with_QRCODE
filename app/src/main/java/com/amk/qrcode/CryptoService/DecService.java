package com.amk.qrcode.CryptoService;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.amk.qrcode.R;
import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @Created by rina on 4/26/17.
 */

public class DecService {

    private Scanner scanner;
    private Context context;
    private JsEvaluator mJsEvaluator;
    private String jsCode;
    private String TAG = "DecService";
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     *
     * @param context
     */
    public DecService(Context context){
        this.context=context;
        mJsEvaluator = new JsEvaluator(context);
        jsCode = "var jsEvaluatorResult = ''; ";
        //testJQuery();
       // testCryptoJs("aHR0cDovL3d3dy5hbWtjYW1ib2RpYS5jb20=");
        //	jsCode += "jsEvaluatorResult;";
       // evaluateAndDisplay();

    }

    public void testCryptoJs(String val) {
        jsCode += loadJs("javascript/b.js");
        jsCode += "; ";
//		jsCode += "var encrypted = CryptoJS.AES.encrypt('CryptoJs is working!', 'Secret Passphrase');"
//				+ "var decrypted = CryptoJS.AES.decrypt(encrypted, 'Secret Passphrase');"
//				+ "jsEvaluatorResult += ' ' + decrypted.toString(CryptoJS.enc.Utf8); ";

        jsCode += "var decrypted = Base64.decode('"+val+"');" +
                "jsEvaluatorResult = decrypted; ";

    }
    public void execute() {
        mJsEvaluator.evaluate(jsCode, new JsCallback() {
            @Override
            public void onResult(final String resultValue) {
                final String val = resultValue;
                Log.d(TAG, "onResult: "+val);
                setResult(val);
                Log.d(TAG, "ON GET RESULT: "+getResult());
            }

            @Override
            public void onError(String errorMessage) {
            }
        });
    }

    /**
     *
     * @param fileName file dir
     * @return
     * @throws IOException
     */
    protected String ReadFile(String fileName) throws IOException {
        final AssetManager am = context.getAssets();
        final InputStream inputStream = am.open(fileName);

        scanner = new Scanner(inputStream, "UTF-8");
        return scanner.useDelimiter("\\A").next();

    }

    private String loadJs(String fileName) {
        try {
            return ReadFile(fileName);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
