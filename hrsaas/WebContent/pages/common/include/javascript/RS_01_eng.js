
//------------------------------------------------------------------------------
var netscape_acctno = "";
var tot_accounts 	= 0;
var	acctno			= "";
var	custname		= "";
var	title			= "";
var	currYear		= "";
var isLOA			= false;
var count 	 = 0;
var accounts = new Array ();
var balance  = new Array ();
var acctstat = new Array ();
var branch   = new Array ();
var namccy   = new Array ();
var codccy   = new Array ();
var product   = new Array ();
var datestr  = "";
var ie4   = (document.all)? true: false;
var isNS4 = (document.layers) ? true : false;
var isIE5 = (document.all && document.getElementById) ? true : false;
var isNS6 = (!document.all && document.getElementById) ? true : false;

var DEMAT_MNEMONIC="demat";
var ACCOUNT_MNEMONIC="accounts";
var TPT_MNEMONIC="transfer";
var BILL_MNEMONIC="bills";
var CARD_MNEMONIC="cards";
var BROKER_MNEMONIC="broker";
var MERCH_MNEMONIC="merchant";
var ALERT_MNEMONIC="alert";
var REG_MNEMONIC="onlinereg";
var ADDR_MNEMONIC="chgaddr";
var PWD_MNEMONIC="chgpwd";
var EMAIL_MNEMONIC="emailsupp";
var INV_MNEMONIC="fcatis";
var MOBBKG_MNEMONIC="mobilebkg";
//------------------------------------------------------------------------------
var FORMAT_AMOUNT = '###,###,###,###,###,###.##';

function formatAmount (p_amount) {

		var l_amt_i,l_fmt_i,l_str='';
		var l_arr = new Array ();


		if (p_amount.indexOf (".") != -1) {
			l_arr = p_amount.split (".");
			if (l_arr.length == 2) {
				if (l_arr [0]== "") {
					l_arr [0] = 0;
				}
				if (l_arr [1].length > 2) {
					l_arr [1] = l_arr [1].substring (0,2);
				} else if (l_arr [1].length == 1) {
					l_arr [1] = l_arr [1] + "0";
				}

				p_amount = l_arr [0] + "." + l_arr[1]
			}

		} else {
			p_amount = p_amount + ".00";
		}
		l_amt_i = p_amount.length - 1;
		l_fmt_i = FORMAT_AMOUNT.length - 1;
		while (l_amt_i >= 0){

				if (p_amount.charAt (l_amt_i) == '.') {
					l_str = "." + l_str;
					l_amt_i--;
					if (FORMAT_AMOUNT.charAt (l_fmt_i) == '.') {
						l_fmt_i--;
					}
				}
				if ((l_fmt_i < 0) || (FORMAT_AMOUNT.charAt (l_fmt_i) == '#') || (p_amount.charAt (l_amt_i) == '-')) {
					l_str = p_amount.charAt (l_amt_i) + l_str;
					l_amt_i--;
					l_fmt_i--;
				} else {
					l_str = FORMAT_AMOUNT.charAt (l_fmt_i) + l_str;
					l_fmt_i--;
				}

		}
		return l_str;
	}
