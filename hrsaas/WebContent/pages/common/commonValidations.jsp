<script>

/* ====================================================================================
	Function	: numbersOnly
	Explanation : Allows only 0-9 digits in textfield or textarea
	Retrieval	: boolean : If entered value has only numbers then return true 
							else return false
	===================================================================================*/
function numbersOnly(e){
	document.onkeypress = numbersOnly;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( key > 47 && key < 58 || key == 8 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}
	
/* ====================================================================================
	Function	: charactersOnly
	Explanation : Allows only characters both in small and capital case with space
	Retrieval	: boolean : If entered value has only characyers and space then return 
							true else return false
	===================================================================================*/
function charactersOnly(e){
	document.onkeypress = charactersOnly;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 96 && key < 123) || (key > 64 && key < 91)|| key == 8 || key == 32 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

/* ====================================================================================
	Function	: charactersOnlyWithoutSpace without space
	Explanation : Allows only characters both in small and capital case without space.(First name and Last name)
	Retrieval	: boolean : If entered value has only characyers then return 
							true else return false
	===================================================================================*/
function charactersOnlyWithoutSpace(e){
	document.onkeypress = charactersOnlyWithoutSpace;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 96 && key < 123) || (key > 64 && key < 91)|| key == 8  || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}
/* ===================================================================================
	Function	: numbersWithColonandDot
	Explanation : Allows 0-9 digits as well as colon (:) & dot &spaces
	Retrieval	: boolean : If entered value has only numbers and colon then return
							true else return false
   ===================================================================================*/
function numbersWithColonandDot(e){
	document.onkeypress = numbersWithColonandDot;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( key > 47 && key < 58 || key == 8 || key == 58 || key == 46 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

/* ====================================================================================
	Function	: alphaNumeric
	Explanation : Allows only characters both in small and capital case with space and
					numbers
	Retrieval	: boolean : If entered value has only characyers and space then return 
							true else return false
	===================================================================================*/
function alphaNumeric(e){
	document.onkeypress = alphaNumeric;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 96 && key < 123) || (key > 64 && key < 91) || (key > 47 && key < 58) || key == 8 || key == 32 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}


/* ===================================================================================
	Function	: numbersWithHiphen
	Explanation : Allows 0-9 digits as well as hiphen (-)
	Retrieval	: boolean : If entered value has only numbers and hiphen then
							 return true else return false
	===================================================================================*/
function numbersWithHiphen(e){
	document.onkeypress = numbersWithHiphen;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 47 && key < 58) || key == 8 || key == 45 || key == 0 || key==46){
	 	return true;} // if so, do nothing
	else {// otherwise, discard character
		return false;
	}
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

function clear(){
	document.onkeypress = "";
}

/* ===================================================================================
	Function	: numbersWithColon
	Explanation : Allows 0-9 digits as well as colon (:)
	Retrieval	: boolean : If entered value has only numbers and colon then return
							true else return false
   ===================================================================================*/
