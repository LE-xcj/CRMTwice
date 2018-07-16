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

<script type="text/javascript" src="/client_relationship_manager/js/zdialog.js"></script>
<link type="text/css" rel="stylesheet" href="/client_relationship_manager/css/zdialog.css">
</head> 
<body class="cbp-spmenu-push">
	<div class="main-content">
		
		<!-- main content start-->
		<div id="page-wrapper" style="margin: 0 0 0 0;">

			<div class="table-responsive bs-example widget-shadow">
				<h4>客户查询</h4>
				<div>
					<p>
						<input id="cname" placeholder="客户姓名,支持模糊搜素" type="text" /> 
						<input type="button" value="查询" onclick="findClient()"/>
					</p>
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>序号</th>
								<th>客户编号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>年龄</th>
								<th>职业</th>
								<th>手机号</th>
								<th>邮箱地址</th>
								<th>住址</th>
							</tr>
						</thead>
						<tbody id="searchCS"></tbody>
					</table>

				</div>
			</div>

			<div class="table-responsive bs-example widget-shadow">
				<h4>客户列表</h4>
				
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>序号</th>
							<th>客户编号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
							<th>职业</th>
							<th>手机号</th>
							<th>邮箱地址</th>
							<th>住址</th>
						</tr>
						
					</thead>
					
					<tbody id="cs"></tbody>
				</table>
				
				<p>
					<button onclick="getClients(-1)">上一页</button> &nbsp&nbsp
					<label id="current">1</label>&nbsp&nbsp
					<button onclick="getClients(1)">下一页</button>
				</p>
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
			getClients(0);
		}

		function findClient(){
			var _cname = $("#cname").val();
			console.info(_cname);
			$.ajax({
	   			type:"post",
	   			url:"${pageContext.request.contextPath }/seller/searchClientByName.action",
	   			data: {cname:_cname},
	   			async:true,
	   			success: function(data){
		   			 if(data.length == 0){
		   				showDialog("查无此人!");
		   			}else{
			   			show(data);
		   			}
	   			}
	   		});
		}

		function show(data){
			var _tb = $("#searchCS");
			_tb.html("");
			for(var i=0; i<data.length; ++i){
				var _tr = $("<tr></tr>");
				$("<td></td>").text(i+1).appendTo(_tr);
				$("<td></td>").text(data[i].cid).appendTo(_tr);
				$("<td></td>").text(data[i].cname).appendTo(_tr);
				$("<td></td>").text(data[i].sex).appendTo(_tr);
				var bir = $("<td></td>");
				if(data[i].birthday == -1){
					bir.text("未知");
				}else{
					bir.text(data[i].birthday);
				}
				bir.appendTo(_tr);
				$("<td></td>").text(data[i].job).appendTo(_tr);
				$("<td></td>").text(data[i].phone).appendTo(_tr);
				$("<td></td>").text(data[i].eMail).appendTo(_tr);
				$("<td></td>").text(data[i].address).appendTo(_tr);
				_tr.appendTo(_tb);
			}
			
		}
/* 		function slide(_num){
			var length = "${sarchClient.size}";
			console.info(length);
			
			var size = 5;
			var _begin = $("#s_current").text();
			var total = $("#total").text();
			
			_begin += _num;
			console.info(_begin + " total " + total);

			if(_begin > total){
				showDialog("最后一页了");
			}else if(_begin <= 0){
				showDialog("已经是第一页了");
			}else{
				var _tb = $("#searchcs");
				for(var i=(_begin-1)*size; i<length; ++i){
					var _tr = $("<tr></tr>");
					$("<td></td>").text(i+1).appendTo(_tr);
					$("<td></td>").text("").appendTo(_tr);
					$("<td></td>").text(data[i].cname).appendTo(_tr);
					$("<td></td>").text(data[i].sex).appendTo(_tr);
					var bir = $("<td></td>");
					if(data[i].birthday == -1){
						bir.text("未知");
					}else{
						bir.text(data[i].birthday);
					}
					bir.appendTo(_tr);
					$("<td></td>").text(data[i].job).appendTo(_tr);
					$("<td></td>").text(data[i].phone).appendTo(_tr);
					$("<td></td>").text(data[i].eMail).appendTo(_tr);
					$("<td></td>").text(data[i].address).appendTo(_tr);
					_tr.appendTo(_tb);
				}
			}
			
		} */
		
		function getClients(_num){
			var _size = 5;
			var _begin = $("#current").text();
			console.info(_begin);
			_begin = parseInt(_begin);
			_begin += _num;;

			console.info("n_begin " + _begin);
			if(_begin == 0){
				showDialog("已经是第一页了");
				return;
			}else{
				$.ajax({
		   			type:"post",
		   			url:"${pageContext.request.contextPath }/seller/showClientsByPage.action",
		   			data: {begin: _begin, size: _size},
		   			async:true,
		   			success: function(data){

		   				fill(data, _begin);
			   			
		   			}
		   		});
			}
			
		}


		function fill(data, _begin){
			if(data.length == 0){
				showDialog("没有了");
			}else{
  				console.info("to fill");
				$("#current").text(_begin);
				var _tb = $("#cs");
				_tb.html("");

				for(var i=0; i<data.length; ++i){
					var _tr = $("<tr></tr>");
					$("<td></td>").text(i+1).appendTo(_tr);
					$("<td></td>").text(data[i].cid).appendTo(_tr);
					$("<td></td>").text(data[i].cname).appendTo(_tr);
					$("<td></td>").text(data[i].sex).appendTo(_tr);
					var bir = $("<td></td>");
					if(data[i].birthday == -1){
						bir.text("未知");
					}else{
						bir.text(data[i].birthday);
					}
					bir.appendTo(_tr);
					$("<td></td>").text(data[i].job).appendTo(_tr);
					$("<td></td>").text(data[i].phone).appendTo(_tr);
					$("<td></td>").text(data[i].eMail).appendTo(_tr);
					$("<td></td>").text(data[i].address).appendTo(_tr);
					_tr.appendTo(_tb);
				}
			}
			

		}
   </script>
   
   
   
 	<script type="text/javascript">
		function showDialog(tip){
	
			$.DialogByZ.Alert({Title: "提示", Content: tip,BtnL:"确定",FunL:alerts});
		}
	
	    function alerts(){
		   
	    	  $.DialogByZ.Close();
	    }
 	</script>
</body>
</html>