//-----------------------------------------------------------------------------
function is_space (
	p_string
) {

	var l_new_string = p_string, l_i;
	var l_length = p_string.length;

	for (l_i=0;l_i<=l_length;l_i++) {
		var l_chr = l_new_string.charAt(l_i);
		if(l_chr == " ") {
			l_new_string = l_new_string.replace (l_chr,"+");
		}
	}
	return (escape (l_new_string));
}
//-----------------------------------------------------------------------------
function cancel_request ()
{
	window.history.back();

    return false;
}
//-----------------------------------------------------------------------------
function date_val (p_date, p_string)
{

	var from_date 	= new Array ();
	var to_date		= new Array ();

	var l_separator = "/";
	var l_day	= 0;
	var l_mon 	= 1;
	var l_year 	= 2;
	var l_err	= 0;
	var l_date_arr = new Array ();
	var l_string = p_string;

	var l_date = p_date ;

	if (l_date.indexOf('/') == -1)   {
		l_err = 1;
		l_errstring = "Date is invalid";
	}

	l_date_arr	= l_date.split(l_separator);

	if (l_date_arr.length != 3)
	{
		l_err=1;
		l_errstring = "Date is invalid.";
	}
	else
	{
		l_d = l_date_arr[l_day];
		l_m = l_date_arr[l_mon];
		l_y = l_date_arr[l_year];


		if ((isNaN(l_d)) || (isNaN(l_m)) || (isNaN(l_y)))
		{
			l_err=1 ;
			l_errstring = "Date is invaid.";
		}

		if (l_m < 1 || l_m > 12)
		{
			l_err = 1;
			l_errstring = "Month is invalid.";
		}

		if (l_d < 1 || l_d > 31)
		{
			l_err = 1 ;
			l_errstring = "Date is invalid.";
		}

		if (l_y < 1900 || l_y > 9999)
		{
			l_err = 1  ;
			l_errstring = "Year is invalid.";
		}

		if (l_m == 2)
		{
			var l_g=parseInt(l_y%4)
			var l_k=parseInt(l_y%100)
			var l_m=parseInt(l_y/100)

			if (isNaN(l_g))
			{
				l_err=1 ;
				l_errstring = "Date is invalid.";
			}

			if (l_d > 29)
			{
				l_err = 1 ;
				l_errstring = "Date is invalid.";
			}

			if (l_d == 29 && ((l_y/4)!=parseInt(l_y/4)))
			{
				l_err=1 ;
				l_errstring = "Date is invalid.";
			}

			if(l_k ==0){
				if (l_d == 29 && ((l_m/4)!=parseInt(l_m/4)))
				{
					l_err=1 ;
					l_errstring = "Date is invalid.";
				}
			}
		}

		if (l_d==31 &&
				(l_m != 1 && l_m != 3 &&
					l_m != 5 && l_m != 7 &&
						l_m != 8 && l_m != 10 && l_m != 12)
			)
		{
			l_err = 1 ;
			l_errstring = "Date is invalid.";
		}

	}

	if (l_err==1)
	{
		alert(l_errstring);
		return false;
	}

	return true;
}
//---------------------------------------------------------------------
function validatedate(p_date)
{
	if((p_date.length!=10) ||
		((p_date.charAt(2)!='/') && (p_date.charAt(5)!='/')))
			return false;

	var l_date=p_date.split("/");
	if(isNaN(l_date[0]) || isNaN(l_date[1]) || isNaN(l_date[2]))
		return false;

	if((l_date[2].length == 4)&& (l_date[1] >0 && l_date[1] <13)
			&& (l_date[0] <= daysInMonth(l_date[1], l_date[2]))
			&& l_date[0]>0 && l_date[2]>0)
		return true;
	else
		return false;
}
//---------------------------------------------------------------------
function isFlexatDateLater(p_date) {
	return !compare_date(p_date,parent.frames[0].flexat_date);
}
//---------------------------------------------------------------------
function compare_date(p_startdate, p_enddate) {
	var l_startdate = p_startdate;
	var l_enddate	= p_enddate;
	var l_separator = "/";


	var from_date 	= new Array ();
	var to_date		= new Array ();

	from_date		= l_startdate.split(l_separator);
	to_date			= l_enddate.split(l_separator);

	if(to_date[1].length==1){
	   to_date[1]='0'+to_date[1]
	}
	if(from_date[1].length==1){
	   from_date[1]='0'+from_date[1]
	}
	if(to_date[0].length==1){
       to_date[0]='0'+to_date[0]
	}
	if(from_date[0].length==1){
	   from_date[0]='0'+from_date[0]
	}
	if (to_date[2] < from_date[2]) {
		return false;
	}
	if ((to_date[2] == from_date[2]) && (to_date[1] < from_date[1])) {
		return false;
	}
	if ((to_date[2] == from_date[2]) && (to_date[1] == from_date[1]) && (to_date[0] < from_date[0])) {
		return false;
	}

	return true;
}
//------------------------------------------------------------------------------
function daysInMonth(p_month, p_year){
	switch(p_month){
		case '01':
		case '03':
		case '05':
		case '07':
		case '08':
		case '10':
		case '12':
			return 31;
	   case '02':

		if(isLeapYear(p_year))
		 	return 29;
		else
			return 28;
		case '04':
		case '06':
		case '09':
		case '11':
			return 30;
		default:
			return -1;
	}
}
//-----------------------------------------------------------------------------
function diffDate(p_date1,p_date2){
	var l_date1=p_date1.split("/");
	var l_date2=p_date2.split("/");

	var d1=daysInMonth(l_date1[1],l_date1[2]);
	var d2=daysInMonth(l_date2[1],l_date2[2]);

	var yr=l_date2[2]-l_date1[2];
	var mn=l_date2[1]-l_date1[1];
	var dy=(l_date2[0]-l_date1[0])+1;
	
	if(dy<0){
		mn=mn-1;
			dy=dy+d1;
	}
	if(dy==d2){
		mn=mn+1;
		dy=0;
	}
	if(mn<0){
		yr=yr-1;
		mn=mn+12;
	}
	return dy+"/"+mn+"/"+yr;
}
//-----------------------------------------------------------------------------
function isLeapYear(p_year){
	if(	((p_year%400)==0)
		 || ( ((p_year%4)==0)
		 		&& ((p_year%100)!=0)
		 	 )
	)
		 return true;

	else
		return false;
}


