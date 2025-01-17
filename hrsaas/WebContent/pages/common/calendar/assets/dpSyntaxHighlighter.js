/**
 * Code Syntax Highlighter.
 * Version 1.3.0
 * Copyright (C) 2004 Alex Gorbatchev.
 * http://www.dreamprojections.com/syntaxhighlighter/
 * 
 * This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General 
 * Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) 
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to 
 * the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA 
 */

//
// create namespaces
//
var dp = {
	sh :					// dp.sh
	{
		Utils	: {},		// dp.sh.Utils
		Brushes	: {},		// dp.sh.Brushes
		Strings : {},
		Version : '1.3.0'
	}
};

dp.sh.Strings = {
	AboutDialog : '<html><head><title>About...</title></head><body class="dp-about"><table cellspacing="0"><tr><td class="copy"><p class="title">dp.SyntaxHighlighter</div><div class="para">Version: {V}</p><p><a href="http://www.dreamprojections.com/syntaxhighlighter/?ref=about" target="_blank">http://www.dreamprojections.com/SyntaxHighlighter</a></p>&copy;2004-2005 Alex Gorbatchev. All right reserved.</td></tr><tr><td class="footer"><input type="button" class="close" value="OK" onClick="window.close()"/></td></tr></table></body></html>',
	
	// tools
	ExpandCode : '+ expand code',
	ViewPlain : 'view plain',
	Print : 'print',
	CopyToClipboard : 'copy to clipboard',
	About : '?',
	
	CopiedToClipboard : 'The code is in your clipboard now.'
};

dp.SyntaxHighlighter = dp.sh;

//
// Dialog and toolbar functions
//

dp.sh.Utils.Expand = function(sender)
{
	var table = sender;
	var span = sender;

	// find the span in which the text label and pipe contained so we can hide it
	while(span != null && span.tagName != 'SPAN')
		span = span.parentNode;

	// find the table
	while(table != null && table.tagName != 'TABLE')
		table = table.parentNode;
	
	// remove the 'expand code' button
	span.parentNode.removeChild(span);
	
	table.tBodies[0].className = 'show';
	table.parentNode.style.height = '100%'; // containing div isn't getting updated properly when the TBODY is shown
}

// opens a new windows and puts the original unformatted source code inside.
dp.sh.Utils.ViewSource = function(sender)
{
	var code = sender.parentNode.originalCode;
	var wnd = window.open('', '_blank', 'width=750, height=400, location=0, resizable=1, menubar=0, scrollbars=1');
	
	code = code.replace(/</g, '&lt;');
	
	wnd.document.write('<pre>' + code + '</pre>');
	wnd.document.close();
}

// copies the original source code in to the clipboard (IE only)
dp.sh.Utils.ToClipboard = function(sender)
{
	var code = sender.parentNode.originalCode;
	
	// This works only for IE. There's a way to make it work with Mozilla as well,
	// but it requires security settings changed on the client, which isn't by
	// default, so 99% of users won't have it working anyways.
	if(window.clipboardData)
	{
		window.clipboardData.setData('text', code);
		
		alert(dp.sh.Strings.CopiedToClipboard);
	}
}

// creates an invisible iframe, puts the original source code inside and prints it
dp.sh.Utils.PrintSource = function(sender)
{
alert('into assests');
	var td		= sender.parentNode;
	var code	= td.processedCode;
	var iframe	= document.createElement('IFRAME');
	var doc		= null;
	var wnd		= 

	// this hides the iframe
	iframe.style.cssText = 'position:absolute; width:0px; height:0px; left:-5px; top:-5px;';
	
	td.appendChild(iframe);
	
	doc = iframe.contentWindow.document;
	code = code.replace(/</g, '&lt;');
	
	doc.open();
	doc.write('<pre>' + code + '</pre>');
	doc.close();
	
	iframe.contentWindow.focus();
	iframe.contentWindow.print();
	
	td.removeChild(iframe);
}

