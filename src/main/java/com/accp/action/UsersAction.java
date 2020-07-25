package com.accp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accp.pojo.Users;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

@RestController
@RequestMapping("/api/users")
public class UsersAction {
	public String dhhmgg;
	public String string1;
	 HashMap<String,Object> map=new HashMap<String, Object>();
	 Date date;
	 long time;
	 
	 Date date2;
	 long time2;
	@GetMapping("user")
	public Users getUsers(HttpSession session) throws Exception {
		return (Users) session.getAttribute("USERS");
	}

	@GetMapping("user/loginOut")
	public Map<String, Object> loginOut(HttpSession session) throws Exception {
		Map<String, Object> message = new HashMap<String, Object>();
		session.removeAttribute("USERS");
		session.invalidate();
		message.put("code", "200");
		message.put("msg", "ok");
		return message;
	}

	@GetMapping("user/{userName}/{userPwd}")
	public Map<String, Object> validateUsers(@PathVariable String userName, @PathVariable String userPwd,
			HttpSession session) throws Exception {
		Map<String, Object> message = new HashMap<String, Object>();
		System.out.println("12");
		if (("admin".equals(userName) && "123".equals(userPwd)) || ("sa".equals(userName) && "456".equals(userPwd))) {
			System.out.println("成功！");
			session.setAttribute("USERS", new Users(userName, userPwd));
			message.put("code", "200");
			message.put("msg", "ok");
			message.put("data", new Users(userName, userPwd));
			message.put("token", session.getId());// 【身份令牌】通过字典算法组合用户唯一信息,可以避免 CSRF攻击与过期设置
		} else {
			System.out.println("错误");
			message.put("code", "300");
			message.put("msg", "用户名或密码错误");
		}
		return message;
	}

	@GetMapping("usersdl/{dhhm}/{yzm}")
	public int gg(@PathVariable String dhhm,@PathVariable String yzm){
		Map<String, Object> message = new HashMap<String, Object>();
		//UsersAction t = new UsersAction();
		
		int j =diaoy(yzm);
		System.out.println(j);
		if(j==1&&dhhmgg.equals(dhhm)) {
			return 1;
		}else if(j==3){
			return 3;
		}else {
			return 2;
		}
	}
	
	@GetMapping("usersss/{dhhm}")
	public Map<String, Object> yzm(@PathVariable String dhhm,
			HttpSession session) throws Exception {
		Map<String, Object> message = new HashMap<String, Object>();
		System.out.println("发送短信！");
		//UsersAction t = new text6();
		contextLoads(dhhm, session);
		return message;
	}
	

	
	 /**
		 * 生成6位随机数验证码
		 * 
		 * @return
		 */
		public static String vcode() {
			String vcode = "";
			for (int i = 0; i < 6; i++) {
				vcode = vcode + (int) (Math.random() * 9);
			}
			return vcode;
		}
	 
