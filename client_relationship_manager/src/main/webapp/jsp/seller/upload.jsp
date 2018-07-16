<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script>
			$(function() {
				$("#upload").click(function() {
					var formData = new FormData();
					formData.append("fileModel", document.getElementById("fileModel").files[0]);
					$.ajax({
						url: "/client_relationship_manager/seller/fileModelUpload.action",
						type: "POST",
						data: formData,
						/**
						 *必须false才会自动加上正确的Content-Type
						 */
						contentType: false,
						/**
						 * 必须false才会避开jQuery对 formdata 的默认处理
						 * XMLHttpRequest会对 formdata 进行正确的处理
						 */
						processData: false,
						success: function(data) {
							if(data.status == 1) {
								alert("上传成功！");
							}else if(data.status == 0) {
								alert(data.msg);
							}
						},
						error: function() {
							alert("上传失败！");
						}
					});
				});
			});
		</script>
	</head>

	<body>
		<input type="file" name="fileModel" id="fileModel" />
		<input type="button" value="上传" id="upload" />
	</body>

</html>

