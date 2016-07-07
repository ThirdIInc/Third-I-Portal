/************************************************************************
	
	COMMON CLASSES
	
*************************************************************************/
TD { /*BACKGROUND-COLOR: FFFFFF;*/
	FONT-SIZE: 11px;
	COLOR: #333333;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
}

.td_bottom_border {
	border-bottom: 1px solid #D5D9E3;
	FONT-SIZE: 11px;
	COLOR: #333333;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
}

INPUT {
	border: 1px solid #A7BEE2;
	FONT-SIZE: 11px;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
	background-color: #ffffff;
}

SELECT {
	BORDER: #A3D6F3 1px solid;
	FONT-SIZE: 11px;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
}

TEXTAREA {
	BORDER: #A3D6F3 1px solid;
	FONT-SIZE: 11px;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
}

BODY {
	FONT-SIZE: 11px;
	COLOR: #333333;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
	TEXT-ALIGN: justify;
	scrollbar-3dlight-color: white;
	scrollbar-arrow-color: #4D84B3;
	scrollbar-base-color: white;
	scrollbar-darkshadow-color: white;
	scrollbar-face-color: white;
	scrollbar-highlight-color: #4D84B3;
	scrollbar-shadow-color: #4D84B3;
	scrollbar-track-color: white;
}

/*
top image class


*/

.top_env_bg{
	background-image:url(images/top.jpg); 
	
}
.bottom_env_bg{
	
		background-image:url(images/bot.jpg); 
}

	
	
 /*BODY.left {
	FONT-SIZE: 11px;
	COLOR: #333333;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
	TEXT-ALIGN: justify;	
	background-color:#3B3F5C;
	background-repeat:repeat;
	margin:0px;	
}
*/
BODY.main {
	FONT-SIZE: 11px;
	COLOR: #333333;
	FONT-FAMILY: Arial, Helvetica, sans-serif;
	TEXT-ALIGN: justify;
	background-color: #A8C9DC;
	background-repeat: no-repeat;
	margin: 0px;
}

.iconImage {
	border: 0px;
	cursor: hand;
}

A:link {
	TEXT-DECORATION: none;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #14425D;
}

A:visited {
	TEXT-DECORATION: none;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #14425D;
}

A:active {
	TEXT-DECORATION: none;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #14425D;
}

A:hover {
	TEXT-DECORATION: underline;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #A50F28;
}

div.scrollF9 {
	position: relative;
	overflow-y: auto;
	height: 270;
}

table.sortable thead {
	cursor: default;
}

/***********************************************************************

             		    Dashlet Classes

***********************************************************************/
.refresh {
	background-image: url(images/refresh.gif);
	background-repeat: no-repeat;
	float: right;
	vertical-align: middle;
	border: 0px;
}

.close {
	background-image: url(images/close.gif);
	background-repeat: no-repeat;
	vertical-align: middle;
	float: right;
	border: 0px;
}

.min {
	background-image: url(images/min.gif);
	background-repeat: no-repeat;
	vertical-align: middle;
	float: right;
	border: 0px;
}

.max {
	background-image: url(images/max.gif);
	background-repeat: no-repeat;
	vertical-align: middle;
	float: right;
	border: 0px;
}

#mainContainer {
	width: 100%;
	margin: 0 auto;
	text-align: left;
	/*padding-bottom: 30px;*/
	vertical-align: top;
}

#header { /* Header at the top of the demo */
	background-image: url(images/bg_heading.gif);
	background-repeat: repeat-x;
}  /* Logo image */
.dragableBox { /* The RSS box */
	margin: 5px;
}

/* A div inside the rss box - with a blue border */
.dragableBoxInner {
	border: 0px solid #317082;
}

.dragableBoxHeader { /* Header inside RSS box */
	background-image: url(images/bighead.gif);
	background-repeat: no-repeat;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #FFFFFF;
	text-decoration: none;
	height: 22px;
	font-weight: bold;
}

.dragableBoxHeader span { /* Text inside header of RSS box */
	line-heigth: 22px;
	vertical-align: middle;
}

.dragableBoxHeader img,.dragableBoxHeader span {
	/* Text and reload image inside RSS box */
	float: left;
	vertical-align: middle;
}

#rectangleDiv {
	/* Dotted rectangle indicating where objects will be dropped */
	border: 1px dotted red;
	margin: 5px;
}

.dragableBoxContent { /* DIV holding data inside dragable boxes */
	overflow: auto;
	width: 100%;
	background-position: left top;
	background-image: url(images/grad.gif);
	background-repeat: repeat-x;
	background-color: #FFF;
	border-top: 2px solid #6692AC;
	border-left: 2px solid #6692AC;
	border-right: 2px solid #6692AC;
	background-color: white;
	/*filter:alpha(opacity=92); 
   -moz-opacity: 0.92; 
   opacity: 0.92;*/
}

.dragableBoxStatus {
	background-color: #6692AC;
	height: 0px;
}

