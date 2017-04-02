(function(angular) {
	'use strict';
	angular.module('customControl', ['ngSanitize']).
	directive('contenteditable', ['$sce', function($sce) {
			return {
				restrict: 'A', // only activate on element attribute
				require: '?ngModel', // get a hold of NgModelController
				link: function(scope, element, attrs, ngModel) {
					if(!ngModel) return; // do nothing if no ng-model

					// Specify how UI should be updated
					ngModel.$render = function() {
						element.html($sce.getTrustedHtml(ngModel.$viewValue || ''));
					};

					// Listen for change events to enable binding
					element.on('blur keyup change', function() {
						scope.$evalAsync(read);
					});
					read(); // initialize

					// Write data to the model
					function read() {
						var html = element.html();
						// When we clear the content editable the browser leaves a <br> behind
						// If strip-br attribute is provided then we strip this out
						if(attrs.stripBr && html === '<br>') {
							html = '';
						}
						ngModel.$setViewValue(html);
					}
				}
			};
		}]).directive('deliveryAddress', ['$compile', '$parse', '$timeout', function($compile, $parse, $timeout) {
			return {
				replace: true,
				require: 'ngModel',
				priority: 1100,
				scope: {
					deliveryAddress: '=',
					action: '&'
				},
				templateUrl: 'deliveryAddress.html',
				link: function(scope, element, attrs, ngModel) {
debugger;
					function render(items) {
						angular.forEach(items, function(item) {
							var e = angular.element(item);
							if(ngModel.$modelValue == e.attr("id")) {
								e.addClass("mailer-selected");
							} else {
								e.removeClass("mailer-selected");
							}
						});
					}

					$timeout(function() {
						var addressDivList = element.children("div");
						// attach event
						addressDivList.on('click', function(event) {
							var div = event.currentTarget;
							var elem = angular.element(div);
							var id = elem.attr("id");
							!!id && scope.$evalAsync(function() {
								if(id) {
									ngModel.$setViewValue(id);
								}
								render(addressDivList);
							});
						})
						//init 
						render(addressDivList);
					}, 0);
				}
			};
		}])
		.controller("addressCtroller", ["$scope", function($scope) {
			var address1 = {
				province: "湖北",
				city: "武汉",
				contact: "顾小北",
				county: "洪山区",
				detailAddress: "街道口",
				mobileNo: "13340255164",
				addressId: "100"
			};
			var address2 = {
				province: "湖北",
				city: "武汉",
				contact: "顾小南",
				county: "武昌区",
				detailAddress: "街道口",
				mobileNo: "13340255164",
				addressId: "200"
			};
			var address3 = {
				province: "湖北",
				city: "武汉",
				contact: "顾小西",
				county: "汉阳区",
				detailAddress: "街道口",
				mobileNo: "13340255164",
				addressId: "300"
			};
			$scope.vm = {
				addressList: [address1, address2, address3],
				confirm: { addressId: '200' }
			};
			
			$scope.setDefault = function(aa){
				console.log("outer FN");
			}
		}]);
})(window.angular);