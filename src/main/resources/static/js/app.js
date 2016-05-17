angular.module('springboot-angular', [])
.controller('usersCtrl', function($scope, $http) {
	
    $http.get("http://localhost:8080/users")
    .success(function(data, status, headers, config) {$scope.users = data;});
    
    
    $scope.addUser = function (){

    	var newUser = {userName:$scope.user.userName, firstName:$scope.user.firstName, lastName:$scope.user.lastName};

    	$scope.users.push(newUser);
    	console.log(newUser);
    	 $http({url : "http://localhost:8080/users", 
    		 method: "POST",
    		 data: newUser})
    	    .success(function(data, status, headers, config) {
    	    	console.log("added sucessfully");
    	    });
    }
    
    $scope.deleteUser = function(userName) {   
    	$http(
            {url: "http://localhost:8080/users/"+userName, 
            method: "DELETE"})
        .success(function(data, status, headers, config) {
        	for(var i = 0; i < $scope.users.length; i++){
                      if( $scope.users[i].userName === userName){

                        $scope.users.splice(i, 1);
                        break;
                      }

            }
        });
      }
});