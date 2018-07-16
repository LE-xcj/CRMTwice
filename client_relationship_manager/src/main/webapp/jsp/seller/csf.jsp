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
<!-- echars 的网络资源 -->
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
    <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
</head> 
	<body class="cbp-spmenu-push">
		<div class="main-content">
			
			<!-- main content start-->
			<div id="page-wrapper" style="margin: 0 0 0 0;">
			<!-- echars -->
			<div
				class="col-md-6 content-top-2 charts-grids w3ls-high card agileits-high">
				<div id="container" style="height: 100%"></div>
			</div>
			<div class="clearfix"></div>

			<!-- 客户评价 -->
			<div class="col-md-4 span_8">
				<div class="activity_box">
					<h2>客户评价</h2>
					<div class="scrollbar" id="style-1">

						<c:forEach items="${evaluations }" var="item">
							<div class="activity-row">
								<div class="col-xs-3 activity-img"><</div>
								<div class="col-xs-7 activity-desc">
									<h5>${item.client.cname }</h5>
									<p>${item.evaluation }</p>
								</div>
								<div class="col-xs-2 activity-desc1">
									<h6>${item.etime }</h6>
								</div>
								<div class="clearfix"></div>
							</div>

						</c:forEach>

					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	
		</div>
		
		<!--scrolling js-->
		<script src="${pageContext.request.contextPath }/js/jquery.nicescroll.js"></script>
		<script src="${pageContext.request.contextPath }/js/scripts.js"></script>
		<!--//scrolling js-->
		
		<!-- Bootstrap Core JavaScript -->
	   <script src="${pageContext.request.contextPath }/js/bootstrap.js"> </script>
	   
	 
	</body>

	<!-- echars的客户满意度饼状图js -->
	<script type="text/javascript">
		$.ajax({	
			type:"post",
			url:"${pageContext.request.contextPath }/seller/getEVAStatue.action",
			async:true,
			success: function(data){
				fillSatisation(data);
			}
		});

		function fillSatisation(data){
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			option = {
				title : {
					text : '客户满意度',
					x : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: (function() {

						var res = [];
						for (var i = 0; i < data.length; ++i) {
							res.push(data[i].title);
						}
						return res;

					})()
			    },
				series : [ {
					name : '客户满意度',
					type : 'pie',
					radius : '55%',
					center : [ '50%', '60%' ],
					data : (function() {

						var res = [];
						for (var i = 0; i < data.length; ++i) {
							var item = {
								value : data[i].amount,
								name : data[i].title
							};
							res.push(item);
						}
						return res;

					})(),
					itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					}
				} ]
			};
			;
			if (option && typeof option === "object") {
				myChart.setOption(option, true);
			}
		}
		
	</script>

</html>