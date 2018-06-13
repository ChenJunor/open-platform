var app = angular.module('pjtCrtApp', ['commonApp']);
app.controller('pjtCrtCtrl', function($scope,$http,$location,commService) {
	$scope.project = {};

    $scope.project.name = null;
    $scope.project.description = null;
    $scope.project.progress = null;
    $scope.project.invest = null;
    $scope.project.reward = null;
    $scope.project.online = null;
    $scope.project.success = null;

	$scope.showTip = "none";
    
    $scope.submit = function() {
        $scope.rstmsg = "正在操作中，请稍后";
        $scope.showTip = "block";
        $http.post(commService.serverUrl + "weixin/pjt",$scope.project).success(function(data, status, headers, config){
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