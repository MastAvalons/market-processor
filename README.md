market-processor
==========================

A simple example application that receives and processes currency trade requests.

1. Rest API that receives new trade requests.
2. JMS Queue to process all the requests
3. AngularJS Dashboard to monitor all the trades
4. Uses Socket.IO to auto refresh the dashboard when a new trade is processed by the Queue

##Technologies

- Spring
- Hibernate 
- JMS w/ ActiveMQ
- Spring MVC
- AngularJS 
- Socket.IO


- Google material design lite dashboard demo layout:  [http://www.getmdl.io/templates/dashboard/index.html](http://www.getmdl.io/templates/dashboard/index.html)
  



##Assumptions

  This application is designed for development test purposes. Because of this, it uses:

- Embedded Databse
- Embedded ActiveMQ
- CDN for front-end libraries

**If you want to upgrade to a production application, It is strongly recommended to use an external database and an external ActiveMQ.**


##Requirements

   * Apache Maven
   * Java 8


## Run this project

   1. Clone:

    <pre>
    git clone https://github.com/brunathayanadantas/market-processor    
    </pre>
  
   2. Run the application:
  
    <pre>
    mvn tomcat7:run
    </pre>

   3. Dashboard URL: [http://localhost:8080/market-processor/](http://localhost:8080/market-processor/) 

   4. Post new Trades using the Rest API: 

   <pre>
    curl -i -X POST -H "Content-Type:application/json" http://localhost:8080/market-processor/api/trades/ -d '{"userId": "134256", "currencyFrom": "EUR", "currencyTo": "GBP", "amountSell": 1000, "amountBuy": 747.10, "rate": 0.7471, "timePlaced" : "24-JAN-15 10:27:44", "originatingCountry" : "FR"}'
   </pre>

