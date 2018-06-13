var app = angular.module('signupApp', ['commonApp']);
app.controller('signupCtrl', function($scope,$http,$location,commService) {
	$scope.user = {};
	$scope.user.code = commService.getUrlPara("code");
	$scope.user.name = null;
	$scope.user.cellphone = null;
	$scope.user.email = null;

	$scope.showTip = "none";
	
    $scope.submit = function() {
    	$scope.rstmsg = "正在注册，请稍后";
    	$scope.showTip = "block";
        $http.post(commService.serverUrl + "weixin/signup",$scope.user).success(function(data, status, headers, config){
            if(data.responeResult.code == 1){
            	$scope.rstmsg = "注册成功";
            }else{
            	$scope.rstmsg = "注册失败";
            }
        }).error(function(data, status, headers, config){
            $scope.rstmsg = "注册失败";
        });

    };

    $scope.closeTip = function(){
        $scope.showTip = "none";
    };

});