.dragableBox .r1,.dragableBox .r2,.dragableBox .r3,.dragableBox .r4 {
	filter: alpha(opacity = 92);
	-moz-opacity: 0.92;
	opacity: 0.92;
	display: block;
	overflow: hidden;
	height: 1px;
	font-size: 1px;
}

.dragableBox .r3,.dragableBox .r4 {
	background-color: #6692AC;
	border-width: 0 1px;
	border-left: 1px solid #6692AC;
	border-right: 1px solid #6692AC;
}

.dragableBox .r2 {
	background-color: #6692AC;
	border-width: 0 1px;
	border-left: 1px solid #6692AC;
	border-right: 1px solid #6692AC;
	background-color: #6692AC;
}

.dragableBox .r1 {
	margin: 0 4px;
}

.dragableBox .r2 {
	margin: 0 3px;
}

.dragableBox .r3 {
	margin: 0 2px;
}

.dragableBox .r4 {
	margin: 0 1px;
	height: 2px;
}

/***********************************************************************

              		   Tabs Classes

***********************************************************************/
.thought {
	background-image: url(images/thought_bg.gif);
	background-repeat: repeat;
	width: 100%;
	border: 1px solid #7d96c3;
	padding: 2px;
	font-family: Arial, Helvetica, sans-serif;
	color: #ffffff;
	font-size: 11px;
	color: #ffffff;
}
.thought td{
	color: #ffffff;
	}
#toplinks a:hover {
	color: #FFFFFF;
}

#toplinks a:link {
	color: #FFFFFF;
}

#toplinks a:visited {
	color: #FFFFFF;
}
/***********************************************************************

             	    Menu Profile Classes

***********************************************************************/
.dtree {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #666;
	white-space: nowrap;
}

.dtree img {
	border: 0px;
	vertical-align: middle;
}

.dtree a {
	color: #333;
	text-decoration: none;
}

.dtree a.node,.dtree a.nodeSel {
	white-space: nowrap;
	padding: 1px 2px 1px 2px;
}

.dtree a.node:hover,.dtree a.nodeSel:hover {
	color: #333;
	text-decoration: underline;
}

.dtree a.nodeSel {
	background-color: #c0d2ec;
}

.dtree .clip {
	overflow: hidden;
}

/***********************************************************************

           			      Buttons Classes

***********************************************************************/
input.save {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #295598;
	height: 20px;
	text-decoration: none;
	background-image: url(images/Save.gif);
	background-color: #ecf3fc;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
}

input.reset {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #295598;
	height: 20px;
	text-decoration: none;
	background-image: url(images/reset.gif);
	background-color: #ecf3fc;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
}

input.delete {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #295598;
	height: 20px;
	text-decoration: none;
	background-image: url(images/delete.gif);
	background-color: #ecf3fc;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
}

input.save_new {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #295598;
	height: 20px;
	text-decoration: none;
	background-image: url(images/savenew.gif);
	background-color: #ecf3fc;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
}

input.save_forward {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #295598;
	height: 20px;
	text-decoration: none;
	background-image: url(images/save_forward.gif);
	background-color: #ecf3fc;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
}

input.forward {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #295598;
	height: 20px;
	text-decoration: none;
	background-image: url(images/forward.gif);
	background-color: #ecf3fc;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
}

input.edit {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #295598;
	height: 20px;
	text-decoration: none;
	background-image: url(images/edit.gif);
	background-color: #ecf3fc;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
}

input.lock {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #295598;
	height: 20px;
	text-decoration: none;
	background-image: url(images/backup.gif);
	background-color: #ecf3fc;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
}

input.unlock {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #295598;
	height: 20px;
	text-decoration: none;
	background-image: url(images/backup.gif);
	background-color: #ecf3fc;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
}

input.search {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #295598;
	height: 20px;
	text-decoration: none;
	background-image: url(images/Search.gif);
	background-color: #ecf3fc;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
}

Input.add {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #295598;
	height: 20px;
	background-image: url(images/add.gif);
	background-color: #ecf3fc;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
}

input.cancel {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #295598;
	height: 20px;
	text-decoration: none;
	background-image: url(images/cancel.gif);
	background-color: #ecf3fc;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
	padding-left: 12px;
}

input.token {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #295598;
	height: 20px;
	text-decoration: none;
	background-image: url(images/backup.gif);
	background-color: #ecf3fc;
	cursor: hand;
	background-repeat: no-repeat;
	background-position: left center;
}

/***********************************************************************

                Form Classes

***********************************************************************/
.text_head {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #000000;
}

.formbg {
	background-image: url(images/grad.gif);
	background-repeat: repeat-x;
	border: 1px solid #A7BEE2;
	background-color: white;
	padding: 5px;
}

.formtext {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #333333;
	text-decoration: none;
}

.formth {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #042F71;
	text-decoration: none;
	height: 16px;
	vertical-align: middle;
	background-image: url(images/grad4.gif);
	padding-right: 5px;
	padding-left: 5px;
}

/***********************************************************************

                Master Form List

***********************************************************************/
.Cell_bg_first {
	background-color: #F2F9FF;
	border: 2px;
	cursor: hand;
}

