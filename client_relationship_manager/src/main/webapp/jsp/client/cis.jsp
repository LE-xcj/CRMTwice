<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Glance Design Dashboard an Admin Panel Category Flat Bootstrap Responsive Website Template | Blank Page :: w3layouts</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel='stylesheet' type='text/css' />

<!-- Custom CSS -->
<link href="${pageContext.request.contextPath }/css/bstyle.css" rel='stylesheet' type='text/css' />

<!-- font-awesome icons CSS -->
<link href="${pageContext.request.contextPath }/css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons CSS -->

 <!-- side nav css file -->
 <link href='${pageContext.request.contextPath }/css/SidebarNav.min.css' media='all' rel='stylesheet' type='text/css'/>
 <!-- side nav css file -->
 
 <!-- js-->
<script src="${pageContext.request.contextPath }/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/modernizr.custom.js"></script>

<!--webfonts-->
<link href="//fonts.googleapis.com/css?family=PT+Sans:400,400i,700,700i&amp;subset=cyrillic,cyrillic-ext,latin-ext" rel="stylesheet">
<!--//webfonts--> 

<!-- Metis Menu -->
<script src="${pageContext.request.contextPath }/js/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath }/js/custom.js"></script>
<link href="${pageContext.request.contextPath }/css/custom.css" rel="stylesheet">
<!--//Metis Menu -->

</head> 
<body class="cbp-spmenu-push">
	<div class="main-content">
		
		<!-- main content start-->
		<div id="page-wrapper" style="margin: 0 0 0 0;">
			<div class=" form-grids row form-grids-right">
				<div class="widget-shadow " data-example-id="basic-forms">
					<div class="form-title">
						<h4>信息填写</h4>
					</div>
					<div class="form-body">
						<form class="form-horizontal" action="${pageContext.request.contextPath }/client/updateImfor.action" method="post">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" name="cname" value="${client.cname }" placeholder="姓名" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">性别</label>
								<div class="col-sm-9">
									男 <input type="radio" value="男" name="sex" <c:if test="${client.sex =='男'}">checked="checked"</c:if> /> &nbsp
									女 <input type="radio" value="女" name="sex" <c:if test="${client.sex =='女'}">checked="checked"</c:if> />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">出生日期：</label>
								<div class="col-sm-9">
									<input id="birthday" name="birthday" type="date" value="${client.birthday }"/>
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="col-sm-2 control-label">职业</label>
								<div class="col-sm-9">
									<select name="job" id="job">
										<option value="java工程师">java工程师</option>
										<option value="教师">教师</option>
										<option value="程序员">程序员</option>
										<option value="码农">码农</option>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label">地区</label>
								<div class="col-sm-9">
									<select id="address" name="address" onchange="getQu();">
										<option value="广州">广州</option>
										<option value="惠州">惠州</option>
										<option value="深圳">深圳</option>
									</select> <select name="qu" id="qu">
										<option value="">请选择你所在的区</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label  class="col-sm-2 control-label">邮箱</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" value="${client.eMail }" name="eMail" placeholder="邮箱">
								</div>
							</div>
							<div class="form-group">
								<label  class="col-sm-2 control-label">手机号</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" value="${client.phone }" name="phone" placeholder="手机号">
								</div>
							</div>
							<div class="col-sm-offset-2">
								<button type="submit" class="btn btn-default">提交</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
	
	<!--scrolling js-->
	<script src="${pageContext.request.contextPath }/js/jquery.nicescroll.js"></script>
	<script src="${pageContext.request.contextPath }/js/scripts.js"></script>
	<!--//scrolling js-->
	
	<!-- Bootstrap Core JavaScript -->
   <script src="${pageContext.request.contextPath }/js/bootstrap.js"> </script>
   
	<script type="text/javascript">
		
		$.ready = function(){
			var address = "${client.address}";
			var job = "${client.job}";
			console.info(job);
			setAddress(address);
			setJob(job);
			showTip();
		}

		function showTip(){
			var flag = "${hasUpdate}";
			if("yes" == flag){
				alert("更新成功！");
				<c:set var="hasUpdate" value="${no}"></c:set>
			}
		}
		
		var city = ["广州", "惠州", "深圳"];
		var qu = [
			["海珠", "萝岗", "黄埔", "白云"],
			["龙门", "惠东", "惠阳", "博罗"],
			["罗湖", "福田", "南山", "宝安"],
		];

		function getQu() {

			var s_city = $("#address option:selected").val();
			console.info(s_city)
			
			var index = 0;
			for(var i = 0; i < city.length; ++i) {
				if(s_city == city[i]) {
					index = i;
				}
			}

			var s_qu = $("#qu");
			s_qu.html("");
			for(var i = 0; i < qu[index].length; ++i) {
				var op = $("<option></option>");
				op.val(qu[index][i]);
				op.text(qu[index][i]);
				op.appendTo(s_qu);
			}

		}
		
		function setAddress(_city){
			console.info(_city);
			
			var i = _city.indexOf('-');
			var city = _city;
			
			_city = _city.substring(0, i);
			var _qu = city.substring(i+1, city.length);
			
			$("#address").each(function(){
				 $(this).children("option").each(function(){
				 	
				 	if(_city == $(this).val()){
				 		$(this).attr("selected", "selected")
				 	}
	
	        	});
			});
			
			getQu();
			
			$("#qu").each(function(){
				 $(this).children("option").each(function(){
				 	
				 	if(_qu == $(this).val()){
				 		$(this).attr("selected", "selected")
				 	}
	
	        	});
			});
		}


		function setJob(_job){
			$("#job").each(function(){
				 $(this).children("option").each(function(){
				 	
				 	if(_job == $(this).val()){
				 		$(this).attr("selected", "selected")
				 	}
	
	        	});
			});
		}
	</script>
</body>
</html>