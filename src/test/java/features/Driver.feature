Feature: Driver

    # Task 1:
    Scenario: Register a car
        Given I am a driver
         When I register a car
         Then The car is added to the drivers account

    #Task 2:
    Scenario: Create a stop point
        Given I am a driver
         When I create a stop point
         Then I can specify the address
         Then The stop point is added to the route
         Then The stop point cannot be added more than once

    #Task 3: // Not required
    
    #Task 4:
    Scenario: Create Trips
    	Given I am a driver
    	 When I add a trip
    	 Then I want to specify the direction
    	 Then I want to specify whether the trip is recurring
    	 Then I want to specify the days
    	 Then I want to specify the expiry date
    	 Then I want to specify which car is used
        