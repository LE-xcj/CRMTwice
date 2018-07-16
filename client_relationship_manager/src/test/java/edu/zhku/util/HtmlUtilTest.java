/**
 * 
 */
package edu.zhku.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**  
* <p>Title: HtmlUtilTest.java</p>  
* <p>Description: </p>  
* 
* @author xcj
* @date 2018年6月19日  
*/
public class HtmlUtilTest {

	/**
	 * Test method for {@link edu.zhku.util.HtmlUtil#addHtmlTag(java.lang.String)}.
	 */
	
	String tags = "<head>\r\n" + 
			"	<style>\r\n" + 
			"		p {\r\n" + 
			"			margin-top: 0pt;\r\n" + 
			"			margin-bottom: 1pt;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		p.a {\r\n" + 
			"			text-align: justified;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		span.a {\r\n" + 
			"			font-family: 'Calibri';\r\n" + 
			"			font-size: 10.0pt;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		p.a3 {\r\n" + 
			"			text-align: left;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		span.a3 {\r\n" + 
			"			font-size: 9.0pt;\r\n" + 
			"		}\r\n" + 
			"	</style>\r\n" + 
			"	<meta charset=\"UTF-8\">\r\n" + 
			"	<style type=\"text/css\">\r\n" + 
			"		/* page styles */\r\n" + 
			"		\r\n" + 
			"		body {\r\n" + 
			"			font-family: \"Segoe UI\", Frutiger, Tahoma, Helvetica, \"Helvetica Neue\", Arial, sans-serif;\r\n" + 
			"			font-size: 62.5%;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		table {\r\n" + 
			"			border-collapse: collapse;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		td,\r\n" + 
			"		th {\r\n" + 
			"			text-align: center;\r\n" + 
			"			border: 1px solid #ddd;\r\n" + 
			"			padding: 2px 5px;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		caption {\r\n" + 
			"			margin: 0 0 .5em;\r\n" + 
			"			font-weight: bold;\r\n" + 
			"		}\r\n" + 
			"		/*main styles*/\r\n" + 
			"		\r\n" + 
			"		table {\r\n" + 
			"			width: 500px;\r\n" + 
			"			height: 200px;\r\n" + 
			"			margin-left: 30px;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		td,\r\n" + 
			"		th {\r\n" + 
			"			font-size: 1.2em;\r\n" + 
			"			padding: 2px;\r\n" + 
			"			width: 13%;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		th {\r\n" + 
			"			background-color: #f4f4f4;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		caption {\r\n" + 
			"			font-size: 1.5em;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		table {\r\n" + 
			"			float: left;\r\n" + 
			"			margin: 40px 40px 0 0;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		.main {\r\n" + 
			"			width: 500px;\r\n" + 
			"		}\r\n" + 
			"		/* input */\r\n" + 
			"		\r\n" + 
			"		td.input {\r\n" + 
			"			padding: 0;\r\n" + 
			"			position: relative;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		td textarea {\r\n" + 
			"			border: 1px solid black;\r\n" + 
			"			background: white;\r\n" + 
			"			/*-webkit-border-radius: 5px;\r\n" + 
			"				-moz-border-radius: 5px;*/\r\n" + 
			"			border-radius: 1px;\r\n" + 
			"			position: absolute;\r\n" + 
			"			overflow: hidden;\r\n" + 
			"			/*				padding: 8px 0;*/\r\n" + 
			"			/*				text-align: right;*/\r\n" + 
			"			width: 60px;\r\n" + 
			"			font-size: 1.4em;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		td.textarea {\r\n" + 
			"			padding: 0;\r\n" + 
			"			position: relative;\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		td.hover {\r\n" + 
			"			background: #eee;\r\n" + 
			"		}\r\n" + 
			"	</style>\r\n" + 
			"	<script src=\"https://code.jquery.com/jquery-3.3.1.min.js\"></script>\r\n" + 
			"	<script type=\"text/javascript\">\r\n" + 
			"		$(function() {\r\n" + 
			"			$('table td').click(function() {\r\n" + 
			"				if(!$(this).is('.textarea')) {\r\n" + 
			"					if(!$(this).is('.textarea')) {\r\n" + 
			"						var p_html = $(this).html();\r\n" + 
			"						var ta = $(\"<textarea></textarea>\");\r\n" + 
			"						var d = $(\"<div></div>\");\r\n" + 
			"						$(this).addClass(\"textarea\").append(ta);\r\n" + 
			"						setTextArea($(this), ta);\r\n" + 
			"						var v = $(this).text();\r\n" + 
			"\r\n" + 
			"						ta.val(v);\r\n" + 
			"\r\n" + 
			"						ta.focus().blur(function() {\r\n" + 
			"							$(this).parent().removeClass('textarea');\r\n" + 
			"							console.info($(this).html());\r\n" + 
			"							$(this).parent().html($(this).val());\r\n" + 
			"\r\n" + 
			"						});\r\n" + 
			"\r\n" + 
			"					}\r\n" + 
			"				}\r\n" + 
			"			}).hover(function() {\r\n" + 
			"				$(this).addClass('hover');\r\n" + 
			"			}, function() {\r\n" + 
			"				$(this).removeClass('hover');\r\n" + 
			"			});\r\n" + 
			"		});\r\n" + 
			"\r\n" + 
			"		function setTextArea(_td, _ta) {\r\n" + 
			"			var td_width = _td.width();\r\n" + 
			"			var td_heigth = _td.height();\r\n" + 
			"\r\n" + 
			"			var fs = _td.css(\"font-size\");\r\n" + 
			"			_ta.css(\"width\", td_width * 1.5);\r\n" + 
			"			_ta.css(\"height\", td_heigth * 1.5);\r\n" + 
			"			_ta.css(\"font-size\", fs);\r\n" + 
			"		}\r\n" + 
			"\r\n" + 
			"		function getHtml() {\r\n" + 
			"			var htm = $(\"#main\").html();\r\n" + 
			"			$(\"#ta_bh\").val(htm);\r\n" + 
			"			var tm = $(\"#ta_bh\").val();\r\n" + 
			"			console.info(tm);\r\n" + 
			"			return true;\r\n" + 
			"		}\r\n" + 
			"	</script>\r\n" + 
			"	<style class=\"blockbyte-bs-style\" data-name=\"content\">\r\n" + 
			"		body>div#blockbyte-bs-indicator>div {\r\n" + 
			"			opacity: 0;\r\n" + 
			"			pointer-events: none\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>iframe#blockbyte-bs-sidebar.blockbyte-bs-visible,\r\n" + 
			"		body>iframe#blockbyte-bs-overlay.blockbyte-bs-visible {\r\n" + 
			"			opacity: 1;\r\n" + 
			"			pointer-events: auto\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body.blockbyte-bs-noscroll {\r\n" + 
			"			overflow: hidden !important\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>div#blockbyte-bs-indicator>div {\r\n" + 
			"			position: absolute;\r\n" + 
			"			transform: translate3d(-40px, 0, 0);\r\n" + 
			"			top: 0;\r\n" + 
			"			left: 0;\r\n" + 
			"			width: 40px !important;\r\n" + 
			"			height: 100%;\r\n" + 
			"			background: rgba(0, 0, 0, 0.5);\r\n" + 
			"			border-radius: 0 10px 10px 0;\r\n" + 
			"			transition: opacity 0.3s, transform 0.3s;\r\n" + 
			"			z-index: 2\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>div#blockbyte-bs-indicator>div>span {\r\n" + 
			"			-webkit-mask: no-repeat center/32px;\r\n" + 
			"			-webkit-mask-image: url(chrome-extension://jdbnofccmhefkmjbkkdkfiicjkgofkdh/img/icon-bookmark.svg);\r\n" + 
			"			background-color: #ffffff;\r\n" + 
			"			position: absolute;\r\n" + 
			"			display: block;\r\n" + 
			"			top: 0;\r\n" + 
			"			left: 0;\r\n" + 
			"			width: 100%;\r\n" + 
			"			height: 100%\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>div#blockbyte-bs-indicator[data-pos='right'] {\r\n" + 
			"			left: auto;\r\n" + 
			"			right: 0\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>div#blockbyte-bs-indicator[data-pos='right']>div {\r\n" + 
			"			transform: translate3d(40px, 0, 0);\r\n" + 
			"			left: auto;\r\n" + 
			"			right: 0;\r\n" + 
			"			border-radius: 10px 0 0 10px\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>div#blockbyte-bs-indicator.blockbyte-bs-fullHeight>div {\r\n" + 
			"			border-radius: 0\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>div#blockbyte-bs-indicator.blockbyte-bs-hover>div {\r\n" + 
			"			transform: translate3d(0, 0, 0);\r\n" + 
			"			opacity: 1\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>div#blockbyte-bs-indicator[data-pos='left'].blockbyte-bs-has-lsb {\r\n" + 
			"			height: 100% !important;\r\n" + 
			"			top: 0 !important\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>div#blockbyte-bs-indicator[data-pos='left'].blockbyte-bs-has-lsb>div {\r\n" + 
			"			background: transparent\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>div#blockbyte-bs-indicator[data-pos='left'].blockbyte-bs-has-lsb>div>span {\r\n" + 
			"			-webkit-mask-position-y: 20px\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>iframe#blockbyte-bs-sidebar {\r\n" + 
			"			width: 240px;\r\n" + 
			"			max-width: none;\r\n" + 
			"			height: 0;\r\n" + 
			"			z-index: 2147483646;\r\n" + 
			"			background-color: rgba(0, 0, 0, 0.6) !important;\r\n" + 
			"			border: none;\r\n" + 
			"			display: block !important;\r\n" + 
			"			transform: translate3d(-240px, 0, 0);\r\n" + 
			"			transition: width 0s 0.3s, height 0s 0.3s, opacity 0.3s, transform 0.3s\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>iframe#blockbyte-bs-sidebar[data-pos='right'] {\r\n" + 
			"			left: auto;\r\n" + 
			"			right: 0;\r\n" + 
			"			transform: translate3d(240px, 0, 0)\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>iframe#blockbyte-bs-sidebar.blockbyte-bs-visible {\r\n" + 
			"			width: calc(100% + 240px);\r\n" + 
			"			height: 100%;\r\n" + 
			"			transform: translate3d(0, 0, 0);\r\n" + 
			"			transition: opacity 0.3s, transform 0.3s\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>iframe#blockbyte-bs-sidebar.blockbyte-bs-hideMask {\r\n" + 
			"			background: none !important\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>iframe#blockbyte-bs-sidebar.blockbyte-bs-hideMask:not(.blockbyte-bs-hover) {\r\n" + 
			"			width: calc(240px + 50px)\r\n" + 
			"		}\r\n" + 
			"		\r\n" + 
			"		body>iframe#blockbyte-bs-overlay {\r\n" + 
			"			width: 100%;\r\n" + 
			"			max-width: none;\r\n" + 
			"			height: 100%;\r\n" + 
			"			z-index: 2147483647;\r\n" + 
			"			border: none;\r\n" + 
			"			background: rgba(0, 0, 0, 0.5) !important;\r\n" + 
			"			transition: opacity 0.3s\r\n" + 
			"		}\r\n" + 
			"	</style>\r\n" + 
			"</head>\r\n" + 
			"\r\n" + 
			"<body>\r\n" + 
			"	<div>\r\n" + 
			"		<div style=\"width:595.0pt;margin-bottom:56.0pt;margin-top:72.0pt;margin-left:85.0pt;margin-right:85.0pt;\">\r\n" + 
			"			<p><span style=\"font-family:'宋体';font-size:12.0pt;font-weight:bold;\">附件</span><span style=\"font-family:'宋体';font-size:12.0pt;font-weight:bold;\">1</span><span style=\"font-family:'宋体';font-size:12.0pt;font-weight:bold;\">：</span></p>\r\n" + 
			"			<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:16.0pt;font-weight:bold;\">仲恺农业工程学院假期留校住宿申请表</span></p>\r\n" + 
			"			<table style=\"width:449.2pt;border-collapse:collapse;\">\r\n" + 
			"				<tbody>\r\n" + 
			"					<tr>\r\n" + 
			"						<td style=\"width:50.2pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">学院</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:84.25pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">计算科学学院</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:50.95pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">班级</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:50.5pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">信计1</span><span style=\"font-family:'宋体';font-size:12.0pt;\">51</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:64.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">学号</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:63.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">2</span><span style=\"font-family:'宋体';font-size:12.0pt;\">01521314109</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:35.45pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">校区</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:49.5pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">白云</span></p>\r\n" + 
			"						</td>\r\n" + 
			"					</tr>\r\n" + 
			"					<tr>\r\n" + 
			"						<td style=\"width:50.2pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">姓名</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:84.25pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">徐楚健</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:50.95pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">性别</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:50.5pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">男</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:64.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">\r\n" + 
			"							<p style=\"text-align:left;text-indent:1.0pt;text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">联系电话</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:148.75pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"4\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">13632495507</span></p>\r\n" + 
			"						</td>\r\n" + 
			"					</tr>\r\n" + 
			"					<tr>\r\n" + 
			"						<td style=\"width:50.2pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">宿舍</span></p>\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">房号</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:84.25pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">1</span><span style=\"font-family:'宋体';font-size:12.0pt;\">6</span><span style=\"font-family:'宋体';font-size:12.0pt;\">栋1</span><span style=\"font-family:'宋体';font-size:12.0pt;\">013</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:50.95pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">辅导员</span></p>\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">姓</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">名</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:72.0pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">徐盼</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:63.0pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">辅导员</span></p>\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">联系电话</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:128.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"3\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">1</span><span style=\"font-family:'宋体';font-size:12.0pt;\">8819266062</span></p>\r\n" + 
			"						</td>\r\n" + 
			"					</tr>\r\n" + 
			"					<tr>\r\n" + 
			"						<td style=\"width:50.2pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">家庭</span></p>\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">地址</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:399.0pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"10\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">广州市</span><span style=\"font-family:'宋体';font-size:12.0pt;\">萝岗区宏岗南街</span><span style=\"font-family:'宋体';font-size:12.0pt;\">2</span><span style=\"font-family:'宋体';font-size:12.0pt;\">0</span><span style=\"font-family:'宋体';font-size:12.0pt;\">号</span></p>\r\n" + 
			"						</td>\r\n" + 
			"					</tr>\r\n" + 
			"					<tr>\r\n" + 
			"						<td style=\"width:50.2pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">留校住宿时间</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:399.0pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"10\" class=\"\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">2</span><span style=\"font-family:'宋体';font-size:12.0pt;\">018</span><span style=\"font-family:'宋体';font-size:12.0pt;\">年</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">7</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">月</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">16</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">日至</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">8</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">月</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">25</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">日</span></p>\r\n" + 
			"						</td>\r\n" + 
			"					</tr>\r\n" + 
			"					<tr>\r\n" + 
			"						<td style=\"width:32.4pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">申请理由</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:416.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"11\">\r\n" + 
			"							<p><br></p>\r\n" + 
			"							<p><span style=\"font-family:'宋体';font-size:12.0pt;\">学习、实习</span></p>\r\n" + 
			"							<p><br></p>\r\n" + 
			"							<p><br></p>\r\n" + 
			"							<p><br></p>\r\n" + 
			"							<p><br></p>\r\n" + 
			"							<p style=\"text-align:right;text-indent:24.0pt;text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">申请人</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">：　</span><span style=\"font-family:'宋体';font-size:12.0pt;\">徐楚健</span><span style=\"font-family:'宋体';font-size:12.0pt;\">　</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">2018</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">年</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">6</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">月</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">13</span><span style=\"font-family:'宋体';font-size:12.0pt;\">日</span></p>\r\n" + 
			"						</td>\r\n" + 
			"					</tr>\r\n" + 
			"					<tr>\r\n" + 
			"						<td style=\"width:67.0pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"3\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">家长知情</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:382.2pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"9\">\r\n" + 
			"							<p style=\"text-indent:24.0pt;\"><br></p>\r\n" + 
			"							<p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">家长联系方式：</span><span style=\"font-family:'宋体';font-size:12.0pt;\">_</span><span style=\"font-family:'宋体';font-size:12.0pt;text-decoration:underline;\">_</span><span style=\"font-family:'宋体';font-size:12.0pt;text-decoration:underline;\">13414119962</span><span style=\"font-family:'宋体';font-size:12.0pt;text-decoration:underline;\">_</span><span style=\"font-family:'宋体';font-size:12.0pt;\">_____</span></p>\r\n" + 
			"							<p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">是否已征得家长同意</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">：</span><span style=\"font-family:'宋体';font-size:12.0pt;\">____</span><span style=\"font-family:'宋体';font-size:12.0pt;\">是</span><span style=\"font-family:'宋体';font-size:12.0pt;\">______(</span><span style=\"font-family:'宋体';font-size:12.0pt;\">请填写是或否</span><span style=\"font-family:'宋体';font-size:12.0pt;\">)</span></p>\r\n" + 
			"							<p style=\"text-indent:24.0pt;\"></p>\r\n" + 
			"						</td>\r\n" + 
			"					</tr>\r\n" + 
			"					<tr>\r\n" + 
			"						<td style=\"width:32.4pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">申请人</span></p>\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">承</span></p>\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">诺</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:416.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"11\" class=\"\">\r\n" + 
			"							<p style=\"text-indent:24.0pt;\"><br></p>\r\n" + 
			"							<p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">申请人承诺：</span></p>\r\n" + 
			"							<p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">1.</span><span style=\"font-family:'宋体';font-size:12.0pt;\">经家长同意，本人申请留校住宿；</span></p>\r\n" + 
			"							<p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">2.</span><span style=\"font-family:'宋体';font-size:12.0pt;\">自觉遵守国家法律法规、校纪校规，服从学校宿舍安排和管理；</span></p>\r\n" + 
			"							<p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">3.</span><span style=\"font-family:'宋体';font-size:12.0pt;\">提高安全意识，杜绝安全隐患，不使用违章电器，不晚归，不留宿异性或校外人员；</span></p>\r\n" + 
			"							<p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">4.</span><span style=\"font-family:'宋体';font-size:12.0pt;\">违反相关规定而发生的后果由本人负责。</span></p>\r\n" + 
			"							<p style=\"text-align:right;text-indent:24.0pt;text-align:right;\"><br></p>\r\n" + 
			"							<p style=\"text-align:right;text-indent:24.0pt;text-align:right;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">承诺人签字</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">：</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span id=\"_GoBack\"></span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">2</span><span style=\"font-family:'宋体';font-size:12.0pt;\">018</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">年</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">6</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">月</span><span style=\"font-family:'宋体';font-size:12.0pt;\"> </span><span style=\"font-family:'宋体';font-size:12.0pt;\">13</span><span style=\"font-family:'宋体';font-size:12.0pt;\">日</span></p>\r\n" + 
			"							<p style=\"text-align:right;text-indent:24.0pt;text-align:right;\"></p>\r\n" + 
			"						</td>\r\n" + 
			"					</tr>\r\n" + 
			"					<tr>\r\n" + 
			"						<td style=\"width:32.4pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\">\r\n" + 
			"							<p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">二级学院意见</span></p>\r\n" + 
			"						</td>\r\n" + 
			"						<td style=\"width:416.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"11\" class=\"\">加油 签字盖章： 　 　2018 年 月 日</td>\r\n" + 
			"					</tr>\r\n" + 
			"				</tbody>\r\n" + 
			"			</table>\r\n" + 
			"			<p><br></p>\r\n" + 
			"		</div>\r\n" + 
			"		<div style=\"width:595.0pt;margin-bottom:72.0pt;margin-top:72.0pt;margin-left:90.0pt;margin-right:90.0pt;\">\r\n" + 
			"			<p></p>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"	<form action=\"#\" method=\"post\" onsubmit=\"return getHtml();\">\r\n" + 
			"		<textarea id=\"ta_bh\" name=\"bh\" style=\"display: none;\"></textarea>\r\n" + 
			"		<input type=\"text\" placeholder=\"金额\" name=\"money\">\r\n" + 
			"		<input type=\"text\" placeholder=\"客户id\" name=\"cid\">\r\n" + 
			"		<input value=\"提交\" type=\"submit\">\r\n" + 
			"		<a href=\"javascript:window.opener=null;window.open('','_self');window.close();\">关闭</a>\r\n" + 
			"		<a href=\"#\">下载</a>\r\n" + 
			"	</form>\r\n" + 
			"\r\n" + 
			"	<iframe id=\"blockbyte-bs-sidebar\" data-pos=\"left\"></iframe>\r\n" + 
			"	<div id=\"blockbyte-bs-indicator\" class=\"blockbyte-bs-fullHeight\" style=\"width: 22px; height: 100%; top: 0%;\"></div><span style=\"border-radius: 3px !important; text-indent: 20px !important; width: auto !important; padding: 0px 4px 0px 0px !important; text-align: center !important; font-style: normal !important; font-variant: normal !important; font-stretch: normal !important; font-size: 11px !important; line-height: 20px !important; font-family: &quot;Helvetica Neue&quot;, Helvetica, sans-serif !important; color: rgb(255, 255, 255) !important; background: url(&quot;data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIGhlaWdodD0iMzBweCIgd2lkdGg9IjMwcHgiIHZpZXdCb3g9Ii0xIC0xIDMxIDMxIj48Zz48cGF0aCBkPSJNMjkuNDQ5LDE0LjY2MiBDMjkuNDQ5LDIyLjcyMiAyMi44NjgsMjkuMjU2IDE0Ljc1LDI5LjI1NiBDNi42MzIsMjkuMjU2IDAuMDUxLDIyLjcyMiAwLjA1MSwxNC42NjIgQzAuMDUxLDYuNjAxIDYuNjMyLDAuMDY3IDE0Ljc1LDAuMDY3IEMyMi44NjgsMC4wNjcgMjkuNDQ5LDYuNjAxIDI5LjQ0OSwxNC42NjIiIGZpbGw9IiNmZmYiIHN0cm9rZT0iI2ZmZiIgc3Ryb2tlLXdpZHRoPSIxIj48L3BhdGg+PHBhdGggZD0iTTE0LjczMywxLjY4NiBDNy41MTYsMS42ODYgMS42NjUsNy40OTUgMS42NjUsMTQuNjYyIEMxLjY2NSwyMC4xNTkgNS4xMDksMjQuODU0IDkuOTcsMjYuNzQ0IEM5Ljg1NiwyNS43MTggOS43NTMsMjQuMTQzIDEwLjAxNiwyMy4wMjIgQzEwLjI1MywyMi4wMSAxMS41NDgsMTYuNTcyIDExLjU0OCwxNi41NzIgQzExLjU0OCwxNi41NzIgMTEuMTU3LDE1Ljc5NSAxMS4xNTcsMTQuNjQ2IEMxMS4xNTcsMTIuODQyIDEyLjIxMSwxMS40OTUgMTMuNTIyLDExLjQ5NSBDMTQuNjM3LDExLjQ5NSAxNS4xNzUsMTIuMzI2IDE1LjE3NSwxMy4zMjMgQzE1LjE3NSwxNC40MzYgMTQuNDYyLDE2LjEgMTQuMDkzLDE3LjY0MyBDMTMuNzg1LDE4LjkzNSAxNC43NDUsMTkuOTg4IDE2LjAyOCwxOS45ODggQzE4LjM1MSwxOS45ODggMjAuMTM2LDE3LjU1NiAyMC4xMzYsMTQuMDQ2IEMyMC4xMzYsMTAuOTM5IDE3Ljg4OCw4Ljc2NyAxNC42NzgsOC43NjcgQzEwLjk1OSw4Ljc2NyA4Ljc3NywxMS41MzYgOC43NzcsMTQuMzk4IEM4Ljc3NywxNS41MTMgOS4yMSwxNi43MDkgOS43NDksMTcuMzU5IEM5Ljg1NiwxNy40ODggOS44NzIsMTcuNiA5Ljg0LDE3LjczMSBDOS43NDEsMTguMTQxIDkuNTIsMTkuMDIzIDkuNDc3LDE5LjIwMyBDOS40MiwxOS40NCA5LjI4OCwxOS40OTEgOS4wNCwxOS4zNzYgQzcuNDA4LDE4LjYyMiA2LjM4NywxNi4yNTIgNi4zODcsMTQuMzQ5IEM2LjM4NywxMC4yNTYgOS4zODMsNi40OTcgMTUuMDIyLDYuNDk3IEMxOS41NTUsNi40OTcgMjMuMDc4LDkuNzA1IDIzLjA3OCwxMy45OTEgQzIzLjA3OCwxOC40NjMgMjAuMjM5LDIyLjA2MiAxNi4yOTcsMjIuMDYyIEMxNC45NzMsMjIuMDYyIDEzLjcyOCwyMS4zNzkgMTMuMzAyLDIwLjU3MiBDMTMuMzAyLDIwLjU3MiAxMi42NDcsMjMuMDUgMTIuNDg4LDIzLjY1NyBDMTIuMTkzLDI0Ljc4NCAxMS4zOTYsMjYuMTk2IDEwLjg2MywyNy4wNTggQzEyLjA4NiwyNy40MzQgMTMuMzg2LDI3LjYzNyAxNC43MzMsMjcuNjM3IEMyMS45NSwyNy42MzcgMjcuODAxLDIxLjgyOCAyNy44MDEsMTQuNjYyIEMyNy44MDEsNy40OTUgMjEuOTUsMS42ODYgMTQuNzMzLDEuNjg2IiBmaWxsPSIjYmQwODFjIj48L3BhdGg+PC9nPjwvc3ZnPg==&quot;) 3px 50% / 14px 14px no-repeat rgb(189, 8, 28) !important; position: absolute !important; opacity: 1 !important; z-index: 8675309 !important; display: none; cursor: pointer !important; border: none !important; font-weight: bold !important; -webkit-font-smoothing: antialiased !important;\">Save</span><span style=\"border-radius: 12px; width: 24px !important; height: 24px !important; background: url(&quot;data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pjxzdmcgd2lkdGg9IjI0cHgiIGhlaWdodD0iMjRweCIgdmlld0JveD0iMCAwIDI0IDI0IiB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiPjxkZWZzPjxtYXNrIGlkPSJtIj48cmVjdCBmaWxsPSIjZmZmIiB4PSIwIiB5PSIwIiB3aWR0aD0iMjQiIGhlaWdodD0iMjQiIHJ4PSI2IiByeT0iNiIvPjxyZWN0IGZpbGw9IiMwMDAiIHg9IjUiIHk9IjUiIHdpZHRoPSIxNCIgaGVpZ2h0PSIxNCIgcng9IjEiIHJ5PSIxIi8+PHJlY3QgZmlsbD0iIzAwMCIgeD0iMTAiIHk9IjAiIHdpZHRoPSI0IiBoZWlnaHQ9IjI0Ii8+PHJlY3QgZmlsbD0iIzAwMCIgeD0iMCIgeT0iMTAiIHdpZHRoPSIyNCIgaGVpZ2h0PSI0Ii8+PC9tYXNrPjwvZGVmcz48cmVjdCB4PSIwIiB5PSIwIiB3aWR0aD0iMjQiIGhlaWdodD0iMjQiIGZpbGw9IiNmZmYiIG1hc2s9InVybCgjbSkiLz48L3N2Zz4=&quot;) 50% 50% / 14px 14px no-repeat rgba(0, 0, 0, 0.4) !important; position: absolute !important; opacity: 1 !important; z-index: 8675309 !important; display: none; cursor: pointer !important; border: none !important;\"></span>\r\n" + 
			"	<div id=\"SL_balloon_obj\" alt=\"0\" style=\"display: block;\">\r\n" + 
			"		<div id=\"SL_button\" class=\"SL_ImTranslatorLogo\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/imtranslator-s.png&quot;); display: none;\"></div>\r\n" + 
			"		<div id=\"SL_shadow_translation_result2\" style=\"display: none;\"></div>\r\n" + 
			"		<div id=\"SL_shadow_translator\" style=\"display: none;\">\r\n" + 
			"			<div id=\"SL_planshet\">\r\n" + 
			"				<div id=\"SL_arrow_up\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/up.png&quot;);\"></div>\r\n" + 
			"				<div id=\"SL_Bproviders\">\r\n" + 
			"					<div class=\"SL_BL_LABLE_ON\" title=\"Google\" id=\"SL_P0\">G</div>\r\n" + 
			"					<div class=\"SL_BL_LABLE_ON\" title=\"Microsoft\" id=\"SL_P1\">M</div>\r\n" + 
			"					<div class=\"SL_BL_LABLE_ON\" title=\"Translator\" id=\"SL_P2\">T</div>\r\n" + 
			"				</div>\r\n" + 
			"				<div id=\"SL_alert_bbl\" style=\"display: none;\">\r\n" + 
			"					<div id=\"SLHKclose\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/delete.png&quot;);\"></div>\r\n" + 
			"					<div id=\"SL_alert_cont\"></div>\r\n" + 
			"				</div>\r\n" + 
			"				<div id=\"SL_TB\">\r\n" + 
			"					<table id=\"SL_tables\" cellspacing=\"1\">\r\n" + 
			"						<tr>\r\n" + 
			"							<td class=\"SL_td\" width=\"10%\" align=\"right\"><input id=\"SL_locer\" type=\"checkbox\" title=\"锁定语言\"></td>\r\n" + 
			"							<td class=\"SL_td\" width=\"20%\" align=\"left\">\r\n" + 
			"								<select id=\"SL_lng_from\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/select.png&quot;) 100% 0px no-repeat rgb(255, 255, 255);\">\r\n" + 
			"									<option value=\"auto\">检测语言</option>\r\n" + 
			"									<option value=\"eo\">世界语</option>\r\n" + 
			"									<option value=\"zh-CN\">中文简体</option>\r\n" + 
			"									<option value=\"zh-TW\">中文繁体</option>\r\n" + 
			"									<option value=\"da\">丹麦语</option>\r\n" + 
			"									<option value=\"uk\">乌克兰语</option>\r\n" + 
			"									<option value=\"uz\">乌兹别克语</option>\r\n" + 
			"									<option value=\"ur\">乌尔都语</option>\r\n" + 
			"									<option value=\"hy\">亚美尼亚语</option>\r\n" + 
			"									<option value=\"ig\">伊博语</option>\r\n" + 
			"									<option value=\"ru\">俄语</option>\r\n" + 
			"									<option value=\"bg\">保加利亚语</option>\r\n" + 
			"									<option value=\"si\">僧伽罗语</option>\r\n" + 
			"									<option value=\"hr\">克罗地亚语</option>\r\n" + 
			"									<option value=\"is\">冰岛语</option>\r\n" + 
			"									<option value=\"gl\">加利西亚语</option>\r\n" + 
			"									<option value=\"ca\">加泰罗尼亚语</option>\r\n" + 
			"									<option value=\"hu\">匈牙利语</option>\r\n" + 
			"									<option value=\"zu\">南非祖鲁语</option>\r\n" + 
			"									<option value=\"kn\">卡纳达语</option>\r\n" + 
			"									<option value=\"hi\">印地语</option>\r\n" + 
			"									<option value=\"su\">印尼巽他语</option>\r\n" + 
			"									<option value=\"jw\">印尼爪哇语</option>\r\n" + 
			"									<option value=\"id\">印尼语</option>\r\n" + 
			"									<option value=\"gu\">古吉拉特语</option>\r\n" + 
			"									<option value=\"kk\">哈萨克语</option>\r\n" + 
			"									<option value=\"tr\">土耳其语</option>\r\n" + 
			"									<option value=\"tg\">塔吉克语</option>\r\n" + 
			"									<option value=\"sr\">塞尔维亚语</option>\r\n" + 
			"									<option value=\"st\">塞索托语</option>\r\n" + 
			"									<option value=\"cy\">威尔士语</option>\r\n" + 
			"									<option value=\"bn\">孟加拉语</option>\r\n" + 
			"									<option value=\"ceb\">宿务语</option>\r\n" + 
			"									<option value=\"ne\">尼泊尔语</option>\r\n" + 
			"									<option value=\"eu\">巴斯克语</option>\r\n" + 
			"									<option value=\"af\">布尔语(南非荷兰语)</option>\r\n" + 
			"									<option value=\"iw\">希伯来语</option>\r\n" + 
			"									<option value=\"el\">希腊语</option>\r\n" + 
			"									<option value=\"de\">德语</option>\r\n" + 
			"									<option value=\"it\">意大利语</option>\r\n" + 
			"									<option value=\"yi\">意第绪语</option>\r\n" + 
			"									<option value=\"la\">拉丁语</option>\r\n" + 
			"									<option value=\"lv\">拉脱维亚语</option>\r\n" + 
			"									<option value=\"no\">挪威语</option>\r\n" + 
			"									<option value=\"cs\">捷克语</option>\r\n" + 
			"									<option value=\"sk\">斯洛伐克语</option>\r\n" + 
			"									<option value=\"sl\">斯洛文尼亚语</option>\r\n" + 
			"									<option value=\"sw\">斯瓦希里语</option>\r\n" + 
			"									<option value=\"pa\">旁遮普语</option>\r\n" + 
			"									<option value=\"ja\">日语</option>\r\n" + 
			"									<option value=\"ka\">格鲁吉亚语</option>\r\n" + 
			"									<option value=\"mi\">毛利语</option>\r\n" + 
			"									<option value=\"fr\">法语</option>\r\n" + 
			"									<option value=\"pl\">波兰语</option>\r\n" + 
			"									<option value=\"bs\">波斯尼亚语</option>\r\n" + 
			"									<option value=\"fa\">波斯语</option>\r\n" + 
			"									<option value=\"te\">泰卢固语</option>\r\n" + 
			"									<option value=\"ta\">泰米尔语</option>\r\n" + 
			"									<option value=\"th\">泰语</option>\r\n" + 
			"									<option value=\"ht\">海地克里奥尔语</option>\r\n" + 
			"									<option value=\"ga\">爱尔兰语</option>\r\n" + 
			"									<option value=\"et\">爱沙尼亚语</option>\r\n" + 
			"									<option value=\"sv\">瑞典语</option>\r\n" + 
			"									<option value=\"be\">白俄罗斯语</option>\r\n" + 
			"									<option value=\"lt\">立陶宛语</option>\r\n" + 
			"									<option value=\"so\">索马里语</option>\r\n" + 
			"									<option value=\"yo\">约鲁巴语</option>\r\n" + 
			"									<option value=\"my\">缅甸语</option>\r\n" + 
			"									<option value=\"ro\">罗马尼亚语</option>\r\n" + 
			"									<option value=\"lo\">老挝语</option>\r\n" + 
			"									<option value=\"fi\">芬兰语</option>\r\n" + 
			"									<option value=\"hmn\">苗语</option>\r\n" + 
			"									<option value=\"en\">英语</option>\r\n" + 
			"									<option value=\"nl\">荷兰语</option>\r\n" + 
			"									<option value=\"tl\">菲律宾语</option>\r\n" + 
			"									<option value=\"pt\">葡萄牙语</option>\r\n" + 
			"									<option value=\"mn\">蒙古语</option>\r\n" + 
			"									<option value=\"es\">西班牙语</option>\r\n" + 
			"									<option value=\"ha\">豪萨语</option>\r\n" + 
			"									<option value=\"vi\">越南语</option>\r\n" + 
			"									<option value=\"az\">阿塞拜疆语</option>\r\n" + 
			"									<option value=\"sq\">阿尔巴尼亚语</option>\r\n" + 
			"									<option value=\"ar\">阿拉伯语</option>\r\n" + 
			"									<option value=\"ko\">韩语</option>\r\n" + 
			"									<option value=\"mk\">马其顿语</option>\r\n" + 
			"									<option value=\"mg\">马尔加什语</option>\r\n" + 
			"									<option value=\"mr\">马拉地语</option>\r\n" + 
			"									<option value=\"ml\">马拉雅拉姆语</option>\r\n" + 
			"									<option value=\"ms\">马来语</option>\r\n" + 
			"									<option value=\"mt\">马耳他语</option>\r\n" + 
			"									<option value=\"km\">高棉语</option>\r\n" + 
			"									<option value=\"ny\">齐切瓦语</option>\r\n" + 
			"								</select>\r\n" + 
			"							</td>\r\n" + 
			"							<td class=\"SL_td\" width=\"3\" align=\"center\">\r\n" + 
			"								<div id=\"SL_switch_b\" title=\"切换语言\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/switchb.png&quot;);\"></div>\r\n" + 
			"							</td>\r\n" + 
			"							<td class=\"SL_td\" width=\"20%\" align=\"left\">\r\n" + 
			"								<select id=\"SL_lng_to\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/select.png&quot;) 100% 0px no-repeat rgb(255, 255, 255);\">\r\n" + 
			"									<option value=\"eo\">世界语</option>\r\n" + 
			"									<option value=\"zh-CN\">中文简体</option>\r\n" + 
			"									<option value=\"zh-TW\">中文繁体</option>\r\n" + 
			"									<option value=\"da\">丹麦语</option>\r\n" + 
			"									<option value=\"uk\">乌克兰语</option>\r\n" + 
			"									<option value=\"uz\">乌兹别克语</option>\r\n" + 
			"									<option value=\"ur\">乌尔都语</option>\r\n" + 
			"									<option value=\"hy\">亚美尼亚语</option>\r\n" + 
			"									<option value=\"ig\">伊博语</option>\r\n" + 
			"									<option value=\"ru\">俄语</option>\r\n" + 
			"									<option value=\"bg\">保加利亚语</option>\r\n" + 
			"									<option value=\"si\">僧伽罗语</option>\r\n" + 
			"									<option value=\"hr\">克罗地亚语</option>\r\n" + 
			"									<option value=\"is\">冰岛语</option>\r\n" + 
			"									<option value=\"gl\">加利西亚语</option>\r\n" + 
			"									<option value=\"ca\">加泰罗尼亚语</option>\r\n" + 
			"									<option value=\"hu\">匈牙利语</option>\r\n" + 
			"									<option value=\"zu\">南非祖鲁语</option>\r\n" + 
			"									<option value=\"kn\">卡纳达语</option>\r\n" + 
			"									<option value=\"hi\">印地语</option>\r\n" + 
			"									<option value=\"su\">印尼巽他语</option>\r\n" + 
			"									<option value=\"jw\">印尼爪哇语</option>\r\n" + 
			"									<option value=\"id\">印尼语</option>\r\n" + 
			"									<option value=\"gu\">古吉拉特语</option>\r\n" + 
			"									<option value=\"kk\">哈萨克语</option>\r\n" + 
			"									<option value=\"tr\">土耳其语</option>\r\n" + 
			"									<option value=\"tg\">塔吉克语</option>\r\n" + 
			"									<option value=\"sr\">塞尔维亚语</option>\r\n" + 
			"									<option value=\"st\">塞索托语</option>\r\n" + 
			"									<option value=\"cy\">威尔士语</option>\r\n" + 
			"									<option value=\"bn\">孟加拉语</option>\r\n" + 
			"									<option value=\"ceb\">宿务语</option>\r\n" + 
			"									<option value=\"ne\">尼泊尔语</option>\r\n" + 
			"									<option value=\"eu\">巴斯克语</option>\r\n" + 
			"									<option value=\"af\">布尔语(南非荷兰语)</option>\r\n" + 
			"									<option value=\"iw\">希伯来语</option>\r\n" + 
			"									<option value=\"el\">希腊语</option>\r\n" + 
			"									<option value=\"de\">德语</option>\r\n" + 
			"									<option value=\"it\">意大利语</option>\r\n" + 
			"									<option value=\"yi\">意第绪语</option>\r\n" + 
			"									<option value=\"la\">拉丁语</option>\r\n" + 
			"									<option value=\"lv\">拉脱维亚语</option>\r\n" + 
			"									<option value=\"no\">挪威语</option>\r\n" + 
			"									<option value=\"cs\">捷克语</option>\r\n" + 
			"									<option value=\"sk\">斯洛伐克语</option>\r\n" + 
			"									<option value=\"sl\">斯洛文尼亚语</option>\r\n" + 
			"									<option value=\"sw\">斯瓦希里语</option>\r\n" + 
			"									<option value=\"pa\">旁遮普语</option>\r\n" + 
			"									<option value=\"ja\">日语</option>\r\n" + 
			"									<option value=\"ka\">格鲁吉亚语</option>\r\n" + 
			"									<option value=\"mi\">毛利语</option>\r\n" + 
			"									<option value=\"fr\">法语</option>\r\n" + 
			"									<option value=\"pl\">波兰语</option>\r\n" + 
			"									<option value=\"bs\">波斯尼亚语</option>\r\n" + 
			"									<option value=\"fa\">波斯语</option>\r\n" + 
			"									<option value=\"te\">泰卢固语</option>\r\n" + 
			"									<option value=\"ta\">泰米尔语</option>\r\n" + 
			"									<option value=\"th\">泰语</option>\r\n" + 
			"									<option value=\"ht\">海地克里奥尔语</option>\r\n" + 
			"									<option value=\"ga\">爱尔兰语</option>\r\n" + 
			"									<option value=\"et\">爱沙尼亚语</option>\r\n" + 
			"									<option value=\"sv\">瑞典语</option>\r\n" + 
			"									<option value=\"be\">白俄罗斯语</option>\r\n" + 
			"									<option value=\"lt\">立陶宛语</option>\r\n" + 
			"									<option value=\"so\">索马里语</option>\r\n" + 
			"									<option value=\"yo\">约鲁巴语</option>\r\n" + 
			"									<option value=\"my\">缅甸语</option>\r\n" + 
			"									<option value=\"ro\">罗马尼亚语</option>\r\n" + 
			"									<option value=\"lo\">老挝语</option>\r\n" + 
			"									<option value=\"fi\">芬兰语</option>\r\n" + 
			"									<option value=\"hmn\">苗语</option>\r\n" + 
			"									<option value=\"en\">英语</option>\r\n" + 
			"									<option value=\"nl\">荷兰语</option>\r\n" + 
			"									<option value=\"tl\">菲律宾语</option>\r\n" + 
			"									<option value=\"pt\">葡萄牙语</option>\r\n" + 
			"									<option value=\"mn\">蒙古语</option>\r\n" + 
			"									<option value=\"es\">西班牙语</option>\r\n" + 
			"									<option value=\"ha\">豪萨语</option>\r\n" + 
			"									<option value=\"vi\">越南语</option>\r\n" + 
			"									<option value=\"az\">阿塞拜疆语</option>\r\n" + 
			"									<option value=\"sq\">阿尔巴尼亚语</option>\r\n" + 
			"									<option value=\"ar\">阿拉伯语</option>\r\n" + 
			"									<option value=\"ko\">韩语</option>\r\n" + 
			"									<option value=\"mk\">马其顿语</option>\r\n" + 
			"									<option value=\"mg\">马尔加什语</option>\r\n" + 
			"									<option value=\"mr\">马拉地语</option>\r\n" + 
			"									<option value=\"ml\">马拉雅拉姆语</option>\r\n" + 
			"									<option value=\"ms\">马来语</option>\r\n" + 
			"									<option value=\"mt\">马耳他语</option>\r\n" + 
			"									<option value=\"km\">高棉语</option>\r\n" + 
			"									<option value=\"ny\">齐切瓦语</option>\r\n" + 
			"								</select>\r\n" + 
			"							</td>\r\n" + 
			"							<td class=\"SL_td\" width=\"5%\" align=\"center\"> </td>\r\n" + 
			"							<td class=\"SL_td\" width=\"8%\" align=\"center\">\r\n" + 
			"								<div id=\"SL_TTS_voice\" title=\"undefined\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/ttsvoice.png&quot;);\"></div>\r\n" + 
			"							</td>\r\n" + 
			"							<td class=\"SL_td\" width=\"8%\" align=\"center\">\r\n" + 
			"								<div id=\"SL_copy\" title=\"复制译文\" class=\"SL_copy\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/copy.png&quot;);\"></div>\r\n" + 
			"							</td>\r\n" + 
			"							<td class=\"SL_td\" width=\"8%\" align=\"center\">\r\n" + 
			"								<div id=\"SL_bbl_font_patch\"></div>\r\n" + 
			"								<div id=\"SL_bbl_font\" title=\"字体大小\" class=\"SL_bbl_font\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/font.png&quot;);\"></div>\r\n" + 
			"							</td>\r\n" + 
			"							<td class=\"SL_td\" width=\"8%\" align=\"center\">\r\n" + 
			"								<div id=\"SL_bbl_help\" title=\"帮助\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/bhelp.png&quot;);\"></div>\r\n" + 
			"							</td>\r\n" + 
			"							<td class=\"SL_td\" width=\"15%\" align=\"right\">\r\n" + 
			"								<div id=\"SL_pin\" class=\"SL_pin_off\" title=\"固定弹出窗口\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/pin-on.png&quot;);\"></div>\r\n" + 
			"							</td>\r\n" + 
			"						</tr>\r\n" + 
			"					</table>\r\n" + 
			"				</div>\r\n" + 
			"			</div>\r\n" + 
			"			<div id=\"SL_shadow_translation_result\" style=\"visibility: visible;\"></div>\r\n" + 
			"			<div id=\"SL_loading\" class=\"SL_loading\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/loading.gif&quot;);\"></div>\r\n" + 
			"			<div id=\"SL_player2\"></div>\r\n" + 
			"			<div id=\"SL_alert100\">文本转语音功能仅限200个字符</div>\r\n" + 
			"			<div id=\"SL_Balloon_options\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/bg3.png&quot;) rgb(255, 255, 255);\">\r\n" + 
			"				<div id=\"SL_arrow_down\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/down.png&quot;);\"></div>\r\n" + 
			"				<table id=\"SL_tbl_opt\" width=\"100%\">\r\n" + 
			"					<tr>\r\n" + 
			"						<td width=\"5%\" align=\"center\"><input id=\"SL_BBL_locer\" type=\"checkbox\" checked=\"1\" title=\"显示翻译器的按钮 3 秒\"></td>\r\n" + 
			"						<td width=\"5%\" align=\"left\">\r\n" + 
			"							<div id=\"SL_BBL_IMG\" title=\"显示翻译器的按钮 3 秒\" style=\"background: url(&quot;chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/img/util/bbl-logo.png&quot;);\"></div>\r\n" + 
			"						</td>\r\n" + 
			"						<td width=\"70%\" align=\"center\">\r\n" + 
			"							<a href=\"chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/html/options/options.html?bbl\" target=\"_blank\" class=\"SL_options\" title=\"显示选项\">选项</a> :\r\n" + 
			"							<a href=\"chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/html/options/options.html?hist\" target=\"_blank\" class=\"SL_options\" title=\"翻译历史记录\">历史</a> :\r\n" + 
			"							<a href=\"chrome-extension://noaijdpnepcgjemiklgfkcfbkokogabh/content/html/options/options.html?feed\" target=\"_blank\" class=\"SL_options\" title=\"反馈\">反馈</a> :\r\n" + 
			"							<a href=\"https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&amp;hosted_button_id=GD9D8CPW8HFA2\" target=\"_blank\" class=\"SL_options\" title=\"作出一点点贡献\">Donate</a>\r\n" + 
			"						</td>\r\n" + 
			"						<td width=\"15%\" align=\"right\"><span id=\"SL_Balloon_Close\" title=\"关闭\">关闭</span></td>\r\n" + 
			"					</tr>\r\n" + 
			"				</table>\r\n" + 
			"			</div>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"</body>";
	
