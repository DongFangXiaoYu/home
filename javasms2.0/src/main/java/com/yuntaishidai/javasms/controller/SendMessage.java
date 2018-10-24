package com.yuntaishidai.javasms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.yuntaishidai.javasms.service.GetMessageCode;

/**
 * 
 * @author shukai
 * @date 2018-03-22
 * @description 短信验证码发送请求类
 *
 */
//@WebServlet注解用于标注在一个继承了HttpServlet类之上，属于类级别的注解。
//@Controller
@WebServlet("/send")
public class SendMessage extends HttpServlet{
	        
	@Override
	//@RequestMapping("/sms.do")
	//@ResponseBody
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取页面传递过来的参数（手机号）
		String phone=req.getParameter("phone");
		System.out.println(phone);
		//根据获取的手机号发送验证码
		String code=GetMessageCode.getCode(phone);
		System.out.println(code);
		resp.getWriter().print(code);
	}
	
/*	@RequestMapping("/SendMessage.do")
	public String showSendMessage(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String phone=req.getParameter("phone");
		String code=GetMessageCode.getCode(phone);
		System.out.println(code);
		resp.getWriter().print(code);
		return "SendMessage";
	}
	*/
	
}
