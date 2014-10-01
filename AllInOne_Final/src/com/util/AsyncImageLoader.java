package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AsyncImageLoader {
	private static final String TAG="AsyncImageLoader";

	private HashMap<String, SoftReference<Drawable>> imageCache;
	private BlockingQueue queue ;   
	private ThreadPoolExecutor executor ;
	  
	     public AsyncImageLoader() {
	    	 imageCache = new HashMap<String, SoftReference<Drawable>>();
	    	
	    	 // �̳߳أ����50����ÿ��ִ�У�1���������߳̽����ĳ�ʱʱ�䣺180��
	    	 queue = new LinkedBlockingQueue();
	    	 executor = new ThreadPoolExecutor(1, 50, 180, TimeUnit.SECONDS, queue);
	     }
	  
	     public Drawable loadDrawable(final Context context, final String imageUrl, final ImageCallback imageCallback) {
	         if (imageCache.containsKey(imageUrl)) {
	             SoftReference<Drawable> softReference = imageCache.get(imageUrl);
	             Drawable drawable = softReference.get();
	             if (drawable != null) {
	                 return drawable;
	             }
	         }
	         final Handler handler = new Handler() {
	             public void handleMessage(Message message) {
	                 imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
	             }
	         };
	         
	         // ���̳߳���������ͼƬ������
	         executor.execute(new Runnable() {
	             @Override
	             public void run() {
	                 Drawable drawable = loadImageFromUrl(context, imageUrl);
	                 imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
	                 Message message = handler.obtainMessage(0, drawable);
	                 handler.sendMessage(message);
	             }
	         });
	         
	         return null;
	     }
	  
	    // ����ͼƬ�����ص�����cacheĿ¼���棬��imagUrl��ͼƬ�ļ������档�����ͬ���ļ���cacheĿ¼�ʹӱ��ؼ���
		public static Drawable loadImageFromUrl(Context context, String imageUrl) {
			Drawable drawable = null;  
	        if(imageUrl == null )  
	            return null;  
	        String imagePath = "";  
	        String   fileName   = "";  
	              
	        // ��ȡurl��ͼƬ���ļ������׺  
	        if(imageUrl!=null&&imageUrl.length()!=0){   
	            fileName  = imageUrl.substring(imageUrl.lastIndexOf("/")+1);  
	        }  
	          
	        // ͼƬ���ֻ����صĴ��·��,ע�⣺fileNameΪ�յ����  
	        imagePath = context.getCacheDir() + "/" + fileName;  
	  
//	        Log.i(TAG,"imagePath = " + imagePath);  
	        File file = new File(context.getCacheDir(),fileName);// �����ļ�  
//	        Log.i(TAG,"file.toString()=" + file.toString());  
	        if(!file.exists()&&!file.isDirectory())  
	        {  
	            try {  
	                // ����������ͨ���ļ������жϣ��Ƿ񱾵��д�ͼƬ  
	                  
	                FileOutputStream   fos=new   FileOutputStream( file );  
	                InputStream is = new URL(imageUrl).openStream();  
	                int   data = is.read();   
	                while(data!=-1){   
	                        fos.write(data);   
	                        data=is.read();;   
	                }   
	                fos.close();  
	                is.close();  
//	              drawable = Drawable.createFromStream(  
//	                      new URL(imageUrl).openStream(), file.toString() ); // (InputStream) new URL(imageUrl).getContent();  
	                drawable = Drawable.createFromPath(file.toString());  
//	                Log.i(TAG, "file.exists()���ļ����ڣ���������:" + drawable.toString());  
	            } catch (IOException e) {  
	                Log.e(TAG, e.toString() + "ͼƬ���ؼ�����ʱ�����쳣��");  
	            }  
	        }else  
	        {  
	            drawable = Drawable.createFromPath(file.toString());  
//	            Log.i("test", "file.exists()�ļ����ڣ����ػ�ȡ");  
	        } 
	        return drawable ;
		}
	  
	     public interface ImageCallback {
	         public void imageLoaded(Drawable imageDrawable, String imageUrl);
	     }

}