dp.sh.Utils.About = function()
{
	var wnd	= window.open('', '_blank', 'dialog,width=320,height=150,scrollbars=0');
	var doc	= wnd.document;
	
	var styles = document.getElementsByTagName('style');
	var links = document.getElementsByTagName('link');
	
	doc.write(dp.sh.Strings.AboutDialog.replace('{V}', dp.sh.Version));
	
	// copy over ALL the styles from the parent page
	for(var i = 0; i < styles.length; i++)
		doc.write('<style>' + styles[i].innerHTML + '</style>');

	for(var i = 0; i < links.length; i++)
		if(links[i].rel.toLowerCase() == 'stylesheet')
			doc.write('<link type="text/css" rel="stylesheet" href="' + links[i].href + '"></link>');
	
	doc.close();
	wnd.focus();
}

//
// Match object
//
dp.sh.Match = function(value, index, css)
{
	this.value		= value;
	this.index		= index;
	this.length		= value.length;
	this.css		= css;
}

//
// Highlighter object
//
dp.sh.Highlighter = function()
{
	this.addGutter = true;
	this.addControls = true;
	this.collapse = false;
	this.tabsToSpaces = true;
}

// static callback for the match sorting
dp.sh.Highlighter.SortCallback = function(m1, m2)
{
	// sort matches by index first
	if(m1.index < m2.index)
		return -1;
	else if(m1.index > m2.index)
		return 1;
	else
	{
		// if index is the same, sort by length
		if(m1.length < m2.length)
			return -1;
		else if(m1.length > m2.length)
			return 1;
	}
	return 0;
}

// gets a list of all matches for a given regular expression
dp.sh.Highlighter.prototype.GetMatches = function(regex, css)
{
	var index = 0;
	var match = null;

	while((match = regex.exec(this.code)) != null)
	{
		this.matches[this.matches.length] = new dp.sh.Match(match[0], match.index, css);
	}
}

dp.sh.Highlighter.prototype.AddBit = function(str, css)
{
	var span = document.createElement('span');
	
	str = str.replace(/&/g, '&amp;');
	str = str.replace(/ /g, '&nbsp;');
	str = str.replace(/</g, '&lt;');
	str = str.replace(/\n/gm, '&nbsp;<br>');

	// when adding a piece of code, check to see if it has line breaks in it 
	// and if it does, wrap individual line breaks with span tags
	if(css != null)
	{
		var regex = new RegExp('<br>', 'gi');
		
		if(regex.test(str))
		{
			var lines = str.split('&nbsp;<br>');
			
			str = '';
			
			for(var i = 0; i < lines.length; i++)
			{
				span			= document.createElement('SPAN');
				span.className	= css;
				span.innerHTML	= lines[i];
				
				this.div.appendChild(span);
				
				// don't add a <BR> for the last line
				if(i + 1 < lines.length)
					this.div.appendChild(document.createElement('BR'));
			}
		}
		else
		{
			span.className = css;
			span.innerHTML = str;
			this.div.appendChild(span);
		}
	}
	else
	{
		span.innerHTML = str;
		this.div.appendChild(span);
	}
}

// checks if one match is inside any other match
dp.sh.Highlighter.prototype.IsInside = function(match)
{
	if(match == null || match.length == 0)
		return;
	
	for(var i = 0; i < this.matches.length; i++)
	{
		var c = this.matches[i];
		
		if(c == null)
			continue;
		
		if((match.index > c.index) && (match.index <= c.index + c.length))
			return true;
	}
	
	return false;
}

dp.sh.Highlighter.prototype.ProcessRegexList = function()
{
	for(var i = 0; i < this.regexList.length; i++)
		this.GetMatches(this.regexList[i].regex, this.regexList[i].css);
}

dp.sh.Highlighter.prototype.ProcessSmartTabs = function(code)
{
	var lines	= code.split('\n');
	var result	= '';
	var tabSize	= 4;
	var tab		= '\t';

	// This function inserts specified amount of spaces in the string
	// where a tab is while removing that given tab. 
	function InsertSpaces(line, pos, count)
	{
		var left	= line.substr(0, pos);
		var right	= line.substr(pos + 1, line.length);	// pos + 1 will get rid of the tab
		var spaces	= '';
		
		for(var i = 0; i < count; i++)
			spaces += ' ';
		
		return left + spaces + right;
	}

	// This function process one line for 'smart tabs'
	function ProcessLine(line, tabSize)
	{
		if(line.indexOf(tab) == -1)
			return line;

		var pos = 0;

		while((pos = line.indexOf(tab)) != -1)
		{
			// This is pretty much all there is to the 'smart tabs' logic.
			// Based on the position within the line and size of a tab, 
			// calculate the amount of spaces we need to insert.
			var spaces = tabSize - pos % tabSize;
			
			line = InsertSpaces(line, pos, spaces);
		}
		
		return line;
	}

	// Go through all the lines and do the 'smart tabs' magic.
	for(var i = 0; i < lines.length; i++)
		result += ProcessLine(lines[i], tabSize) + '\n';
	
	return result;
}

