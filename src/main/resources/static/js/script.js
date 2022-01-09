/**
 * 
 */
alert("new js");
var rows = document.getElementById("bankAccounts").rows;

for (let i = 0; i < rows.length; i++) {
	rows[i].addEventListener("click", () => {
    var accountId = rows[i].firstElementChild.getAttribute('data-accountId');
	var url = `/account/myBankAccount/${accountId}`;
	window.location.href = url;
});
}