//---------------------------------------------------------------------
function chkFutureDate(p_startdate) {
	var today = new Date ();
	var year =	today.getYear ();
	var month = today.getMonth () + 1;
	var date = 	today.getDate ();

	var l_startdate = p_startdate;
	var l_separator = "/";


	var from_date 	= new Array ();
	var to_date		= new Array;
	to_date[0] = date;
	to_date[1] = month;
	to_date[2] = year;

	from_date		= l_startdate.split(l_separator);

	if(to_date[1].length==1){
	   to_date[1]='0'+to_date[1];
	}
	if(from_date[1].length==1){
	   from_date[1]='0'+from_date[1];
	}
	if(to_date[0].length==1){
       to_date[0]='0'+to_date[0];
	}
	if(from_date[0].length==1){
	   from_date[0]='0'+from_date[0];
	}
	if (!ie4){
		if(to_date[2] >= 100){
			to_date[2] = to_date[2]+1900;
		}
	}

	if (to_date[2] < from_date[2]) {
		return false;
	}
	if ((to_date[2] == from_date[2]) && (to_date[1] < from_date[1])) {
		return false;
	}
	if ((to_date[2] == from_date[2]) && (to_date[1] == from_date[1]) && (to_date[0] < from_date[0])) {
		return false;
	}

	return true;

}
//---------------------------------------------------------------------
function chkCurrentDate(p_startdate) {
	var today = new Date ();
	var year =	today.getYear ();
	var month = today.getMonth () + 1;
	var date = 	today.getDate ();

	var l_startdate = p_startdate;
	var l_separator = "/";


	var from_date 	= new Array ();
	var to_date		= new Array ();
	to_date[0] = date;
	to_date[1] = month;
	to_date[2] = year;

	if (!ie4){
		if(to_date[2] >= 100){
			to_date[2] = to_date[2]+1900;
		}
	}

	from_date		= l_startdate.split(l_separator);

	if ((to_date[2] == from_date[2]) && (to_date[1] == from_date[1])&& (to_date[0] == from_date[0])) {
		return false;
	}

	return true;

}

//---------------------------------------------------------------------

