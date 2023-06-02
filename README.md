# Stones Game (Homework Project)

Stones Game is a two-player game played on a 3*3 board.
The objective of the game is to have three stones of the same color in a row, column, or diagonal.

## Rules
- The game is played with two players.

![1](https://github.com/INBPA0420L/homework-project-HanyAyad/assets/52006612/ff925616-b9eb-4e0b-b5d7-9d134d766e17)


- The board is initially empty.

![2](https://github.com/INBPA0420L/homework-project-HanyAyad/assets/52006612/8fb30d26-e255-4ff9-abb9-4f45f12dd951)


- Players take turns placing stones onto the board.
- The stones come in three colors: red, yellow, and green.

![4](https://github.com/INBPA0420L/homework-project-HanyAyad/assets/52006612/747cb742-c730-4359-b5a5-e5dbae91133e)


- The first player places a red stone onto an empty cell.

![3](https://github.com/INBPA0420L/homework-project-HanyAyad/assets/52006612/11d27c7f-8f12-488e-940c-81df57dd20c3)


- On turns, players can replace a red stone with a yellow stone or a yellow stone with a green stone.

![5](https://github.com/INBPA0420L/homework-project-HanyAyad/assets/52006612/52e6a96d-8981-4ba8-bdd0-33a1ddc4db57)


- The game ends when a player has three stones of the same color in a row, column, or diagonal.
- The player who makes the winning move wins the game.

![6](https://github.com/INBPA0420L/homework-project-HanyAyad/assets/52006612/7f820dca-8489-4c06-a1a9-28360195fd92)


- After the game ends, the leaderboard is updated with the Top 5 players based on their wins count.

![7](https://github.com/INBPA0420L/homework-project-HanyAyad/assets/52006612/5b67dc23-6f1c-45f2-ad53-1332b5074db2)


## Project tech stack and workflow:
- JavaFX SceneBuilder was used to build the Graphical User Interface.
- The project was built using Maven buildtool, following Model-View-Controller (MVC) architectural pattern, and implementing all SOLID principles and clean code principles. Unit tests were made for the model classes using JUnit5.
- Plugins like Maven Javadoc, Maven JXR, Maven Checkstyle, Maven Surefire Report, JaCoCo Maven were used to generate reports to be displayed on project site.
- Jackson JSON processor was used to store game results in a json file to maintain a form of database.
- Tinylog 2 framework was used to perform lightweight logging for the project.