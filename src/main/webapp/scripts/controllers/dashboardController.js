'use strict';

/**
 * @name dashboardController
 * @description 
 * Dashboard Controller.
 */
angular.module("dashboardApp").controller('dashboardController',
    function ($scope){
        $scope.tradeDashboard = {
        		trades : [],
        		quantityTransaction : 0,
        };
        $scope.dashboardLoaded = false;

        //Connect to the remote socket io server init.
        var socket = io('http://localhost:9092');
        
        //SocketIO Event emitter - Init the dashboard
        google.charts.load('current', {'packages':['geochart']});
        google.charts.setOnLoadCallback(initDashboard);
        function initDashboard(){
            //Emit event to request the dashboard
            socket.emit('request-dashboard-trade');
        }
        
        
        //Event Listener that receives the dashboard
        socket.on('dashboard-trade', function(tradeDashboard){ 
        	$scope.tradeDashboard = tradeDashboard; 
        	
        	var details = [['Country', 'Transactions Originated']]; 
        	angular.forEach(tradeDashboard.tradesDetails, function(detail){
        		details.push([detail.originatingCountry, detail.quantity]);
        	});
        	
            //Update the Geo Map  
            var data = google.visualization.arrayToDataTable(details);
            var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));
            chart.draw(data, {});
             
            $scope.dashboardLoaded = true; 
            $scope.$apply();
        });

    });
