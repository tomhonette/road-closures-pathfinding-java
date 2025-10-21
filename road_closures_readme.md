# Road Closures Pathfinding

This Java program computes the **shortest travel time** between two towns while accounting for **temporary road closures**. It extends the classic Dijkstra algorithm by introducing **time-dependent constraints** — if a road is closed during the time you reach it, the algorithm will automatically wait until it reopens.


## Overview

- **Language:** Java
- **Algorithm:** Modified Dijkstra with waiting times
- **Input:** File specifying towns, roads, closures, and departure time
- **Output:** Minimum possible arrival time

This project was created as part of an early academic exercise exploring **graph traversal algorithms** and **temporal constraints**.


## Features

- Shortest path computation using a **priority queue** (efficient Dijkstra variant)
- Supports **time-based road closures** (dynamic waiting handled automatically)
- Reads structured input from file
- Clean **object-oriented design** with `Roads` and `Closures` classes
- Outputs the minimal travel time from start to destination


## Input Format

The input file must follow this structure:

```
T R C a b tdep
<t1> <t2> <duration>
<t1> <t2> <duration>
...
<road_index> <closure_start> <closure_end>
<road_index> <closure_start> <closure_end>
```

**Where:**
- `T` -> number of towns
- `R` -> number of roads
- `C` -> number of closures
- `a` -> starting town index
- `b` -> destination town index
- `tdep` -> departure time

Each of the next `R` lines defines a **bidirectional road** between two towns and its **travel duration**.
Each of the next `C` lines defines a **closure period** for a specific road.


## Example

### Example Input (`input.txt`):
```
5 6 2 1 5 0
1 2 4
1 3 2
2 3 1
2 4 5
3 4 8
4 5 3
2 0 10
4 3 7
```

### Example Output:
```
15
```

*(The shortest path from town 1 to 5 takes 15 units of time, considering closures.)*


## Algorithm Explanation

The program adapts **Dijkstra’s shortest path algorithm** to handle roads that can temporarily close.

At each step:
1. The algorithm checks if the road between the current town and the next is **closed** at the time of arrival.
2. If it is closed, it waits until the road reopens.
3. It then updates the total travel time and continues exploring the graph.

This approach ensures that even with multiple, overlapping closures, the computed path remains **time-feasible and optimal**.


## Class Structure

```text
Closed.java          → main class, handles input, algorithm, and output
Roads.java           → represents a connection between two towns
Closures.java        → represents closure intervals for a given road
```

## Running the Program

1. **Compile the code:**
   ```bash
   javac Closed.java
   ```

2. **Run the program:**
   ```bash
   java Closed input.txt
   ```

3. **Output:**
   The program prints the minimum arrival time at the destination.

---

## Customization

You can modify the input file to simulate different scenarios:
- Add or remove roads to change graph connectivity.
- Adjust closure intervals to test waiting logic.
- Change departure time (`tdep`) to explore temporal effects.


## Author
**Tom Honette**  
Originally developed as an assignment in my first year of studying Computer Sciences.

