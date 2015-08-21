/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yqm.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * util for http request
 * @author hzx
 */
public class HttpRequest {
    private static final Log logger = LogFactory.getLog(HttpRequest.class);
    
    public static String encoding = "UTF-8";
    
    public static String encodeParam(Map<String, String> params) throws UnsupportedEncodingException {
        String param = "";
        for(Map.Entry<String, String> entry : params.entrySet()) {
            if(param.length()>0) {
                param += "&";
            }
            param += URLEncoder.encode(entry.getKey(), encoding) + "=" + URLEncoder.encode(entry.getValue(), encoding);
        }
        return param;
    }
    
    /**
     * send get request
     * @param url
     * @param params key: name, value: value
     * @return 
     */
    public static String sendGet(String url, Map<String, String> params) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + encodeParam(params);
        
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            // connect
            conn.connect();
            //get input
            in = new BufferedReader(new InputStreamReader(
                            conn.getInputStream()));
            String line;
            while((line = in.readLine()) != null) {
                result += line;
            }
        } catch (UnsupportedEncodingException | MalformedURLException ex ) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        } 
        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    logger.error(ex);
                }
            }
        }
        
        return result;
    }
    
    /**
     * sent post request
     * @param url
     * @param params 
     * @return 
     */
    public static String sendPost(String url, String params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            out = new PrintWriter(conn.getOutputStream());
            out.print(params);
            out.flush();
            
            in = new BufferedReader(new InputStreamReader(
                            conn.getInputStream()));
            
            String line;
            while((line = in.readLine()) != null) {
                result += line;
            }  
        } catch (UnsupportedEncodingException | MalformedURLException ex ) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        } 
        finally {
            try{
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
        return result;
    }
    
    public static String sendPost(String url, String params, Map<String,String> headers) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            for(Map.Entry<String, String> header : headers.entrySet()) {
                conn.setRequestProperty(header.getKey(), header.getValue());
            }
            
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            out = new PrintWriter(conn.getOutputStream());
            out.print(params);
            out.flush();
            
            in = new BufferedReader(new InputStreamReader(
                            conn.getInputStream()));
            
            String line;
            while((line = in.readLine()) != null) {
                result += line;
            }  
        } catch (UnsupportedEncodingException | MalformedURLException ex ) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        } 
        finally {
            try{
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
        return result;
    }
}
