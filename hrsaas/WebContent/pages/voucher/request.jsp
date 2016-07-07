
<html>

<form action="reqSuccess.jsp" id="paraFrm"
	validate="true" >
 
	
<table class="tableBody" width="100%" colspan="4">

	<tr>
		<td  width="100%" colspan="4">
		<center>Request For Information</center>
		</td>
	</tr>
	<tr>
		<td width="100%" colspan="4"></td>
	</tr>



	<tr>
		<td width="25%">First Name <font color="red">*</font>:</td>
		<td width="25%"><input type="text" name="fName" value=""
			size="25" /></td>
		<td width="25%" />Last Name <font color="red">*</font>:</td>
		<td width="25%" ><input type="text" name="lName" value=""
			size="25" /></td>
	</tr>

	<tr>
		<td width="25%">Email <font color="red">*</font>:</td>

		<td width="25%"><input type="text" name="email" value=""
			size="25" /></td>
		<td width="25%">Designation <font color="red">*</font>:</td>

		<td width="25%"><input type="text" name="desg" value=""
			size="25" /></td>

	</tr>


	<tr>
		<td width="25%">Company <font color="red">*</font>:</td>
		<td width="25%"><input type="text" name="cmp" value=""
			size="25" /></td>
		<td width="25%" valign="top">Address:</td>
		<td width="25%"><textarea name='add' rows=4 cols=20></textarea></td>

	</tr>


	<tr>
		<td width="25%">Phone <font color="red">*</font>:</td>

		<td width="25%"><input type="text" name="phoneNo" value=""
			size="25" onkeypress="return numbersonly(this);"/></td>
		<td width="25%">Fax:</td>

		<td width="25%"><input type="text" name="fax" value=""
			size="25" onkeypress="return numbersonly(this);" /></td>

	</tr>

	<tr>
		<td width="25%">City:</td>

		<td width="25%"><input type="text" name="city" value=""
			size="25" /></td>
		<td width="25%">State:</td>

		<td width="25%"><input type="text" name="state" value=""
			size="25" /></td>

	</tr>
	<tr>

		<td width="25%">Zip/Postal Code:</td>
		<td width="25%" onkeypress="return numbersonly(this);"><input type="text" name="zipCode" value=""
			size="25" /></td>
		<td width="25%" height="19">Country <font color="red">*</font>:</td>
		<td width="25%" height="19"><select name="country" id='postappId'>
			<option value="country">Please select one...</option>
			<option value="Abu Dhabi">Abu 
                      Dhabi</option>
                    <option value="Afghanistan"> Afghanistan</option>
                    <option value="Ajman">Ajman </option>
                    <option value="Albania">Albania </option>
                    <option value="Algeria">Algeria </option>
                    <option value="Andorra">Andorra </option>
                    <option value="Angola">Angola </option>
                    <option value="Anguilla"> Anguilla</option>
                    <option value="Antigua Barbuda "> Antigua &amp; Barbuda</option>
                    <option value="Argentina"> Argentina</option>
                    <option value="Armenia">Armenia </option>
                    <option value="Aruba">Aruba </option>
                    <option value="Ascension Islands"> Ascension Islands</option>
                    <option value="Australia"> Australia</option>
                    <option value="Austria">Austria </option>
                    <option value="Azerbaijan"> Azerbaijan</option>
                    <option value="Azores">Azores </option>
                    <option value="Bahamas">Bahamas </option>
                    <option value="Bahrain">Bahrain </option>
                    <option value="Bangladesh"> Bangladesh</option>
                    <option value="Barbados"> Barbados</option>
                    <option value="Belarus">Belarus </option>
                    <option value="Belgium">Belgium </option>
                    <option value="Belize">Belize </option>
                    <option value="Benin">Benin </option>
                    <option value="Bermuda">Bermuda </option>
                    <option value="Bhutan">Bhutan </option>
                    <option value="Bolivia">Bolivia </option>
                    <option value="Borneo">Borneo </option>
                    <option value="Bosnia-hertzagovina"> Bosnia-hertzagovina</option>
                    <option value="Botswana"> Botswana</option>
                    <option value="Brazil">Brazil </option>
                    <option value="British Virge Isls."> British Virge Isls.</option>
                    <option value="Brunei">Brunei </option>
                    <option value="Bulgaria"> Bulgaria</option>
                    <option value="Burkina (Upp. Volta)"> Burkina (Upp. Volta)</option>
                    <option value="Burma (Myanmar)"> Burma (Myanmar)</option>
                    <option value="Burundi">Burundi </option>
                    <option value="Cambodia (Kampuchea)"> Cambodia (Kampuchea)</option>
                    <option value="Cameroon"> Cameroon</option>
                    <option value="Canada">Canada </option>
                    <option value="Canary Islands"> Canary Islands</option>
                    <option value="Cape Vere Islands"> Cape Vere Islands</option>
                    <option value="Caroline Islands"> Caroline Islands</option>
                    <option value="Cayman Island"> Cayman Island</option>
                    <option value="Central African Rep"> Central African Rep</option>
                    <option value="Chad">Chad </option>
                    <option value="Channel Islands"> Channel Islands</option>
                    <option value="Chile">Chile </option>
                    <option value="China">China </option>
                    <option value="Christmas Island"> Christmas Island</option>
                    <option value="Colombia"> Colombia</option>
                    <option value="Comoros Islands"> Comoros Islands</option>
                    <option value="Congo">Congo </option>
                    <option value="Cook Islands"> Cook Islands</option>
                    <option value="Corsica">Corsica </option>
                    <option value="Costa Rica">Costa 
                      Rica</option>
                    <option value="Croatia">Croatia </option>
                    <option value="Cuba">Cuba </option>
                    <option value="Cyprus">Cyprus </option>
                    <option value="Czech Republic"> Czech Republic</option>
                    <option value="Denmark">Denmark </option>
                    <option value="Djibouti"> Djibouti</option>
                    <option value="Dominica"> Dominica</option>
                    <option value="Dominican Republic"> Dominican Republic</option>
                    <option value="Dubai">Dubai </option>
                    <option value="East Timor">East 
                      Timor</option>
                    <option value="Ecuador">Ecuador </option>
                    <option value="Egypt">Egypt </option>
                    <option value="El Salvador">El 
                      Salvador</option>
                    <option value="Equatorial Guinea"> Equatorial Guinea</option>
                    <option value="Estonia">Estonia </option>
                    <option value="Ethiopia"> Ethiopia</option>
                    <option value="Falkland Islands"> Falkland Islands</option>
                    <option value="Faroe Islands"> Faroe Islands</option>
                    <option value="Fiji">Fiji </option>
                    <option value="Finland">Finland </option>
                    <option value="France">France </option>
                    <option value="French Guiana"> French Guiana</option>
                    <option value="French Polynesia"> French Polynesia</option>
                    <option value="Gabon">Gabon </option>
                    <option value="Gambia">Gambia </option>
                    <option value="Georgia">Georgia </option>
                    <option value="Germany">Germany </option>
                    <option value="Ghana">Ghana </option>
                    <option value="Gibraltar"> Gibraltar</option>
                    <option value="Gibraltar"> Gibraltar</option>
                    <option value="Greenland"> Greenland</option>
                    <option value="Grenada">Grenada </option>
                    <option value="Guadeloupe"> Guadeloupe</option>
                    <option value="Guam">Guam </option>
                    <option value="Guatemala"> Guatemala</option>
                    <option value="Guinea">Guinea </option>
                    <option value="Guinea-bissau"> Guinea-bissau</option>
                    <option value="Guyana">Guyana </option>
                    <option value="Haiti">Haiti </option>
                    <option value="Honduras"> Honduras</option>
                    <option value="Hong Kong">Hong 
                      Kong</option>
                    <option value="Hungary">Hungary </option>
                    <option value="Iceland">Iceland </option>
                    <option value="INDIA">India </option>
                    <option value="Indonesia"> Indonesia</option>
                    <option value="Iran">Iran </option>
                    <option value="Iraq">Iraq </option>
                    <option value="Irish Republic"> Irish Republic</option>
                    <option value="Isle Of Man">Isle 
                      Of Man</option>
                    <option value="Israel">Israel </option>
                    <option value="Italy">Italy </option>
                    <option value="Ivory Coast"> Ivory Coast</option>
                    <option value="Jamaica">Jamaica </option>
                    <option value="Japan">Japan </option>
                    <option value="Jordan">Jordan </option>
                    <option value="Kampuchea"> Kampuchea</option>
                    <option value="Kazakhstan"> Kazakhstan</option>
                    <option value="Kenya">Kenya </option>
                    <option value="Kiribati"> Kiribati</option>
                    <option value="Kokos Islands"> Kokos Islands</option>
                    <option value="Korea">Korea </option>
                    <option value="Korea South"> Korea South</option>
                    <option value="Kuwait">Kuwait </option>
                    <option value="Kyrgyzstan"> Kyrgyzstan</option>
                    <option value="Laos">Laos </option>
                    <option value="Latvia">Latvia </option>
                    <option value="Lebanon">Lebanon </option>
                    <option value="Leeward Islands"> Leeward Islands</option>
                    <option value="Lesotho">Lesotho </option>
                    <option value="Liberia">Liberia </option>
                    <option value="Libya">Libya </option>
                    <option value="Liechtenstein"> Liechtenstein</option>
                    <option value="Lithuania"> Lithuania</option>
                    <option value="Luxembourg"> Luxembourg</option>
                    <option value="Macao">Macao </option>
                    <option value="Macedonia"> Macedonia</option>
                    <option value="Madagascar"> Madagascar</option>
                    <option value="Madeira Islands"> Madeira Islands</option>
                    <option value="Malawi">Malawi </option>
                    <option value="Malaysia"> Malaysia</option>
                    <option value="Maldives"> Maldives</option>
                    <option value="Mali">Mali </option>
                    <option value="Malta">Malta </option>
                    <option value="Mariana Islands"> Mariana Islands</option>
                    <option value="Martinique"> Martinique</option>
                    <option value="Maruitius"> Maruitius</option>
                    <option value="Mauritania"> Mauritania</option>
                    <option value="Mexico">Mexico </option>
                    <option value="Micronesa"> Micronesa</option>
                    <option value="Moldavia"> Moldavia</option>
                    <option value="Monaco">Monaco </option>
                    <option value="Mongolia"> Mongolia</option>
                    <option value="Montenegro"> Montenegro</option>
                    <option value="Montserrat"> Montserrat</option>
                    <option value="Morocco">Morocco </option>
                    <option value="Mozambique"> Mozambique</option>
                    <option value="Naura">Naura </option>
                    <option value="Nepal">Nepal </option>
                    <option value="Netherlands"> Netherlands</option>
                    <option value="Netherlands Antilles"> Netherlands Antilles</option>
                    <option value="New Caledonia"> New Caledonia</option>
                    <option value="New Zealand">New 
                      Zealand</option>
                    <option value="Nicaragua"> Nicaragua</option>
                    <option value="Niger">Niger </option>
                    <option value="Nigeria">Nigeria </option>
                    <option value="Niue">Niue </option>
                    <option value="Northern Ireland"> Northern Ireland</option>
                    <option value="Norway">Norway </option>
                    <option value="Oman">Oman </option>
                    <option value="Pakistan"> Pakistan</option>
                    <option value="Palau">Palau </option>
                    <option value="Panama">Panama </option>
                    <option value="Papua New Guinea"> Papua New Guinea</option>
                    <option value="Paraguay"> Paraguay</option>
                    <option value="Peru">Peru </option>
                    <option value="Philippines"> Philippines</option>
                    <option value="Pitcaihn Islands"> Pitcaihn Islands</option>
                    <option value="Poland">Poland </option>
                    <option value="Portugal"> Portugal</option>
                    <option value="Puerto Rico"> Puerto Rico</option>
                    <option value="Qatar">Qatar </option>
                    <option value="Ras Al Khaimah"> Ras Al Khaimah</option>
                    <option value="Reunion Island"> Reunion Island</option>
                    <option value="Romania">Romania </option>
                    <option value="Russia">Russia </option>
                    <option value="Rwanda">Rwanda </option>
                    <option value="S.W. Africa (Namibia)"> S.W. Africa (Namibia)</option>
                    <option value="Saba">Saba </option>
                    <option value="Sabah">Sabah </option>
                    <option value="Saint Helena"> Saint Helena</option>
                    <option value="Saint Lucia"> Saint Lucia</option>
                    <option value="San Marino">San 
                      Marino</option>
                    <option value="Santa Cruz Islands"> Santa Cruz Islands</option>
                    <option value="Sao Tome Principe"> Sao Tome &amp; Principe</option>
                    <option value="Sarawak">Sarawak </option>
                    <option value="Saudi Arabia"> Saudi Arabia</option>
                    <option value="Scotland"> Scotland</option>
                    <option value="Senegal">Senegal </option>
                    <option value="Serbia">Serbia </option>
                    <option value="Seychelles"> Seychelles</option>
                    <option value="Sharjah">Sharjah </option>
                    <option value="Sierra Leone"> Sierra Leone</option>
                    <option value="Singapore"> Singapore</option>
                    <option value="Slovak Republic"> Slovak Republic</option>
                    <option value="Slovenia"> Slovenia</option>
                    <option value="Solomon Islands"> Solomon Islands</option>
                    <option value="Somalia">Somalia </option>
                    <option value="South Africa"> South Africa</option>
                    <option value="Spain">Spain </option>
                    <option value="Sri Lanka">Sri 
                      Lanka</option>
                    <option value="St Pierre Miquelon"> St Pierre &amp; Miquelon</option>
                    <option value="St Vincent">St 
                      Vincent</option>
                    <option value="St. Kitts Nevis"> St. Kitts &amp; Nevis</option>
                    <option value="Sudan">Sudan </option>
                    <option value="Suriname"> Suriname</option>
                    <option value="Swaziland"> Swaziland</option>
                    <option value="Sweden">Sweden </option>
                    <option value="Switzerland"> Switzerland</option>
                    <option value="Syria">Syria </option>
                    <option value="Taiwan">Taiwan </option>
                    <option value="Tajikistan"> Tajikistan</option>
                    <option value="Tanzania"> Tanzania</option>
                    <option value="Thailand"> Thailand</option>
                    <option value="Togo">Togo </option>
                    <option value="Tonga">Tonga </option>
                    <option value="Trinidad Tobago"> Trinidad &amp; Tobago</option>
                    <option value="Tristan De Cunha"> Tristan De Cunha</option>
                    <option value="Tunisia">Tunisia </option>
                    <option value="Turkey">Turkey </option>
                    <option value="Turkmenistan"> Turkmenistan</option>
                    <option value="Turks Caicos Islnd"> Turks &amp; Caicos Islnd</option>
                    <option value="Tuvalu">Tuvalu </option>
                    <option value="USA">USA</option>
                    <option value="Uganda">Uganda </option>
                    <option value="Ukraine">Ukraine </option>
                    <option value="Umm Al Qaiwain"> Umm Al Qaiwain</option>
                    <option value="United Arab Emirates"> United Arab Emirates</option>
                    <option value="United Kingdom"> United Kingdom</option>
                    <option value="Uruguay">Uruguay </option>
                    <option value="Uzbekistan"> Uzbekistan</option>
                    <option value="Vanuatu">Vanuatu </option>
                    <option value="Vatican City"> Vatican City</option>
                    <option value="Venezuela"> Venezuela</option>
                    <option value="Vietnam">Vietnam </option>
                    <option value="Virgin Islands"> Virgin Islands</option>
                    <option value="Wales">Wales </option>
                    <option value="Western Samoa"> Western Samoa</option>
                    <option value="Yemen">Yemen </option>
                    <option value="Yemen Arab Rep"> Yemen Arab Rep</option>
                    <option value="Zaire">Zaire </option>
                    <option value="Zambia">Zambia </option>
                    <option value="Zimbabwe"> Zimbabwe</option>
		</select></td>
	</tr>


	<tr>
		<td width="25%" height="19">Which Industry Are You In <font color="red">*</font>:</td>
		<td width="25%" height="19"><select name='industry' id='postappId'  >
			<option value="select">Please 
                            select one...</option>
                          <option value="Automotive"> Automotive</option>
                          <option value="Banking &amp; Financial Services"> Banking &amp; Financial Services </option>
                          <option value="Consumer Goods"> Consumer Goods</option>
                          <option value="Energy">Energy </option>
                          <option value="Government"> Government</option>
                          <option value="HealthCare"> HealthCare</option>
                          <option value="Insurance"> Insurance</option>
                          <option value="Manufacturing"> Manufacturing</option>
                          <option value="Retail">Retail </option>
                          <option value="Service">Service </option>
                          <option value="Technology"> Technology</option>
                          <option value="Travel">Travel </option>
                          <option value="Transportation"> Transportation</option>
                          <option value="Telecommunications"> Telecommunications</option>
                          <option value="Others">Others </option>

		</select></td>
	</tr>

	<tr>
		<td width="25%" height="19">How Many Employees Does Your Company
		Have worldwide? <font color="red">*</font>:</td>
		<td width="25%" height="19"><select name='employee' id='postappId'>
			<option selected>Please select one...</option>
                          <option value="&lt;100">&lt;100 </option>
                          <option value="QqQq	Q">101-500</option>
                          <option value="501-1000"> 501-1000</option>
                          <option value="1001-3000"> 1001-3000</option>
                          <option value="3001-5000"> 3001-5000</option>
                          <option value="5001-10000"> 5001-10000</option>
                          <option value="&gt;10000">&gt;10000 </option>

		</select></td>
	</tr>


	<tr>
		<td width="25%" valign="top">Additional Comments <font color="red">*</font>:</td>
		<td width="25%" colspan="4"><textarea name='comments' rows=4 cols=90></textarea></td>
	</tr>

	<tr><td><br></td></tr>


	<tr align="center">
	<td></td>

		<td align="right"><input type="submit" name="Submit"
			value="Submit" onclick="return formValidate();" />&nbsp;&nbsp;<input type="submit"  name="Go" value="GO" /></td>
	</tr>

</table>

</form>
</html>
<script>
	function formValidate(){
	
		var fName =document.getElementById('fName').value;
		var lName=document.getElementById('lName').value;
		var email=document.getElementById('email').value;
		var phoneNo=document.getElementById('phoneNo').value;
		
		if(fName == ""){
  			alert("Please Enter First Name ");
  			return false;
  		}
	if(lName == ""){
  			alert("Please Enter Last Name ");
  			return false;
  		}
  		if(email == ""){
  			alert("Please Enter Email Id ");
  			return false;
  		}
  			if ((email.length >0))	
  		{
			  	var email =document.getElementById('email').value;
			 	var filter1=/^.+@.+\..{2,3}$/
			 	var filter=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
			 	if (filter.test(email))
 				{
  									  testresults=true
   				 }
   				 else
    			{
   					 alert("Please Enter a valid email address!")
   						 testresults=false
    			}
 					 return (testresults)
  		}
  		if(phoneNo == ""){
  			alert("Please Select Which Industry Are You In  ");
  			return false;
  		}
  		
	}
	
	 function numbersonly(myfield)
{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
		
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789").indexOf(keychar) > -1))
		return true;	
	else {
	
		myfield.focus();
		
		return false;
	}
}
	
</script>


