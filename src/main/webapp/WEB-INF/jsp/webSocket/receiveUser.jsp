<%@ page pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitionl//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>webSocket</title>
    <script type="text/javascript" src="/jquery-easyui-1.9.7/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/socket.min.js"></script>
    <script type="text/javascript" src="/static/js/stomp.js"></script>
    <script type="text/javascript">
        var noticeSocket = function () {
            var socket = new SockJS('/ws_user');
            var stompClient = Stomp.over(socket);
            stompClient.connect({}, function () {
                console.log("notice socket connected")
                // /user必须要有，表示指定用户
                // stompClient.subscribe('/user/client_chat/friend', function (data) {
                // stompClient.subscribe('/user/client_user/add_friend', function (data) {
                stompClient.subscribe('/user/client_chat/receive_msg', function (data) {
                    $("#receive").html(data.body);

                })
            })
        }

        noticeSocket();
    </script>
</head>
<body>
<h1>等待接收消息</h1>
<h2>
    <span id="receive"></span>
</h2>
</body>
</html>