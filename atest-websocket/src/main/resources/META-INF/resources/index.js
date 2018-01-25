(function() {
"strict";


angular.module("app", [
	"ngResource",
	]);


angular.module("app")
	.controller("HttpController", HttpController)
	.controller("WebSocketController", WebSocketController)
	;


HttpController.$inject = [ "$scope", "$http", ];
function HttpController($scope, $http) {
	var vm = this;
	$scope.vm = vm;
	
	vm.msg = "";
	
	vm.get = function() {
		$http.get("/http").then(function(resp) {
			vm.msg = resp.data;
		});
	}
}


WebSocketController.$inject = [ "$scope", ];
function WebSocketController($scope) {
	var vm = this;
	$scope.vm = vm;

	vm.ws = null;
	vm.msg = "";
	vm.sendMsg = "";
	vm.msgList = jQuery("#msgList");
	
	vm.connect = function() {
		if (vm.ws == null) {
			var uri = "ws://" + window.location.host + "/ws";
			vm.ws = new WebSocket(uri);
			vm.ws.onmessage = function(event) {
				vm.msg = event.data;
				vm.msgList.append(jQuery("<li/>").text("SERVER: " + vm.msg));
				$scope.$apply();
			}
		}
	}
	
	vm.send = function() {
		var msg = vm.sendMsg;
		vm.msgList.append(jQuery("<li/>").text("SEND: " + msg));
		vm.ws.send(msg);
		vm.sendMsg = "";
	}

}


})();
