<%@ page pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitionl//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>文件上传</title>
</head>
<body>
<form action="./request/part" method="post" enctype="multipart/form-data">
    <input type="file" name="file" value="上传文件">
    <input type="text" name="userId">
    <input type="text" name="diaryId">
    <input type="text" name="diaryTitle">
    <input type="text" name="diaryWeather">
    <input type="text" name="diaryMood">
    <input type="text" name="diaryEvent">
    <input type="text" name="diaryLocation">
    <input type="text" name="diaryContent">
    <input type="submit" value="提交">
</form>
</body>
</html>