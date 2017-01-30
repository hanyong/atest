(function() {
"strict";

angular.module("app", [
	"ngResource",
	]);

angular.module("app")
	.controller("HttpController", HttpController)
	;

HttpController.$inject = [ "$scope", "$http", ];
function HttpController($scope, $http) {
	var vm = this;
	$scope.vm = vm;
	
	vm.name = "";
	vm.msg = "";
	
	vm.get = function() {
		$http.get("/http").then(function(resp) {
			vm.msg = resp.data;
		});
	}
}

})();
