//读取风速
function GetMessage()
{
    $.get("https://fishei.cn/competition/windForce", function (data) {
        //console.log(data) //返回对象
        //console.log(data["firstData"]["avgData"])
        //console.log(data["firstData"]["notice"])
        //console.log(data["avgOneNotice"])
        document.getElementById("show-fs").innerHTML=data["firstData"]["avgData"];
        //document.getElementById("h-fs-one").innerHTML="最大风速："+data["firstData"]["maxData"]+"m/s,最小风速："+data["firstData"]["minData"]+"m/s";
        document.getElementById("h-fs-two").innerHTML=data["firstData"]["notice"];
        document.getElementById("w-one").innerHTML=data["avgOneNotice"];
        document.getElementById("w-two").innerHTML=data["avgTwoNotice"];
        document.getElementById("w-three").innerHTML=data["comfort"];
        if(data["firstData"]["avgData"]>=13)
        {
            document.getElementById("show-fs").style.color="red";
        }
        else{
            document.getElementById("show-fs").style.color="white";
        }
        })
    //风向
    $.get("https://fishei.cn/competition/windDirection", function (data) {
        //console.log(data) //返回对象
        //console.log(data["firstData"]["dataPoints"][0]);
        var detail=data["firstData"]["dataPoints"][0];
        var wind = detail["value"];
        //console.log(wind);
        document.getElementById("show-fx").innerHTML=wind;
        })
    //噪声
    $.get("https://fishei.cn/competition/noise", function (data) {
        //console.log(data)
        console.log(data["firstData"]["dataPoints"][0]);
        var noise = data["firstData"]["dataPoints"][0];
        document.getElementById("show-noise").innerHTML=noise["value"];
        //document.getElementById("h-noise-one").innerHTML="最大噪声："+data["firstData"]["maxData"]+"dB,最小噪声："+data["firstData"]["minData"]+"dB";
        document.getElementById("h-noise-two").innerHTML=data["firstData"]["notice"];
        console.log(noise["value"]);
        if(noise["value"]>=55)
        {
            document.getElementById("show-noise").style.color="red";
        }
        else{
            document.getElementById("show-noise").style.color="white";
        }
        })
    //pm 2.5
    $.get("https://fishei.cn/competition/pm25", function (data) {
        //console.log(data)
        //console.log(data["firstData"]["avgData"]);
        document.getElementById("show-pm").innerHTML=data["firstData"]["avgData"];
        //document.getElementById("h-pm-one").innerHTML="PM2.5最大值："+data["firstData"]["maxData"]+"μg/m3,PM2.5最小值："+data["firstData"]["minData"]+"μg/m3";
        document.getElementById("h-pm-two").innerHTML=data["firstData"]["notice"];
        if(data["firstData"]["avgData"]>=100)
        {
            document.getElementById("show-pm").style.color="red";
        }
        else{
            document.getElementById("show-pm").style.color="white";
        }
        })
    //温度+湿度
    $.get("https://fishei.cn/competition/temp", function (data) {
        //console.log(data)
        //console.log(data["firstData"]["avgData"]);
        //console.log(data["secondData"]["avgData"]);
        document.getElementById("show-temp").innerHTML=data["firstData"]["avgData"];
        //document.getElementById("h-temp-one").innerHTML="最高温度："+data["firstData"]["maxData"]+"°C,最低温度："+data["firstData"]["minData"]+"°C";
        document.getElementById("h-temp-two").innerHTML=data["firstData"]["notice"];
        document.getElementById("show-humidity").innerHTML=data["secondData"]["avgData"];
        //document.getElementById("h-humidity-one").innerHTML="最大湿度："+data["secondData"]["maxData"]+"%rh,最小湿度："+data["secondData"]["minData"]+"%rh";
        document.getElementById("h-humidity-two").innerHTML=data["secondData"]["notice"];
        if(data["firstData"]["avgData"]>=35 || data["firstData"]["avgData"]<=4)
        {
            document.getElementById("show-temp").style.color="red";
        }
        else{
            document.getElementById("show-temp").style.color="white";
        }

        if(data["secondData"]["avgData"]>=70 || data["secondData"]["avgData"]<=40)
        {
            document.getElementById("show-humidity").style.color="red";
        }
        else{
            document.getElementById("show-humidity").style.color="white";
        }
        })
}
GetMessage();
setInterval(GetMessage, 1000);

