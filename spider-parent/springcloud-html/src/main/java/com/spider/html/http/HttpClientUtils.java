package com.spider.html.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;


public class HttpClientUtils {

	public static void main(String[] args) {
		HttpClientUtils.downloadFile("https://www.1195v.com//Html/91/23605.html");
	}
	
	public static byte[] get(String url) {
		HttpClient httpClient = new HttpClient();
		byte[] bs = new byte[4096];
		// 20秒钟连接超时时间
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(20000);
		// 50分钟数据响应时间0
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(50 * 60 * 1000);
		System.out.println("访问地址：" + url);
		List<Header> headers = new ArrayList<Header>();  
        headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));  
        headers.add(new Header("Content-Type", "application/mp4"));  
        headers.add(new Header("Accept", "*/*"));  
        headers.add(new Header("Access-Control-Allow-Headers","DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type"));  
        headers.add(new Header("Access-Control-Allow-Methods","GET, POST, OPTIONS"));  
        headers.add(new Header("Access-Control-Allow-Origin","*"));  
        headers.add(new Header("Connection", "keep-alive"));  
        httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);  
		GetMethod getMethod = new GetMethod(url);
		InputStream is = null;
		try {
			int status = httpClient.executeMethod(getMethod);
			if (status == 200) {
				is = getMethod.getResponseBodyAsStream();
			}
			bs = inputStreamToBytes(is);
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
			System.out.println("连接超时地址:" + url);
			saveErrorMessage(e,url);
		} catch (Exception e) {
			e.printStackTrace();
			saveErrorMessage(e, url);
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
			}
		}
		
		return bs;
	}
	
	

	private static void saveErrorMessage(Exception e,String url) {
		ApplicationContext ac = ApplicationContextUtil.getApplicationContext();
		UrlErrorDao urlErrorDao = (UrlErrorDao) ac.getBean("urlErrorDao");
		UrlError record = new UrlError();
		record.setExceptionMessage(e.getMessage());
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setUrlError(url);
		urlErrorDao.insertSelective(record);
	}

	public static InputStream post(String url, String requestBody) {
		HttpClient httpClient = new HttpClient();
		// 5秒钟设置超时时间
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		PostMethod postMethod = new PostMethod(url);
		InputStream is = null;
		try {
			postMethod.setRequestEntity(new StringRequestEntity(requestBody, "", ""));
			int status = httpClient.executeMethod(postMethod);
			if (status == 200) {
				is = postMethod.getResponseBodyAsStream();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		return is;
	}

	public static void downloadFile(String url) {
		byte[] bytes = get(url);
		if(bytes == null || bytes.length == 0){
			throw new RuntimeException("下载文件为NULL:" + url);
		}
		File file = new File(Constant.DOWNLOAD_DIR + "/" + url.substring(url.lastIndexOf("/") + 1));
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			os.write(bytes);
			os.flush();
			System.out.println("下载完成：" + url);
		} catch (Exception e) {
			System.out.println("下载文件失败");
			e.printStackTrace();
			saveErrorMessage(e, url);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static byte[] inputStreamToBytes(InputStream is) {
		if(is == null){
			return null;
		}
		ByteArrayOutputStream os= new ByteArrayOutputStream(1024);
		byte[] bs = new byte[1024];
		int byteread = 0; 
		try {
			while((byteread=is.read(bs))!= -1 ){
				try {
					os.write(bs,0,byteread);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
					if(os != null){
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return os.toByteArray();
	}
}
