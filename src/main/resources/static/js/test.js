
//login
function login(canvas){
	var info = {
            imgString: (typeof canvas == "string")?canvas:canvas.toDataURL("image/png"),
        }

    $.post("/face1to1/login",info,function(data){
        console.log(data);
        var score = 0;
        if(JSON.stringify(data.scores)!="null") {
        	score= JSON.stringify(data.scores[0]);
        }
        console.log("score " + score);
        if(score>79.9){
        	alert("登入成功");
        	window.location.assign("/face1to1/demoTest");
//        	window.location.href("/index_1N.html");
        } else {
        	alert("登入失敗");
        	$("#attr").val("");
        	window.location.href("/face1to1");
        }
    },"json") 
}

//groupAdd
function groupAdd(canvas, groupname){
	var info = {
            imgString: (typeof canvas == "string")?canvas:canvas.toDataURL("image/png"),
            groupname: groupname
        }

    $.post("/face1to1/groupAdd",info,function(data){
        console.log(data);
        alert("groupAdd: "+data);
    },"json") 
}


//1.1
function verify(canvas, canvas2){
	var info = {
            imgString: (typeof canvas == "string")?canvas:canvas.toDataURL("image/png"),
            imgString2: (typeof canvas2 == "string")?canvas2:canvas2.toDataURL("image/png")
        }

    $.post("/face1to1/verify",info,function(data){
        console.log(data);
        if(JSON.stringify(data.confidence)==null){
        	$("#confidence").val("不同人");
        }else{
			$("#confidence").val("相似率 "+JSON.stringify(data.confidence)+"%");
        }
        $("#attr").text(JSON.stringify(data));
//        $("#file").val("");
    },"json") 
}

//1.2
function detect(canvas){
	
  	var info = {
        imgString: (typeof canvas == "string")?canvas:canvas.toDataURL("image/png")
    }
  	var detectValue;
//  	console.log("canvas.toDataURL('image/png') " + canvas.toDataURL("image/png"));
  	 $.post("/face1to1/detect",info,function(data){
//           var test = data.faces[0];
//           var landmarks = JSON.stringify(test.Landmarks);
           $("#attr").text(JSON.stringify(data));
       },"json");
}


//1.3
function extract(canvas) {
	// $.post("/extract",info,function(data){
	// // console.log("extract");
	// // console.log(data);
	// },"json") ;
	var info = {
		imgString: (typeof canvas == "string")?canvas:canvas.toDataURL("image/png")
	}
	$.post("/face1to1/detect", info, function(data) {
		var test = data.faces[0];
		var landmarks = JSON.stringify(test.Landmarks);
		var info = {
			imgString: (typeof canvas == "string")?canvas:canvas.toDataURL("image/png"),
			landmarks : landmarks
		}
		$.post("/face1to1/extract", info, function(data) {
			 console.log(data);
			 $("#attr").text(JSON.stringify(data));
		}, "json");
		
	}, "json");
}



//1.4
function compare(canvas, canvas2){
	
	var info = {
			 imgString1: (typeof canvas == "string")?canvas:canvas.toDataURL("image/png"),
			 imgString2: (typeof canvas2 == "string")?canvas2:canvas2.toDataURL("image/png")
	} 
	
    $.post("/face1to1/compare",info,function(data){
        console.log(data);
        if(JSON.stringify(data.confidence)==null){
        	$("#confidence").val("不同人");
        }
        else{
        	$("#confidence").val("相似率 "+JSON.stringify(data.confidence)+"%");
        }
        $("#attr").text(JSON.stringify(data));
    },"json") 
	
}


//1.5
function extract_with_detect(canvas) {
	
	var info = {
		imgString: (typeof canvas == "string")?canvas:canvas.toDataURL("image/png")
	}
	$.post("/face1to1/extract_with_detect", info, function(data) {
		 console.log(data);
//		var test = data.faces[0];
//		var landmarks = JSON.stringify(test.Landmarks);
		 $("#attr").text(JSON.stringify(data));
	}, "json");
}

//1.6
function version(){
	$.post("/face1to1/version", function(data){
		 $("#attr").text(JSON.stringify(data));
	}, "json");
}
