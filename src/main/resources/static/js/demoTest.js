var action = -1;


	function plugin() {
            return document.getElementById('plugin');
    }

    function addEvent(obj, name, func)
        {
            if (obj.attachEvent) {
                obj.attachEvent("on"+name, func);
            } else {
                obj.addEventListener(name, func, false); 
            }
    }

    function init() {
    	loadDevices();
        plugin().init(JSON.stringify({
        	//bestImageCount: 4,
            //saveToLocalPath: "d:/faceTmp",
        	picWidth:100,
        	tipHeight: 100,
			

        	// roi: {
        	// 	x: 100,
        	// 	y: 100,
        	// 	width: 160,
        	// 	height: 120
        	// }

            //fps: 30,
            //minQuality: 30,
            //tipHeight: 150,
            //drawDoor: true,
			//picWidth: 100,
            //liveFaceRectThickness: 1,
            //resultFaceRectThickness: 1,
            //cropVideo: true,
            //aimlessTimeout: 56,
            //hideProgress: true,
            //hasSubTip: true,
            //textAlign: "center",
            //deviceVidPid: "2b16bcd6",
            // roi: {
            // 	x: 100,
            // 	y: 75,
            // 	width: 400,
            // 	height: 300,
            // },
            //roiThickness: 1,
            // minFaceWidth: 150,
            //videoSource:"rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov"    
            /*        
            showDebugging: true,

            fmpThreshold: 1,
            minQuality: 0,
            fmpMinQuality: 0,
            aimlessGoodCount: 8,
            minFaceWidth: 80,
            // roi: {
            //     x: 200,
            //     y: 200,
            //     width: 320,
            //     height: 240
            // },
            tipHeight: 200,
            picWidth: 0,
            hideProgress: true,
            hasSubTip: true,
            textAlign: "center",
            minBrightness: 40,
            maxBrightness: 10000*/
        }));
        //start();
    }

    function loadDevices() {
    	var devices = plugin().getVideoDevices();
    	devices = JSON.parse(devices);
    	var deviceSetting = document.getElementById("deviceSetting");
    	var select = document.getElementById("devices");
    	if(devices.length <= 1) {
    		deviceSetting.style.visibility = "hidden";
    		return;
    	}
    	var defaultVidPid = plugin().getDefaultVideoDevice();

    	for(var i=0;i<devices.length;i++){
  			var device = devices[i];
  			var option = document.createElement("option");
  			option.setAttribute("value", device.vidpid);
  			option.text = device.name;
  			if(device.vidpid == defaultVidPid) {
  				option.setAttribute("selected", "selected");
  			}
  			select.appendChild(option);
    	}
    	function handleChangeDevice() {
    		var vidpid = select.options[select.selectedIndex].value;
    		plugin().setDefaultVideoDevice(vidpid);
    	}
    	select.onchange = handleChangeDevice;
    }

    function start() {
            plugin().start();
    }
    
    function getVersion() {
        var plugin = document.getElementById("plugin");
        alert("version:" + plugin.getVersion() + "\n" +
			  "Build:" + plugin.getBuild()
		);
        
    }

    function getVideoDevices() {
    	var devices = plugin().getVideoDevices();
    	//alert(devices);
    	/**/
    	devices = JSON.parse(devices);
    	var s = "";
    	for(var i=0;i<devices.length;i++) {
    		var device = devices[i];
    		s += "id:" + device.id + "\n" +
    		   "name:" + device.name + "\n" +
    		   "path:" + device.path + "\n" +
    		   "vid:" + device.vid + "\n" +
    		   "pid:" + device.pid + "\n" +
    		   "vidpid:" + device.vidpid + "\n";
    	}
    	alert(s);
    	
    }

    load = function() {
    
        addEvent(plugin(), 'hello', function(a){
        	if(typeof console !== "undefined") {
            	console.log(a);
            }
        });
        addEvent(plugin(), "facesuccess", function(a) {
            //window.location.reload();
            //return;
            //alert(a);
            //console.log("facesuccess:", a)
            var x = JSON.parse(a);
            if(typeof console !== "undefined") {
            	var base64 = x.bestImage.image_data;
            	console.log("facesuccess:" + x);
				console.log("bestImage.image_data(BASE64_jpeg):" + base64);
				console.log("delta:" + x.delta);
				console.log("deltaCheck:" + x.deltaCheck);
				if(action==1){
					//1.1
					$("#attr").text("");
					console.log(1.1);
					var canvas1 = document.getElementById("canvas1");
					verify(base64, canvas1);
				} else if(action==2){
					//1.2
					$("#attr").text("");
					console.log(1.2);
					detect(base64);
				} else if(action==3){
					//1.3
					console.log(1.3);
					$("#attr").text("");
					extract(base64)
				} else if(action==4){
					//1.4
					console.log(1.4);
					$("#attr").text("");
					var canvas1 = document.getElementById("canvas1");
					compare(canvas1, base64);
				} else if(action==5){
					//1.5
					console.log(1.5)
					$("#attr").text("");;
					extract_with_detect(base64);
            	} else if(action==0){
            		//capture
            		$("#attr").text("");
            		console.log("capture");
            		console.log(base64);
            		draw(base64);
            	} else if(action==6){
            		$("#attr").text("驗證中，請稍等。");
            		console.log("login");
					login(base64);
            	}
            }
        });
        addEvent(plugin(), "facefail", function(a) {
        	//window.location.reload();
        	//return;
//        	$("#file").val("");
        	var x = JSON.parse(a);
        	if(typeof console !== "undefined") {
        		console.log("facefail:", x);
        	}
        }); 
        setTimeout(init, 0);
        //init();
    }

    function stop() {
            plugin().stop();
    }
    
    function getFile(){
    	var test = $(this);
    	console.log(test[0]);
    	var file = document.getElementById("file").files[0];
    	var canvas1 = document.getElementById("canvas1");
    	console.log(file);
    	var reader = new FileReader();
	    var img = new Image;
	    var context = canvas1.getContext('2d');
        reader.readAsDataURL(file);
        reader.onload = function(e) {
            // 圖片base64化
            var newUrl = this.result;
            img.src = newUrl;
            img.onload = function(){
            	//canvas2.style.backgroundImage = "url(" + newUrl + ")";
//              canvas1.width = img.width;
//    			canvas1.height = img.height;
            	canvas1.width = 600;
    			canvas1.height = 600;
            	context.clearRect(0, 0, canvas1.width, canvas1.height);
            	context.drawImage(img, 0, 100);
            }
            console.log("img "+img);
            action = 0;
//            $("#file").val("");
            
			
        };
    }
    
    
    function capture(){
    	action = 0;
    	start();
    }
    
    function draw(data){
//    	console.log("data ");
//    	console.log(data);
    	data = "data:image/jpeg;base64,"+data;
//    	console.log(data);
    	var image = new Image();
    	image.src = data;
//    	console.log(image);
    	var canvas1 = document.getElementById("canvas1");
    	var context = canvas1.getContext("2d");
    	
    	
    	image.onload = function() {
    		canvas1.width = 600;
			canvas1.height = 600;
			context.clearRect(0, 0, canvas1.width, canvas1.height);
    		context.drawImage(image, 0, 100);
    	};
    	stop();
   
    }
   
    