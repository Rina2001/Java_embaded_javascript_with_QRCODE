package com.amk.qrcode.CryptoService;

import android.content.Context;
import android.content.res.AssetManager;

import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by rina on 4/26/17.
 */

public class Jsexecutor {
    public Jsexecutor(){
        scripting();
    }

    void scripting(){
        org.mozilla.javascript.Context moContext = org.mozilla.javascript.Context.enter();
        Scriptable scope = moContext.initStandardObjects();
        Object decodeObj = scope.get("Base64.decode",scope);
        if(! (decodeObj instanceof Function)){
            System.out.print("Not instance of object");
        }else{
            Object functionArg[] = {"aHR0cDovL3d3dy5hbWtjYW1ib2RpYS5jb20="};
            Function f = (Function)decodeObj;
            Object result = f.call(moContext,scope,scope,functionArg);
            System.out.print(moContext.toString(result));
        }

    }
  /*  protected String ReadFile(String fileName) throws IOException {
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
*/
}