dp.sh.Highlighter.prototype.SwitchToTable = function()
{
	// thanks to Lachlan Donald from SitePoint.com for this <br/> tag fix.
	var html	= this.div.innerHTML.replace(/<(br)\/?>/gi, '\n');
	var lines	= html.split('\n');
	var row		= null;
	var cell	= null;
	var tBody	= null;
	var html	= '';
	var pipe	= ' | ';

	// creates an anchor to a utility
	function UtilHref(util, text)
	{
		return '<a href="#" onclick="dp.sh.Utils.' + util + '(this); return false;">' + text + '</a>';
	}
	
	tBody = document.createElement('TBODY');	// can be created and all others go to tBodies collection.

	this.table.appendChild(tBody);
		
	if(this.addGutter == true)
	{
		row = tBody.insertRow(-1);
		cell = row.insertCell(-1);
		cell.className = 'tools-corner';
	}

	if(this.addControls == true)
	{
		var tHead = document.createElement('THEAD');	// controls will be placed in here
		this.table.appendChild(tHead);

		row = tHead.insertRow(-1);

		// add corner if there's a gutter
		if(this.addGutter == true)
		{
			cell = row.insertCell(-1);
			cell.className = 'tools-corner';
		}
		
		cell = row.insertCell(-1);
		
		// preserve some variables for the controls
		cell.originalCode = this.originalCode;
		cell.processedCode = this.code;
		cell.className = 'tools';
		
		if(this.collapse == true)
		{
			tBody.className = 'hide';
			cell.innerHTML += '<span><b>' + UtilHref('Expand', dp.sh.Strings.ExpandCode) + '</b>' + pipe + '</span>';
		}

		cell.innerHTML += UtilHref('ViewSource', dp.sh.Strings.ViewPlain) + pipe + UtilHref('PrintSource', dp.sh.Strings.Print);
		
		// IE has this clipboard object which is easy enough to use
		if(window.clipboardData)
			cell.innerHTML += pipe + UtilHref('ToClipboard', dp.sh.Strings.CopyToClipboard);
		
		cell.innerHTML += pipe + UtilHref('About', dp.sh.Strings.About);
	}

	for(var i = 0, lineIndex = this.firstLine; i < lines.length - 1; i++, lineIndex++)
	{
		row = tBody.insertRow(-1);
		
		if(this.addGutter == true)
		{
			cell = row.insertCell(-1);
			cell.className = 'gutter';
			cell.innerHTML = lineIndex;
		}

		cell = row.insertCell(-1);
		cell.className = 'line' + (i % 2 + 1);		// uses .line1 and .line2 css styles for alternating lines
		cell.innerHTML = lines[i];
	}
	
	this.div.innerHTML	= '';
}

