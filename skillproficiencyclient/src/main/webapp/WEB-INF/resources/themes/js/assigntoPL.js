$(document).ready(function(){
	$(".textarea_select_other").prop("readonly", true);
	$(".select_tech").on("change",function(){
		var selectValue=$(this).val();
		if(selectValue==0){
			$(this).parent().next().next().find("button").addClass("disabled");
			$(this).parent().next().find("button").addClass("disabled");
			$(this).parent().next().find("textarea").prop("readonly", true);
			$(this).parent().next().find("textarea").val("");
			$(this).parent().next().next().find("textarea").prop("readonly", false);
		}else{
			$(this).parent().next().find("textarea").prop("readonly", false);
			$(this).parent().next().next().find("textarea").prop("readonly", true);
			$(this).parent().next().next().find("textarea").val("");
			$(this).parent().next().next().find("button").addClass("disabled");
			$(this).parent().next().find("button").addClass("disabled");
		}
	});
	
	$(".textarea_select").on("input",function(){
		if($(this).val().trim().length > 0 && $(this).val().trim().length < 100){
			$(this).parent().find("button").removeClass("disabled");
		}else{
			var prev_data_textarea=$(this).val().slice(0,-1);
			$(this).empty();
			$(this).val(prev_data_textarea);
			$(this).parent().find("button").addClass("disabled");
			if($(this).val().trim().length > 0){
				alert("comment shold be more than are equal to one character and sholun't exceed more than 100 characters");
			}
		}
	});
	
	$(".textarea_select_other").on("input",function(){
		if($(this).val().trim().length > 0 && $(this).val().trim().length < 100){
			$(this).parent().find("button").removeClass("disabled");
		}else{
			var prev_data_textarea1=$(this).val().slice(0,-1);
			$(this).empty();
			$(this).val(prev_data_textarea1);
			$(this).parent().find("button").addClass("disabled");
			if($(this).val().trim().length > 0){
				alert("comment shold be more than are equal to one character and sholun't exceed more than 100 characters");
			}
		}
	});
	
//	$(".btn_action").on("click",function(){
//		alert("action--->"+$(this).parent().find("textarea").val());
//	});
});


function assignTechToPL(pId, row, type, deleteRow) {
	
	var reviewComment="";
	if(type=="Domain")
		{
		reviewComment= document.getElementById("domainCommentForPL"+deleteRow).value;
		
		}
	else
		{
		reviewComment=document.getElementById("techCommentForPL"+deleteRow).value;
		
		}

	
	var path1 = document.getElementById("contextPaht").value;

	var Base64 = {
		_keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
		encode : function(e) {
			var t = "";
			var n, r, i, s, o, u, a;
			var f = 0;
			e = Base64._utf8_encode(e);
			while (f < e.length) {
				n = e.charCodeAt(f++);
				r = e.charCodeAt(f++);
				i = e.charCodeAt(f++);
				s = n >> 2;
				o = (n & 3) <<4|r>> 4;
				u = (r & 15) <<2|i>> 6;
				a = i & 63;
				if (isNaN(r)) {
					u = a = 64
				} else if (isNaN(i)) {
					a = 64
				}
				t = t + this._keyStr.charAt(s) + this._keyStr.charAt(o)
						+ this._keyStr.charAt(u) + this._keyStr.charAt(a)
			}
			return t
		},
		decode : function(e) {
			var t = "";
			var n, r, i;
			var s, o, u, a;
			var f = 0;
			e = e.replace(/[^A-Za-z0-9+/=]/g, "");
			while (f < e.length) {
				s = this._keyStr.indexOf(e.charAt(f++));
				o = this._keyStr.indexOf(e.charAt(f++));
				u = this._keyStr.indexOf(e.charAt(f++));
				a = this._keyStr.indexOf(e.charAt(f++));
				n = s <<2|o>> 4;
				r = (o & 15) <<4|u>> 2;
				i = (u & 3) << 6 | a;
				t = t + String.fromCharCode(n);
				if (u != 64) {
					t = t + String.fromCharCode(r)
				}
				if (a != 64) {
					t = t + String.fromCharCode(i)
				}
			}
			t = Base64._utf8_decode(t);
			return t
		},
		_utf8_encode : function(e) {
			e = e.replace(/rn/g, "n");
			var t = "";
			for (var n = 0; n < e.length; n++) {
				var r = e.charCodeAt(n);
				if (r < 128) {
					t += String.fromCharCode(r)
				} else if (r > 127 && r < 2048) {
					t += String.fromCharCode(r >> 6 | 192);
					t += String.fromCharCode(r & 63 | 128)
				} else {
					t += String.fromCharCode(r >> 12 | 224);
					t += String.fromCharCode(r >> 6 & 63 | 128);
					t += String.fromCharCode(r & 63 | 128)
				}
			}
			return t
		},
		_utf8_decode : function(e) {
			var t = "";
			var n = 0;
			var r = c1 = c2 = 0;
			while (n < e.length) {
				r = e.charCodeAt(n);
				if (r < 128) {
					t += String.fromCharCode(r);
					n++
				} else if (r > 191 && r < 224) {
					c2 = e.charCodeAt(n + 1);
					t += String.fromCharCode((r & 31) << 6 | c2 & 63);
					n += 2
				} else {
					c2 = e.charCodeAt(n + 1);
					c3 = e.charCodeAt(n + 2);
					t += String.fromCharCode((r & 15) << 12
							| (c2 & 63) << 6 | c3 & 63);
					n += 3
				}
			}
			return t
		}
	}
	var encodedString = Base64.encode(row);
	$.ajax({
		type : "POST",
		url : path1 + "/AssignTaskToPL/" + encodedString + "/" + pId +"/" + type+"/"+reviewComment,
		success : function(message) {
		var str ="attra.com.au";
if(message.indexOf(str)!== -1)
	{
	alert("Assigned to "+message);

	// code to be written in success method from here 
	var techCount = $('#tech tr').length;
	var domainCount = $('#domainBody tr').length;

	if (techCount == 0 && domainCount == 0) {
		$('#techTab').hide();
		$('#domainTab').hide();
		$('#home').hide();
		$('#messages').hide();

		/*$('#completed').show();*/
	}

	if (techCount == 0) {
		$('#techTab').hide();
		$('#home').hide();

	}
	if (domainCount == 0) {
		$('#domainTab').hide();
		$('#messages').hide();
	}
	if(type=="Domain")
	{
	$('#d' +deleteRow).remove();

	}
else
	{
	$('#t' +deleteRow).remove();
	}
	}
else
	{
	alert(message);
	//alert("Failed to sent request to Practice Lead");


	}


		},
		error : function() {
			alert("try again");
		}
	});
}