var app = angular.module('roleApp', ['commonApp']);
app.controller('roleCtrl', function($scope,$http,$location,commService) {
	
    $http.get(commService.serverUrl + "weixin/roles")
    .success(function(response) {
        $scope.roles = response.roleList;
    });

});