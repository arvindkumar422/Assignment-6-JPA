var y = document.getElementById("updateButton");
var z = document.getElementById("logoutbutton"); 

y.addEventListener("click", function() {
	alert("Data saved!");
});

z.addEventListener("click", function() {
	window.location.href='../homepage/homepage.template.client.html';
});