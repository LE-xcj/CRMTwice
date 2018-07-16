<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<!-- 引入 echarts.js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/echarts/4.1.0/echarts-en.common.min.js"></script>
</head> 
<body class="cbp-spmenu-push">
	<div class="main-content">
		
		<!-- main content start-->
		<div id="page-wrapper" style="margin: 0 0 0 0;">
			
			<div class="table-responsive bs-example widget-shadow">
				<div>
					<!-- 柱状分析图 -->
				    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
				    <p>
				    	 <div id="main" style="width: 600px;height:400px;"></div>
						<div id="main2" style="width: 600px;height:400px;"></div>
				    </p>
					
					<!-- 价值客户列表 -->
					<div class="table-responsive bs-example widget-shadow">
					<h4>价值客户</h4>
				
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
						
						<tbody id="vcs">
							<c:forEach items="${vcs }" var="item" varStatus="i" step="1">
								<tr>
									<td>${i.index +1 }</td>
									<td>${item.cid}</td>
									<td>${item.cname}</td>
									<td>${item.sex}</td>
									<td>${item.birthday}</td>
									<td>${item.job}</td>
									<td>${item.phone}</td>
									<td>${item.eMail}</td>
									<td>${item.address}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				
					<p>
						<button onclick="getValueClients(-1)">上一页</button> &nbsp&nbsp
						<input type="text" value="1" id="v_current"/>&nbsp&nbsp
						<button onclick="getValueClients(1)">下一页</button>
						共
						<label id="svn">
							<c:choose>
								<c:when test="${svn ==0}">
									1
								</c:when>
								<c:when test="${svn %5 ==0}">
									<fmt:formatNumber type="number" value="${svn /5}" maxFractionDigits="0"/>
								</c:when>
								<c:otherwise>
									
									<fmt:formatNumber type="number" value="${svn /5 +1 -0.5}" maxFractionDigits="0"/>
								</c:otherwise>
							</c:choose>
						</label>
						页&nbsp&nbsp
						<a href="${pageContext.request.contextPath }/seller/importExcel.action?action=vc&begin=1&size=15" target="_blank">导出Excel</a>
					</p>
				</div>
					


				</div>
			
			
			</div>

			<div class="table-responsive bs-example widget-shadow">
				<h4>潜在客户</h4>
				
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
					
					<tbody id="pcs">
						<c:forEach items="${pros }" var="item" varStatus="i" step="1">
							<tr>
								<td>${i.index +1 }</td>
								<td>${item.cid}</td>
								<td>${item.cname}</td>
								<td>${item.sex}</td>
								<td>${item.birthday}</td>
								<td>${item.job}</td>
								<td>${item.phone}</td>
								<td>${item.eMail}</td>
								<td>${item.address}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<p>
					<button onclick="getProClients(-1)">上一页</button> &nbsp&nbsp
					<input type="text" value="1" id="p_current"/>&nbsp&nbsp
					<button onclick="getProClients(1)">下一页</button>
					共
					<label id="pron">
						<c:choose>
								<c:when test="${pron ==0}">
									1
								</c:when>
								<c:when test="${pron %5 ==0}">
									<fmt:formatNumber type="number" value="${pron /5}" maxFractionDigits="0"/>
								</c:when>
								<c:otherwise>
									<fmt:formatNumber type="number" value="${pron /5 +1 -0.5}" maxFractionDigits="0"/>  
								</c:otherwise>
							</c:choose>
					</label>
					页&nbsp&nbsp
					<a href="${pageContext.request.contextPath }/seller/importExcel.action?action=pro&begin=1&size=15" target="_blank">导出Excel</a>
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
			var data = [];
			var cnames = [];
			var moneys = [];
			var nums = [];
			<c:forEach items="${vcs }" var="item">
				var cname = "${item.cname}";
				var money = "${item.money}";
				var num = "${item.num}";

				cnames.push(cname);
				moneys.push(money);
				nums.push(num);
			</c:forEach>

			data.push({
				cnames : cnames,
				moneys : moneys,
				nums : nums
			});
			console.info(data);
			console.info(data[0].cnames);

			fillBar(data);
		}

		function getValueClients(_num){
			var _size = 5;
			var _begin = $("#v_current").val();
			console.info(typeof  _begin);
			_begin = parseInt(_begin);
			_begin += _num;;

			var _total = $("#svn").text();
			console.info("n_begin " + _total);
			if(_begin == 0){
				showDialog("已经是第一页了");
				return;
			}else if(_begin > _total){
				showDialog("最后一页了");
			}else{
				$.ajax({
		   			type:"post",
		   			url:"${pageContext.request.contextPath }/seller/getValueClients.action",
		   			data: {begin: _begin, size: _size},
		   			async:true,
		   			success: function(data){
		   				fill(data, _begin,1);
		   				processAndFill(data);
		   			}
		   		});
			}
			
		}

		/* 获取潜在客户 */
		function getProClients(_num){
			var _size = 5;
			var _begin = $("#p_current").val();
			console.info(_begin);
			_begin = parseInt(_begin);
			_begin += _num;;

			var _total = $("#pron").text();
			console.info("_total " + _total);
			if(_begin == 0){
				showDialog("已经是第一页了");
				return;
			}else if(_begin > _total){
				showDialog("最后一页了");
			}else{
				$.ajax({
		   			type:"post",
		   			url:"${pageContext.request.contextPath }/seller/getProClients.action",
		   			data: {begin: _begin, size: _size},
		   			async:true,
		   			success: function(data){
			   			
		   				fill(data, _begin,0);
		   				
		   			}
		   		});
			}
			
		}

		/* 填充表格 */
		function fill(data, _begin, flag){
			
			if(flag == 1){
				$("#v_current").val(_begin);
				var _tb = $("#vcs");
			}else{
				$("#p_current").val(_begin);
				var _tb = $("#pcs");
			}
			_tb.html("");

			for(var i=0; i<data.length; ++i){
				var _tr = $("<tr></tr>");
				$("<td></td>").text(i+1).appendTo(_tr);
				$("<td></td>").text(data[i].cid).appendTo(_tr);
				$("<td></td>").text(data[i].cname).appendTo(_tr);
				$("<td></td>").text(data[i].sex).appendTo(_tr);
				var bir = $("<td></td>");
				if(data[i].birthday == -1){
					bir.text("");
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
   </script>
   
   <script type="text/javascript">

   		function processAndFill(data){
   			var _data = [];
   			var cnames = [];
   			var moneys = [];
   			var nums = [];
   			
   			var length = data.length;

   			for(var i=0; i<length; ++i){

   	   			var cname = data[i].cname;
				var money = data[i].money;
				var num = data[i].num;

				cnames.push(cname);
				moneys.push(money);
				nums.push(num);
   	   	   	}

   	   	   	_data.push({
   	   	   	   	cnames : cnames,
 	   	   	   	moneys : moneys,
   	   	   	   	nums : nums
   	   	   	})
   	   	   	
   	   		fillBar(_data);
   		}
   
   		function fillBar(data){
   			fillV(data);
   			fillN(data);
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
 	
	<!-- echars的柱状图js -->
 	<script type="text/javascript">
      	function fillV(data){
      	  // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main2'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '客户下单量'
                },
                tooltip: {},
                legend: {
                    data: ['单量']
                },
                xAxis: {
                    data: data[0].cnames
                },
                yAxis: {},
                series: [{
                    name: '单量',
                    type: 'bar',
                    data: data[0].nums
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
       	}
    </script>
    
    	<!-- echars的柱状图js -->
 	<script type="text/javascript">
       function fillN(data){
    	   // 基于准备好的dom，初始化echarts实例
           var myChart = echarts.init(document.getElementById('main'));

           // 指定图表的配置项和数据
           var option = {
               title: {
                   text: '客户带来的价值'
               },
               tooltip: {},
               legend: {
                   data:['利润']
               },
               xAxis: {
                   data: data[0].cnames
               },
               yAxis: {},
               series: [{
                   name: '利润',
                   type: 'bar',
                   data: data[0].moneys
               }]
           };

           // 使用刚指定的配置项和数据显示图表。
           myChart.setOption(option);
       }
    </script>
</body>
</html>