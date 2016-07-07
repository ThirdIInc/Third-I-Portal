function validateEmail(){
}

function numbersOnly(myField){
	var key;
	var keyChar;
	if(window.event)
		key = window.event.keyCode;
	else 
		return true;
	
	keyChar = String.formCharCode(key);
	if(("0123456789").indexOf(keyChar)>-1)
		return true;
	else{
		myField.focus();
		return false;
	}
}

function charactersOnly(myField){
}

function validateAmount(myField){
}