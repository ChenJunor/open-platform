var app = angular.module('pjtDtlApp', ['commonApp']);
app.controller('pjtDtlCtrl', function($scope,$http,$location,commService) {
	$scope.project = {};

    var state = commService.getUrlPara("state");
    $http.get(commService.serverUrl + "weixin/pjtdtl?id="+state)
    .success(function(response) {
        $scope.project.id = state;
        $scope.project = response.project;
    });

});