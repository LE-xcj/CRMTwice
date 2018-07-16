<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>仲恺销售员</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Innovative Login Form template Responsive, Login form web template,Flat Pricing tables,Flat Drop downs  Sign up Web Templates, Flat Web Templates, Login sign up Responsive web template, SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Custom Theme files -->
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- //Custom Theme files -->
<!-- web font -->
<link href='//fonts.googleapis.com/css?family=Text+Me+One' rel='stylesheet' type='text/css'>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/zdialog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/zdialog.js"></script>

<!-- //web font -->
<!-- js -->
<!-- <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script> -->
<script src="${pageContext.request.contextPath }/js/easyResponsiveTabs.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function () {
			$('#horizontalTab').easyResponsiveTabs({
				type: 'default', //Types: default, vertical, accordion           
				width: 'auto', //auto or any width like 600px
				fit: true   // 100% fit in a container
			});
		});

		
		function canRegist(){
			var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			var cid = $("#r_cid").val();
			var cname = $("#r_cname").val();
			var psw = $("#r_psw").val();
			var dpsw = $("input[name='dpsw']").val();
			var vertifyCode = $("#emailCode").val();

			console.info(cid + " " + "" + cname + psw + " " + dpsw + " " + vertifyCode);
			var canRegist = false;
			if(!cid.match(reg)){
				showDialog("邮箱格式错误!");
			}else if(psw != dpsw){
				showDialog("两次密码输入不一致!");
			}else{
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath }/client/regist.action",
					data:{cid:cid, cname:cname, psw:psw, vertifyCode:vertifyCode},
					async:false,
					success: function(data){
						var flag = data.flag;
						if(flag == 1){
							canRegist = true;
						}else if(flag == 0){
							showDialog("该邮箱已经注册过!");
						}else{
							showDialog("验证码错误!");
						}
					}
				});
			}
			console.info(canRegist);
			return canRegist;
		}

		function canLogin(){
			var cid = $("#l_cid").val();
			var psw = $("#l_psw").val();
			var vertifyCode = $("#l_vc").val();
			var canLogin = false;
			console.info(cid + " " + psw + " " + vertifyCode);
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/client/login.action",
				async:false,
				data:{cid:cid, psw:psw, vertifyCode:vertifyCode},
				success: function(data){
					console.info(data);
					var flag = data.flag;
					if(flag == 1){
						canLogin = true;
					}else {
						var tip = "";
						if(flag == -1){
							tip = "验证码错误!";
						}else{
							tip = "邮箱和密码不匹配!";
						}
						showDialog(tip);
					}
				}
			});
			console.info(canLogin);
			return canLogin;
		}
		
		function reload(){
			$("#i_code").attr("src","${pageContext.request.contextPath }/vertifyCode/setNormalVC.action?random="+Math.random());
		}

		function sendCode(){
			console.info("this is sendCode");
			var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			var cid = $("#r_cid").val();
			if(!cid.match(reg)){
				showDialog("邮箱格式错误!");
			}else{
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath }/vertifyCode/setEMailVC.action",
					data:{cid:cid},
					success: function(data){
						
					}
				});
			}
		}

		function showDialog(tip){
			$.DialogByZ.Alert({Title: "提示", Content: tip,BtnL:"确定",FunL:alerts});
		}

        function alerts(){
       	  	$.DialogByZ.Close();
        }
	   </script>
<!-- //js -->
</head>
<body>
	<!-- main -->
	<div class="main">
		<h1>客户界面</h1> 
		<div class="main-info">
			<div class="sap_tabs">
				<div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
					<ul class="resp-tabs-list">
						<li class="resp-tab-item" aria-controls="tab_item-0"><h2><span>登录</span></h2></li>
						<li class="resp-tab-item" aria-controls="tab_item-1"><span>注册</span></li> 
					</ul>	
					<div class="clear"> </div>	
					<div class="resp-tabs-container">
						
						<!--登录table-->
						<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
							<div class="agileits-login">
								<form action="${pageContext.request.contextPath }/client/clientBackground.action" method="post" onsubmit="return canLogin();">
									<input id="l_cid" type="text" class="email" name="cid" placeholder="邮箱" required=""/>
									<input id="l_psw" type="password" class="password" name="psw" placeholder="密码" required=""/>
									<div>
										<input id="l_vc" type="text" class="email" name="vertifyCode" placeholder="验证码" required=""/>
										<img style="margin-bottom: 20px;" id="i_code" src="${pageContext.request.contextPath }/vertifyCode/setNormalVC.action" onclick="reload()"/>
									</div>
									<div class="wthree-text"> 
										<ul> 
											<li>
												<label class="anim">
													<input type="checkbox" class="checkbox" name="remember">
													<span> 记住密码</span> 
												</label> 
											</li>
											<li> <a href="#">忘记密码?</a> </li>
										</ul>
										<div class="clear"> </div>
									</div>  
									<div class="w3ls-submit">
										<div class="submit-text">
											<input type="submit" value="登录"> 
										</div>	
									</div>	
								</form>
							</div> 
						</div>
						
						<!--注册table-->
						<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-1">
							<div class="login-top sign-top">
								<div class="agileits-login">
									<form action="${pageContext.request.contextPath }/client/clientBackground.action" method="post" onsubmit="return canRegist();">
										<input id="r_cid" type="text" class="email" name="cid" placeholder="邮箱" required=""/>
										<input id="r_cname" type="text" name="cname" placeholder="姓名" required="">
										<input id="r_psw" type="password" class="password" name="psw" placeholder="密码" required=""/>	
										<input type="password" class="password" name="dpsw" placeholder="确认密码" required=""/>	
										<input id="emailCode" type="text" name="vertifyCode" placeholder="验证码" required="">
										<input id="btn_send" type="button" onclick="sendCode();" value="发送"/>
										<div class="w3ls-submit">
											<div class="submit-text">
												<input class="register" type="submit" value="注册">  
											</div>	
										</div>
									</form> 
								</div>  
							</div>
						</div>
					</div>	
					<!--结束注册-->
				</div>
				<div class="clear"> </div>
			</div>  
		</div>
		<!-- copyright -->
		<div class="copyright">
			<p> © 2018 版权归仲恺农业工程学院拥有</p>
		</div>
		<!-- //copyright -->
	</div>	
	<!-- //main --> 
</body>
</html>