	String sHtml = "<head>\r\n" + 
			"  <style>p{margin-top:0pt;margin-bottom:1pt;}p.X1{text-align:justified;}span.X1{font-family:'Calibri';font-size:10.0pt;}p.X2{text-align:left;}span.X2{font-size:9.0pt;}</style>\r\n" + 
			"  <meta charset=\"UTF-8\">\r\n" + 
			"  <style type=\"text/css\">\r\n" + 
			"			/* page styles */\r\n" + 
			"			\r\n" + 
			"			body {\r\n" + 
			"				font-family: \"Segoe UI\", Frutiger, Tahoma, Helvetica, \"Helvetica Neue\", Arial, sans-serif;\r\n" + 
			"				font-size: 62.5%;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			table {\r\n" + 
			"				border-collapse: collapse;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			td,\r\n" + 
			"			th {\r\n" + 
			"				text-align: center;\r\n" + 
			"				border: 1px solid #ddd;\r\n" + 
			"				padding: 2px 5px;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			caption {\r\n" + 
			"				margin: 0 0 .5em;\r\n" + 
			"				font-weight: bold;\r\n" + 
			"			}\r\n" + 
			"			/*main styles*/\r\n" + 
			"			\r\n" + 
			"			table {\r\n" + 
			"				width: 500px;\r\n" + 
			"				height: 200px;\r\n" + 
			"				margin-left: 30px;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			td,\r\n" + 
			"			th {\r\n" + 
			"				font-size: 1.2em;\r\n" + 
			"				padding: 2px;\r\n" + 
			"				width: 13%;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			th {\r\n" + 
			"				background-color: #f4f4f4;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			caption {\r\n" + 
			"				font-size: 1.5em;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			table {\r\n" + 
			"				float: left;\r\n" + 
			"				margin: 40px 40px 0 0;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			.main {\r\n" + 
			"				width: 500px;\r\n" + 
			"			}\r\n" + 
			"			/* input */\r\n" + 
			"			\r\n" + 
			"			td.input {\r\n" + 
			"				padding: 0;\r\n" + 
			"				position: relative;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			td textarea {\r\n" + 
			"				border: 1px solid black;\r\n" + 
			"				background: white;\r\n" + 
			"				/*-webkit-border-radius: 5px;\r\n" + 
			"				-moz-border-radius: 5px;*/\r\n" + 
			"				border-radius: 1px;\r\n" + 
			"				position: absolute;\r\n" + 
			"				overflow:hidden;\r\n" + 
			"/*				padding: 8px 0;*/\r\n" + 
			"/*				text-align: right;*/\r\n" + 
			"				width: 60px;\r\n" + 
			"\r\n" + 
			"				font-size: 1.4em;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			td.textarea {\r\n" + 
			"				padding: 0;\r\n" + 
			"				position: relative;\r\n" + 
			"			}\r\n" + 
			"			\r\n" + 
			"			\r\n" + 
			"			td.hover {\r\n" + 
			"				background: #eee;\r\n" + 
			"			}\r\n" + 
			"		</style> \r\n" + 
			"  <script src=\"https://code.jquery.com/jquery-3.3.1.min.js\"></script> \r\n" + 
			"  <script type=\"text/javascript\">\r\n" + 
			"			$(function() {\r\n" + 
			"				$('table td').click(function() {\r\n" + 
			"					if(!$(this).is('.textarea')) {\r\n" + 
			"						if(!$(this).is('.textarea')){\r\n" + 
			"							var p_html = $(this).html();\r\n" + 
			"							var ta = $(\"<textarea></textarea>\");\r\n" + 
			"							var d = $(\"<div></div>\");\r\n" + 
			"							$(this).addClass(\"textarea\").append(ta);\r\n" + 
			"							setTextArea($(this),ta);\r\n" + 
			"							var v = $(this).text();\r\n" + 
			"							\r\n" + 
			"							ta.val(v);\r\n" + 
			"							\r\n" + 
			"							ta.focus().blur(function(){\r\n" + 
			"								$(this).parent().removeClass('textarea');\r\n" + 
			"								console.info($(this).html());\r\n" + 
			"								$(this).parent().html($(this).val());\r\n" + 
			"\r\n" + 
			"							});\r\n" + 
			"							\r\n" + 
			"							\r\n" + 
			"						}\r\n" + 
			"					}\r\n" + 
			"				}).hover(function() {\r\n" + 
			"					$(this).addClass('hover');\r\n" + 
			"				}, function() {\r\n" + 
			"					$(this).removeClass('hover');\r\n" + 
			"				});\r\n" + 
			"			});\r\n" + 
			"			\r\n" + 
			"			function setTextArea(_td, _ta){\r\n" + 
			"				var td_width = _td.width();\r\n" + 
			"				var td_heigth = _td.height();\r\n" + 
			"				\r\n" + 
			"				var fs = _td.css(\"font-size\");\r\n" + 
			"				_ta.css(\"width\", td_width *1.5);\r\n" + 
			"				_ta.css(\"height\", td_heigth *1.5);\r\n" + 
			"				_ta.css(\"font-size\", fs);\r\n" + 
			"			}\r\n" + 
			"\r\n" + 
			"			function getHtml() {\r\n" + 
			"				var htm = $(\"#main\").html();\r\n" + 
			"				var bh = $(\"#div_bh\").html();\r\n" + 
			"				$(\"#ta_bh\").val(bh);\r\n" + 
			"				$(\"#ta_htm\").val(htm);\r\n" + 
			"				var tm = $(\"#ta_bh\").val();\r\n" + 
			"				console.info(tm);\r\n" + 
			"				return true;\r\n" + 
			"			}\r\n" + 
			"		</script>\r\n" + 
			" </head>\r\n" + 
			" <body>\r\n" + 
			"  <div id=\"div_bh\">\r\n" + 
			"   <div style=\"width:595.0pt;margin-bottom:56.0pt;margin-top:72.0pt;margin-left:85.0pt;margin-right:85.0pt;\">\r\n" + 
			"    <p><span style=\"font-family:'宋体';font-size:12.0pt;font-weight:bold;\">附件</span><span style=\"font-family:'宋体';font-size:12.0pt;font-weight:bold;\">1</span><span style=\"font-family:'宋体';font-size:12.0pt;font-weight:bold;\">：</span></p>\r\n" + 
			"    <p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:16.0pt;font-weight:bold;\">仲恺农业工程学院假期留校住宿申请表</span></p>\r\n" + 
			"    <table class=\"X4\" style=\"width:449.2pt;border-collapse:collapse;\">\r\n" + 
			"     <tbody>\r\n" + 
			"      <tr class=\"X4\">\r\n" + 
			"       <td class=\"X4\" style=\"width:50.2pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">学院</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:72.1pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">仲恺</td>\r\n" + 
			"       <td class=\"X4\" style=\"width:63.1pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">班级</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:50.5pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\">妓院</td>\r\n" + 
			"       <td class=\"X4\" style=\"width:64.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">学号</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:63.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">123456</td>\r\n" + 
			"       <td class=\"X4\" style=\"width:35.45pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">校区</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:49.5pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\">海珠</td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr class=\"X4\">\r\n" + 
			"       <td class=\"X4\" style=\"width:50.2pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">姓名</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:72.1pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">徐楚健</td>\r\n" + 
			"       <td class=\"X4\" style=\"width:63.1pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">性别</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:50.5pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\">女</td>\r\n" + 
			"       <td class=\"X4\" style=\"width:64.55pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\"><p style=\"text-align:left;text-indent:1.0pt;text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">联系电话</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:148.75pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"4\">123456</td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr class=\"X4\">\r\n" + 
			"       <td class=\"X4\" style=\"width:50.2pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">宿舍</span></p><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">房号</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:72.1pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\">8888</td>\r\n" + 
			"       <td class=\"X4\" style=\"width:63.1pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">辅导员</span></p><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">姓 名</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:72.0pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\"><p></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:63.0pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">辅导员</span></p><p><span style=\"font-family:'宋体';font-size:12.0pt;\">联系电话</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:128.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"3\"><p></p></td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr class=\"X4\">\r\n" + 
			"       <td class=\"X4\" style=\"width:50.2pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">家庭</span></p><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">地址</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:399.0pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"10\">中国某个地方</td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr class=\"X4\">\r\n" + 
			"       <td class=\"X4\" style=\"width:50.2pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"2\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">留校住宿时间</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:399.0pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"10\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">年 月 日至 月 日</span></p></td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr class=\"X4\">\r\n" + 
			"       <td class=\"X4\" style=\"width:32.4pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">申请理由</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:416.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"11\">我想成为一个很有钱的人 \r\n" + 
			"\r\n" + 
			"申请人 ：　　 年 月 日</td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr class=\"X4\">\r\n" + 
			"       <td class=\"X4\" style=\"width:67.0pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"3\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">家长知情</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:382.2pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"9\"><p style=\"text-indent:24.0pt;\"><br></p><p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">家长联系方式：_________________</span></p><p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">是否已征得家长同意 ：_________________(请填写是或否)</span></p><p style=\"text-indent:24.0pt;\"></p></td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr class=\"X4\">\r\n" + 
			"       <td class=\"X4\" style=\"width:32.4pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">申请人</span></p><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">承</span></p><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">诺</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:416.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"11\"><p style=\"text-indent:24.0pt;\"><br></p><p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">申请人承诺：</span></p><p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">1.经家长同意，本人申请留校住宿；</span></p><p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">2.自觉遵守国家法律法规、校纪校规，服从学校宿舍安排和管理；</span></p><p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">3.提高安全意识，杜绝安全隐患，不使用违章电器，不晚归，不留宿异性或校外人员；</span></p><p style=\"text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">4.违反相关规定而发生的后果由本人负责。</span></p><p style=\"text-align:right;text-indent:24.0pt;text-align:right;\"><br></p><p style=\"text-align:right;text-indent:24.0pt;text-align:right;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">承诺人签字 ： 　 年 月 日</span></p><p style=\"text-align:right;text-indent:24.0pt;text-align:right;\"></p></td>\r\n" + 
			"      </tr>\r\n" + 
			"      <tr class=\"X4\">\r\n" + 
			"       <td class=\"X4\" style=\"width:32.4pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\"><p style=\"text-align:center;\"><span style=\"font-family:'宋体';font-size:12.0pt;\">二级学院意见</span></p></td>\r\n" + 
			"       <td class=\"X4\" style=\"width:416.8pt;border-top:0.5px solid #000000;border-bottom:0.5px solid #000000;border-left:0.5px solid #000000;border-right:0.5px solid #000000;\" colspan=\"11\"><p><br></p><p style=\"text-align:right;text-indent:24.0pt;\"><br></p><p style=\"text-align:right;text-indent:24.0pt;\"><br></p><p style=\"text-align:right;text-indent:24.0pt;\"><br></p><p style=\"text-align:right;text-indent:24.0pt;\"><br></p><p style=\"text-align:right;text-indent:24.0pt;\"><span style=\"font-family:'宋体';font-size:12.0pt;\"> 签字盖章： 　 　 年 月 日</span></p><p style=\"text-align:right;text-indent:24.0pt;\"></p></td>\r\n" + 
			"      </tr>\r\n" + 
			"     </tbody>\r\n" + 
			"    </table>\r\n" + 
			"    <p><br></p>\r\n" + 
			"   </div>\r\n" + 
			"   <div style=\"width:595.0pt;margin-bottom:72.0pt;margin-top:72.0pt;margin-left:90.0pt;margin-right:90.0pt;\">\r\n" + 
			"    <p></p>\r\n" + 
			"   </div>\r\n" + 
			"  </div>\r\n" + 
			"  <form action=\"/client_relationship_manager/seller/generateBill.action\" method=\"post\" onsubmit=\"return getHtml();\">\r\n" + 
			"   <textarea id=\"ta_bh\" name=\"bh\" style=\"display: none;\"></textarea>\r\n" + 
			"   <textarea id=\"ta_htm\" name=\"htm\" style=\"display: none;\"></textarea>\r\n" + 
			"   <input placeholder=\"金额\" name=\"money\" type=\"text\">\r\n" + 
			"   <input placeholder=\"客户id\" name=\"cid\" type=\"text\">\r\n" + 
			"   <input value=\"提交\" type=\"submit\">\r\n" + 
			"   <a href=\"javascript:window.opener=null;window.open('','_self');window.close();\">关闭</a>\r\n" + 
			"   <a href=\"/zhku/model/bill/附件1 仲恺农业工程学院假期留校住宿申请表.docx\">下载</a>\r\n" + 
			"  </form>\r\n" + 
			" \r\n" + 
			"</body>";
	
	@Test
	public void testAddHtmlTag() {
		
		//String html = HtmlUtil.addHtmlTag(tags);
		//System.out.println(html);
		//String s =  HtmlUtil.processHtml("仲恺农业工程学院假期留校住宿申请表.html", "#", "#", true);
		//HtmlUtil.saveHtml("zhku", "<p>你好</P>");
		//String s =	HtmlUtil.readHtml("zhku");
		//String s = HtmlUtil.addHtmlTag(tags);
		
		//String s = HtmlUtil.getUpdateHtml(sHtml, "2015");
		//HtmlUtil.generateEvaHtml("123", "456");
		String parent = PropertyUtil.getProperty("EVA_HTML_PATH");
		HtmlUtil.removeFileHtml(parent,"123");
		//System.out.println(s);
	}

}
