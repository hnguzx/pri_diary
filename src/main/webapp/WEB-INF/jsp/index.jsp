<%@ page pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitionl//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>上传下载测试</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#submit").click(function () {
                var userId = $("#userId").val();
                var detailContent = $("#detailContent").val();
                var diaryId = $("#diaryId").val();
                var diaryTitle = $("#diaryTitle").val();
                var diaryWeather = $("#diaryWeather").val();
                var diaryMood = $("#diaryMood").val();
                var diaryEvent = $("#diaryEvent").val();
                var diaryLocation = $("#diaryLocation").val();
                var global = $("#global").val();


                var formData = new FormData();

                var diary = JSON.stringify({
                    userId: userId,
                    detailContent: detailContent,
                    diaryId: diaryId,
                    diaryTitle: diaryTitle,
                    diaryWeather: diaryWeather,
                    diaryMood: diaryMood,
                    diaryEvent: diaryEvent,
                    diaryLocation: diaryLocation
                })

                var detailPhoto = document.getElementById("detailPhoto").files[0];

                formData.append("detailPhoto", detailPhoto);
                formData.append("diary", new Blob([diary], {type: "application/json"}));

                $.ajax({
                    // url: "../diary/" + userId + "/" + global,
                    url: "../diary/insertDiary/",
                    type: "post",
                    // type: "delete",
                    data: formData,
                    cache: false,
                    async: false,
                    contentType: false,
                    processData: false,
                    success: function (result) {
                        $("#img").attr("src",result.data.detailPhoto)
                        alert(result);
                    }
                });
            })
        });
    </script>
</head>
<body>
<form action="./part" method="post" enctype="multipart/form-data">
    <input id="detailPhoto" type="file" name="detailPhoto" value="上传文件">
    <br>
    <input id="userId" type="text" value="userId"><br>
    <input id="detailContent" type="text" value="detailContent"><br>
    <input id="diaryId" type="text" value="diaryId"><br>
    <input id="diaryTitle" type="text" value="diaryTitle"><br>
    <input id="diaryWeather" type="text" value="diaryWeather"><br>
    <input id="diaryMood" type="text" value="diaryMood"><br>
    <input id="diaryEvent" type="text" value="diaryEvent"><br>
    <input id="diaryLocation" type="text" value="diaryLocation">
    <input id="global" type="text" value="">


    <input id="submit" type="button" value="提交">
    <img src="" id="img" width="100px" height="100px">
</form>
</body>
</html>