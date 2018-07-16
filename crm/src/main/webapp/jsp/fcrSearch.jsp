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
	.title {margin: auto; width: 50%;}
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
	
		<h2 id="title">空课室查询</h2>
		<div class="title">
			<form id="myform" method="post" onsubmit="return searchFCR();">
				<p>
					<label>校区</label>
					<select id="xq" name="cr.xq" onchange="getJxl(this.value);" required="required">
						<option></option>
						<option value="白云校区">白云校区</option>
						<option value="海珠校区">海珠校区</option>
					</select>
					
					&nbsp<label>教学楼</label>
					<select id="jxl" name="cr.jxl" required="required"></select>
					
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
					<select id="weeks" name="weeks"  required="required"></select>
					
					&nbsp<label>日次</label>
					<select id="day" name="day" required="required"></select>
					
					&nbsp<label>开始节次</label>
					<select id="beginJie" name="beginJie" onchange="setEndJie(this.value);" required="required"></select>
					
					
					&nbsp<label>结束节次</label>
					<select id="endJie" name="endJie" required="required"></select>
					
					<input type="submit" value="查询"/> 
				</p>
				
			</form>
		
		</div>
		
		<div>
			<table>
				<tbody id ="tb"></tbody>
			</table>
		</div>
	
	</div>
	
	
	<script type="text/javascript">
		var arr = ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"];
		$(document).ready(function(){
			setDays();
			setBeginJie();
			setEndJie(1);
		});
	
		function setDays(){
			var _days = $("#day");
			for(var i=1; i<=7; ++i){
				var op = $("<option></option>");
				op.val(i);
				var day = arr[i-1];
				op.text(day);
				op.appendTo(_days);
			}
		}

		function setBeginJie(){
			var _beginJie = $("#beginJie");
			for(var i=1; i<=12; ++i){
				var op = $("<option></option>");
				op.val(i);
				var jie = "第" + i + "节";
				op.text(jie);
				op.appendTo(_beginJie);
			}
		}

		function setEndJie(begin){
			var _endJie = $("#endJie");
			_endJie.html("");
			for(var i=begin; i<=12; ++i){
				var op = $("<option></option>");
				op.val(i);
				var jie = "第" + i + "节";
				op.text(jie);
				op.appendTo(_endJie);
			}
		}
	</script>
	
	<script type="text/javascript">

		function getJxl(xq){
			$.ajax({
	   			type:"post",
	   			url:"/classroom/getJxl.action",
	   			data: {xq: xq},
	   			async:true,
	   			success: function(data){
		   			fill($("#jxl"), data);
	   			}
	   		});
		}


		function getWeeks(term){
			$.ajax({
	   			type:"post",
	   			url:"/classroom/getWeeks.action",
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

	</script>
	
	<script type="text/javascript">
		function searchFCR(){
			$.ajax({
				type:"post",
				url:"/classroom/searchFCR.action",
				data: $('#myform').serialize(),
				async:true,
				success: function(data){
		   			showFreeRoom(data);
				}
			});
			return false;
		}

		function showFreeRoom(data){
			var floors = data.length;
			var _tb = $("#tb");
			_tb.html("");
			for(var i=1; i<floors; ++i){
				var rooms = data[i].length;
				var _tr = $("<tr></tr>");
				for(var j=0; j<rooms; ++j){
					var _td = $("<td></td>");
					_td.text(data[i][j]);
					_td.appendTo(_tr);
				}
				_tr.appendTo(_tb);
			}
			
		}
	</script>
</body>
</html>