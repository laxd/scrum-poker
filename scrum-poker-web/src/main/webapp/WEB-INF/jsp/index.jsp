<html>
<head>

    <script>
        var context = "${pageContext.request.contextPath}";
    </script>

    <script src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.0.3/sockjs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</head>
<body>

<h2>Scrum Poker</h2>

<label for="name">Name</label>
<div>
    <input id="name" type="text"/>
    <button type="button" onclick="submitName();">Submit</button>
</div>
<p id="response">

</p>

</body>
</html>