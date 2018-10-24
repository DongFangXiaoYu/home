<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手机验证码</title>
</head>

<body >
<div style="width:400px;height:400px;margin-left:400px;margin-top:200px;" >

	手机号:<input id="phone" minlength="11" maxlength="11" type="text" placeholder="请输入手机号" />
	<input id="btn" type="button" value="发送验证码"/>
	<br></br>
	验证码:<input id="sms" minlength="6" maxlength="6" type="text" placeholder="请输入验证码"/>
	<br></br>
	<input type="button" value="确定" id="bt-login"/>
</div>


<script src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
 //定义全局变量
    var code=""; 
 //获取输入框内容
 $("#btn").click(function(){
	 //利用ajax将获取到的手机号发送到后台
	   var phone=$("#phone").val();//var 声明一个变量接受输入的手机号
	   $.ajax({
		  /* 发送地址 */ 
		  url:"send",
		  /* 发送方式 */ 
		  type:"POST",
		  /* 发送参数 */
		  data:{"phone":phone},//发送一个字符串
		  //datatype:"txt"
		  /* 返回结果 -回调函数*/
		  success:function(result){
			 code=result;
		   /* 控制台打印 */
		   //console.log(result);
		  }
	   });
 });
 
 $("#bt-login").click(function(){
	 //验证验证码是否正确
	 var sms= $("#sms").val();
	 if(sms==""){
		 alert("请输入验证码！");
	 }else{
		/*   if(code==ssm){
			 alert("登陆成功！");
		 }else{
			 alert("验证码错误"); 
		 } */
		 alert("登录成功！")
	 }
 }) 

</script>
</body>
</html>