dp.sh.Highlighter.prototype.Highlight = function(code)
{
	function Trim(str)
	{
		return str.replace(/^\s*(.*?)[\s\n]*$/g, '$1');
	}
	
	function Chop(str)
	{
		return str.replace(/\n*$/, '').replace(/^\n*/, '');
	}

	function Unindent(str)
	{
		var lines = str.split('\n');
		var indents = new Array();
		var regex = new RegExp('^\\s*', 'g');
		var min = 1000;

		// go through every line and check for common number of indents
		for(var i = 0; i < lines.length && min > 0; i++)
		{
			if(Trim(lines[i]).length == 0)
				continue;
				
			var matches = regex.exec(lines[i]);

			if(matches != null && matches.length > 0)
				min = Math.min(matches[0].length, min);
		}

		// trim minimum common number of white space from the begining of every line
		if(min > 0)
			for(var i = 0; i < lines.length; i++)
				lines[i] = lines[i].substr(min);

		return lines.join('\n');
	}
	
	// This function returns a portions of the string from pos1 to pos2 inclusive
	function Copy(string, pos1, pos2)
	{
		return string.substr(pos1, pos2 - pos1);
	}

	var pos	= 0;
	
	this.originalCode = code;
	this.code = Chop(Unindent(code));
	this.div = document.createElement('DIV');
	this.table = document.createElement('TABLE');
	this.matches = new Array();

	if(this.CssClass != null)
		this.table.className = this.CssClass;

	// replace tabs with spaces
	if(this.tabsToSpaces == true)
		this.code = this.ProcessSmartTabs(this.code);

	this.table.border = 0;
	this.table.cellSpacing = 0;
	this.table.cellPadding = 0;

	this.ProcessRegexList();	

	// if no matches found, add entire code as plain text
	if(this.matches.length == 0)
	{
		this.AddBit(this.code, null);
		this.SwitchToTable();
		return;
	}

	// sort the matches
	this.matches = this.matches.sort(dp.sh.Highlighter.SortCallback);

	// The following loop checks to see if any of the matches are inside
	// of other matches. This process would get rid of highligting strings
	// inside comments, keywords inside strings and so on.
	for(var i = 0; i < this.matches.length; i++)
		if(this.IsInside(this.matches[i]))
			this.matches[i] = null;

	// Finally, go through the final list of matches and pull the all
	// together adding everything in between that isn't a match.
	for(var i = 0; i < this.matches.length; i++)
	{
		var match = this.matches[i];

		if(match == null || match.length == 0)
			continue;
		
		this.AddBit(Copy(this.code, pos, match.index), null);
		this.AddBit(match.value, match.css);
		
		pos = match.index + match.length;
	}
	
	this.AddBit(this.code.substr(pos), null);

	this.SwitchToTable();
}

dp.sh.Highlighter.prototype.GetKeywords = function(str) 
{
	return '\\b' + str.replace(/ /g, '\\b|\\b') + '\\b';
}

// highlightes all elements identified by name and gets source code from specified property
dp.sh.HighlightAll = function(name, showGutter /* optional */, showControls /* optional */, collapseAll /* optional */, firstLine /* optional */)
{

	function FindValue()
	{
		var a = arguments;
		
		for(var i = 0; i < a.length; i++)
		{
			if(a[i] == null)
				continue;
				
			if(typeof(a[i]) == 'string' && a[i] != '')
				return a[i] + '';
		
			if(typeof(a[i]) == 'object' && a[i].value != '')
				return a[i].value + '';
		}
		
		return null;
	}
	
	function IsOptionSet(value, list)
	{
		for(var i = 0; i < list.length; i++)
			if(list[i] == value)
				return true;
		
		return false;
	}
	
	function GetOptionValue(name, list, defaultValue)
	{
		var regex = new RegExp('^' + name + '\\[(\\w+)\\]$', 'gi');
		var matches = null;

		for(var i = 0; i < list.length; i++)
			if((matches = regex.exec(list[i])) != null)
				return matches[1];
		
		return defaultValue;
	}

	var elements = document.getElementsByName(name);
	var highlighter = null;
	var registered = new Object();
	var propertyName = 'value';
	
	// if no code blocks found, leave
	if(elements == null)
		return;

	// register all brushes
	for(var brush in dp.sh.Brushes)
	{
		var aliases = dp.sh.Brushes[brush].Aliases;
		
		if(aliases == null)
			continue;
		
		for(var i = 0; i < aliases.length; i++)
			registered[aliases[i]] = brush;
	}

	for(var i = 0; i < elements.length; i++)
	{
		var element = elements[i];
		var options = FindValue(
				element.attributes['class'], element.className, 
				element.attributes['language'], element.language
				);
		var language = '';
		
		if(options == null)
			continue;
		
		options = options.split(':');
		
		language = options[0].toLowerCase();
		
		if(registered[language] == null)
			continue;
		
		// instantiate a brush
		highlighter = new dp.sh.Brushes[registered[language]]();
		
		// hide the original element
		element.style.display = 'none';

		highlighter.addGutter = (showGutter == null) ? !IsOptionSet('nogutter', options) : showGutter;
		highlighter.addControls = (showControls == null) ? !IsOptionSet('nocontrols', options) : showControls;
		highlighter.collapse = (collapseAll == null) ? IsOptionSet('collapse', options) : collapseAll;
		
		// first line idea comes from Andrew Collington, thanks!
		highlighter.firstLine = (firstLine == null) ? parseInt(GetOptionValue('firstline', options, 1)) : firstLine;

		highlighter.Highlight(element[propertyName]);

		// place the result table inside a div
		var div = document.createElement('DIV');
		
		div.className = 'dp-highlighter';
		div.appendChild(highlighter.table);

		element.parentNode.insertBefore(div, element);		
	}	
}


