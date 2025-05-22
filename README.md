# Football World Cup Score Board

Coding exercise for <Sportradar>.  Simple implementation in Java.

## Requirements
### The boards support the following operations:
1. **Start a game**. When a game starts, it should capture (being initial score 0-0)  
   a. Home team  
   b. Away Team
2. **Finish a game**. It will remove a match from the scoreboard.
3. **Update score**. Receiving the pair score; home team score and away team score
   updates a game score
4. **Get a summary of games by total score**. Those games with the same total score
   will be returned ordered by the most recently added to our system.

### As an example, being the current data in the system:
a. Mexico - Canada: 0 – 5  
b. Spain - Brazil: 10 – 2  
c. Germany - France: 2 – 2  
d. Uruguay - Italy: 6 – 6  
e. Argentina - Australia: 3 - 1  

The summary would provide with the following information:  
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2

### Used technologies
- **Java**
- **Gradle**
- **Spock**

## Tests
See `src/test` for executable specification.  
run `./gradlew test`

## Assumptions

### Business Assumptions

- Scores can only be increased, not decreased during a match
- Multiple matches with the same teams can be played simultaneously
- Only positive scores are allowed
- Updating the score to smaller values is not possible
- Match can be finished at any time regardless of the score
- Multiple matches can be started at the same time, which can give unpredictable sorting in scoreboard summary

### Technical Assumptions

- System is not thread-safe and doesn't support concurrent operations
- Generic exceptions are thrown for validation errors instead of specific business exceptions
- Match IDs are generated using UUID to ensure uniqueness
- In-memory storage is used for simplicity
