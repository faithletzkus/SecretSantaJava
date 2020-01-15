# SecretSantaJava

-----About the program-----

This program allows the users to create a list of participants, generates Santa-Human pairs for each participant, and lets the users see the outcomes secretively. A Santa is the person giving the gift. A Human is the person receiving the gift. Each participant is both a Santa and a Human. 


-----Purpose-----

This is a personal project inspired by one of my suitemate's loves for Secret Santa. She wanted everyone in my suite to play Secret Santa, and I thought that It would be a fun project to code! This is my first iteration, written in Java, using the console for user input. 
I have done a second iteration of this SecretSanta program, where I created a website using HTML, CSS, and JavaScript (this program is also posted on my GitHub). 
Both iteratons were a lot of fun, and my suitemate really loved using it :D We are now going to make Secret Santa a suite tradition.


-----My Solution-----

My solution to the problem is modeled after the original version of Secret Santa where all participant’s names get put into a hat and everyone then draws from the hat to get their Human.
However, the old system has an honor system policy; if someone draws themself, they have to speak up so that they can draw again. My solution accounts for this and does not allow a person to have themselves. 

My solution allows for solutions such as
Participants = [a, b, c, d]
SantaToHuman = {a=b, b=a, c=d, d=c}
This solution is comprised of two subgroups where the first person is the Santa of the second, and the second is the Santa first.
I allow this to happen because it is closer to a truly random solution and most similar to the original hat-drawing method.
However, it causes a problem if there are an odd number because the last person cannot have themself.
I solve for this with the remainingHumans list, generating a new solution over again if the last humanRemaining is also the last Santa without a human.

Another way I could have solved the Secret Santa problem would be to have a full list of participants and an empty list of Santas, randomly select one participant from the original list each time, remove them from that list and add them to the Santa list. Then, each node in the Santa list would point to its Human and would be pointed to be its Santa. Once all participants were added to the Santa list, the last Santa in the Santa list would point to the first Node.
I did not solve it this way because this solution does not allow for subgroups (the first person is the Santa of the second and the second is the Santa first), which I think are an important and fun part of the Secret Santa solution.



-----Functionality-----

The program’s main method has two main parts, the setup where the user builds the participants list and the game play where the user finds out the Santa-Human pairs. 

In Setup, the user can add names to the list, print the usage, get the current participants list, remove a name from the list, finish creating the list, and end the program.
To move on from this point, the participants list must be at least of length 3. 

In Game Play, the user can type in their name to get their human, print the usage, edit the participants list and thus generate a new solution, get their Santa (if its really necessary), generate a new Santa-Human pair solution with the current participants list, and end the program.



-----Problems I ran into-----

I was originally printing out everything in the console. However, part of the fun of Secret Santa is that other people do not know who is buying who presents. To fix this, information on who is paired with who comes from a JFrame Message Dialog. Everything else occurs within the console.

The other issue I had was when coming up with a solution. Because I did allow for subgroups(a=b, b=a), I needed to come up with a good way for seeing when a solution was bad, and I needed to generate a new one. 
I ended up solving for this with the remainingHumans list, generating a new solution over again if the last humanRemaining is also the last Santa without a human.
For example: 
remainingHumans = [c]
santaToHuman = {a=b, b=a, c=?}
At this point, the last remaining Human is also the last remaining Santa without a partner, so I will generate a new solution.


-----Design choices-----

There must be at least 3 people in the participants list to continue on from the setup mode. I considered making this 4, because if there are just three people and each person knows their Human, you know the other person must be your Santa. However, I kept it with 3 as the minimum allowed because, with 3, it is still random whether each of the other two people is your Santa or your Human.

Get my Santa is a functionality in Game Play for administrative purposes. If it would be bad if a certain human had certain Santas, then you could see just that and generate a new solution to fix it. Without this feature, if you search by Santa as normal, then to figure out the Santa-Human match you might have to look through a lot of Santas. 
An additional update might be to allow the user to specify if any Santas and Humans should not be a match. 

Generate new solution, when clicked, runs the solution generating code. This means there is a chance that it produces the exact same solution as before. This was purposeful as it makes each solution more truly random. 



-----Test cases-----

I tested groups of sizes 4, 5, and 6, and tested all user inputs specified by the usage methods.