function numbersWithColon(e){
	document.onkeypress = numbersWithColon;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( key > 47 && key < 58 || key == 8 || key == 58 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

/* ===================================================================================
	Function	: numbersWithSpaceandDot
	Explanation : Allows 0-9 digits as well as space ( ) and dot (.)
	Retrieval	: boolean : If entered value has only numbers as well as space and 
							dot then return true else return false
	===================================================================================*/
function numbersWithSpaceandDot(e){
	document.onkeypress = numbersWithSpaceandDot;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( key > 47 && key < 58 || key == 8 || key == 32 || key == 46 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

/* ===================================================================================
	Function	: numbersWithDot
	Explanation : Allows 0-9 digits as well as dot (.)
	Retrieval	: boolean : If entered value has only numbers and dot then return 
							true else return false
	===================================================================================*/
function numbersWithDot(e){
	document.onkeypress = numbersWithDot;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( key > 47 && key < 58 || key == 8 || key == 46 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

/* ===================================================================================
	Function	: numbersWithSpecialCharacters
	Explanation : Allows 0-9 digits as well as special characters like .@ $%&*()-+
	Retrieval	: boolean : If entered value has numbers as well as special characters
							then return true else return false
	===================================================================================*/
function numbersWithSpecialCharacters(e){
	document.onkeypress = numbersWithSpecialCharacters;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 47 && key < 58) || (key > 35 && key < 39) || (key > 39 && key < 44) 
		|| key == 8 || key == 46 || key == 32 || key == 64 || key == 45 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

/* ===================================================================================
	Function	: validatePhoneNo
	Explanation : Allows 0-9 digits as well as special characters like ()-+,
	Retrieval	: boolean : If entered value has numbers as well as special characters
							then return true else return false
	===================================================================================*/
function validatePhoneNo(e){
	document.onkeypress = validatePhoneNo;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 47 && key < 58) || key == 8 || key == 40 || key == 41 
		|| key == 43 || key == 45 || key == 44 || key == 32 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

/* ===================================================================================
	Function	: validateMobile
	Explanation : Allows 0-9 digits as well as special characters like -+
	Retrieval	: boolean : If entered value has numbers as well as special characters
							then return true else return false
	===================================================================================*/
function validateMobile(e){
	document.onkeypress = validateMobile;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 47 && key < 58) || key == 8 || key == 40 || key == 41 
		|| key == 43 || key == 45 || key == 44 || key == 32 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

/* ===================================================================================
	Function	: charactersWithSpecialCharacters
	Explanation : Allows characters in both small and capital case as well 
				  as special characters like .@ $%&*()-+
	Retrieval	: boolean : If entered value has characters as well as special 
							characters then return true else return false
   ===================================================================================*/
function charactersWithSpecialCharacters(e){
	document.onkeypress = charactersWithSpecialCharacters;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 96 && key < 123) || (key > 64 && key < 91) || (key > 35 && key < 39) || (key > 39 && key < 44) 
		|| key == 8 || key == 46 || key == 32 || key == 64 || key == 45 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

/* ===================================================================================
	Function	: allCharacters
	Explanation : Allows all characters, numbers and special characters except '"/\
	Retrieval	: boolean : If entered value has characters as well as special 
							characters then return true else return false
   ===================================================================================*/
function allCharacters(e){
	document.onkeypress = allCharacters;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	   //	alert(key);
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if (key == 39 || key == 34 || key == 92)
	 	return false; // if so, do nothing
	else // otherwise, discard character
		return true;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

/* ===================================================================================
	Function	: emailCheck
	Explanation : Allows characters in both small and capital case as well 
				  as special characters like .@-_with numbers
	Retrieval	: boolean : If entered value has characters as well as special 
							characters then return true else return false
   ===================================================================================*/
function emailCheck(e){
	
	
	document.onkeypress = emailCheck;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 96 && key < 123) || (key > 64 && key < 91) || (key > 47 && key < 58) 
		|| key == 8 || key == 46 || key == 64 || key == 45 || key == 95 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

/* ===================================================================================
	Function	: webSiteCheck
	Explanation : Allows characters in both small and capital case as well 
				  as special characters like .@-_with numbers
	Retrieval	: boolean : If entered value has characters as well as special 
							characters then return true else return false
   ===================================================================================*/
function webSiteCheck(e){
	document.onkeypress = webSiteCheck;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( (key > 96 && key < 123) || (key > 64 && key < 91) || (key > 47 && key < 58) 
		|| key == 8 || key == 46 || key == 45 || key == 95 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

/* ===================================================================================
	Function	: validateEmail
	Input		: obj   : Object 	
	Explanation : Validate the email whether it is in proper format or not
				  i.e. in abc@xyz.com format (domain may change to few other values)
	Retrieval	: boolean : If entered emailId is in proper format then return true 
							else return false
	===================================================================================*/
function validateEmail (name) {
	
	var emailStr        = document.getElementById(name).value;
	if(emailStr=="")return true;
	
	var checkTLD        = 1;
	
	var knownDomsPat    = /^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;
	
	var emailPat		= /^(.+)@(.+)$/;
	
	var specialChars	= "\\(\\)><@,;:\\\\\\\"\\.\\[\\]";
	
	var validChars		= "\[^\\s" + specialChars + "\]";
	
	var quotedUser		= "(\"[^\"]*\")";
	
	var ipDomainPat		= /^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;
	
	var atom			= validChars + '+';
	
	var word			= "(" + atom + "|" + quotedUser + ")";
	
	var userPat			= new RegExp("^" + word + "(\\." + word + ")*$");
	
	var domainPat		= new RegExp("^" + atom + "(\\." + atom +")*$");
	
	var matchArray		= emailStr.match(emailPat);

	if (matchArray==null) {
		alert("Email address seems incorrect (check @ and .'s)");
		document.getElementById(name).focus();
		return false;
	}
	var user			= matchArray[1];
	var domain			= matchArray[2];
	
	/*
	if(user.indexOf(".")!=-1 && user.split(".").length>2){
	 		alert("Only one dot is allowed in user name of email id");
			document.getElementById(name).focus();
			return false;
	 	}
	*/
	
	 	
	 	if(user.indexOf("_")!=-1 && user.split(".").length>1){
	 		alert("The username doesn't seem to be valid for email.");
			document.getElementById(name).focus();
			return false;
	 	}
	
	if(domain.indexOf("-")!=-1 && domain.split("-").length>2){
	 		alert("The domain doesn't seem to be valid for email.");
			document.getElementById(name).focus();
			return false;
	 	}

	for (i=0; i<domain.length; i++) {
		if (domain.charCodeAt(i)>127 || domain.indexOf("_")!=-1) {
			alert("The domain name contains invalid characters.");
			document.getElementById(name).focus();
			return false;
   		}
	}


	if (user.match(userPat)==null) {
		alert("The username doesn't seem to be valid for email.");
		document.getElementById(name).focus();
		return false;
	}

	var IPArray=domain.match(ipDomainPat);
	if (IPArray!=null) {
		for (var i=1;i<=4;i++) {
			if (IPArray[i]>255) {
				alert("Destination IP address is invalid!");
				document.getElementById(name).focus();
				return false;
		   }
		}
			return true;
	}

	var atomPat		= new RegExp("^" + atom + "$");
	var domArr		= domain.split(".");
	var len    		= domArr.length;
	
	var dot         = domain.indexOf(".");
	var hiphen		= domain.indexOf("-");
	
	if(dot>hiphen && (dot-hiphen)<2){
		alert("The domain name does not seem to be valid");
		document.getElementById(name).focus();
		return false;
	}if(dot<hiphen && (hiphen-dot)<2){
		alert("The domain name does not seem to be valid");
		document.getElementById(name).focus();
		return false;
	}if(len>3){
		alert("Maximum two dots are allowed in domain name");
		document.getElementById(name).focus();
		return false;
	}
	
	for (i=0;i<len;i++) {
		if (domArr[i].search(atomPat)==-1) {
			alert("The domain name does not seem to be valid");
			document.getElementById(name).focus();
			return false;
	   }
	}if(domain.indexOf("-")!=-1){
		if(domain.indexOf("-")<2){
			alert("The domain name does not seem to be valid");
			document.getElementById(name).focus();
			return false;
		}
	}

	if (len<2) {
		alert("This email address is missing a hostname!");
		document.getElementById(name).focus();
		return false;
	}
	
	return true;
}

/* ===================================================================================
	Function	: validateWebSite
	Input		: obj   : Object 	
	Explanation : Validate the website whether it is in proper format or not
				  i.e. in www.abc.com format (domain may change to few other values)
	Retrieval	: boolean : If entered website is in proper format then return true 
							else return false
	===================================================================================*/
function validateWebSite(name) {
     var theurl=document.getElementById(name).value;
     if(theurl=="")return true;
     
    var RegExp = /^(([\w]+:)?\/\/)?(([\d\w]|%[a-fA-f\d]{2,2})+(:([\d\w]|%[a-fA-f\d]{2,2})+)?@)?([\d\w][-\d\w]{0,253}[\d\w]\.)+[\w]{2,4}(:[\d]+)?(\/([-+_~.\d\w]|%[a-fA-f\d]{2,2})*)*(\?(&?([-+_~.\d\w]|%[a-fA-f\d]{2,2})=?)*)?(#([-+_~.\d\w]|%[a-fA-f\d]{2,2})*)?$/; 
    if(RegExp.test(theurl)){ 
        return true; 
    }else{ 
    	window.alert("Please enter web site in proper format");
         document.getElementById(name).focus();
         return false;
    } 
}

/* ===================================================================================
	Function	: validateDate
	Input		: date, fieldName, labName   : String 	
	Explanation : Validate date whether it is in proper format or not 
				  i.e. in DD-MM-YYYY format
	Retrieval	: boolean : If entered date is in proper format then return
							true else return false
	===================================================================================*/
function validateDate(fieldName, labName){

	var date = document.getElementById(fieldName).value;
	if(date=='') return true;
	var dateFormat = /^[0-9]{2}[-]?[0-9]{2}[-]?[0-9]{4}$/;
	
	if(!(date.match(dateFormat)) || date.length<10){
		alert(""+document.getElementById(labName).innerHTML.toLowerCase()+" should be in DD-MM-YYYY format");
		document.getElementById(fieldName).focus();
		return false;
	}
	var dateArray = date.split("-");
	var day   = dateArray[0];
	var month = dateArray[1];
	var year  = dateArray[2];
	
	if(day<1 || day>31){
		alert("Day "+day+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(month<1 || month>12){
		alert("Month "+month+" is not a valid month");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(day>29 && month==2){
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if((month==2 && day==29) && ((year%4!=0) || (year%100==0 && year%400!=0))){
		window.alert("29th of February is not a valid date in "+year);
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if (day>30 && (month == 2 || month==4 || month==6 || month==9 || month==11)) {
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}

/*D1 Time Message changes*/
function validateTimeD1(name, labName){
	var time        = document.getElementById(name).value;
	if(time=="")return true;
	var timeExp 	= /^[0-9]{2}[:]?[0-9]{2}$/
	var timeArray 	= time.split(":");
	var hour	    = timeArray[0];
	var min         = timeArray[1];
	if(!(time.match(timeExp)) || time.length<5){
		alert(""+document.getElementById(labName).innerHTML.toLowerCase()+" should be in 24Hours HH:MM format");
		document.getElementById(name).focus();
		return false;
	}
	
	if(hour>23){
		alert("Hour "+hour+" is not valid");
		document.getElementById(name).focus();
		return false;
	}
	
	if(min>59){
		alert("Minute "+min+" is not valid");
		document.getElementById(name).focus();
		return false;
	}
	return true ;
}




/* ===================================================================================
	Function	: validateTime
	Input		: obj   : Object, labName    : String 
	Explanation : Validate the time whether it is in proper format or not
				  i.e. in HH:MM format
	Retrieval	: boolean : If entered time is in proper format then return
							true else return false
	===================================================================================*/
function validateTime(name, labName){
	var time        = document.getElementById(name).value;
	if(time=="")return true;
	var timeExp 	= /^[0-9]{2}[:]?[0-9]{2}$/
	var timeArray 	= time.split(":");
	var hour	    = timeArray[0];
	var min         = timeArray[1];
	if(!(time.match(timeExp)) || time.length<5){
		alert(""+document.getElementById(labName).innerHTML.toLowerCase()+" should be in 24Hours HH:MI format");
		document.getElementById(name).focus();
		return false;
	}
	
	if(hour>23){
		alert("Hour "+hour+" is not valid");
		document.getElementById(name).focus();
		return false;
	}
	
	if(min>59){
		alert("Minute "+min+" is not valid");
		document.getElementById(name).focus();
		return false;
	}
	return true ;
}

/* ===================================================================================
	Function	: timeDifference
	Input		: fromTime, toTime, fieldName, fromLabName, toLabName  : String 	
	Explanation : Calculate the difference between two times 
				  i.e. between fromTime and toTime
	Retrieval	: boolean : If toTime is greater than fromTime then return 
							true else return false
	===================================================================================*/
function timeDifference(fromTime,toTime,fieldName,fromLabName,toLabName){   
try{
	var fromTimeSplit  = fromTime.split(":");
	var toTimeSplit    = toTime.split(":");
	var fromHour       = parseInt(eval(fromTimeSplit[0]));
	var fromMin        = parseInt(eval(fromTimeSplit[1]));
	var toHour         = parseInt(eval(toTimeSplit[0]));
	var toMin 		   = parseInt(eval(toTimeSplit[1]));
	var hourDifference = toHour-fromHour;
	var minDifference  = toMin-fromMin;
	if(hourDifference<0){
		alert(""+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be greater than "+document.getElementById(fromLabName).innerHTML.toLowerCase());
		document.getElementById(fieldName).focus();
		return false;
	}if(hourDifference==0 && minDifference<=0){
		alert(""+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be greater than "+document.getElementById(fromLabName).innerHTML.toLowerCase());
		document.getElementById(fieldName).focus();
		return false;
	}
	}catch(e)
	{
	
	}
	return true;
}

/* ===================================================================================
	Function	: timeDifference
	Input		: fromTime, toTime, fieldName, fromLabName, toLabName  : String 	
	Explanation : Calculate the difference between two times 
				  i.e. between fromTime and toTime
	Retrieval	: boolean : If toTime is greater than fromTime then return 
							true else return false
	===================================================================================*/
function timeDifferenceEqual(fromTime, toTime, fieldName, fromLabName, toLabName){

	var fromTimeSplit  = fromTime.split(":");
	var toTimeSplit    = toTime.split(":");
	var fromHour       = parseInt(eval(fromTimeSplit[0]));
	var fromMin        = parseInt(eval(fromTimeSplit[1]));
	var toHour         = parseInt(eval(toTimeSplit[0]));
	var toMin 		   = parseInt(eval(toTimeSplit[1]));
	var hourDifference = toHour-fromHour;
	var minDifference  = toMin-fromMin;
	if(hourDifference<0){
		alert(""+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be greater or equal to "+document.getElementById(fromLabName).innerHTML.toLowerCase());
		document.getElementById(fieldName).focus();
		return false;
	}if(hourDifference==0 && minDifference<0){
		alert(""+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be greater or equal to "+document.getElementById(fromLabName).innerHTML.toLowerCase());
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}


/* ==================================================================
	Function	: dateDifference
	Input		: fromDate, toDate, fieldName, fromLabName, toLabName   : String 				  				  
	Explanation : Calculate the difference between two dates 
				  i.e. fromDate and toDate
	Retrieval	: boolean : If toDate is greater than fromDate then return 
							true else return false even equal also return false
	===================================================================================*/
function dateDifference(fromDate, toDate, fieldName, fromLabName, toLabName){
	
	strDate1 = fromDate.split("-"); 
	starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	strDate2 = toDate.split("-"); 
	endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	
	if(endtime <= starttime) 
	{ 
		alert(""+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be greater than "+document.getElementById(fromLabName).innerHTML.toLowerCase());
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}
/* ==================================================================
	Function	: dateDifferenceEqual
	Input		: fromDate, toDate, fieldName, fromLabName, toLabName   : String 				  				  
	Explanation : Calculate the difference between two dates 
				  i.e. fromDate and toDate
	Retrieval	: boolean : If toDate is greater or equal to fromDate then return 
							true else return false if both r equal return true
	===================================================================================*/
function dateDifferenceEqual(fromDate, toDate, fieldName, fromLabName, toLabName){
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 

	if(endtime < starttime) 
	{ 
		alert(""+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be greater or equal to "+document.getElementById(fromLabName).innerHTML.toLowerCase());
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}



/* ===================================================================================
	Function	: dateBetweenTwoDates
	Input		: fromDate, toDate, fieldName, fromLabName, toLabName   : String 				  				  
	Explanation : Calculate the difference between two dates 
				  i.e. fromDate and toDate
	Retrieval	: boolean : If toDate is greater than fromDate then return 
							true else return false
	===================================================================================*/
function dateBetweenTwoDates(fieldName, labelName)
{	
	var fromDate     = document.getElementById(fieldName[0]).value;
	var toDate       = document.getElementById(fieldName[1]).value;
	var enteredDate  = document.getElementById(fieldName[2]).value;
	
	var strDate   = fromDate.split("-"); 
	var starttime = new Date(strDate[2],strDate[1]-1,strDate[0]); 
	
	var endDate   = toDate.split("-"); 
	var endtime   = new Date(endDate[2],endDate[1]-1,endDate[0]); 
	
	var validDate   = enteredDate.split("-");
	var validTime   = new Date(validDate[2],validDate[1]-1,validDate[0]); 
	
	if((validTime < starttime) || (validTime > endtime)) 
	{ 
		alert(""+document.getElementById(labelName[2]).innerHTML.toLowerCase()+" should be between "+document.getElementById(labelName[0]).innerHTML.toLowerCase()+" and "+document.getElementById(labelName[1]).innerHTML.toLowerCase());
		document.getElementById(fieldName[2]).focus();
		return false;
	}
	return true;
}

/* ===================================================================================
	Function	: dateCheckWithToday
	Input		: fieldName, fromLabName   : String 				  				  
	Explanation : Check whether the entered date is less than today's date
	Retrieval	: boolean : If entered date is greater than today's date then return 
							true else return false
	===================================================================================*/
function dateCheckWithToday(fieldName, fromLabName){
	var enteredDate = document.getElementById(fieldName).value;
	var strDate   = enteredDate.split("-"); 
	var starttime = new Date(strDate[2],strDate[1]-1,strDate[0]); 
	
	var todayDate = new Date();
	var day       = todayDate.getDate();
	var month	  = todayDate.getMonth();
	var year 	  = todayDate.getFullYear();
	endtime 	  = new Date(year,month,day); 
	
	if(eval(starttime) < eval(endtime)) 
	{ 
		alert(""+document.getElementById(fromLabName).innerHTML.toLowerCase()+" should be greater or equal to today's date");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}

/* ===================================================================================
	Function	: dateCheckLessThanToday
	Input		: fieldName, fromLabName   : String 				  				  
	Explanation : Check whether the entered date is less than today's date
	Retrieval	: boolean : If entered date is greater than today's date then return 
							true else return false
	===================================================================================*/
function dateCheckLessThanToday(fieldName, fromLabName){
	var enteredDate = document.getElementById(fieldName).value;
	var strDate   = enteredDate.split("-"); 
	var starttime = new Date(strDate[2],strDate[1]-1,strDate[0]);
	 
	var todayDate = new Date();
	var day       = todayDate.getDate();
	var month	  = todayDate.getMonth();
	var year 	  = todayDate.getFullYear();
	endtime = new Date(year,month,day); 
	
	if(starttime > endtime) 
	{ 
		alert(""+document.getElementById(fromLabName).innerHTML.toLowerCase()+" should be Less or equal to today's date");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}

/* ===================================================================================
	Function	: checkDatewithCurrentDate
	Input		: date, fromTime, labName   : String 				  				  
	Explanation : Calculate the difference between the entered date and current 
				  system date as well as calculate difference between entered 
				  time and current time
	Retrieval	: boolean : If entered date and time are greater than the 
							current system date and time then return true 
							esle return false
	===================================================================================*/
function checkDatewithCurrentDate(date, fromTime, labName){
		todayDate 	= new Date();
		var year   	= todayDate.getFullYear();
		var month   = todayDate.getMonth();
		var day     = todayDate.getDate();
		var hour    = todayDate.getHours();
		var min     = todayDate.getMinutes();
		
		enteredDate = date.split("-");
		enteredTime = fromTime.split(":");
		enteredHour = parseInt(enteredTime[0]);
		enteredMin  = parseInt(enteredTime[1]);
		
		hourDiff    = enteredHour-hour;
		minDiff     = enteredMin-min;
		
		startTime   = new Date (enteredDate[2], enteredDate[1]-1, enteredDate[0]);
		endTime     = new Date(year, month, day);
		
		var diff = startTime-endTime;
		
		if(startTime<endTime){
			alert(""+document.getElementById(labName).innerHTML.toLowerCase()+" date should be greater or equal to today's date");
			return false;
		}if(year==enteredDate[2] && month==enteredDate[1]-1 && day==enteredDate[0] && hourDiff<0){
			alert(""+document.getElementById(labName).innerHTML.toLowerCase()+" time should be greater than current time");
			return false;
		}if(year==enteredDate[2] && month==enteredDate[1]-1 && day==enteredDate[0] &&hourDiff==0 && minDiff<=0){
			alert(""+document.getElementById(labName).innerHTML.toLowerCase()+" time should be greater than current time");
			return false;
		}
		return true;
}

/* ===================================================================================
	Function	: checkTimeWithCurrentTime
	Input		: code   : String 				
	Explanation : Calculate the difference between the entered time and current 
				  system time 
	Retrieval	: boolean : If entered time is greater than the current system time 
							then return true esle return false
	===================================================================================*/
	
	function checkTimeWithCurrentTime(time){
		var enteredTime = document.getElementById(time).value;
		var timeSplit	= enteredTime.split(":");
		var enteredHour = timeSplit [0];
		var enteredMin  = timeSplit [1];
		
		todayDate 	= new Date();
		var hour    = todayDate.getHours();
		var min     = todayDate.getMinutes();
		
		hourDiff    = enteredHour-hour;
		minDiff     = enteredMin-min;
		
		if(hourDiff<0){
			alert("Time should be greater than current time");
			document.getElementById(time).focus();
			return false;
		}if(hourDiff==0 && minDiff<0){
			alert("Time should be greater than current time");
			document.getElementById(time).focus();
			return false;
		}
		return true;
	}

/* ===================================================================================
	Function	: callDelete
	Input		: code   : String 				
	Explanation : Check whether a record is available to delete or not
				  also ask user to confirm that he is actually want to 
				  delete record
	Retrieval	: boolean : If no record is there or user does not click on ok 
						    button of the confirm window then return false else
							return true
	===================================================================================*/
function callDelete(code)
   {
	   if(document.getElementById(code).value=="")
	   {
		   alert("Please select a record to delete");
		   return false;
	   } 
      var conf=confirm("Do you really want to delete this record ?");
  			if(conf) 
  			{
  				return true;
  			}
	  		else
	  		{
	  			 return false;
	  		}
	  	return true;
 }
 /* ===================================================================================
	Function	: callCancel
	Input		: code   : String 				
	Explanation : Check whether a record is available to Cancel or not
				  also ask user to confirm that he is actually want to 
				  Cancel record
	Retrieval	: boolean : If no record is there or user does not click on ok 
						    button of the confirm window then return false else
							return true
	===================================================================================*/
function callCancel(code)
   {
	   if(document.getElementById(code).value=="")
	   {
		   alert("Please select a record to cancel");
		   return false;
	   } 
      var conf=confirm("Do you really want to cancel this record ?");
  			if(conf) 
  			{
  				return true;
  			}
	  		else
	  		{
	  			 return false;
	  		}
	  	return true;
 }
 /* ===================================================================================
	Function	: callReport
	Input		: name   : String
	Explanation : Call report function of the action class to generate the report
	===================================================================================*/
 function callReport(name)
  	{
  	  	document.getElementById('paraFrm').target = "_blank";
  		document.getElementById('paraFrm').action = name;
  		document.getElementById('paraFrm').submit();
  		document.getElementById('paraFrm').target = "main";
  	}
  	
 /* ===================================================================================
	Function	: callReportforSelected
	Input		: name, fieldValue  : String
	Explanation : Call report function of the action class to generate the report
				  It also checks whether any record is selected or not
	Retrieval	: boolean : If no record is there then return false else
							return true
	===================================================================================*/
 function callReportforSelected(name, fieldValue)
  	{
  	if(document.getElementById(fieldValue).value != "")
  		{
	  	  	document.getElementById('paraFrm').target = "_blank";
	  		document.getElementById('paraFrm').action = name;
	  		document.getElementById('paraFrm').submit();
	  		document.getElementById('paraFrm').target = "main";
	  	}
	  else{
			alert("Please select an application to generate report !");
			return false;
		}
  	}
  	
 /* ===================================================================================
	Function	: checkMandatory
	Input		: name, labName  : String
	Explanation : Check for all mandatory fields whether they are empty or not
	Retrieval	: boolean : If field is empty or null then return false else
							return true
	===================================================================================*/
function checkMandatory(name, labName, flag){
	for (i=0;i<name.length;i++){
		var val   = document.getElementById(name[i]).value;
		var value = LTrim(RTrim(val));
		var iChars = "`\\\'/\" ";
		document.getElementById(name[i]).value=value;
		if(LTrim(val)==""){
			alert("Please "+flag[i]+" "+labName[i]);
			document.getElementById(name[i]).focus();
			return false;
		}
	
	}
	return true;
}
/* ===================================================================================
	Function	: f9specialchars
	Input		: name, labName  : String
	Explanation : Restrict the Special characters in f9 windows 
	Retrieval	: boolean : If Special characters is there return false else
							return true
	===================================================================================*/
function f9specialchars(name)
{
	
	for (i=0;i<name.length;i++){	
		
		var val   = document.getElementById(name[i]).value;
		//alert(document.getElementById(name[i]).value);
		var value = LTrim(RTrim(val));
		var iChars = "`\\\'\"";
		document.getElementById(name[i]).value=value;
				
		for (var j = 0; j < val.length; j++){
			if (iChars.indexOf(val.charAt(j)) != -1){
				alert ("Special characters are not Allowed \n   e.g.  [ \\ , \" ,  \' ]");
				document.getElementById(name[i]).focus();
				return false;				  	
			}
		}
	}	
	return true;
}

/* ===================================================================================
	Function	: callChk
	Input		: id  : String
	Explanation : change the status of check box as mouse clicked on check box
	===================================================================================*/
function callChk(id){
	if(document.getElementById(id).value=='Y'){
		document.getElementById(id).value='N';
	}else  if(document.getElementById(id).value=='N'){
		document.getElementById(id).value='Y';
	} 
}

/* ===================================================================================
	Function	: Trim
	Explanation : to avoid the blank spaces in the field to be saved
	Retrieval	: String : return the value after ignoring spaces
   ===================================================================================*/
	function trim(str) {     
	if(!str || typeof str != 'string')         
		return null;     
	return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
	}
	


/* ===================================================================================
	Function	: LTrim
	Input		: value  : String
	Explanation : to avoid the blank spaces in the left hand side to be saved
	Retrieval	: String : return the value after ignoring spaces to the left
   ===================================================================================*/
function LTrim(value) {
	var re = /\s*((\S+\s*)*)/;
	return value.replace(re, "$1");
}

/* ===================================================================================
	Function	: RTrim
	Input		: value  : String
	Explanation : to avoid the blank spaces in the right hand side to be saved
	Retrieval	: String : return the value after ignoring spaces to the right
   ===================================================================================*/
function RTrim( value ) {
	
	var re = /((\s*\S+)*)\s*/;
	return value.replace(re, "$1");
}

/* ===================================================================================
	Function	: checkBlankSpace
	Input		: value  : String
	Explanation : to avoid the blank spaces in the left hand side to be saved
	Retrieval	: String : return the value after ignoring spaces to the left
   ===================================================================================*/
function checkBlankSpace(obj) {
	var val	  = obj.value;
	var value = LTrim(RTrim(val));
	obj.value=value;
	return true;
}

/* ===================================================================================
	Function	: validatePassword
	Input		: password, oldPass, newPass, confPass  : String
	Explanation : to check old password and new password
	Retrieval	: String : if there is any mistake then it will return false else
				  return true
   ===================================================================================*/
function validatePassword(password, oldPass, newPass, confPass){
	if(password!=oldPass){
		alert("Please enter old password correctly");
		return false;
	}
	if(newPass.length<4){
		alert("New password should be of atleast 4 characters");
		return false;
	}
	if(oldPass==newPass){
		alert("Old password and new password must not be same");
		return false;
	}
	if(newPass!=confPass){
		alert("New password and confirm password must be same");
		return false;
	}
	return true;
	}
	
	/* ===================================================================================
	Function	: incrToDate
	Input		: fromDate, toDate  : String
	Explanation : to auto increase to date according to from date
	Retrieval	: boolean : return true
   ===================================================================================*/
	function incrToDate(fromDate, toDate)
  	{
  		var fromDate = document.getElementById(fromDate).value;
  		var fromDateSplit = fromDate.split("-");
  		var date = fromDateSplit[0];
  		var month = fromDateSplit[1];
  		var year = parseInt(fromDateSplit[2]);
  		document.getElementById(toDate).value = date + '-'+ month + '-' + parseInt(year+1);
  		return true;
  	}
  	
	
	/* ===================================================================================
	Function	: checkYear
	Input		: fieldName, labelName  : String
	Explanation : to check the length of the year 
	Retrieval	: boolean : if the entered year contains 4 digits then return true
							else return false
   ===================================================================================*/
   function checkYear(name, labelName){
   		var year = document.getElementById(name).value;
   		if(year == "") {
   			return true;
   		}
   		if(year.length < 4) {
   			alert(document.getElementById(labelName).innerHTML + " should have atleast 4 digits");
   			document.getElementById(name).focus();
   			return false;
   		}
   		return true;
   }
   
   /* ===================================================================================
	Function	: checkPercentage
	Input		: obj, labelName  : String
	Explanation : to check the length of the year 
	Retrieval	: boolean : if the entered percentage is less than or equal to 100 then 
							return true else return false
   ===================================================================================*/
   function checkPercentage(name, labelName){
	   	var percentage = document.getElementById(name).value;
	   	if(percentage=="")return true;
	   	if(percentage>100){
	   		alert(''+document.getElementById(labelName).innerHTML.toLowerCase()+" should not be greater than 100");
	   		document.getElementById(name).focus();
	   		return false;
	   	}
   		return true;
   }
   
   /* ===================================================================================
	Function	: checkPincode
	Input		: obj, labelName  : String
	Explanation : to check the length of the year 
	Retrieval	: boolean : if the entered year contains 4 digits then return true
							else return false
   ===================================================================================*/
   function checkPincode(name, labelName){
   		var pinCode = document.getElementById(name).value;
   		if(pinCode=="")return true;
   		if(pinCode.length<6){
   			alert(''+document.getElementById(labelName).innerHTML.toLowerCase()+" should have atleast 6 digits");
   			document.getElementById(name).focus();
   			return false;
   		}
   		return true;
   }
   
  /* ===================================================================================
	Function	: checkPincode
	Input		: obj, labelName  : String
	Explanation : to check the length of the year 
	Retrieval	: boolean : if the entered year contains 4 digits then return true
							else return false
   ===================================================================================*/
  function howLong(fieldName){ 
  		var dob = document.getElementById(fieldName).value;
  		var bDate =dob;
  		var afterSplit =bDate.split("-");
		var dy =afterSplit[0];
 		var mo =afterSplit[1];
  		var yr = afterSplit[2];
  		var nDate = new Date();  // current date (local)
  		var nowTime = nDate.getTime();  // current time (UTC)
  		var thenTime = Date.UTC(yr, mo-1, dy);  // specified time (UTC)
  		var thisYear = nDate.getFullYear();
  		var thisMonth = nDate.getMonth();
  		var thisDay = nDate.getDay();
			
		if (nowTime >= thenTime) {   //-----------------Past or present time
			if ((thisMonth > mo-1) || ((thisMonth == mo-1) && (thisDay >= dy))) {
  				whYrs = thisYear - yr;
  				spareDys = parseInt((nowTime - Date.UTC(thisYear,mo-1,dy))/(3600000*24));
   				if ((mo == 2 && dy == 29)  && ((thisYear%4 != 0) || (thisYear%100 == 0 &&  thisYear%400 != 0))){
   					spareDys = spareDys + 1
   				}
  			} else {
	  				whYrs = thisYear - yr - 1;
	  				spareDys = parseInt((nowTime - Date.UTC(thisYear-1,mo-1,dy))/(3600000*24));
	   				if ((mo == 2 && dy == 29)  && (((thisYear-1)%4 != 0) || ((thisYear-1)%100 == 0 && (thisYear-1)%400 != 0))) {
	   					spareDys = spareDys + 1
	   				}
	   			}
 		} else {   //----------------------------Future time
				if ((thisMonth < mo-1) || ((thisMonth == mo-1)&& (thisDay <= dy))) {
  					whYrs = yr - thisYear;
  					spareDys = parseInt((thenTime - Date.UTC(yr,thisMonth,thisDay))/(3600000*24));
   					if ((thisMonth == 1 && thisDay == 29)  && ((yr%4 != 0) || (yr%100 == 0 && yr%400 != 0))) {
   						spareDys = spareDys - 1
   					}
  				} else {
  					whYrs = yr - thisYear - 1;
  					spareDys = parseInt((thenTime - Date.UTC(yr-1,thisMonth,thisDay)) /(3600000*24));
   					if ((thisMonth == 1 && thisDay == 29)  && (((yr-1)%4 != 0) || ((yr-1)%100 == 0 && (yr-1)%400 !=   0))) {
   						spareDys = spareDys - 1;
   					}
   			}
  		}
		spareDys = spareDys/365;
		whYrs += spareDys;
		if(whYrs<16){
			alert("Your age is not eligible to apply.");
			document.getElementById(fieldName).focus();
    		return false;
		}
		return true;
	}
	
	 /* ===================================================================================
	Function	: callLength
	Input		:	type,lengthType,maxLenght
	Explanation : to set remaining characters of textarea 

   ===================================================================================*/
	 function callLength(type,lengthType,maxLenght){
			var cmt = document.getElementById('paraFrm_'+type).value;
			var remain = eval(maxLenght) - eval(cmt.length);
			document.getElementById('paraFrm_'+lengthType).value = remain;
			if(eval(remain)< 0){
				document.getElementById('paraFrm_'+type).style.background = '#FFFF99';
			}
			else 
				document.getElementById('paraFrm_'+type).style.background = '#FFFFFF';
	}
	
	 /* ===================================================================================
	Function	: validateBlank
	Input		: name, labName, flag  : String
	Explanation : Check for all mandatory fields whether they are empty or not
	Retrieval	: boolean : If field is empty or null then return false else
							return true
	===================================================================================*/
	function validateBlank(name, labName, flag){
		for (i=0;i<name.length;i++){
			var value = trim(document.getElementById(name[i]).value);
			document.getElementById(name[i]).value=value;
			if(value==""){
				alert("Please "+flag[i]+" "+document.getElementById(labName[i]).innerHTML.toLowerCase());
				document.getElementById(name[i]).focus();
				return false;
			}
		}
		return true;
	}
	
	
	 /* ===================================================================================
	Function	: callWindow
	Input		: fieldName,windowName,readFlag
	Explanation :display the pop up text area
	Retrieval	:return the window value in textara
	===================================================================================*/
	
	function callWindow(fieldName,windowName,readFlag) {
		mytitle=document.getElementById(windowName).innerHTML;
		 if (window.event){
		window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&readFlag='+readFlag+'','','width=580,height=430,scrollbars=no,resizable=no,top=250,left=350');
		 }else{
		 window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&readFlag='+readFlag+'','','width=650,height=450,scrollbars=no,resizable=no,top=250,left=350');
		 }
		document.getElementById('paraFrm').target ="";	  
	}
	
	function callWindow(fieldName,windowName,readFlag,charCount,maxLength) {
	   mytitle=document.getElementById(windowName).innerHTML;
	   
	    if (window.event){
		window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&charCntName='+charCount+'&maxLength='+maxLength+'&readFlag='+readFlag+'','','width=580,height=430,scrollbars=no,resizable=no,top=250,left=350');
		 }else{
		 window.open('../pages/common/popupTextArea.jsp?fieldName='+fieldName+'&windowName='+mytitle+'&charCntName='+charCount+'&maxLength='+maxLength+'&readFlag='+readFlag+'','','width=650,height=450,scrollbars=no,resizable=no,top=250,left=350');
		 }
		document.getElementById('paraFrm').target ="";	 
	}
	
 /* ===================================================================================
	Function	: callNumOfFraction
	Input		: fieldName,numOfDec
	Explanation :display the number of fraction according to given number
	Retrieval	:display the value in textfiled
	===================================================================================*/
 function callNumOfFraction(fieldName,numOfDec)
{ 
 var MathText = document.getElementById(fieldName).value;  
 var float;
 var concatValue ;
 
 if(MathText!="" && MathText!="null"){ 
	  var digitValue = MathText.split(".");
	  var digit  = digitValue[0];   
	    if(MathText.indexOf(".")==-1){ 
	          concatValue = MathText;
	    }else{ 
		    float = digitValue[1];  
		    var numFloat = float.substring(0,numOfDec);  
		    concatValue = digit +"."+ numFloat; 
	    } 
	  document.getElementById(fieldName).value =concatValue;
  }
}

 /* ===================================================================================
	Function	: setFinancialYear
	Input		: fromFieldName,toFieldName
	
	===================================================================================*/
 function setFinancialYear(fromFieldName,toFieldName)
{ 

 var tDay = new Date();
 var tYear = tDay.getFullYear();
 document.getElementById(fromFieldName).value=tYear;
 document.getElementById(toFieldName).value= eval(tYear) + 1;
 
 
}
/* ===================================================================================
	Function	: setCurrentYear
	Input		: fieldName
	
	===================================================================================*/
 function setCurrentYear(fieldName)
{ 
 var tDay = new Date();
 var tYear = tDay.getFullYear();
 document.getElementById(fieldName).value=tYear; 
}


/* ===================================================================================
	Function	: FOR CLEAR THE TEXTFILED VALUE AND ASSIGN TEXT COLOR AS BLACK
	Input		: fieldName
	
	===================================================================================*/
function clearText(fieldName,format){
	if(document.getElementById(fieldName).value == format){
		document.getElementById(fieldName).value ='';
		document.getElementById(fieldName).style.color = 'black';
	}
}

/* ===================================================================================
	Function	: FOR CLEAR THE MULITIPLE TEXTFILED VALUE AND ASSIGN TEXT COLOR AS BLACK
	Input		: fieldName
	
	===================================================================================*/
	
  function clearTextArray(fieldName,format){ 
   for(i=0;i <fieldName.length;i++){ 
		if(document.getElementById(fieldName[i]).value == format){
			document.getElementById(fieldName[i]).value ='';
			document.getElementById(fieldName[i]).style.color = 'black';
		}
	  }
   }
   
   /* ===================================================================================
	Function	: FOR CLEAR THE ITERATOR TEXTFILED VALUE AND ASSIGN TEXT COLOR AS BLACK
	Input		: fieldName 
	===================================================================================*/
   
     function clearTextIterator(fieldName,format,length,startId){ 
       for(i=startId;i <(length+startId);i++){ 
		if(document.getElementById(fieldName+i).value == format){
			document.getElementById(fieldName+i).value ='';
			document.getElementById(fieldName+i).style.color = 'black';
		}
	  }
   }
   
 /* ===================================================================================
	Function	: FOR SET dd-mm-yyyy FORMAT VALUE TO THE TEXTFILED IF THE TEXTFILED VALUE IS BALNK
	Input		: fieldName
	
	===================================================================================*/

function setText(fieldName,format){
	
	if(document.getElementById(fieldName).value == '' ){
		document.getElementById(fieldName).value =format; 
		document.getElementsByName(fieldName).value =format;
		document.getElementById(fieldName).style.color = '#ACACAC';
	}else if(document.getElementById(fieldName).value==format){
			document.getElementById(fieldName).style.color = '#ACACAC';
		}
	} 
   
 /* ===================================================================================
	Function	: FOR SET dd-mm-yyyy FORMAT VALUE TO MULTIPLE TEXTEFILED IF THE TEXTFILED VALUE IS BALNK
	Input		: fieldName
	
	===================================================================================*/
	
   function setTextArray(fieldName,format){ 
   
     for(i=0;i <fieldName.length;i++){ 
		if(document.getElementById(fieldName[i]).value == ''){
			document.getElementById(fieldName[i]).value =format;
			document.getElementById(fieldName[i]).style.color = '#ACACAC';
		}else if(document.getElementById(fieldName[i]).value==format){
			document.getElementById(fieldName[i]).style.color = '#ACACAC';
		}
	  }  
   	}
   
	function callPage(id, pageImg, totalPageHid, action) {		
		pageNo = document.getElementById('pageNoField').value;	
		actPage = document.getElementById('myPage').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField').value = actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField').value=actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPage').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
	}
	
	
		function callPageText(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;
		 	var actPage = document.getElementById('myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPage').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}
	
function textCounter(field,  maxlimit) {
	if (field.value.length > maxlimit){
		field.value = field.value.substring(0, maxlimit);
		return false;
	}
	return true;
}

function checkMaxLenthTextarea(fieldName,remainCharFieldName,maxLength)
 { 
	   var fieldNameVal =document.getElementById(fieldName).value;
	   var textVal="";
	   var charStr;
			for(var i =0; i < eval(fieldNameVal.length);i++)
			 {
			   charStr =fieldNameVal.substring(i,eval(i+1));
			     if(charStr=='\n') { 
				    textVal=textVal+'  '; 
				 }else {
				     textVal=textVal+charStr;
				    }
			}  // end of for loop
		 			 
		var remain = maxLength - eval(textVal.length);
		document.getElementById(remainCharFieldName).value = remain;
		
		if(eval(remain)< 0){ 
			document.getElementById(fieldName).style.background = '#FFFF99';
		}else 
		{
		 document.getElementById(fieldName).style.background = '#FFFFFF';
		 }
 }
 
 
 function checkMaxLenthTextareaInList(fieldName,maxLength)
 { 
   
	   var fieldNameVal =document.getElementById(fieldName).value;
	   var textVal="";
	   var charStr;
			for(var i =0; i < eval(fieldNameVal.length);i++)
			 {
			   charStr =fieldNameVal.substring(i,eval(i+1));
			     if(charStr=='\n') { 
				    textVal=textVal+'  '; 
				 }else {
				     textVal=textVal+charStr;
				    }
			}  // end of for loop
		 			 
		var remain = maxLength - eval(textVal.length);
		document.getElementById(remainCharFieldName).value = remain;
		
		if(eval(remain)< 0){ 
			document.getElementById(fieldName).style.background = '#FFFF99';
		}else 
		{
		 document.getElementById(fieldName).style.background = '#FFFFFF';
		 }
 }
	/*
	CREATE DYNAMIC TABLE
	tableId=TBALE ID
	fields=FILED NAME IN ARRAY
	type=TYPE(H=HIDDEN,
			  S=SPROPERTY
			  ,D=DELETE ICON
			  N=SR NO)
	*/
	/*	
	addRowToTable('tableID','qty,unit,price,manufacture,mrfPart,vendorPart,delete','S,S,S,S,S,S,D','qty,unit,price,manufacture,mrfPart,vendorPart,D')"
	*/
	
	
	function addRowToTable(tableId,fields,type,ittfields)
		{
		  fieldList = fields.split(",");
		  ittfieldList = ittfields.split(",");
		   fieldType = type.split(",");
		  var tbl = document.getElementById(tableId);
		  var lastRow = tbl.rows.length;
		  var iteration = lastRow;
		  var row = tbl.insertRow(lastRow);
		  var cellRight ="";	
		  var el="";
		  for(i = 0; i < fieldList.length; i++) {		 
		  if(fieldType[i]=='D'){
		   		cellRight = row.insertCell(i);
			   var column3 = document.createElement('img');
			  column3.type='image';
			  column3.src="../pages/common/css/icons/delete.gif";
			  column3.align='absmiddle';
			  column3.id='img'+ i;
			  column3.theme='simple';
			  cellRight.align='center';
			  cellRight.className='sortableTD';				
			  column3.onclick=function(){
			  try {
			   	deleteSelectedRow(this);			  	
			  }catch(e){alert(e);}
			  };
			  cellRight.appendChild(column3);
		  }
		  else{			  
			  
			  if(fieldType[i]=='N'){
			  	 el = document.createElement('input');
			  	 cellRight = row.insertCell(i);
			     el.style.border = 'none';
			     el.type = 'text';
			     el.readOnly =true;			     
			     el.value=iteration;
			  	 el.name = ittfieldList[i];
			 	 el.size = 10;
			   	 cellRight.className='sortableTD';
			  	 cellRight.appendChild(el);
			  }
			  
			  
			 else if(fieldType[i]=='S'){
			  	 el = document.createElement('input');
			  	 cellRight = row.insertCell(i);
			     el.style.border = 'none';
			     el.type = 'text';
			     el.readOnly =true;			     
			     el.value=document.getElementById('paraFrm_'+fieldList[i]).value;
			  	 el.name = ittfieldList[i];
			 	 el.size = 10;
			   	 cellRight.className='sortableTD';
			  	 cellRight.appendChild(el);
			  }
			  else if(fieldType[i]=='H'){
			   			   		   
			  	el = document.createElement('input');
				el.setAttribute('type', 'hidden');
				el.setAttribute('value', document.getElementById('paraFrm_'+fieldList[i]).value);
				el.setAttribute('name', ittfieldList[i]);
				cellRight.appendChild(el);		  
			   }
				 
		  }		  
		  }		 
		   for(i = 0; i < fieldList.length; i++) {
		   if(fieldType[i]=='H'||fieldType[i]=='S'){
		    document.getElementById('paraFrm_'+fieldList[i]).value='';	
		    }
		   }  
		 }
	function  deleteSelectedRow(obj){
		var con=confirm('Do you want to delete the record(s) ?');
	 	if(con){
			var delRow = obj.parentNode.parentNode;
			var tbl = delRow.parentNode.parentNode;
			var rIndex = delRow.sectionRowIndex;
			var rowArray = new Array(delRow);
			deleteRows(rowArray);
		}
	}
	
	function deleteRows(rowObjArray){
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
		}
	}
	
</script>