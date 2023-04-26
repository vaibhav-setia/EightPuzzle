import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class HeuristicSearch {

  int[] state;
  int depth;
  List<String> solutionPath;
  int priority;

  public HeuristicSearch() {
    this.state = new int[9];
    this.depth = 0;
    this.solutionPath = new ArrayList<>();
    this.priority = 0;
  }

  public HeuristicSearch
          (int[] input, int depth, List<String> path, int priority) {
    this.state = input;
    this.depth = depth;
    this.solutionPath = path;
    this.priority = priority;
  }

  PQueue pq = new PQueue();

  List<HeuristicSearch> vis = new ArrayList<>();

  int hOption;

  int[] goalSolution = {1, 2, 3, 4, 5, 6, 7, 8, 0};

  public static void main(String[] argv) {
    HeuristicSearch puzzle = new HeuristicSearch();
    puzzle.hOption = puzzle.initPuzzle();
    int[] init = puzzle.getState();

    System.out.print("Initial State: ");
    puzzle.printState(init);
    System.out.print("Goal State: ");
    puzzle.printGoalState();
    System.out.println("Searching...");

    long start = System.nanoTime();

    List<String> solution = (puzzle.AStarSearch(init));

    long totalTime = System.nanoTime() - start;

    //Final Output
    System.out.println("First 3 Moves of A* Search Strategy: ");
    puzzle.printSolutionPath(solution, init);
    System.out.println("Number of Moves Required: " + solution.size());
    System.out.println("Number of States Explored: " + puzzle.vis.size());
    System.out.println("Search Time: " + (totalTime / 1000000) + " milliseconds");
  }

  public List<String> AStarSearch(int[] initial) {

    ArrayList<String> path = new ArrayList<>();
    HeuristicSearch puzzle = new HeuristicSearch (initial, 0, path, 0);
    pq.add(puzzle);

    while (!pq.isEmpty()) {
      puzzle = pq.removeMin();
      vis.add(puzzle);
      if (isGoal(puzzle.getState())) return puzzle.getPath();
      else {
        expandState(puzzle.getState(), puzzle.getCost() + 1, puzzle.getPath());
      }
    }
    return puzzle.getPath();
  }

  public int[] getState() {
    return state;
  }

  public int getCost() {
    return depth;
  }

  public int getPriority() {
    return priority;
  }

  public List<String> getPath() {
    return solutionPath;
  }

  public void addToPath(String move) {
    solutionPath.add(move);
  }

  public void expandState(int[] state, int depth, List<String> path) {

    int idx = blankSpace(state);

    if (idx != 4) {

      if (idx == 0) {
        List<String> goRight = new ArrayList<>(path);
        int[] rightState = rightState(state, idx);
        int rightF = getCostPriority(rightState, depth);
        List<String> goDown = new ArrayList<>(path);
        int[] downState = downState(state, idx);
        int downF = getCostPriority(downState, depth);
        HeuristicSearch right = new HeuristicSearch(rightState, depth, goRight, rightF);
        HeuristicSearch down = new HeuristicSearch(downState, depth, goDown, downF);
        if ((!exploredContains(right)) && (!pq.contains(right))) {
          right.addToPath("Right");
          pq.add(right);
        }
        if ((!exploredContains(down)) && (!pq.contains(down))) {
          down.addToPath("Down");
          pq.add(down);
        }
      } else if (idx == 2) {
        List<String> goLeft = new ArrayList<>(path);
        int[] leftState = leftState(state, idx);
        int leftF = getCostPriority(leftState, depth);
        List<String> goDown = new ArrayList<>(path);
        int[] downState = downState(state, idx);
        int downF = getCostPriority(downState, depth);
        HeuristicSearch l = new HeuristicSearch(leftState, depth, goLeft, leftF);
        HeuristicSearch d = new HeuristicSearch(downState, depth, goDown, downF);
        if ((!exploredContains(l)) && (!pq.contains(l))) {
          l.addToPath("Left");
          pq.add(l);
        }
        if ((!exploredContains(d)) && (!pq.contains(d))) {
          d.addToPath("Down");
          pq.add(d);
        }
      } else if (idx == 6) {
        List<String> goRight = new ArrayList<>(path);
        int[] rightState = rightState(state, idx);
        int rightF = getCostPriority(rightState, depth);
        List<String> goUp = new ArrayList<>(path);
        int[] upState = upState(state, idx);
        int upF = getCostPriority(upState, depth);
        HeuristicSearch right = new HeuristicSearch(rightState, depth, goRight, rightF);
        HeuristicSearch up = new HeuristicSearch(upState, depth, goUp, upF);

        if ((!exploredContains(right)) && (!pq.contains(right))) {
          right.addToPath("Right");
          pq.add(right);
        }
        if ((!exploredContains(up)) && (!pq.contains(up))) {
          up.addToPath("Up");
          pq.add(up);
        }
      } else if (idx == 8) {
        ArrayList<String> leftPath = new ArrayList<>(path);
        int[] leftState = leftState(state, idx);
        int leftF = getCostPriority(leftState, depth);
        ArrayList<String> upPath = new ArrayList<>(path);
        int[] upState = upState(state, idx);
        int upF = getCostPriority(upState, depth);
        HeuristicSearch left = new HeuristicSearch(leftState, depth, leftPath, leftF);
        HeuristicSearch up = new HeuristicSearch(upState, depth, upPath, upF);
        if ((!exploredContains(left)) && (!pq.contains(left))) {
          left.addToPath("Left");
          pq.add(left);
        }
        if ((!exploredContains(up)) && (!pq.contains(up))) {
          up.addToPath("Up");
          pq.add(up);
        }
      } else if (idx == 1) {
        ArrayList<String> rightPath = new ArrayList<>(path);
        int[] rightState = rightState(state, idx);
        int rightF = getCostPriority(rightState, depth);
        ArrayList<String> leftPath = new ArrayList<>(path);
        int[] leftState = leftState(state, idx);
        int leftF = getCostPriority(leftState, depth);
        ArrayList<String> downPath = new ArrayList<>(path);
        int[] downState = downState(state, idx);
        int downF = getCostPriority(downState, depth);
        HeuristicSearch right = new HeuristicSearch(rightState, depth, rightPath, rightF);
        HeuristicSearch left = new HeuristicSearch(leftState, depth, leftPath, leftF);
        HeuristicSearch down = new HeuristicSearch(downState, depth, downPath, downF);
        if ((!exploredContains(right)) && (!pq.contains(right))) {
          right.addToPath("Right");
          pq.add(right);
        }
        if ((!exploredContains(left)) && (!pq.contains(left))) {
          left.addToPath("Left");
          pq.add(left);
        }
        if ((!exploredContains(down)) && (!pq.contains(down))) {
          down.addToPath("Down");
          pq.add(down);
        }
      } else if (idx == 3) {
        ArrayList<String> rightPath = new ArrayList<>(path);
        int[] rightState = rightState(state, idx);
        int rightF = getCostPriority(rightState, depth);
        ArrayList<String> upPath = new ArrayList<>(path);
        int[] upState = upState(state, idx);
        int upF = getCostPriority(upState, depth);
        ArrayList<String> downPath = new ArrayList<>(path);
        int[] downState = downState(state, idx);
        int downF = getCostPriority(downState, depth);
        HeuristicSearch right = new HeuristicSearch(rightState, depth, rightPath, rightF);
        HeuristicSearch up = new HeuristicSearch(upState, depth, upPath, upF);
        HeuristicSearch down = new HeuristicSearch(downState, depth, downPath, downF);

        if ((!exploredContains(right)) && (!pq.contains(right))) {
          right.addToPath("Right");
          pq.add(right);
        }
        if ((!exploredContains(up)) && (!pq.contains(up))) {
          up.addToPath("Up");
          pq.add(up);
        }
        if ((!exploredContains(down)) && (!pq.contains(down))) {
          down.addToPath("Down");
          pq.add(down);
        }
      } else if (idx == 5) {
        ArrayList<String> leftPath = new ArrayList<>(path);
        int[] leftState = leftState(state, idx);
        int leftF = getCostPriority(leftState, depth);
        ArrayList<String> upPath = new ArrayList<>(path);
        int[] upState = upState(state, idx);
        int upF = getCostPriority(upState, depth);
        ArrayList<String> downPath = new ArrayList<>(path);
        int[] downState = downState(state, idx);
        int downF = getCostPriority(downState, depth);
        HeuristicSearch left = new HeuristicSearch(leftState, depth, leftPath, leftF);
        HeuristicSearch up = new HeuristicSearch(upState, depth, upPath, upF);
        HeuristicSearch down = new HeuristicSearch(downState, depth, downPath, downF);

        if ((!exploredContains(left)) && (!pq.contains(left))) {
          left.addToPath("Left");
          pq.add(left);
        }
        if ((!exploredContains(up)) && (!pq.contains(up))) {
          up.addToPath("Up");
          pq.add(up);
        }
        if ((!exploredContains(down)) && (!pq.contains(down))) {
          down.addToPath("Down");
          pq.add(down);
        }
      } else if (idx == 7) {
        ArrayList<String> rightPath = new ArrayList<>(path);
        int[] rightState = rightState(state, idx);
        int rightF = getCostPriority(rightState, depth);
        ArrayList<String> leftPath = new ArrayList<>(path);
        int[] leftState = leftState(state, idx);
        int leftF = getCostPriority(leftState, depth);
        ArrayList<String> upPath = new ArrayList<>(path);
        int[] upState = upState(state, idx);
        int upF = getCostPriority(upState, depth);
        HeuristicSearch right = new HeuristicSearch(rightState, depth, rightPath, rightF);
        HeuristicSearch left = new HeuristicSearch(leftState, depth, leftPath, leftF);
        HeuristicSearch up = new HeuristicSearch(upState, depth, upPath, upF);
        if ((!exploredContains(right)) && (!pq.contains(right))) {
          right.addToPath("Right");
          pq.add(right);
        }
        if ((!exploredContains(left)) && (!pq.contains(left))) {
          left.addToPath("Left");
          pq.add(left);
        }
        if ((!exploredContains(up)) && (!pq.contains(up))) {
          up.addToPath("Up");
          pq.add(up);
        }
      }
    } else {
      ArrayList<String> rightPath = new ArrayList<>(path);
      int[] rightState = rightState(state, idx);
      int rightF = getCostPriority(rightState, depth);
      ArrayList<String> leftPath = new ArrayList<>(path);
      int[] leftState = leftState(state, idx);
      int leftF = getCostPriority(leftState, depth);
      ArrayList<String> upPath = new ArrayList<>(path);
      int[] upState = upState(state, idx);
      int upF = getCostPriority(upState, depth);
      ArrayList<String> downPath = new ArrayList<>(path);
      int[] downState = downState(state, idx);
      int downF = getCostPriority(downState, depth);
      HeuristicSearch
              right = new HeuristicSearch
              (rightState, depth, rightPath, rightF);
      HeuristicSearch
              left = new HeuristicSearch
              (leftState, depth, leftPath, leftF);
      HeuristicSearch
              up = new HeuristicSearch
              (upState, depth, upPath, upF);
      HeuristicSearch
              down = new HeuristicSearch
              (downState, depth, downPath, downF);

      if ((!exploredContains(right)) && (!pq.contains(right))) {
        right.addToPath("Right");
        pq.add(right);
      }
      if ((!exploredContains(left)) && (!pq.contains(left))) {
        left.addToPath("Left");
        pq.add(left);
      }
      if ((!exploredContains(up)) && (!pq.contains(up))) {
        up.addToPath("Up");
        pq.add(up);
      }
      if ((!exploredContains(down)) && (!pq.contains(down))) {
        down.addToPath("Down");
        pq.add(down);
      }
    }
  }

  public int[] leftState(int[] state, int index) {
    int[] newState = duplicateState(state);
    newState[index] = newState[index - 1];
    newState[index - 1] = 0;
    return newState;
  }

  public int[] rightState(int[] state, int index) {
    int[] newState = duplicateState(state);
    newState[index] = newState[index + 1];
    newState[index + 1] = 0;
    return newState;
  }

  public int[] upState(int[] state, int index) {
    int[] newState = duplicateState(state);
    newState[index] = newState[index - 3];
    newState[index - 3] = 0;
    return newState;
  }

  public int[] downState(int[] state, int index) {
    int[] newState = duplicateState(state);
    newState[index] = newState[index + 3];
    newState[index + 3] = 0;
    return newState;
  }

  public int blankSpace(int[] state) {
    for (int i = 0; i < 9; i++) {
      if (state[i] == 0) return i;
    }
    return -1;
  }

  public int initPuzzle() {
    Scanner s = new Scanner(System.in);

    System.out.println("Enter the input state please. Ensure it is space separated numbers only.");
    String answer = s.nextLine();

    if (answer.equals("YES")) {
      System.out.println("Enter puzzle numbers in row-major order:");
      BFSSearch.scan(s, state);
    } else {
      ArrayList<Integer> random = new ArrayList<>();
      for (int i = 0; i < 9; i++) {
        random.add(i);
      }
      Collections.shuffle(random);
      for (int i = 0; i < random.size(); i++) {
        state[i] = random.get(i);
      }
    }
    System.out.println("Enter which heuristic you would like to use. Type '1' for heuristic 1 and 2 for heuristic '2': ");
    int heuristic = s.nextInt();
    s.close();
    return heuristic;
  }

  public int[] duplicateState(int[] state) {
    int[] newState = new int[9];
    System.arraycopy(state, 0, newState, 0, 9);
    return newState;
  }

  public Boolean isGoal(int[] state) {
    for (int i = 0; i < 9; i++) {
      if (state[i] != goalSolution[i]) return false;
    }
    return true;
  }

  public void printState(int[] state) {
    for (int i = 0; i < 9; i++) {
      System.out.print(state[i] + " ");
    }
    System.out.println();
  }


  public void printGoalState() {
    printState(goalSolution);
  }

  public void printSolutionPath(List<String> path, int[] initialState) {
    int[] state = initialState;
    int index;
    int moves = 3;
    if (path.size() == 0) printState(state);
    else {
      if (path.size() < 3) moves = path.size();
      for (int i = 0; i < moves; i++) {
        index = blankSpace(state);
        if (Objects.equals(path.get(i), "Right")) {
          state = rightState(state, index);
          printState(state);
        } else if (Objects.equals(path.get(i), "Left")) {
          state = leftState(state, index);
          printState(state);
        } else if (Objects.equals(path.get(i), "Up")) {
          state = upState(state, index);
          printState(state);
        } else {
          state = downState(state, index);
          printState(state);
        }
      }
    }
  }

  public int getCostPriority(int[] inputState, int cost) {
    int stateHeuristic;
    if (hOption == 1) stateHeuristic = one_heuristic(inputState);
    else stateHeuristic = heuristic2(inputState);
    return stateHeuristic + cost;
  }

  public int one_heuristic(int[] state) {
    int heuristic = 0;
    for (int i = 0; i < 9; i++) {
      if ((state[i] != 0) && (state[i] != goalSolution[i])) heuristic++;
    }
    return heuristic;

  }

  public int heuristic2(int[] state) {
    int heuristic2 = 0;

    for (int i = 0; i < 9; i++) {
      if ((state[i] != 0) && (state[i] != goalSolution[i])) {

        int idx = checkGoalIndex(state[i], 0);

        if ((idx == i + 2) || (idx == i - 2)) heuristic2 += 2;
        else if ((idx == i + 3) || (idx == i - 3)) heuristic2 += 1;
        else if ((idx == i + 5) || (idx == i - 5)) heuristic2 += 3;
        else if ((idx == i + 6) || (idx == i - 6)) heuristic2 += 2;
        else if ((idx == i + 7) || (idx == i - 7)) heuristic2 += 3;
        else if ((idx == i + 8) || (idx == i - 8)) heuristic2 += 4;
        else if ((idx == i + 4) || (idx == i - 4)) {
          if ((idx == 6) || (idx == 2)) heuristic2 += 4;
          else heuristic2 += 2;
        } else if ((idx == i + 1) || (idx == i - 1)) {
          if (((idx == 2) && (i == 3)) || ((idx == 3) && (i == 2))
                  || ((idx == 5) && (i == 6)) || ((idx == 6) && (i == 5))) {
            heuristic2 += 3;
          } else heuristic2 += 1;
        }
      }
    }
    return heuristic2;

  }


  public int checkGoalIndex(int state, int goal) {
    if (goal == 0) {
      for (int i = 0; i < goalSolution.length; i++) {
        if (state == goalSolution[i]) {
          return i;
        }
      }
    }

    return -1;
  }


  public String stateToString(int[] state) {
    StringBuilder newState = new StringBuilder();
    for (int i = 0; i < 9; i++) {
      newState.append(state[i]);
    }
    return newState.toString();
  }


  public Boolean exploredContains(HeuristicSearch puzzle) {
    for (HeuristicSearch vi : vis) {
      if (stateToString(vi.getState()).equals(stateToString(puzzle.getState()))) return true;
    }
    return false;
  }
}