function valXslEmail (fldEmail) {

	if (fldEmail == "") {
		return false;
	} else {
		var amp_idx	=	fldEmail.indexOf("@");
		repeat_atr_idx	=	fldEmail.indexOf ("@",amp_idx+1);

		if (repeat_atr_idx != -1) {
			return false;
		}

		var e_length	=	fldEmail.length;
		if (amp_idx == -1) {
			return false;
		}

		if (amp_idx == (e_length - 1)) {
			return false;
		}
		var dot_idx	=	fldEmail.lastIndexOf(".");
		if (dot_idx < amp_idx) {
			return false;
		}
		if (amp_idx == (dot_idx -1)) {
			return false;
		}

		if (dot_idx == (e_length - 1)) {
			return false;
		}
		if (e_length > 0) {
			if(fldEmail.search('["~!#$%^&*()+=?;:/\,` |]') >= 0 
				||	fldEmail.search('<') >= 0 
				||	fldEmail.search('>') >= 0 
				||	fldEmail.search('{') >= 0 
				||	fldEmail.search('}') >= 0 ) {
				alert("Invalid characters found in the e-mail ID");
				return false;
			}
			if(fldEmail.indexOf("\\") > -1 
				||	fldEmail.indexOf("[") > -1
				||	fldEmail.indexOf("]") > -1) {
				alert("Invalid characters found in the e-mail ID");
				return false;
			}
		}
	}
	return true;

}
//---------------------------------------------------------------------
function validateEmail (p_fldEmail) {
	var fldEmail = ltrim(rtrim(p_fldEmail));
	if (fldEmail =='') {
		alert("Please enter a e-mail ID" );
		return false;
	}
	else {
		var amp_idx	=	fldEmail.indexOf("@");
		repeat_atr_idx	=	fldEmail.indexOf ("@",amp_idx+1);

		if(amp_idx == 0){
			alert("Please enter a valid e-mail ID");
			return false;
		}
		if (repeat_atr_idx != -1) {
			alert("Please enter a valid e-mail ID");
			return false;
		}

		var e_length	=	fldEmail.length;
		if (amp_idx == -1) {
			alert("Please enter a valid e-mail ID");
			return false;
		}

		if (amp_idx == (e_length - 1)) {
			alert("Please enter a valid e-mail ID");
			return false;
		}
		var dot_idx	=	fldEmail.lastIndexOf(".");
		if (dot_idx < amp_idx) {
			alert("Please enter a valid e-mail ID");
			return false;
		}
		if (fldEmail.indexOf("..")!=-1) {
			alert("Please enter a valid e-mail ID");
			return false;
		}
		if (amp_idx == (dot_idx -1)) {
			alert("Please enter a valid e-mail ID");
			return false;
		}

		if (dot_idx == (e_length - 1)) {
			alert("Please enter a valid e-mail ID");
			return false;
		}

		if (e_length > 0) {
			if(fldEmail.search('["~!#$%^&*()+=?;:/\,` |]') >= 0 
				||	fldEmail.search('<') >= 0 
				||	fldEmail.search('>') >= 0 
				||	fldEmail.search('{') >= 0 
				||	fldEmail.search('}') >= 0 ) {
				alert("Invalid characters found in the e-mail ID");
				return false;
			}
			if(fldEmail.indexOf("\\") > -1 
				||	fldEmail.indexOf("[") > -1
				||	fldEmail.indexOf("]") > -1) {
				alert("Invalid characters found in the e-mail ID");
				return false;
			}
		}
	}
	return true;

}
//---------------------------------------------------------------------
function doSubmit () {
	return false;
}
//---------------------------------------------------------------------
function rtrim(argvalue) {
   	while (true && (argvalue != null)) {
    	if (argvalue.substring(argvalue.length - 1, argvalue.length) == " "){
    	 	argvalue = argvalue.substring(0, argvalue.length - 1);
      	}else{
     		break;
     	}
  	}
  	return argvalue
}
//----------------------------------------------------------------------
function ltrim(argvalue) {
  while (true && (argvalue != null) ) {
      if (argvalue.substring(0, 1) == " "){
       	  argvalue = argvalue.substring(1, argvalue.length);
      }else{
      	  break;
      }
  }
  return argvalue
}
//---------------------------------------------------------------------
/**
This function checks if the passed value is a number
**/
function isNumeric (
	p_val
) {
	if (isNaN(p_val) || (p_val.indexOf("e") != -1) 
			|| (p_val.indexOf("E") != -1) 
			|| (p_val.indexOf("+") != -1)
			|| (p_val.indexOf("-") != -1)
			|| (p_val.indexOf(" ") != -1)
			|| (p_val.indexOf(",") != -1)
			|| (p_val.indexOf(" ") != -1)		// this is Alt + 255 Key and not whitespace.
	){
			return false;
		}
	return true;
}
//---------------------------------------------------------------------
function listrsaccts (dispbal){
 	str  = '<select name="selAcct" ';
	if (dispbal) {
		str += 'onchange="return refreshbalance (selAcct.selectedIndex - 1)"';
	}
	str += '>';
 	str += '<option>';
	str += '- Select An Account -';
	str += '</option>';
 	for (var i=0; i<count; i++) {
		str += '<option value="'+accounts [i]+'">';
		str += accounts [i];
		str += ' - ' + branch [i];
		str += '</option>';
 	}
 	str += '</select>';
	document.write (str);
	if (dispbal && !isNS4) {
		displaybalance();
	}
	return true;
}
//------------------------------------------------------------------------------
function displaybalance() {
	if (ie4 || isNS6 || isIE5) {
		document.write('<input type="text" size="16" name="balance" maxlength="20" value="" onFocus="blur()" style="border: medium none; background-color:transparent" tabindex = "-1" >');//transparent
		document.write('</input>');
	} else {
		document.write ('<input type="hidden" name="balance"/>');
		if (isNS4) {
			document.write('<layer top="92" left="380" bgcolor="red" id="layerCurr" name="layerCurr"/>');
		}
	}
}
//------------------------------------------------------------------------------
function refreshbalance (index) {
//	alert ("index :"+index);
	if (ie4 || isNS6 || isIE5) {
		if (index == -1) {
			document.frmTxn.balance.value =  ' ';
		} else
		{
			document.frmTxn.balance.value =  ' ' + namccy [index] + ' ' + balance [index];
		}

		if(document.frmTxn.fldTxnId.value=='DDI' || document.frmTxn.fldTxnId.value=='BCI'){
			chgCurr();
		}
	} else {
		document.layerCurr.document.write ();

		if (index == -1) {
			document.layerCurr.document.write(' ');
		} else {
			document.layerCurr.document.write(' ' + namccy [index]);
			document.layerCurr.document.write(' ' + balance [index]);
		}
		document.layerCurr.document.write ();
		document.layerCurr.document.close ();
	}
	if(document.frmTxn.fldTxnId.value=='DDI' || document.frmTxn.fldTxnId.value=='BCI'){
		chgCurr();
	}
}
//-------------------------------------------------------------------------------
function selectdate (name,yrstart,yrend){
	datestr='';
	datestr += '<select name="date' +name +'">';
	datestr += '<option value="">-dd-</option>';
    for (var i=1;i<=31;i++)
	{
		if(i<10){
			datestr += '<option value="0'+i+'">0'+i+'</option>';
		}
		else{
			datestr += '<option value="'+i+'">'+i+'</option>';
		}
	}
    datestr += '</select>';
    datestr += ' ';
    datestr += '<select name="month'+name+'">';
    datestr += '<option value="" selected>-mmm-</option>';
    datestr += '<option value="01">Jan</option>';
    datestr += '<option value="02">Feb</option>';
    datestr += '<option value="03">Mar</option>';
    datestr += '<option value="04">Apr</option>';
    datestr += '<option value="05">May</option>';
    datestr += '<option value="06">Jun</option>';
    datestr += '<option value="07">Jul</option>';
    datestr += '<option value="08">Aug</option>';
    datestr += '<option value="09">Sep</option>';
    datestr += '<option value="10">Oct</option>';
    datestr += '<option value="11">Nov</option>';
    datestr += '<option value="12">Dec</option>';
    datestr += '</select>';
    datestr += ' ';
    datestr += '<select name="year'+name+'">';
    datestr += '<option value="" selected>-yyyy-</option>';
    for (var j=yrstart;j<=yrend;j++)
    {
			datestr += '<option value="'+j+'">'+j+'</option>';
    }
    datestr += '</select>';
    document.write (datestr);
}
//-------------------------------------------------------------------------------
function populateDOB (){
	datestr = '';
	datestr += '<select name="cmbDayDOB">';
	for (var i=1;i<=31;i++)
	{
		if(i<10){
			datestr += '<option value="0'+i+'">0'+i+'</option>';
		}
		else{
			datestr += '<option value="'+i+'">'+i+'</option>';
		}
	}
    datestr += '</select>';
    document.write (datestr);
    document.write (' ');
    document.write ('<select name="cmbMonthDOB">');
    document.write ('<option value="01">Jan</option>');
    document.write ('<option value="02">Feb</option>');
    document.write ('<option value="03">Mar</option>');
    document.write ('<option value="04">Apr</option>');
    document.write ('<option value="05">May</option>');
    document.write ('<option value="06">Jun</option>');
    document.write ('<option value="07">Jul</option>');
    document.write ('<option value="08">Aug</option>');
    document.write ('<option value="09">Sep</option>');
    document.write ('<option value="10">Oct</option>');
    document.write ('<option value="11">Nov</option>');
    document.write ('<option value="12">Dec</option>');
    document.write ('</select>');
    document.write (' ');
    currYear =getCurrYear();
    var l_prev_year = currYear - 99;
    var l_i = l_prev_year;
	document.write ('<select name="cmbYearDOB">');
	for (l_i=l_prev_year; l_i<=currYear; l_i++) {
		document.write ('<option value="'+ l_i + '">' + l_i + '</option>');
	}
	document.write ('</select>');
    document.write (' ');
}
//-------------------------------------------------------------------------------
function openLink(p_link){
	window.open(p_link,'link',"toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=780,height=515,top=50,left=50");
	return false;
}
//-------------------------------------------------------------------------------
function refreshLeftMenu(p_frmname,p_txn_req)
{	
	if (parent.frames['common_menu1'].l_txn_req == undefined)
	{
		return false;
	}

	var l_txnid;
	if(parent.frames['common_menu1'].l_txn_req!=p_txn_req || p_txn_req == PWD_MNEMONIC){
		history.forward();
	}
	if(p_txn_req != PWD_MNEMONIC)
		parent.frames['common_menu1'].selectTab(p_txn_req);
	
	l_txnid = document[p_frmname].fldTxnId.value; 
	if(document[p_frmname].fldTxnId.value=='MTN'){
		document[p_frmname].fldTxnId.value='BLP';
		l_txnid = 'BLP';
	}else if(document[p_frmname].fldTxnId.value=='XRH'){
		l_txnid ='MAS';
	}
	if(parent.frames['left_menu'].sel_txnid!=document[p_frmname].fldTxnId.value){
		parent.frames['left_menu'].sub_menu_click=false;
		parent.frames['left_menu'].menu_click(l_txnid);
	}
	return false;
}
//-------------------------------------------------------------------------------
function isAllowedChar(p_string){

	if (p_string.indexOf('%')!= -1)
	{
		return '%';
	}else if (p_string.indexOf(';')!= -1)
	{
		return ';';
	}else if (p_string.indexOf('"')!= -1)
	{
		return '"';
	}else if (p_string.indexOf("'")!= -1)
	{
		return "'";
	}
	return null;
}
//-------------------------------------------------------------------------------
function getCurrYear(){
	return parent.common_menu1.l_curr_login_date.substr(7,4);
}
//-------------------------------------------------------------------------------
function isStringValid(p_str, p_validation,p_alertFlg,p_fldTitle){
		var valdnStrArr = new Array();
		valdnStrArr = p_validation.split(",");
		for (l_indx=0 ;l_indx < valdnStrArr.length; l_indx++){
				var l_val_indx = (valdnStrArr[l_indx].indexOf('=') >0)? valdnStrArr[l_indx].indexOf('=') : valdnStrArr[l_indx].length;
				var valdnReq	=	rtrim(ltrim(valdnStrArr[l_indx].substring(0,l_val_indx)));
				var l_value		=	rtrim(ltrim(valdnStrArr[l_indx].substring(valdnStrArr[l_indx].indexOf('=')+1,valdnStrArr[l_indx].length)));
				var l_str = ltrim(rtrim(p_str));
				switch(valdnReq){
					case "required":
						if ((l_str.length == 0)){
							if(p_alertFlg){
								alert( p_fldTitle +" cannnot be left blank.");
							}
							return "required";
						}
						break;
			        case "maxlen": 
						if (eval(l_str.length) > eval(l_value)){
							if(p_alertFlg){
								alert( p_fldTitle +" cannnot be greater than "+ l_value +" characters.");
							}
							return "maxlen";
						}
						break;
			        case "minlen": 
							if (eval(l_str.length) < eval(l_value)){
							if(p_alertFlg){
								alert( p_fldTitle +" cannnot be less than " + l_value +"characters.");
							}
							return "minlen";
						}
						break;
			        case "alphanum": 
						if(l_str.length >0 && l_str.search("[^A-Za-z0-9]") >= 0 ){
							if(p_alertFlg){
								alert( p_fldTitle +" should contain Alphabets or Numbers");
							}
							return "alphanum";
						}
						break;
			        case "alphanumhyphen": 
						if(l_str.length >0 && l_str.search("[^A-Za-z0-9\-_]") >= 0 ){
							if(p_alertFlg){
								alert( p_fldTitle +" should contain Alphabets or Numbers or - or _");
							}
							return "alphanumhyphen";
						}
						break;
			        case "numeric": 
						if(l_str.length >0 && l_str.search("[^0-9]") >=0 ){
							if(p_alertFlg){
								alert( p_fldTitle +" should contain Numbers");
							}
							return "numeric";
						}
						break;
			        case "decimal": 
						if(l_str.length >0 && l_str.search("[^0-9.]") >=0 ){
							if(p_alertFlg){
								alert( p_fldTitle +" should be Numeric.");
							}
							return "decimal";
						}
						break;
			        case "alpha": 
						if(l_str.length >0 && l_str.search("[^A-Za-z]") >=0 ){
							if(p_alertFlg){
								alert( p_fldTitle +" should contain Alphabets.");
							}
							return "alpha";
						}
						break;
				}//switch(valdnReq)
		}//for (l_indx=0 ;l_indx < valdnStrArr.length; l_indx++)
	return null;		
}
//-----------------------------------------------------------------------------
function selecttime (name,yrstart,yrend){
	timestr='';
	timestr += '<select name="hour' +name +'">';
    for (var i=0;i<24;i++)
	{
		if(i<10){
			timestr += '<option value="0'+i+'">0'+i+'</option>';
		}
		else{
			timestr += '<option value="'+i+'">'+i+'</option>';
		}
	}
    timestr += '</select> (HH)';
    timestr += ' ';
    timestr += '<select name="min'+name+'">';
	for (var j=0;j<60;j++) {
		if(j<10){
			timestr += '<option value="0'+j+'">0'+j+'</option>';
		}
		else{
			timestr += '<option value="'+j+'">'+j+'</option>';
		}
	}
    timestr += '</select> (mm)';
    document.write (timestr);
}
//-----------------------------------------------------------------------------
function compare_date_time(p_fromdate, p_fromtime, p_todate, p_totime) {
	if(!compare_date(p_fromdate, p_todate)) {
		return false;
	}
	var l_date_sep	= "/";
	var	l_time_sep	= ":";

	var from_date 	= new Array ();
	var to_date		= new Array ();
	var from_time	= new Array ();
	var to_time		= new Array ();

	from_date		= p_fromdate.split(l_date_sep);
	to_date			= p_todate.split(l_date_sep);
	from_time		= p_fromtime.split(l_time_sep);
	to_time			= p_totime.split(l_time_sep);

	if ((to_date[2] == from_date[2]) && (to_date[1] == from_date[1]) && (to_date[0] == from_date[0])) {
		if(to_time[0] < from_time[0]) {
			return false;
		}
		if((to_time[0] == from_time[0]) && (to_time[1] < from_time[1])) {
			return false;
		}
	}
	return true;
}
//------------------------------------------------------------------------------
function LZ(x) {return(x < 0||x > 9?"":"0")+x;}
//------------------------------------------------------------------------------
function validateTxnDesc(p_string){
	if(p_string.length >0 && p_string.search("[^A-Za-z0-9 ]") >= 0 ){
			return "false";
	}
	return null;
}
//------------------------------------------------------------------------------
//-------------------------For DCMS ---------------------------------------------

	function populateDCYear(){
     
	var yearstr ='';
	var str="";
	yearstr += '<select name="cardExpYear">';

	var currDate = new Date();
	
	var currYr = currDate.getFullYear();
   
	for (var i=currYr;i<=currYr+15;i++)
	{   

		str = '"'+i+'"';
		str = str.substr(3,2);
		yearstr += '<option value="'+i+'">'+str+'</option>';
		
	}

    yearstr += '</select>';
    document.write (yearstr);
    document.write (' ');
	}
//-------------------------End DCMS ---------------------------------------------


/******************************************************************************
**
**	End of file
**
*****************************************************************************/