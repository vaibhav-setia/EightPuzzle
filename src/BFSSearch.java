import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BFSSearch {
  int[] puzzleState;
  int depth;
  ArrayList<String> solutionPath;

  public BFSSearch() {
    this.puzzleState = new int[9];
    this.depth = 0;
    this.solutionPath = new ArrayList<>();
  }

  public BFSSearch(int[] puzzle, int depth, ArrayList<String> path) {
    this.puzzleState = puzzle;
    this.depth = depth;
    this.solutionPath = path;
  }

  ArrayList<BFSSearch> frontier = new ArrayList<>();

  ArrayList<BFSSearch> explored = new ArrayList<>();

  static int[] goal0 = {1, 2, 3, 4, 5, 6, 7, 8, 0};


  public static void main(String[] argv) {

    BFSSearch puzzle = new BFSSearch();
    puzzle.initPuzzle();
    int[] initialState = puzzle.getState();

    System.out.println("Input State : ");
    puzzle.printState(initialState);
    System.out.println("Final State: ");
    puzzle.printState(goal0);
    System.out.println(" Solving the 8 puzzle game ");

    long startTime = System.nanoTime();

    List<String> solution = (puzzle.BFS(initialState));

    long totalTime = System.nanoTime() - startTime;

    System.out.println("First 3 Moves of BFS Strategy: ");
    puzzle.printSolution(solution, initialState);
    System.out.println("Number of Moves Required: " + solution.size());
    System.out.println("Number of States Explored: " + puzzle.explored.size());
    System.out.println("Search Time: " + (totalTime / 1000000) + " milliseconds");

  }
  public ArrayList<String> BFS(int[] initial) {

    ArrayList<String> path = new ArrayList<>();
    BFSSearch puzzle = new BFSSearch (initial, 0, path);
    frontier.add(puzzle);

    while (!frontier.isEmpty()) {
      puzzle = frontier.remove(0);
      if(puzzle.getCost()>25000)
        return new ArrayList<>();
      explored.add(puzzle);
      if (isGoal(puzzle.getState())) return puzzle.getPath();
      else {
        ArrayList<String> state = expandState(puzzle.getState(), puzzle.getCost() + 1, puzzle.getPath());
        if (state.size() != 0) return state;
      }
    }
    return puzzle.getPath();
  }



  public int[] getState() {
    return puzzleState;
  }

  public int getCost() {
    return depth;
  }

  public ArrayList<String> getPath() {
    return solutionPath;
  }

  public void addToPath(String move) {
    solutionPath.add(move);
  }

  public ArrayList<String> expandState(int[] state, int depth, ArrayList<String> path) {

    ArrayList<String> notGoal = new ArrayList<>();
    int spaceIndex = findBlank(state);
    if (spaceIndex != 4) {

      if (spaceIndex == 0) {
        ArrayList<String> rightPath = new ArrayList<>(path);
        ArrayList<String> downPath = new ArrayList<>(path);

        BFSSearch right = new BFSSearch(moveRight(state, spaceIndex), depth, rightPath);
        BFSSearch down = new BFSSearch(moveDown(state, spaceIndex), depth, downPath);

        if ((isExplored(right)) && (isFrontier(right))) {
          right.addToPath("Right");
          if (isGoal(right.getState())) {
            explored.add(right);
            return right.getPath();
          }
          frontier.add(right);
        }
        if ((isExplored(down)) && (isFrontier(down))) {
          down.addToPath("Down");
          if (isGoal(down.getState())) {
            explored.add(down);
            return down.getPath();
          }
          frontier.add(down);
        }
      } else if (spaceIndex == 2) {
        ArrayList<String> leftPath = new ArrayList<>(path);
        ArrayList<String> downPath = new ArrayList<>(path);

        BFSSearch left = new BFSSearch
                (moveLeft(state, spaceIndex), depth, leftPath);
        BFSSearch down = new BFSSearch
                (moveDown(state, spaceIndex), depth, downPath);

        if ((isExplored(left)) && (isFrontier(left))) {
          left.addToPath("Left");
          if (isGoal(left.getState())) {
            explored.add(left);
            return left.getPath();
          }
          frontier.add(left);
        }
        if ((isExplored(down)) && (isFrontier(down))) {
          down.addToPath("Down");
          if (isGoal(down.getState())) return down.getPath();
          frontier.add(down);
        }
      } else if (spaceIndex == 6) {
        ArrayList<String> rightPath = new ArrayList<>(path);
        ArrayList<String> upPath = new ArrayList<>(path);

        BFSSearch right = new BFSSearch
                (moveRight(state, spaceIndex), depth, rightPath);
        BFSSearch up = new BFSSearch
                (moveUp(state, spaceIndex), depth, upPath);

        if ((isExplored(right)) && (isFrontier(right))) {
          right.addToPath("Right");
          if (isGoal(right.getState())) {
            explored.add(right);
            return right.getPath();
          }
          frontier.add(right);
        }
        if ((isExplored(up)) && (isFrontier(up))) {
          up.addToPath("Up");
          if (isGoal(up.getState())) {
            explored.add(up);
            return up.getPath();
          }
          frontier.add(up);
        }
      } else if (spaceIndex == 8) {
        ArrayList<String> leftPath = new ArrayList<>(path);
        ArrayList<String> upPath = new ArrayList<>(path);

        BFSSearch left = new BFSSearch
                (moveLeft(state, spaceIndex), depth, leftPath);
        BFSSearch up = new BFSSearch
                (moveUp(state, spaceIndex), depth, upPath);

        if ((isExplored(left)) && (isFrontier(left))) {
          left.addToPath("Left");
          if (isGoal(left.getState())) {
            explored.add(left);
            return left.getPath();
          }
          frontier.add(left);
        }
        if ((isExplored(up)) && (isFrontier(up))) {
          up.addToPath("Up");
          if (isGoal(up.getState())) {
            explored.add(up);
            return up.getPath();
          }
          frontier.add(up);
        }
      } else if (spaceIndex == 1) {
        ArrayList<String> rightPath = new ArrayList<>(path);
        ArrayList<String> leftPath = new ArrayList<>(path);
        ArrayList<String> downPath = new ArrayList<>(path);

        BFSSearch right = new BFSSearch(moveRight(state, spaceIndex), depth, rightPath);
        BFSSearch left = new BFSSearch(moveLeft(state, spaceIndex), depth, leftPath);
        BFSSearch down = new BFSSearch(moveDown(state, spaceIndex), depth, downPath);

        if ((isExplored(right)) && (isFrontier(right))) {
          right.addToPath("Right");
          if (isGoal(right.getState())) {
            explored.add(right);
            return right.getPath();
          }
          frontier.add(right);
        }
        if ((isExplored(left)) && (isFrontier(left))) {
          left.addToPath("Left");
          if (isGoal(left.getState())) {
            explored.add(left);
            return left.getPath();
          }
          frontier.add(left);
        }
        if ((isExplored(down)) && (isFrontier(down))) {
          down.addToPath("Down");
          if (isGoal(down.getState())) {
            explored.add(down);
            return down.getPath();
          }
          frontier.add(down);
        }
      } else if (spaceIndex == 3) {
        ArrayList<String> rightPath = new ArrayList<>(path);
        ArrayList<String> upPath = new ArrayList<>(path);
        ArrayList<String> downPath = new ArrayList<>(path);

        BFSSearch right = new BFSSearch(moveRight(state, spaceIndex), depth, rightPath);
        BFSSearch up = new BFSSearch(moveUp(state, spaceIndex), depth, upPath);
        BFSSearch down = new BFSSearch(moveDown(state, spaceIndex), depth, downPath);

        if ((isExplored(right)) && (isFrontier(right))) {
          right.addToPath("Right");
          if (isGoal(right.getState())) {
            explored.add(right);
            return right.getPath();
          }
          frontier.add(right);
        }
        if ((isExplored(up)) && (isFrontier(up))) {
          up.addToPath("Up");
          if (isGoal(up.getState())) {
            explored.add(up);
            return up.getPath();
          }
          frontier.add(up);
        }
        if (isExplored(down) && (isFrontier(down))) {
          down.addToPath("Down");
          if (isGoal(down.getState())) {
            explored.add(down);
            return down.getPath();
          }
          frontier.add(down);
        }
      } else if (spaceIndex == 5) {
        ArrayList<String> leftPath = new ArrayList<>(path);
        ArrayList<String> upPath = new ArrayList<>(path);
        ArrayList<String> downPath = new ArrayList<>(path);

        BFSSearch left = new BFSSearch(moveLeft(state, spaceIndex), depth, leftPath);
        BFSSearch up = new BFSSearch(moveUp(state, spaceIndex), depth, upPath);
        BFSSearch down = new BFSSearch(moveDown(state, spaceIndex), depth, downPath);

        if ((isExplored(left)) && (isFrontier(left))) {
          left.addToPath("Left");
          if (isGoal(left.getState())) {
            explored.add(left);
            return left.getPath();
          }
          frontier.add(left);
        }
        if ((isExplored(up)) && (isFrontier(up))) {
          up.addToPath("Up");
          if (isGoal(up.getState())) {
            explored.add(up);
            return up.getPath();
          }
          frontier.add(up);
        }
        if ((isExplored(down)) && (isFrontier(down))) {
          down.addToPath("Down");
          if (isGoal(down.getState())) {
            explored.add(down);
            return down.getPath();
          }
          frontier.add(down);
        }
      } else if (spaceIndex == 7) {
        ArrayList<String> rightPath = new ArrayList<>(path);
        ArrayList<String> leftPath = new ArrayList<>(path);
        ArrayList<String> upPath = new ArrayList<>(path);

        BFSSearch right = new BFSSearch(moveRight(state, spaceIndex), depth, rightPath);
        BFSSearch left = new BFSSearch(moveLeft(state, spaceIndex), depth, leftPath);
        BFSSearch up = new BFSSearch(moveUp(state, spaceIndex), depth, upPath);

        if ((isExplored(right)) && (isFrontier(right))) {
          right.addToPath("Right");
          if (isGoal(right.getState())) {
            explored.add(right);
            return right.getPath();
          }
          frontier.add(right);
        }
        if ((isExplored(left)) && (isFrontier(left))) {
          left.addToPath("Left");
          if (isGoal(left.getState())) {
            explored.add(left);
            return left.getPath();
          }
          frontier.add(left);
        }
        if ((isExplored(up)) && (isFrontier(up))) {
          up.addToPath("Up");
          if (isGoal(up.getState())) {
            explored.add(up);
            return up.getPath();
          }
          frontier.add(up);
        }
      }
    } else {
      ArrayList<String> rightPath = new ArrayList<>(path);
      ArrayList<String> leftPath = new ArrayList<>(path);
      ArrayList<String> upPath = new ArrayList<>(path);
      ArrayList<String> downPath = new ArrayList<>(path);

      BFSSearch right = new BFSSearch(moveRight(state, spaceIndex), depth, rightPath);
      BFSSearch left = new BFSSearch(moveLeft(state, spaceIndex), depth, leftPath);
      BFSSearch up = new BFSSearch(moveUp(state, spaceIndex), depth, upPath);
      BFSSearch down = new BFSSearch(moveDown(state, spaceIndex), depth, downPath);

      if ((isExplored(right)) && (isFrontier(right))) {
        right.addToPath("Right");
        if (isGoal(right.getState())) {
          explored.add(right);
          return right.getPath();
        }
        frontier.add(right);
      }
      if ((isExplored(left)) && (isFrontier(left))) {
        left.addToPath("Left");
        if (isGoal(left.getState())) {
          explored.add(left);
          return left.getPath();
        }
        frontier.add(left);
      }
      if ((isExplored(up)) && (isFrontier(up))) {
        up.addToPath("Up");
        if (isGoal(up.getState())) {
          explored.add(up);
          return up.getPath();
        }
        frontier.add(up);
      }
      if ((isExplored(down)) && (isFrontier(down))) {
        down.addToPath("Down");
        if (isGoal(down.getState())) {
          explored.add(down);
          return down.getPath();
        }
        frontier.add(down);
      }
    }
    return notGoal;
  }

  public int[] moveLeft(int[] state, int index) {
    int[] newState = duplicateState(state);
    newState[index] = newState[index - 1];
    newState[index - 1] = 0;
    return newState;
  }

  public int[] moveRight(int[] state, int index) {
    int[] newState = duplicateState(state);
    newState[index] = newState[index + 1];
    newState[index + 1] = 0;
    return newState;
  }

  public int[] moveUp(int[] state, int index) {
    int[] newState = duplicateState(state);
    newState[index] = newState[index - 3];
    newState[index - 3] = 0;
    return newState;
  }

  public int[] moveDown(int[] state, int index) {
    int[] newState = duplicateState(state);
    newState[index] = newState[index + 3];
    newState[index + 3] = 0;
    return newState;
  }

  public int findBlank(int[] state) {
    for (int i = 0; i < 9; i++) {
      if (state[i] == 0) return i;
    }
    return -1;
  }

  public void initPuzzle() {
    Scanner s = new Scanner(System.in);

      System.out.println("Please input initial matrix");
      scan(s, puzzleState);

    s.close();
  }

  static void scan(Scanner s, int[] puzzleState) {
    String initial = s.nextLine();
    int numIndex = 0;
    for (int i = 0; i < initial.length(); i++) {
      if (initial.charAt(i) != ' ') {
        int num = Character.getNumericValue(initial.charAt(i));
        puzzleState[numIndex] = num;
        numIndex++;
      }
    }
  }

  public int[] duplicateState(int[] state) {
    int[] newState = new int[9];
    System.arraycopy(state, 0, newState, 0, 9);
    return newState;
  }

  public Boolean isGoal(int[] state) {

      for (int i = 0; i < 9; i++) {
        if (state[i] != goal0[i]) return false;
      }
      return true;
  }

  public void printState(int[] state) {
    System.out.println("  ---+---+---");
    for (int i = 0; i < 9; i+=3) {
      for (int j = i; j < i+3; j++) {
        System.out.print(" |");
        if(state[j]==0)
          System.out.print("  ");
          else
            System.out.print(" "+state[j]);
      }
      System.out.print(" | ");
      System.out.println();
      System.out.println("  ---+---+---");
    }
  }

  public void printSolution(List<String> path, int[] initState) {
    int[] state = initState;
    int idx;
    int moves = 3;
    if (path.isEmpty()) {
      printState(state);
    } else {
      if (path.size() < 3) {
        moves = path.size();
      }
      for (int i = 0; i < moves; i++) {
        idx = findBlank(state);
        if (Objects.equals(path.get(i), "Right")) {
          state = moveRight(state, idx);
          printState(state);
        } else if (Objects.equals(path.get(i), "Left")) {
          state = moveLeft(state, idx);
          printState(state);
        } else if (Objects.equals(path.get(i), "Up")) {
          state = moveUp(state, idx);
          printState(state);
        } else {
          state = moveDown(state, idx);
          printState(state);
        }
      }
    }
  }

  public String convertStateToString(int[] initState) {
    StringBuilder stringState = new StringBuilder();
    for (int i = 0; i < 9; i++) {
      stringState.append(initState[i]);
    }
    return stringState.toString();
  }

  public boolean isExplored(BFSSearch bfsSearch) {
    for (BFSSearch search : explored) {
      if (convertStateToString(search.getState()).equals(convertStateToString(bfsSearch.getState()))) {
        return false;
      }
    }
    return true;
  }

  public boolean isFrontier(BFSSearch bfsSearch) {
    for (BFSSearch search : frontier) {
      if (convertStateToString(search.getState()).equals(convertStateToString(bfsSearch.getState()))) {
        return false;
      }
    }
    return true;
  }

}
