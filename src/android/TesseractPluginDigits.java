package com.dgp.cordova;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.content.Context;

import com.googlecode.tesseract.android.TessBaseAPI;

public class TesseractPluginDigits extends CordovaPlugin {
    public static final String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/OCRFolder/";
    private static final String TAG = "TesseractPluginDigits";
    private String lang = "eng";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        try {
            String language = args.getString(0);

            String result = null;
            Log.v(TAG, "Action: " + action);
            if (action.equals("recognizeText")) {
                String imageData = args.getString(1);
                result = recognizeText(imageData, language);
            } else {
                result = loadLanguage(language);
            }

            Log.v(TAG, "Result: " + result);
            this.echo(result, callbackContext);
            return true;
        } catch (Exception e) {
            Log.v(TAG, "Exception in Execute:" + e.getMessage());
            callbackContext.error(e.getMessage());
            return false;
        }
    }


    public void echo(String result, CallbackContext callbackContext) {
        if (result != null && result.length() > 0) {
            callbackContext.success(result);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

   
}