dp.sh.Brushes.Xml = function()
{
	this.CssClass = 'dp-xml';
}

dp.sh.Brushes.Xml.prototype	= new dp.sh.Highlighter();
dp.sh.Brushes.Xml.Aliases	= ['xml', 'xhtml', 'xslt', 'html', 'xhtml'];

dp.sh.Brushes.Xml.prototype.ProcessRegexList = function()
{
	function push(array, value)
	{
		array[array.length] = value;
	}
	
	/* If only there was a way to get index of a group within a match, the whole XML
	   could be matched with the expression looking something like that:
	
	   (<!\[CDATA\[\s*.*\s*\]\]>)
	   | (<!--\s*.*\s*?-->)
	   | (<)*(\w+)*\s*(\w+)\s*=\s*(".*?"|'.*?'|\w+)(/*>)*
	   | (</?)(.*?)(/?>)
	*/
	var index	= 0;
	var match	= null;
	var regex	= null;

	// Match CDATA in the following format <![ ... [ ... ]]>
	// <\!\[[\w\s]*?\[(.|\s)*?\]\]>
	this.GetMatches(new RegExp('<\\!\\[[\\w\\s]*?\\[(.|\\s)*?\\]\\]>', 'gm'), 'cdata');
	
	// Match comments
	// <!--\s*.*\s*?-->
	this.GetMatches(new RegExp('<!--\\s*.*\\s*?-->', 'gm'), 'comments');

	// Match attributes and their values
	// (\w+)\s*=\s*(".*?"|\'.*?\'|\w+)*
	regex = new RegExp('([\\w-\.]+)\\s*=\\s*(".*?"|\'.*?\'|\\w+)*', 'gm');
	while((match = regex.exec(this.code)) != null)
	{
		push(this.matches, new dp.sh.Match(match[1], match.index, 'attribute'));
	
		// if xml is invalid and attribute has no property value, ignore it	
		if(match[2] != undefined)
		{
			push(this.matches, new dp.sh.Match(match[2], match.index + match[0].indexOf(match[2]), 'attribute-value'));
		}
	}

	// Match opening and closing tag brackets
	// </*\?*(?!\!)|/*\?*>
	this.GetMatches(new RegExp('</*\\?*(?!\\!)|/*\\?*>', 'gm'), 'tag');

	// Match tag names
	// </*\?*\s*(\w+)
	regex = new RegExp('</*\\?*\\s*([\\w-\.]+)', 'gm');
	while((match = regex.exec(this.code)) != null)
	{
		push(this.matches, new dp.sh.Match(match[1], match.index + match[0].indexOf(match[1]), 'tag-name'));
	}
}


dp.sh.Brushes.Php = function()
{
	var keywords =	'and or xor __FILE__ __LINE__ array as break case ' +
					'cfunction class const continue declare default die do echo else ' +
					'elseif empty enddeclare endfor endforeach endif endswitch endwhile eval exit ' +
					'extends for foreach function global if include include_once isset list ' +
					'new old_function print require require_once return static switch unset use ' +
					'var while __FUNCTION__ __CLASS__';

	this.regexList = [
		{ regex: new RegExp('//.*$', 'gm'),							css: 'comment' },			// one line comments
		{ regex: new RegExp('/\\*[\\s\\S]*?\\*/', 'g'),				css: 'comment' },			// multiline comments
		{ regex: new RegExp('"(?:[^"\n]|[\"])*?"', 'g'),			css: 'string' },			// double quoted strings
		{ regex: new RegExp("'(?:[^'\n]|[\'])*?'", 'g'),			css: 'string' },			// single quoted strings
		{ regex: new RegExp('\\$\\w+', 'g'),						css: 'vars' },				// variables
		{ regex: new RegExp(this.GetKeywords(keywords), 'gm'),		css: 'keyword' }			// keyword
		];

	this.CssClass = 'dp-c';
}

