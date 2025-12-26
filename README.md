# Project Spaceship 
- **Author:** Kelly
- **Version:** 26/12/2025

## Overview

Project spaceship is a strategic space battle game for 2 players developed as a coding challenge for RSM. This implements necessary logic for the game. 
This project emphasises OOP principles while providing scalable and maintainable code. 

## Game actions
- Creates a “Player 1” fleet with 1 starbase and 3 ships in “Sector 1”.
- Creates a “Player 2” fleet with 1 starbase and 3 ships in “Sector 2”.
- Moves all ships in the “Player 1” fleet to “Sector 2”.
- Docks two ships from the “Player 2” fleet into the “Player 2” starbase.
- Selects one ship from the “Player 1” fleet and uses it to attack the remaining undocked starship from the “Player 2” fleet two times.
- Docks the remaining undocked starship in the “Player 2” fleet with the “Player 2” starbase then repairs it.
- Commands all starships in the “Player 1” fleet to attack the “Player 2” starbase repeatedly, until the “Player 2” starbase is destroyed.

## Features
 - Fleets  that can be initialised for multiple players with any number of starships and starbases. 
 - Starship objects that can move between sectors, dock and undock from starbases, repair at starbases, attack starships and starbases.
 - Starbase objects that dock and undock starships, calculate their current defence strength, calculate their current health and take damage from starships. 
 - Tracks health and crew of starships and starbases. 
 - Console-based action logs for easy debugging and testing. 

## Notes
Further developments will include the implementation of a UI and comprehensive JUnit testing.

## How to run the game actions script

Run the main file and messages that reflect the current game actions will be displayed in the console. 

## How to Run this project

Clone the repository:
   ```bash
   git clone https://github.com/hiraethene/spaceship-game

 