.Cell_bg_second {
	background-color: #BDD9F2;
	cursor: hand;
	border-bottom: 2px;
}

.onOverCell {
	background-color: #FFFFCC;
	cursor: hand;
	border-bottom: 2px;
	font-weight: bold;
}

/***********************************************************************

             TAsk Planner & Menu List 

***********************************************************************/
.left_menu_bg {
	background-image: url(images/grad.gif);
	background-repeat: no-repeat;
	background-position: left top;
	overflow: auto;
	height: 300px;
	width: 180px;
	scrollbar-3dlight-color: white;
	scrollbar-arrow-color: #4D84B3;
	scrollbar-base-color: white;
	scrollbar-darkshadow-color: white;
	scrollbar-face-color: white;
	scrollbar-highlight-color: #4D84B3;
	scrollbar-shadow-color: #4D84B3;
	scrollbar-track-color: white;
}

.header {
	background-image: url(images/smallhead.png);
	background-repeat: no-repeat;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	font-weight: bold;
	color: #FFFFFF;
	padding-left: 4px;
	text-decoration: none;
	height: 20px;
}

.bg_grad {
	background-image: url(images/grad.gif);
	background-repeat: repeat-x;
	background-color: white;
	filter: alpha(opacity = 92);
	-moz-opacity: 0.92;
	opacity: 0.92;
}

.curvedBox {
	width: 100%;
}

.curvedBox .r1,.curvedBox .r2,.curvedBox .r3,.curvedBox .r4,.curvedBox .r5
	{
	display: block;
	overflow: hidden;
	height: 1px;
	font-size: 1px;
}

.curvedBox .r2,.curvedBox .r3,.curvedBox .r4,.curvedBox .r5 {
	background: #fff;
	border-width: 0 1px;
	border-left: 1px solid #254189;
	border-right: 1px solid #254189;
}

.curvedBox .r1 {
	margin: 0 6px;
}

.curvedBox .r2 {
	margin: 0 3px;
}

.curvedBox .r3 {
	margin: 0 2px;
}

.curvedBox .r4 {
	margin: 0 1px;
	height: 1px;
}

.curvedBox .r5 {
	margin: 0 0px;
	height: 2px;
}

.curvedBox .r2 {
	border-width: 0 1px;
	border-left: 1px solid #254189;
	border-right: 1px solid #254189;
	background-color: #254189;
}

.curvedBox .content {
	background: #fff;
	border-left: 1px solid #254189;
	border-right: 1px solid #254189;
	padding: 0 5px;
}

.borderLR {
	border-top-width: 4px;
	border-right-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-color: #6692AC;
}

.layoutcurve {
	width: 100%;
}

.layoutcurve .r1,.layoutcurve .r2,.layoutcurve .r3,.layoutcurve .r4,.layoutcurve .r5
	{
	display: block;
	overflow: hidden;
	height: 1px;
	font-size: 1px;
}

.layoutcurve .r2,.layoutcurve .r3,.layoutcurve .r4,.layoutcurve .r5 {
	background: #6692AC;
	border-width: 0 1px;
	border-left: 1px solid #6692AC;
	border-right: 1px solid #6692AC;
}

.layoutcurve .r1 {
	margin: 0 6px;
}

.layoutcurve .r2 {
	margin: 0 3px;
}

.layoutcurve .r3 {
	margin: 0 2px;
}

.layoutcurve .r4 {
	margin: 0 1px;
	height: 1px;
}

.layoutcurve .r5 {
	margin: 0 0px;
	height: 2px;
}

.layoutcurve .r2 {
	border-width: 0 1px;
	border-left: 1px solid #6692AC;
	border-right: 1px solid #6692AC;
	background-color: #6692AC;
}

.layoutcolor {
	background-color: #6692AC;
}

/*TABS*/
#tabnavcontainer {
	clear: both;
	margin: 0;
	padding: 0;
	text-align: left;
	background: #e9e9e9;
}

#tabnav {
	float: left;
	width: 100%;
	font: normal 11px/ 13px Arial;
}

#tabnav ul {
	margin: 0;
	padding: 0;
	list-style: none;
}

#tabnav li {
	display: inline;
	margin: 0;
	padding: 0;
}

#tabnav a {
	float: left;
	background: url(images/tab_left.gif) no-repeat left top;
	margin: 0;
	padding: 0 0 0 7px;
	text-decoration: none;
}

#tabnav a span {
	float: left;
	display: block;
	background: url(images/tab_right.gif) no-repeat right top;
	padding: 3px 10px 3px 3px;
	color: #000;
}

/* Commented Backslash Hack hides rule from IE5-Mac \*/
#tabnav a span {
	float: none;
}

/* End IE5-Mac hack */
#tabnav a:hover {
	background-position: 0% -42px;
}

#tabnav a.on {
	background-position: 0% -84px;
}

#tabnav a:hover span {
	color: #254189;
	background-position: 100% -42px;
}

#tabnav a.on span {
	color: #FFF;
	background-position: 100% -84px;
}

#tabnavww a:hover {
	color: #F10;
}