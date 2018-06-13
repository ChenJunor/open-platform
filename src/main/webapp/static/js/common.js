var commonApp = angular.module('commonApp', []);
commonApp.factory('commService', [    //factory方式
    function(){
         var commSrv = {};

         commSrv.serverUrl = "http://l1453f9417.imwork.net/";

         commSrv.getUrlPara = function(name){
    		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");  
    		//匹配目标参数  
    		var r = window.location.search.substr(1).match(reg);  
    		//返回参数值  
    		if (r!=null) return unescape(r[2]);  
    		return null;  
        };

        return commSrv;
     }
 ]);