	public void contextLoads(String phend,HttpSession session) {
			
//		/**
//		 * 手机验证部分配置
//		 */
//		// 设置超时时间-可自行调整
//		final static String defaultConnectTimeout  = "sun.net.client.defaultConnectTimeout";
//		final static String defaultReadTimeout = "sun.net.client.defaultReadTimeout";
//		final static String Timeout = "10000";
//		// 初始化ascClient需要的几个参数
//		final static String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
//		final static String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）
//		// 替换成你的AK (产品密)//LTAI4GEKaFyg4AsAy5EiTkMB
//		final static String accessKeyId = "LTAI4GEKaFyg4AsAy5EiTkMB";// 你的accessKeyId,填你自己的 上文配置所得  自行配置
//		final static String accessKeySecret = "oXf4ziAoz8p2WbXGFKKoJSIkJq2vuV";// 你的accessKeySecret,填你自己的 上文配置所得 自行配置
//		// 必填:短信签名-可在短信控制台中找到
//		final static String SignName = "德招文汽修管理系统"; // 阿里云配置你自己的短信签名填入
//		// 必填:短信模板-可在短信控制台中找到
//		final static String TemplateCode = "SMS_196147941"; // 阿里云配置你自己的短信模板填入
		
		
		
		
			dhhmgg = phend;
			//张耀文
			// DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GJzRyDmtR3TyUeuHSTA", "IDubuFFReaqHRBWP8co4RC03MshDkS");
			//1
			 DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G2JvBcCmnEndNoyogfc", "Ltqo4gFwUvfzcC3KLUwwQjoENBMMUm");
			//杨剑
			//  DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FzEA6XGpQSbEiy114UQ", "qe0bcOEoicQcTyjLPcWv4dy0TleUJV");
	        
			//2
			// DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G7dPzRGsrVkPnU3i6Gs", "KrjrDgnrHVvaBr6tZVWndZG0QoVfZo");
			
			
			IAcsClient client = new DefaultAcsClient(profile);
	        CommonRequest request = new CommonRequest();
	        request.setMethod(MethodType.POST);
	        request.setDomain("dysmsapi.aliyuncs.com");
	        request.setVersion("2017-05-25");
	        request.setAction("SendSms");
	        request.putQueryParameter("PhoneNumbers", phend);
//	        request.putQueryParameter("SignName", "万有引力");
//	        request.putQueryParameter("TemplateCode", "SMS_196657200");
	        
	        request.putQueryParameter("SignName", "艺优");
	        request.putQueryParameter("TemplateCode", "SMS_196658454");
	        
//	        request.putQueryParameter("SignName", "龙抬头科技");
//	        request.putQueryParameter("TemplateCode", "SMS_196653441");
	        
//	        request.putQueryParameter("SignName", "德招文汽修管理系统");
//	        request.putQueryParameter("TemplateCode", "SMS_196147941");
	        date = new Date();
	        date = new Date();
			time = date.getTime();
			 string1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
			System.out.println(time);
		    date2 = new Date();
			time2 = date2.getTime()+60000;
			System.out.println(time2);
			String yzm = vcode();
	        map.put("code",yzm);
	        System.out.println("验证码为："+yzm);
	        session.setAttribute("vcode",vcode());
	        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));
	        try {
	            CommonResponse response = client.getCommonResponse(request);
	            System.out.println(response.getData());
	        } catch (ServerException e) {
	            e.printStackTrace();
	        } catch (ClientException e) {
	            e.printStackTrace();
	        }
	    }
	 public  int  diaoy(String i) {
		// contextLoads("");
		 HashMap<String,Object> map2=map;
		 Date date = new Date();
		 long time = date.getTime();
		 String string = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
		
		//		 if(time>t.time2) {
//			 System.out.println("验证码过期!");
//			 return;
//		 }else {
		 		for (Object s : map.values()) {
		 			System.out.println(s.toString());
		 			if(i.equals(s)&&string1.equals(string)) {	
		 				 if(time>time2) {
		 					System.out.println("验证码过期!");
		 					System.out.println(s);
							return 3;
		 				 }else {
		 					System.out.println("验证成功!");
						    return 1;
		 				 }
						
						}else {
						System.out.println("验证失败!");
						return 2;
					}
				}
		return 0;
				
		   
		
		 }

}

// class text6 {
//	 HashMap<String,Object> map=new HashMap<String, Object>();
//	 Date date;
//	 long time;
//	 
//	 Date date2;
//	 long time2;
//	 /**
//		 * 生成6位随机数验证码
//		 * 
//		 * @return
//		 */
//		public String vcode() {
//			String vcode = "";
//			for (int i = 0; i < 6; i++) {
//				vcode = vcode + (int) (Math.random() * 9);
//			}
//			return vcode;
//		}
//	 
//	public void contextLoads(String phend) {
//			UsersAction u = new UsersAction();
//			u.dhhmgg = phend;
//			DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G2JvBcCmnEndNoyogfc", "Ltqo4gFwUvfzcC3KLUwwQjoENBMMUm");
//	        IAcsClient client = new DefaultAcsClient(profile);
//	        CommonRequest request = new CommonRequest();
//	        request.setMethod(MethodType.POST);
//	        request.setDomain("dysmsapi.aliyuncs.com");
//	        request.setVersion("2017-05-25");
//	        request.setAction("SendSms");
//	        request.putQueryParameter("PhoneNumbers", phend);
//	        request.putQueryParameter("SignName", "艺优");
//	        request.putQueryParameter("TemplateCode", "SMS_196658454");
//	        date = new Date();
//	        date = new Date();
//			time = date.getTime();
//			System.out.println(time);
//		    date2 = new Date();
//			time2 = date2.getTime()+60000;
//			System.out.println(time2);
//	        map.put("code",vcode());
//	        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));
//	        try {
//	            CommonResponse response = client.getCommonResponse(request);
//	            System.out.println(response.getData());
//	        } catch (ServerException e) {
//	            e.printStackTrace();
//	        } catch (ClientException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	 public  void  diaoy(String i) {
//		 text6 t= new text6();
//		// contextLoads("");
//		 HashMap<String,Object> map2=t.map;
//		 boolean isok = false;
//		 Date date = new Date();
//		 long time = date.getTime();
////		 if(time>t.time2) {
////			 System.out.println("验证码过期!");
////			 return;
////		 }else {
//		 for (Object s : map2.values()) {
//				if(i.equals(s)) {			
//					System.out.println("验证成功!");
//				    
//					}else {
//					System.out.println("验证失败!");
//					
//					}
//		   }
//		
//		 }
// 
//		 
// }	 
//	




		
		
		
