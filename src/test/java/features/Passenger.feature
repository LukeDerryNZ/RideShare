Feature: Passenger

    # Task 7:
    Scenario: View Available Rides
        Given I am a passenger
         When I select a stop point
         Then The available rides are displayed ordered by day and time
         Then The available rides may be filtered based on direction
         Then The user may book a ride or see its details

    #Task 8:
    Scenario: View A Rides Details
        Given I am a passenger
         When I select a ride
         Then I can view the drivers info
         Then I can view the car info
         Then I can view the route info
         Then I can book the ride
         Then I can go back to the list of rides

    #Task 9:
    Scenario: Book A Ride
        Given I am a passenger
         When I want to book a ride
         Then One of the cars seats will be flagged as booked
         Then It is not displayed as an available ride if no seats remain unflagged
