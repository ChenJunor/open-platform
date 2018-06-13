var app = angular.module('roleMrgApp', ['commonApp']);
app.controller('roleMrgCtrl', function($scope,$http,$location,commService) {
	$scope.userRole = {};
	$scope.userRole.roleId = commService.getUrlPara("id");

    $http.get(commService.serverUrl + "weixin/role?id=" + $scope.userRole.roleId)
    .success(function(response) {
        $scope.userRole.name = response.role.name;
    });

    $scope.userRole.type = commService.getUrlPara("type");
    $scope.userRole.opt = '添加';
    if('remove' == $scope.userRole.type){
        $scope.userRole.opt = '移除';
    }

	$scope.userRole.cellphone = null;

	$scope.showTip = "none";
	
    $scope.submit = function() {
    	$scope.rstmsg = "正在操作中，请稍后";
    	$scope.showTip = "block";
        $http.post(commService.serverUrl + "weixin/mrgur",$scope.userRole).success(function(data, status, headers, config){
            if(data.responeResult.code == 1){
            	$scope.rstmsg = "操作成功";
            }else{
            	$scope.rstmsg = "操作失败";
            }
        }).error(function(data, status, headers, config){
            $scope.rstmsg = "操作失败";
        });

    };

    $scope.closeTip = function(){
        $scope.showTip = "none";
    };

});