dp.sh.Brushes.Php.prototype	= new dp.sh.Highlighter();
dp.sh.Brushes.Php.Aliases	= ['php'];


dp.sh.Brushes.JScript = function()
{
	var keywords =	'abstract boolean break byte case catch char class const continue debugger ' +
					'default delete do double else enum export extends false final finally float ' +
					'for function goto if implements import in instanceof int interface long native ' +
					'new null package private protected public return short static super switch ' +
					'synchronized this throw throws transient true try typeof var void volatile while with';

	this.regexList = [
		{ regex: new RegExp('//.*$', 'gm'),							css: 'comment' },			// one line comments
		{ regex: new RegExp('/\\*[\\s\\S]*?\\*/', 'g'),				css: 'comment' },			// multiline comments
		{ regex: new RegExp('"(?:[^"\n]|[\"])*?"', 'g'),			css: 'string' },			// double quoted strings
		{ regex: new RegExp("'(?:[^'\n]|[\'])*?'", 'g'),			css: 'string' },			// single quoted strings
		{ regex: new RegExp('^\\s*#.*', 'gm'),						css: 'preprocessor' },		// preprocessor tags like #region and #endregion
		{ regex: new RegExp(this.GetKeywords(keywords), 'gm'),		css: 'keyword' }			// keywords
		];

	this.CssClass = 'dp-c';
}

dp.sh.Brushes.JScript.prototype	= new dp.sh.Highlighter();
dp.sh.Brushes.JScript.Aliases	= ['js', 'jscript', 'javascript'];

