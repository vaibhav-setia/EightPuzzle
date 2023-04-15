import java.util.ArrayList;

public class bfs {

  int[] puzzleState;
  int depth;
  ArrayList<String> solutionPath;

  public bfs() {
    this.puzzleState = new int[9];
    this.depth = 0;
    this.solutionPath = new ArrayList<>();
  }
  public bfs(int[] puzzle, int depth, ArrayList<String> path) {
    this.puzzleState = puzzle;
    this.depth = depth;
    this.solutionPath = path;
  }
}
