package com.yuntaishidai.javasms.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//import org.json.JSONObject;

/**
 * @description 发送验证码
 * @author shukai
 * @date 2018-03-22
 */
public class GetMessageCode {
	private static final String QUERY_PATH="https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
	private static final String ACCOUNT_SID="470f33cdb2364deaacc04d958fdd4b62";
	private static final String AUTH_TOKEN="d257b43a513a437484be1429f199495f";
	
	/**
	 * @Tittle getCode()
	 * @Description 根据相应的手机号发送验证码
	 * @param phone
	 * @return  String 返回值类型
	 * @author shukai
	 * @throws IOException 
	 */
   public static String getCode(String phone) throws IOException{
	   String random=smsCode();
	   String timestamp=getTimeStamp();
	   String sig=getMD5(ACCOUNT_SID, AUTH_TOKEN, timestamp	);
	   String tamp="【云台时代】尊敬的用户，您好，您正在注册智慧校园网站，验证码为:"+random+"，若非本人操作请忽略此条短信。";
	   OutputStreamWriter out=null;
	   BufferedReader br=null;
	   //定义一个容器
	   StringBuilder result=new StringBuilder();	
	   try {
		URL url=new URL(QUERY_PATH); 
		HttpURLConnection connection=(HttpURLConnection) url.openConnection();
		//URLConnection connection=url.openConnection();
		connection.setRequestMethod("POST");	
		connection.setDoInput(true);//设置是否允许参数写入
		connection.setDoOutput(true);//设置是否允许参数输出
		connection.setConnectTimeout(5000);//设置连接响应时间
		connection.setReadTimeout(10000); //设置参数读取时间
		connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
		//提交请求
		out=new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		String args= getQueryArgs(ACCOUNT_SID, tamp, phone, timestamp, sig, "json");
		out.write(args);
		out.flush();
		//读取返回结果
		br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String temp="";
		while ((temp=br.readLine()) != null){
			result.append(temp);
		}
	} catch (MalformedURLException e) {
		e.printStackTrace();
	}
	   
/*	JSONObject json=new JSONObject(result.toString());
	   String respCode=json.getString("respCode");
	   String defaultResCode="00000";
	   if(defaultResCode.equals(respCode)){
		   return random;
	   }else{
		// return result.toString();//将结果以字符串的形式返回 
		  return defaultResCode;
	   }*/
	 
	   return random;
  }
   /**
    * @Tittle getQueryArgs
    * @description   参数拼接
    * @param accountSid
    * @param smsContent
    * @param to
    * @param timestamp
    * @param sig
    * @param respDataType
    * @author shukai
    * @return
    */
   public static String getQueryArgs(String accountSid,String 
	    smsContent,String to,String timestamp, String sig,String respDataType){
	return "accountSid="+accountSid+"&smsContent="+smsContent
	    +"&to="+to+"&timestamp="+timestamp+"&sig="+sig+"&respDataType="+respDataType;
   }
   	
   
   /**
    * @tittle getTimeStramp()
    * @discription 获取时间戳
    * @author shukai
    * @return String 返回值类型
    */
   public static String getTimeStamp(){
	   //format()将时间值的date类型转换成字符串类型
	return new SimpleDateFormat("yyMMddHHmmss").format(new 	Date());
   }
   /**
    * @Tittle getMD5()加密
    * @param sid
    * @param token
    * @param timestramp
    * @return 
    * @author shukai
    */
   public static String getMD5(String sid ,String token,String timestamp){
	   StringBuilder result=new StringBuilder();
	   String source = sid+token+timestamp;
	   try {
		  MessageDigest digest=MessageDigest.getInstance("MD5");
		 byte[] bytes= digest.digest(source.getBytes());//将source字符串转为数组
		 //利用循还对每个字节进行处理
		 for(byte b:bytes){
			 String hex=Integer.toHexString(b&0xff);//将字节转换为16进制
			 //如果字符太短，比如只有一位的情况，就自动在前面加一个0（不改变大小）
			 if(hex.length()==1){
				result.append("0"+hex);
			 }else{
				 result.append(hex);
			 }
			 
		 }
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result.toString();//设置返回参数结果为字符串
	
    	   
   }
   /**
    * @Tittle smsCode
    * @decription 创建验证码
    * @author shukai
    * @param args
    * @date 2018-03-22
    */
   public static String smsCode(){
	 //int类型转字符串类型(后面直接加一个字符串)
	   //随机产生一个数字范围为0-1000000
	   String ran=new Random().nextInt(1000000)+"";
	   //作一个判断条件使产生的数字的位数为6位
	   
	   if(ran.length()!=6){
		 //递归（如果长度不是6位就再调一下自己做判断）
		   return smsCode();  
	   }else{
		  return ran; 
	   }
   }
   
   
   //输出测试
   public static void main(String[] args) throws IOException {
	   
/*	for(int i=0;i<100;i++){
		 System.out.println(smsCode());
	 
	}*/
	   
	   
/*	String timestamp =getTimeStamp();
	System.out.println(timestamp);*/
	
	/*String md5=getMD5("25","2","3");
	System.out.println(md5);*/
	   
	String code= getCode("15972918820");
	  System.out.println(code);
}
}
