var newsApp = angular.module('newsApp', ['commonApp']);

newsApp.controller('newsCtrl', function($scope,$http,commService) {
	$http.get(commService.serverUrl + "weixin/news")
    .success(function(response) {
        $scope.newsList = response.articleList;
    });
});