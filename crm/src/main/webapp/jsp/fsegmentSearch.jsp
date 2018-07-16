<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	
		<h2 id="title">课室空闲段查询</h2>
		<div class="title">
			<form id="myform" method="post" onsubmit="return searchFS();">
				<p>
					<label>学期</label>
					<select id="term" name="term" onchange="getWeeks(this.value);" required="required">
						<option></option>
						<c:forEach items="${terms }" var="term">
							<option value="${term }">${term }</option>
						</c:forEach>
					</select>
					
					&nbsp<label>校区</label>
					<select id="xq" name="xq" onchange="getJxl(this.value);" required="required">
						<option></option>
						<option value="白云校区">白云校区</option>
						<option value="海珠校区">海珠校区</option>
					</select>
					
					&nbsp<label>教学楼</label>
					<select id="jxl" name="jxl" onchange="getRoom(this.value);" required="required"></select>
					
					&nbsp<label>课室</label>
					<select id="ks" name="ks" required="required"></select>
				</p>
				
				
				<p>
					
					<label>周次: 从</label>
					<select id="beginWeek" name="beginWeek" onchange="setEndWeek(this.value)"  required="required"></select>
					
					<label> 到</label>
					<select id="endWeek" name="endWeek"  required="required"></select>
					
				</p>
				
				<p>
					<label>日次: 从</label>
					<select id="beginDay" name="beginDay" onchange="setEndDay(this.value);" required="required"></select>					
					
					
					<label> 到</label>
					<select id="endDay" name="endDay" required="required"></select>
					
					<input type="submit" value="查询"/> 
				</p>
				
			</form>
		
		</div>
		
		<div>
			<table>
				<thead id="th"></thead>
				<tbody id ="tb"></tbody>
			</table>
		</div>
	
	</div>
	
	
	<script type="text/javascript">
		var arr = ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"];
		$(document).ready(function(){
			setBeginDay();
			setEndDay(1);
		});

		function setEndWeek(begin){
			var _endWeek = $("#endWeek");
			_endWeek.html("");
			var end = $("#beginWeek option:last").val();
			
			being = parseInt(begin);
			end = parseInt(end);
			
			for(var i=begin; i<=end; ++i){
				var op = $("<option></option>");
				op.val(i);
				var week = "第" + i + "周";
				op.text(week);
				op.appendTo(_endWeek);
			}
		}
	
		function setBeginDay(){
			var _days = $("#beginDay");
			for(var i=1; i<=7; ++i){
				var op = $("<option></option>");
				op.val(i);
				var day = arr[i-1];
				op.text(day);
				op.appendTo(_days);
			}
		}

		function setEndDay(begin){
			var _endDay = $("#endDay");
			_endDay.html("");
			for(var i=begin; i<=7; ++i){
				var op = $("<option></option>");
				op.val(i);
				var day = arr[i-1];
				op.text(day);
				op.appendTo(_endDay);
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
		   			getRoom($("#jxl option:selected").attr("value"));
	   			}
	   		});
		}

		function getRoom(jxl){
			var xq = $("#xq option:selected").attr("value");
			$.ajax({
	   			type:"post",
	   			url:"/classroom/getKs.action",
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
	   			url:"/classroom/getWeeks.action",
	   			data: {term: term},
	   			async:true,
	   			success: function(data){
		   			var weeks = parseInt(data);
		   			
		   			var _select = $("#beginWeek");
		   			_select.html("");
		   			for(var i=1; i<= weeks; ++i){
		   				var op = $("<option></option>");
		   				op.val(i);
		   				var title = "第" + i + "周";
						op.text(title);
						op.appendTo(_select);
			   		}
			   		var begin = $("#beginWeek option:selected").attr("value");
		   			setEndWeek(begin);
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
		function searchFS(){
			$.ajax({
	   			type:"post",
	   			url:"/classroom/seachFreeSegment.action",
	   			data: $('#myform').serialize(),
	   			async:true,
	   			success: function(data){
		   			showSegment(data);
	   			}
	   		});
	   		return false;
		}

		function showSegment(data){
			
			var _beginDay = $("#beginDay option:selected").attr("value");
			_beginDay = parseInt(_beginDay);
			
			var _beginWeek = parseInt($("#beginWeek option:selected").attr("value"));
			var _endWeek = parseInt($("#endWeek option:selected").attr("value"));
			
			addHead(_beginWeek, _endWeek);
			
			var _tb = $("#tb");
			_tb.html("");
			
			//行的长度
			var dlength = data.length;
			for(var i=0; i<dlength; ++i){
				
				var _tr = $("<tr></tr>");

				var _day = $("<td></td>");
				var _title = "星期" + (i+_beginDay);
				_day.text(_title);
				_day.appendTo(_tr);
				
				//列的长度
				var wlength = data[i].length;
				for(var j=0; j<wlength; ++j){
					
					var _td = $("<td></td>");

					_td.appendTo(_tr);
					
					setJies(data[i][j], _td);
					
				}
				_tr.appendTo(_tb);
			}
			
		}

		function addHead(_beginWeek, _endWeek){
			var _th = $("#th");
			_th.html("");
			var _tr = $("<tr></tr>");
			var _temp = $("<td></td>");
			_temp.text("\n");
			_temp.appendTo(_tr);
			for(var i=_beginWeek; i<=_endWeek; ++i){
				var _td = $("<td></td>");
				var title = "第" + i + "周";
				_td.text(title);
				_td.appendTo(_tr);
			}
			_tr.appendTo(_th);
		}

		function setJies(data, _td){
			var length = data.length;
			var _text = "";
			if(length != 0){
				for(var i=0; i<length; ++i){
					_text += data[i] +"<br/>";
				}
			}else{
				_text = "无";
			}
			_td.html(_text);

			var _flag = _td.text();
			if("1-4节6-12节" == _flag){
				_td.html("");
			}
		}
	</script>
</body>
</html>