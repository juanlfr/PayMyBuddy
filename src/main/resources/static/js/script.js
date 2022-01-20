/**
 * 
 */
alert(" newmodifica js");
getAppAccounts ();
getbankAccounts ();

function getAppAccounts () {
var rows = document.getElementById("appAccounts").rows;
console.log(rows);
console.log(rows[0].firstElementChild.getAttribute('data-accountId'));
console.log(rows[1].firstElementChild.getAttribute('data-accountId'));
for (let i = 0; i < rows.length; i++) {
	rows[i].addEventListener("click", () => {
	var accountId = rows[i].firstElementChild.getAttribute('data-accountId');
	console.log(accountId);
	var url = `/account/transactions/${accountId}`;
	window.location.href = url;
});
}
	
}


function getbankAccounts () {	
var rows = document.getElementById("bankAccounts").rows;

for (let i = 0; i < rows.length; i++) {
	rows[i].addEventListener("click", () => {
    var accountId = rows[i].firstElementChild.getAttribute('data-accountId');
	var url = `/account/myBankAccount/${accountId}`;
	window.location.href = url;
});
}
	
}