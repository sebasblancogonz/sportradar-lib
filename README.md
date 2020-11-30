## About The Project

Sportradar Lib is a simple library that uses spring events in order to keep the updated in real 
time, as long as the providers publish the given events following this structure:

| MatchEvent    |
| :----:        |
| name (string) |
| MatchDTO      |

* _start_: The start event will provide the match with an initial score of 0 for each
of the teams.  

* _update_: This event will provide the match with the information updated, and we will
update our information if the match has been already registered.

* _end_: If we receive this event, it means that the match has finished, and can proceed to
its elimination from our live scoreboard.

We have another component, which is our Service, that will provide a summary of the scoreboard,
ordering the matches by the most recently added to our system. 

### Built With

I decided to move with **Spring events**, since in my opinion, it was a good way to keep information 
updated. Every time the score changes, I assumed that the data providers would trigger an 
event to be captured by their listeners.

You can see I also decided to use a **LinkedHashMap** as an in-memory store solution. I chose
this data structure because I wanted to have a __key - value__ approach, but I also wanted to 
keep an order into it, in this case, insertion order. Later on, in the summary service I just 
had to reverse this list, and I would have it properly ordered. 

I also decided to use **ArrayList** instead of **LinkedList** (my first approach was LinkedList) 
because it was going to be used only to read, so for this purpose ArrayLists are more efficient than
LinkedLists.

## Run tests

To run the tests simply execute this in the project's folder:
``gradlew clean test -i``