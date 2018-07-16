<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		
			<div class="main-page">
				<div class="tables">
					<h2 class="title1">订单管理</h2>
					
					<!--模板开单-->
					<div class="bs-example widget-shadow" data-example-id="hoverable-table"> 
						<h4>模板开单</h4>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>模板名</th>
									<th>开单</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${modelNames }" var="item" varStatus="i" step="1">
									<tr>
										<td>${i.index +1 }</td>
										<td>${item }</td>
										<td><a href="${pageContext.request.contextPath }/seller/createBill.action?docName=${item}" target="_blank">开单</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!--end 模板开单-->

					<!--进行中的订单-->
					<div class="panel-body widget-shadow">
						<h4>进行中的订单</h4>
						<table class="table">
							<thead>
								<tr>
								  <th>序号</th>
								  <th>单号</th>
								  <th>金额</th>
								  <th>创建时间</th>
								  <th>修改订单金额和状态</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pList }" var="item" varStatus="i"
									step="1">
									<tr>
										<td>${i.index +1 }</td>
										<td>
											<a href="/zhku/bill/html/${item.bid }.html" target="_blank">${item.bid }</a>
										</td>
										<td>${item.money }</td>
										<td>${item.createtime }</td>
										<td>
											<!-- 按钮触发模态框 -->
											<button data-toggle="modal" data-target="#myModal"
												onclick="setImfor('${item.bid }', ${item.money });">修改</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!--end 进行中的订单-->
					
					<!--完成订单-->
					<div class="bs-example widget-shadow" data-example-id="hoverable-table">
						<h4>完成订单</h4>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>单号</th>
								  	<th>金额</th>
								  	<th>开单时间</th>
									<th>状态</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${cList }" var="item" varStatus="i"
									step="1">
									<tr>
										<td>${i.index +1 }</td>
										<td>
											<a href="/zhku/bill/html/${item.bid }.html" target="_blank">${item.bid }</a>
										</td>
										<td>${item.money }</td>
										<td>${item.createtime }</td>
										<td>
											<c:choose>
												<c:when test="${item.statue == 2 }">
													<p style="color: greenyellow;">完成</p>
												</c:when>
												<c:when test="${item.statue == -1 }">
													<p style="color: red;">拒绝</p>
												</c:when>
												<c:when test="${item.statue == -2 }">
													<p style="color: yellow;">取消</p>
												</c:when>
												<c:otherwise>
													<p style="color: darkgray;">未知</p>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!--end 完成订单-->
					
				</div>
			</div>
		
		</div>

	</div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改订单状态信息</h4>
				</div>

				<form action="${pageContext.request.contextPath }/seller/updateBillDB.action" method="post">

					<div class="modal-body">
						<p>
							<div>
							<input type="text" style="display: none;" name="bid" />
						</div>
						<div>
							<span style="margin-right: 100px;">
								金额 :&nbsp;<input type="text" name="money" />
							</span>
							
							<span>
								<input type="radio" name="statue" value="1" checked="checked" /> &nbsp;进行中 &nbsp;&nbsp;
							</span>
							
							<span>
								<input type="radio" name="statue" value="2" /> &nbsp;完成  &nbsp;&nbsp;
							</span>
							
							<span>
								<input type="radio" name="statue" value="-2" /> &nbsp;取消
							</span>
						</div>
					</p>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-primary">提交更改</button>
				</div>
				
			</form>
		
		</div>
			<!-- /.modal-content -->
	</div>
		<!-- /.modal -->
</div>
	
	
	<!--scrolling js-->
	<script src="${pageContext.request.contextPath }/js/jquery.nicescroll.js"></script>
	<script src="${pageContext.request.contextPath }/js/scripts.js"></script>
	<!--//scrolling js-->
	
	<!-- Bootstrap Core JavaScript -->
   <script src="${pageContext.request.contextPath }/js/bootstrap.js"> </script>
   
   
   <!-- 模态框修改订单的金额和状态信息 -->
	<script>
		
		function setImfor(_bid, _money){
			
		  	console.info(typeof _bid);
			console.info(_bid + " " + _money);
			
			$("input[name='money']").val(_money);
			console.info($("input[name='money']").val());
			
			$("input[name='bid']").val(_bid);
			console.info($("input[name='bid']").val());
		
		}
		
	</script>

</body>
</html>