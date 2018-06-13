var app = angular.module('pjtMdfApp', ['commonApp']);
app.controller('pjtMdfCtrl', function($scope,$http,$location,commService) {
	$scope.project = {};

    var state = commService.getUrlPara("state");

    $http.get(commService.serverUrl + "weixin/pjtdtl?id="+state)
    .success(function(response) {
        $scope.project.id = state;
        $scope.project = response.project;
    });

    if(!$scope.disabled){
        $scope.showBtn = "block";
    }
	
	$scope.showTip = "none";
	
    $scope.submit = function() {
    	$scope.rstmsg = "正在更新，请稍后";
    	$scope.showTip = "block";
        $http.post(commService.serverUrl + "weixin/pjt",$scope.project).success(function(data, status, headers, config){
            if(data.responeResult.code == 1){
            	$scope.rstmsg = "更新成功";
            }else{
            	$scope.rstmsg = "更新失败";
            }
        }).error(function(data, status, headers, config){
            $scope.rstmsg = "更新失败";
        });

    };

    $scope.closeTip = function(){
        $scope.showTip = "none";
    };

});