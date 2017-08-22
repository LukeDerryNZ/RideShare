# UC_RSS  
Ride Share System  

Project Details: A Ride Sharing System using Java/JavaFX  


Project Backlog:  

1. As a ​​driver​​, I want to ​​register a car​​ so that it can be used for sharing a ride.  
   Acceptance criteria:  
  ● A driver can add as many cars as s/he wants to their account, defining data such  
    as: type, model, colour, license plate, year and the number of physical seats.  

2. As ​​driver​​, I want to ​​create stop points so I can specify where I will pick up or drop  
   off passengers.  
   Acceptance criteria:  
  ● Stop points can be defined by their street address;  
  ● The same address cannot be added more than once.  

3. As ​​driver​​, I want to ​​add a route so I don’t have to re-enter the stop points I will  
   pass for every trip (see Story 4).  
   Acceptance criteria:  
  ● A route is a list of stop points that the drivers is willing to pick up or drop off a  
    passenger.  

4. As ​​driver​​, I want to ​​add trips so that I can set up all the information for the rides  
   that I will share later on (see Story 5).  
   Acceptance criteria:  
  ● A driver will:  
    ○ Select a route, and set the times that they will pass each stop point;  
    ○ Specify the direction they will travel (to / from Uni);  
    ○ Indicate whether this is a recurrent trip, and if so,  
      ■ Define the days on which the trip will happen;  
      ■ Define the expiry date after which trips will no longer be rescheduled.  
    ○ Indicate which of the driver’s cars will be used.  
  ● The user will then be notified that the trip was created and all the trip data will be  
    displayed.  

5. As a ​​driver​​, I want to ​​share a ride so that it can be displayed to potential  
   passengers.  
   Acceptance criteria:  
  ● While sharing a ride, the driver will define the initial number of seats that will be  
    made available to passengers for this particular ride;  
  ● The number of available seats on the car must be less than or equal than the  
    number of physical seats (defined in Story 1);  
  ● When a passenger books a ride, the number of available seats will be decreased  
    accordingly;  
  ● As default, all the physical seats are created as not available for the ride;  
  ● While a ride has available seats, the ride data (stop-times, price, driver, etc.) should  
    be available. When no more seats are available, the ride should no longer be  
    displayed when a passenger searches for rides (see Story 7).  

6. As a ​​user​​, I want to ​​search for existing stop points so that I can define or find  
   rides.  
   Acceptance criteria:  
  ● A user can see a list of all the stop points ordered by the location (number, street,  
    suburb);  
  ● A user can search for an existing stop point in a suburb or on a street. The result  
    will be ordered by location.  
  
7. As a ​​passenger​​, I want to ​​see the available rides at a specific stop point so that I  
   can choose one.  
   Acceptance criteria:  
  ● When a user selects a stop point, the available rides (stop-times) will be displayed  
    ordered by day and time;  
  ● The user can filter rides based on the direction they are travelling (to/from Uni);  
  ● The user can select an option and book a ride ​or​​ see its details (Story 8).  
  
8. As ​​passenger​​, I want to ​​see the details of a specific ride so that I can choose the  
   best ride for me.  
   Acceptance criteria:  
  ● The user will see the related trip details such as: driver's info (name, photo, grade,  
    etc.), car data (model, color, year, performance, seats available, etc.), route length  
    and number of stops.  
  ● The user can book the ride detailed ​or​​ go back to the list of rides (Story 7 result).  
  
9. As ​​passenger​​, I want to ​​book a ride​​ so that I can get to where I am going.  
   Acceptance criteria:  
  ● Either while browsing the Story 7 result or looking at a ride details (Story 8) a  
    passenger can invoke the functionality to book a ride.  
  ● The consequences of this action will be:  
    ○ One of available seats in the driver’s car will be flagged as booked for the current  
    passenger;  
    ○ When there are no more available seats in the driver's car, it should no longer be  
      displayed as result of Story 7.  
