<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<!-- <script src="webjars/jquery/3.1.1/jquery.min.js"></script> -->
<script src="/js/jquery.js"></script>
<!-- <script src=”http://code.jquery.com/jquery-2.1.1.min.js”></script> -->

<style type="text/css">
	#main {margin: auto; width: 100%;}
	#title {text-align: center}
	.title {margin: auto; width: 30%;}
	.myHide {display : none}
	table {
            width: 100%;
            font-size: .938em;
            border-collapse: collapse;/*边框会合并为一个单一的边框*/
        }
	       table {
            width: 100%;
            font-size: .938em;
            border-collapse: collapse;/*边框会合并为一个单一的边框*/
        }
        caption {
            margin: 1em 0 .7em 0;
            text-align: center;
            font-weight: bold;
            font-size: 120%;
            letter-spacing: .5px;
            color: #fff;
        }

        th {
            text-align: left;
            padding: .5em .5em;
            font-weight: bold;
            background: #66677c;color: #fff;
        }

        td {
            padding: .5em .5em;
            border-bottom: solid 1px #ccc;
        }

        table,table tr th, table tr td { border:1px solid #0094ff; }/*设置边框的*/
</style>
</head>

<body>

	<div id="main">
	
		<h2 id="title">课室使用情况</h2>
		<div class="title">
			<form id="myform" method="post" onsubmit="return getCRUseState();">
				<p>
					<label>校区</label>
					<select id="xq" name="cr.xq" onchange="getJxl(this.value);" required="required">
						<option></option>
						<option value="白云校区">白云校区</option>
						<option value="海珠校区">海珠校区</option>
					</select>
					
					&nbsp<label>教学楼</label>
					<select id="jxl" name="cr.jxl" onchange="getRoom(this.value);" required="required"></select>
					
					&nbsp<label>课室</label>
					<select id="ks" name="cr.ks" required="required"></select>
				</p>
				
				
				<p>
					&nbsp<label>学期</label>
					<select id="term" name="term" onchange="getWeeks(this.value);" required="required">
						<option></option>
						<c:forEach items="${terms }" var="term">
							<option value="${term }">${term }</option>
						</c:forEach>
					
					</select>
					
					&nbsp<label>周次</label>
					<select id="weeks" name="weeks" required="required"></select>
					
					<input type="submit" value="查询"/> 
				</p>
				
			</form>
		
		</div>
	
		<div>
			<table>
				<thead>
					<tr>
						<td>&nbsp</td>
						<td>星期一</td>
						<td>星期二</td>
						<td>星期三</td>
						<td>星期四</td>
						<td>星期五</td>
						<td>星期六</td>
						<td>星期日</td>
					</tr>
				</thead>
				<tbody id="tb"></tbody>
			</table>
			
		</div>
	
	</div>
	
	

	
	<script type="text/javascript">
		function getJxl(xq){
			$.ajax({
	   			type:"post",
	   			url:"${pageContext.request.contextPath }/classroom/getJxl.action",
	   			data: {xq: xq},
	   			async:true,
	   			success: function(data){
		   			fill($("#jxl"), data);
		   			getRoom($("#jxl option:selected").attr("value"));
	   			}
	   		});
		}

		function getRoom(jxl){
			var xq = $("#xq option:selected").attr("value");
			$.ajax({
	   			type:"post",
	   			url:"${pageContext.request.contextPath }/classroom/getKs.action",
	   			data: {xq: xq, jxl: jxl},
	   			async:true,
	   			success: function(data){
		   			fill($("#ks"), data);
	   			}
	   		});
		}

		function getWeeks(term){
			$.ajax({
	   			type:"post",
	   			url:"${pageContext.request.contextPath }/classroom/getWeeks.action",
	   			data: {term: term},
	   			async:true,
	   			success: function(data){
		   			var weeks = parseInt(data);
		   			console.info(typeof weeks);

		   			
		   			var _select = $("#weeks");
		   			_select.html("");
		   			for(var i=1; i<= weeks; ++i){
		   				var op = $("<option></option>");
		   				op.val(i);
		   				var title = "第" + i + "周";
						op.text(title);
						op.appendTo(_select);
			   		}
	   			}
	   		});
		}

		function fill(_select, data){
			var length = data.length;
			_select.html("");
			for(var i=0; i<length; ++i){
				var op = $("<option></option>");
				op.val(data[i]);
				op.text(data[i]);
				op.appendTo(_select);
			}
		}

		function getCRUseState(){
			$.ajax({
	   			type:"post",
	   			url:"/classroom/getCRUseState.action",
	   			data: $('#myform').serialize(),
	   			async:true,
	   			success: function(data){
		   			console.info(data);
		   			setTable(data);
	   			}
	   		});
			return false;
			
		}

		function setTable(data){
			var _tb = $("#tb");
			_tb.html("");
			var row = data.length;
			for(var i=0; i<row; ++i){
				var col = data[i].length;
				var _tr = $("<tr></tr>");

				var _jie = $("<td></td>");
				_jie.text("第" + (i+1) + "节");
				_jie.appendTo(_tr);

				
				for(var j=0; j<col; ++j){
					var _td = $("<td></td>");
					var cname = data[i][j].cname;
					if(cname == null){
						_td.html("<br />")
					}else{
						_td.text(cname);
					}
					_td.appendTo(_tr);
				}

				_tr.appendTo(_tb);
			}
		}
	</script>
</body>
</html>