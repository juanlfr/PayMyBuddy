/**
 * 
 */
alert("js");
var rows = document.getElementsByTagName("tr");

for (let i = 0; i < rows.length; i++) {
	rows[i].addEventListener("click", () => {
    var accountId = rows[i].firstElementChild.getAttribute('data-accountId');
	var url = `/account/myBankAccount/${accountId}`;
	window.location.href = url;
});
}