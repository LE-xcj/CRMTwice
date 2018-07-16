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
					
					<div class="table-responsive bs-example widget-shadow">
					<h4 id="action">
						<c:if test="${action == 'vc' }">价值客户</c:if>
						<c:if test="${action != 'vc' }">潜在客户</c:if>
					</h4>
				
					<table class="table table-bordered">
						<thead id="cth">
							<tr>
								<th>#</th>
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
						
						<tbody id="ctb">
							<c:forEach items="${importCS }" var="item" varStatus="i" step="1">
								<tr>
									<td>
										<input type="checkbox" value="${item.cid}"/>
									</td>
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
						<button onclick="getClients(-1)">上一页</button> &nbsp&nbsp
						<input type="text" value="1" id="current"/>&nbsp&nbsp
						<button onclick="getClients(1)">下一页</button>
						共
						<label id="num">
							<c:choose>
								<c:when test="${num ==0}">
									1
								</c:when>
								<c:when test="${num %15 ==0}">
									<fmt:formatNumber type="number" value="${num /15}" maxFractionDigits="0"/>
								</c:when>
								<c:otherwise>
									<fmt:formatNumber type="number" value="${num /15 +1 -0.5}" maxFractionDigits="0"/>
								</c:otherwise>
							</c:choose>
						</label>
						页
						
						<button onclick="downLoad();">导出</button>
		
						<!--以下a标签不需要内容-->
						<a href="" download="客户.xlsx" id="hf"></a>
					</p>
					
					<div style="display: none">
						<table id="save"></table>
					</div>
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

		//一定要在异步请求完下一页之后在展示
		function showPre(_page){
			//显示之前选过的,注意这个contains只是检查文本类容是否包含检测值，举个例子：如果检测文本是1，那么文本13也是符合的
			$("span:contains(" + _page + ")").each(function(){

				//这里还要获取span中的文本内容，也就是页数
				var _text = $(this).html();

				//如果页数是符合，那么就要将之前选过的值再次显示出来
				if(_page == _text){

					//这里是通过id进行匹配哪些被选过
					var _cid = $(this).parent().parent().children("td").eq(1).html();

					//遍历异步请求回来的table数据（注意：已经添加到table中了）
					$("table :checkbox").each(function(key, value){
						if($(value).prop('value') == _cid){
							$(value).attr("checked",true);
						}
					});
					
				}
				
			});
		}


		//记录之前选中的值
		function save(_page){

			//移除当前页中以前的记录，其他页都是没办法通过该页进行改变的
			$("span:contains(" + _page + ")").each(function(){
				var _text = $(this).html();

				//匹配是否当前这页
				if(_page == _text){
					//移除
					$(this).parent().parent().remove();
				}
				
			});

			//更新,重新将当前页的选中的值添加到看不见的table中
			var _save = $("#save");
			var ctb = document.getElementById('ctb');
			$("table :checkbox").each(function(key, value){
				if($(value).prop('checked')){
					var length = ctb.rows[key].cells.length;

					var _tr = $("<tr></tr>");

					//这个span是用于辨别这是哪一页
					var _span = $("<span></span>");
					_span.html(_page);
					
					var _span_td = $("<td></td>");
					_span.appendTo(_span_td);
					_span_td.appendTo(_tr);

					//添加客户数据
					for(var i=2; i<length; ++i){
						var _tdHtml = ctb.rows[key].cells[i].innerHTML;
						var _td = $("<td></td>");
						_td.html(_tdHtml);
						_td.appendTo(_tr);
					}
					_tr.appendTo(_save);
				}

				
			});
		}
	
		function getClients(_num){
			var _size = 15;
			var _begin = $("#current").val();
			var _page = _begin;
			console.info(typeof  _begin);
			_begin = parseInt(_begin);
			_begin += _num;;
		
			var _total = $("#num").text();
			console.info("n_begin " + _total);
			
			if(_begin == 0){
				showDialog("已经是第一页了");
				return;
			}else if(_begin > _total){
				showDialog("最后一页了");
			}else{
				var _title = $("#action").text;

				if('价值客户' == _title){
					var _url = "${pageContext.request.contextPath }/seller/getValueClients.action";
				}else{
					var _url = "${pageContext.request.contextPath }/seller/getProClients.action"
				}
				//记录之前的选中值
				save(_page);
				
				$.ajax({
		   			type:"post",
		   			url: _url,
		   			data: {begin: _begin, size: _size},
		   			async:true,
		   			success: function(data){
		   				fill(data, _begin);

		   				//切换另一页并填充好数据之后，就需要将这页之前选中的数据重新显示
		   				showPre(_begin);
		   			}
		   		});
			}
		}

		function fill(data, _begin){

			$("#current").val(_begin);
			var _tb = $("#ctb");
			_tb.html("");

			for(var i=0; i<data.length; ++i){
				var _tr = $("<tr></tr>");
				var _td = $("<td></td>");
				
				$("<input />").attr("type", "checkbox").attr("value",data[i].cid).appendTo(_td);
				_td.appendTo(_tr);
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
		function showDialog(tip){
	
			$.DialogByZ.Alert({Title: "提示", Content: tip,BtnL:"确定",FunL:alerts});
		}
	
	    function alerts(){
		   
	    	  $.DialogByZ.Close();
	    }
 	</script>


	<!-- 导出Excel -->
	<script src="http://oss.sheetjs.com/js-xlsx/xlsx.full.min.js"></script>
	<script>
	
		var jsono = [{ //测试数据
			"保质期临期预警(天)": "adventLifecycle",
			"商品标题": "title",
			"建议零售价": "defaultPrice",
			"高(cm)": "height",
			"商品描述": "Description",
			"保质期禁售(天)": "lockupLifecycle",
			"商品名称": "skuName"
		}];


		//导出Excel，将之前选中的值添加到jsono
		function addPre(title, jsono){
			var _save = document.getElementById('save');
			var _rows = _save.rows;

			//获取当前页，因为当前这页的之前的数据是不需要导出Excel的，以用户当前操作为准
			var _current = $("#current").val();
			for(var i=0; i<_rows.length; ++i){

				//获取列长
				var _col = _rows[i].cells.length;

				//获取看不见的table中第一列的span的文本
				var _temp = _rows[i].cells[0].innerText;
				
				console.info("this is addPre" + _temp);
				var _obj = {};
				for(var j=1; j<_col; ++j){

					//如果是和当前页一样，那么就不需要
					if(_current == _temp){
						break;
					}else{
						_obj[title[j+1]] = _rows[i].cells[j].innerHTML;
					}
					
				}

				//同理这里也是
				if(_current != _temp){
					jsono.push(_obj);
				}
				
			}
			return jsono;
		}


		var tmpDown; //导出的二进制对象
		
		/* 处理数据 */
		function downLoad(){

			var title = [];
			var cth = document.getElementById("cth");

			var length = cth.rows[0].cells.length;

			//添加属性名，也就是前端表格中的thead的字段名
			for(var i=0; i<length; ++i){
				title.push(cth.rows[0].cells[i].innerHTML);
			}

			var jsono = [];

			//添加之前的选中的值
			jsono = addPre(title, jsono);

			console.info(jsono);
			
			var ctb = document.getElementById('ctb');
			$("table :checkbox").each(function(key, value){
				if($(value).prop('checked')){
					var length = ctb.rows[key].cells.length;

					var _obj = {};
					for(var i=2; i<length; ++i){
						_obj[title[i]] = ctb.rows[key].cells[i].innerHTML;
					}

					jsono.push(_obj);
				}
			});
			
			downloadExl(jsono);
		}
		
		function downloadExl(json, type) {
			var tmpdata = json[0];
			json.unshift({});
			var keyMap = []; //获取keys
			//keyMap =Object.keys(json[0]);
			for(var k in tmpdata) {
				keyMap.push(k);
				json[0][k] = k;
			}
			
			var tmpdata = []; //用来保存转换好的json 
			json.map((v, i) => keyMap.map((k, j) => Object.assign({}, {
				v: v[k],
				position: (j > 25 ? getCharCol(j) : String.fromCharCode(65 + j)) + (i + 1)
			}))).reduce((prev, next) => prev.concat(next)).forEach((v, i) => tmpdata[v.position] = {
				v: v.v
			});
			
			var outputPos = Object.keys(tmpdata); //设置区域,比如表格从A1到D10
			var tmpWB = {
				SheetNames: ['mySheet'], //保存的表标题
				Sheets: {
					'mySheet': Object.assign({},
						tmpdata, //内容
						{
							'!ref': outputPos[0] + ':' + outputPos[outputPos.length - 1] //设置填充区域
						})
				}
			};
			tmpDown = new Blob([s2ab(XLSX.write(tmpWB, { bookType: (type == undefined ? 'xlsx' : type), bookSST: false, type: 'binary' } //这里的数据是用来定义导出的格式类型
			))], {
				type: ""
			}); //创建二进制对象写入转换好的字节流
			
			var href = URL.createObjectURL(tmpDown); //创建对象超链接
			
			document.getElementById("hf").href = href; //绑定a标签
			
			document.getElementById("hf").click(); //模拟点击实现下载
			
			setTimeout(function() { //延时释放
				URL.revokeObjectURL(tmpDown); //用URL.revokeObjectURL()来释放这个object URL
			}, 100);
		}
	
		function s2ab(s) { //字符串转字符流
			var buf = new ArrayBuffer(s.length);
			var view = new Uint8Array(buf);
			for(var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
			return buf;
		}
		
		// 将指定的自然数转换为26进制表示。映射关系：[0-25] -> [A-Z]。
		function getCharCol(n) {
			let temCol = '',
				s = '',
				m = 0
			while(n > 0) {
				m = n % 26 + 1
				s = String.fromCharCode(m + 64) + s
				n = (n - m) / 26
			}
			return s
		}
	</script>
	
</body>
</html>