var app = angular.module('svrhallApp', ['commonApp']);
app.controller('svrhallCtrl', function($scope,$http,$location,commService) {
	
    $http.get(commService.serverUrl + "weixin/haspvl?resid=1")
    .success(function(response) {
        $scope.roles = response.roleList;
    });

});