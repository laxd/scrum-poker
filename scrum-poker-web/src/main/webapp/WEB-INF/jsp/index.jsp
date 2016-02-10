<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<select id="vote">
    <c:forTokens items="0,1,2,3,5,8,13,20,40,100,Infinity" delims="," var="v">
        <option>${v}</option>
    </c:forTokens>
</select>
<button type="button" onclick="submitVote();">Vote</button>

<div id="currentIssue">

</div>
<label for="newIssue">New Issue:</label>
<input id="newIssue"></input>
<button type="button" onclick="submitIssue();">New Issue</button>

<div id="votes">

</div>

<p id="users" style="float: right">

</p>

<button id="disconnect">Disconnect</button>

</body>
</html>