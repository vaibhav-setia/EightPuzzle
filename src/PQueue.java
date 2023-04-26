import java.util.ArrayList;

public class PQueue {

  ArrayList<HeuristicSearch> priorityQueue = new ArrayList<>();

  public Boolean contains(HeuristicSearch puzzle) {
    for (HeuristicSearch heuristicSearch : priorityQueue) {
      if (stateToString(heuristicSearch.getState()).equals(stateToString(puzzle.getState())))
        return true;
    }
    return false;
  }

  public Boolean isEmpty() {
    return priorityQueue.isEmpty();
  }

  public void add(HeuristicSearch puzzle) {
    priorityQueue.add(puzzle);
  }

  public HeuristicSearch removeMin() {

    HeuristicSearch minPuzzle = priorityQueue.get(0);
    int puzzleF = priorityQueue.get(0).getPriority();

    for (int i = 1; i < priorityQueue.size(); i++) {
      int stateF  = priorityQueue.get(i).getPriority();
      if (stateF < puzzleF) {
        minPuzzle = priorityQueue.get(i);
        puzzleF = stateF;
      }
    }
    int index = priorityQueue.indexOf(minPuzzle);
    return priorityQueue.remove(index);
  }

  public String stateToString(int[] state) {
    StringBuilder newState = new StringBuilder();
    for (int i = 0; i < 9; i++) {
      newState.append(state[i]);
    }
    return newState.toString();
  }
}