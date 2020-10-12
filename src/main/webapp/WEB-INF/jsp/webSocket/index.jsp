<%@ page pageEncoding="utf-8" language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitionl//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
    <title>webSocket</title>
    <script type="text/javascript" src="../jquery-easyui-1.9.7/jquery.min.js"></script>
<%--    <script type="text/javascript" src="./../static/js/webSocket.js"></script>--%>
    <script type="text/javascript">
        var webSocket = null;
        // 判断游览器是否支持websocket
        // if ('WebSocket' in Window) {
        webSocket = new WebSocket("ws://localhost:83/ws")
        // } else {
        //     alert("该游览器不支持！")
        // }

        webSocket.onerror = function () {
            appendMessage("error")
        }

        /**
         * 连接建立成功的回调方法
         */
        webSocket.onopen = function () {
            appendMessage("open")
        }

        /**
         * 接收到消息的回调方法
         */
        webSocket.onmessage = function (event) {
            appendMessage(event.data)
        }

        /**
         * 连接关闭的回调方法
         */
        webSocket.onclose = function () {
            appendMessage("close")
        }

        /**
         * 监听窗口关闭事件，防止连接还没断开就关闭窗口导致服务端抛异常
         */
        window.onbeforeunload = function () {
            webSocket.close();
        }

        /**
         * 将消息显示在网页上
         * @param message
         */
        function appendMessage(message) {
            var context = $("#context").html() + "<br>" + message;
            $("#context").html(context);

        }

        /**
         * 关闭连接
         */
        function closeWebSocket() {
            webSocket.close();
        }

        /**
         * 发送消息
         */
        function sendMessage() {
            var message = $("#message").val();
            // var messageTo = {
            //     name:'guzhixiong',
            //     message:message
            // }
            // messageTo = JSON.stringify(messageTo);
            webSocket.send(message);
        }
    </script>
</head>
<body>
<h2>测试WebSocket站点</h2>
<br>
<input type="text" id="message">
<button onclick="sendMessage()">发送消息</button>
<button onclick="closeWebSocket()">关闭连接</button>
<div id="context"></div>
</body>
</html>