dp.sh.Brushes.CSS = function() {
    //Not used yet - added to values
    var tags = 'abbr acronym address applet area a b base basefont bdo big blockquote body br button ' +
            'caption center cite code col colgroup dd del dfn dir div dl dt em fieldset form frame frameset h1 h2 h3 h4 h5 h6 head hr html img i ' +
            'iframe img input ins isindex kbd label legend li link map menu meta noframes noscript ol optgroup option p param pre q s samp script select ' +
            'span strike strong style sub sup table tbody td textarea tfoot th thead title tr tt ul u';
	var keywords =	'ascent azimuth background-attachment background-color background-image background-position ' +
			'background-repeat background baseline bbox border-collapse border-color border-spacing border-style border-top ' +
			'border-right border-bottom border-left border-top-color border-right-color border-bottom-color border-left-color ' +
			'border-top-style border-right-style border-bottom-style border-left-style border-top-width border-right-width ' +
			'border-bottom-width border-left-width border-width border bottom cap-height caption-side centerline clear clip color ' +
			'content counter-increment counter-reset cue-after cue-before cue cursor definition-src descent direction display ' +
			'elevation empty-cells float font-size-adjust font-family font-size font-stretch font-style font-variant font-weight font ' +
			'height letter-spacing line-height list-style-image list-style-position list-style-type list-style margin-top ' +
			'margin-right margin-bottom margin-left margin marker-offset marks mathline max-height max-width min-height min-width orphans ' +
			'outline-color outline-style outline-width outline overflow-x overflow-y overflow padding-top padding-right padding-bottom padding-left padding page ' +
			'page-break-after page-break-before page-break-inside pause pause-after pause-before pitch pitch-range play-during position ' +
			'quotes richness right left bottom top size slope src speak-header speak-numeral speak-punctuation speak speech-rate stemh stemv stress ' +
			'table-layout text-align text-decoration text-indent text-shadow text-transform unicode-bidi unicode-range units-per-em ' +
			'vertical-align visibility voice-family volume white-space widows width widths word-spacing x-height z-index zoom important after filter opacity';

    var values = 'progid:DXImageTransform.Microsoft.AlphaImageLoader src sizingMethod alpha opacity ' +
            'above absolute all always aqua armenian attr aural auto avoid baseline behind below bidi-override black blink block blue bold bolder both bottom braille capitalize center center-left center-right circle close-quote code collapse compact condensed '+
			'continuous counter counters crop cross crosshair cursive dashed decimal decimal-leading-zero default digits disc dotted double embed embossed e-resize expanded extra-condensed extra-expanded fantasy far-left far-right fast faster fixed format fuchsia '+
			'gray green groove handheld hebrew help hidden hide high higher inline-table inline inset inside invert italic justify landscape large larger left-side left leftwards level lighter lime line-through list-item local loud lower-alpha '+
			'lowercase lower-greek lower-latin lower-roman lower low ltr marker maroon medium message-box middle mix move narrower navy ne-resize no-close-quote none no-open-quote no-repeat normal nowrap n-resize nw-resize oblique olive once open-quote outset '+
			'outside overline pointer portrait print projection purple red relative repeat repeat-x repeat-y rgb ridge right right-side rightwards rtl run-in screen scroll semi-condensed semi-expanded separate se-resize show silent silver slower slow '+
			'small small-caps small-caption smaller soft solid speech spell-out square s-resize static status-bar sub super sw-resize table-caption table-cell table-column table-column-group table-footer-group table-header-group table-row table-row-group teal '+
			'text-bottom text-top thick thin top transparent tty tv ultra-condensed ultra-expanded underline upper-alpha uppercase upper-latin upper-roman url visible wait white wider w-resize x-fast x-high x-large x-loud x-low x-slow x-small x-soft xx-large xx-small yellow';
	

	this.regexList = [
		{ regex: new RegExp('//.*$', 'gm'),							css: 'comment' },			// one line comments
		{ regex: new RegExp('/\\*[\\s\\S]*?\\*/', 'g'),				css: 'comment' },			// multiline comments
		{ regex: new RegExp('"(?:[^"\n]|[\"])*?"', 'g'),			css: 'string' },			// double quoted strings
		{ regex: new RegExp("'(?:[^'\n]|[\'])*?'", 'g'),			css: 'string' },			// single quoted strings
		{ regex: new RegExp('^\\s*.*{', 'gm'),						css: 'preprocessor' },		// everything before a {
		{ regex: new RegExp('}', 'gm'),						css: 'preprocessor' },		// The }
		{ regex: new RegExp(this.GetKeywordsCSS(keywords), 'gm'),		css: 'keyword' },		// keywords
		{ regex: new RegExp(this.GetValuesCSS(values), 'gm'),		css: 'value' },			// values
        { regex: new RegExp('(-?\\d+)(\.\\d+)?(px|em|pt|\:|\%|)', 'g'),  css: 'value' }        //Sizes
		];
        //Not used any more
        /*
        { regex: new RegExp('(-?\\d+)(\.\\d+)', 'g'),  css: 'value' },        //Plain Numbers
        { regex: new RegExp('(0(?=;))', 'g'),  css: 'value' }        //Number 0
		{ regex: new RegExp('\([.]\)', 'gm'),			css: 'string' },			// Things in parenthesis
        { regex: new RegExp('\\#[a-zA-Z0-9]{3,6}', 'g'),            css: 'colors' },            // html colors  
		{ regex: new RegExp(this.GetKeywords(tags), 'g'),		css: 'tags' },			// keywords
        */

	this.CssClass = 'dp-css';
}
dp.sh.Highlighter.prototype.GetKeywordsCSS = function(str) {
	//var str = '\\b' + str.replace(/ /g, '(?=:)\\b|\\b') + '\:\\b';
	var str = '\\b([a-z_]|)' + str.replace(/ /g, '(?=:)\\b|\\b([a-z_\\*]|\\*|)') + '(?=:)\\b';
    //console.log(str);
    return str;
}
dp.sh.Highlighter.prototype.GetValuesCSS = function(str) {
	var str = '\\b' + str.replace(/ /g, '(?!-)(?!:)\\b|\\b()') + '\:\\b';
    //console.log(str);
    return str;
}

dp.sh.Brushes.CSS.prototype	= new dp.sh.Highlighter();
dp.sh.Brushes.CSS.Aliases	= ['css'];



