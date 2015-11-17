# kChallenge
Kuali Challenge Problem

NOTES:

Written in Java.

Possibly could have mapped out more sub objects & stuff here, but tried to simplify and ended up
with only 3 classes - A Building, a controller for the building elevators, and the elevators themselves. (I had a 'Building' class, but really it was the same as the controller, so opted to remove it, but then added it back again.  The only real purpose of the building is that it contains the others.  An elevator would be of no purpose outside of a building, so it is important.)

the elevators should be pretty self sufficient, receiving commands and figuring out the best
way to carry them out (smart enough to stop at each floor that has been requested)

The main purpose of the controller is to figure out which elevators are best suited for the request
and either initiate an elevator on a trip, or tell a moving elevator to add a stop.

Some things to consider (future) is how to optimize all the threading, and how to handle edge
cases (like someone staying on the elevator and pushing all the buttons at once, or top then bottom, then middle - essentially how to prioritize.  For instance, in 100 floor building, if the elevator is on the 4th floor and someone pushes 100, then someone pushes 2, do we go to #2 because its closer, or go to #100 because they pushed it first?)  Anyway, just food for thought.

I was not able to perfect and get all the code working, but I think most of the structure is there.

Hope you enjoy!
