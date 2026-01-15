import java.util.*;

public class Grid {
    private final GridCell[][] cells;
    private final List<GridCell> cellList = new ArrayList<>();
    private final List <GridCell> mineCells = new ArrayList<>();
    private final int mineCount;

    Grid(int size, int mineCount) {
        this.mineCount = mineCount;
        cells = new GridCell[size * 2 + 2][size * 2 + 2];
        for (int x = 1; x < size * 2; x++) {
            for (int y = 1; y < size * 2; y++) {
                if (!(x - y > size - 1 || y - x > size - 1)) {
                    GridCell cell = new GridCell(cells[x - 1][y - 1], cells[x + 1][y + 1], cells[x - 1][y], cells[x][y - 1], cells[x + 1][y], cells[x][y + 1], x, y, false);
                    cells[x][y] = cell;
                    cellList.add(cell);
                }
            }
        }
        fillGridWithMines();
        for (GridCell cell: cellList){
            cell.determineAdjacentMines();
        }
    }

    private void fillGridWithMines(){
        Collections.shuffle(cellList);
        for (int i = 0; i < mineCount; i++) {
            GridCell cell = cellList.get(i);
            cell.setMine(true);
        }
    }
    /**
     * Method to get a cell at given coordinates
     *
     * @param x x Coordinate of the cell
     * @param y y Coordinate of the cell
     * @return the cell at the given coordinates
     */
    GridCell getCell(int x, int y) {
        if(x < 1 || x > 50 || y < 1 || y > 50){
            return null;
        }
        if(cells[x][y] == null){
            throw new IllegalArgumentException("No cell at the given coordinates");
        }else {
            return cells[x][y];
        }
    }

    /**
     * Method to get all cells in the grid
     *
     * @return List of all cells in the grid
     */
    List<GridCell> getCells() {
        return cellList;
    }

    void rightClickedOnCell(GridCell cell) {
        if(cell.isRevealed()){
            if(cell.isMine()){
                throw new IllegalStateException("If a mine cell is revealed, the game should be over.");
            }

        }else{
            if (cell.isMine()) {
                gameOver();
            }else{
                cell.setIsRevealed(true);
            }
        }
    }
    void leftClickedOnCell(GridCell cell) {
        if(cell.isRevealed()){
            for (GridCell currentCell: cell.getNeighbors()){
                if(!currentCell.isRevealed()){
                    if(currentCell.getAdjacentMines() == 0){
                        revealZeroCells(currentCell);
                    }
                    else {
                        currentCell.setIsRevealed(true);
                    }
                }
            }
        }else{
            cell.setFlagged(true);
        }
    }
    void gameOver(){
        for (GridCell cell: cellList){
            if(cell.isMine()){
                cell.setIsRevealed(true);
            }
        }
    }
    void revealZeroCells (GridCell rootCell){
        Queue<GridCell> toReveal = new LinkedList<>(List.of(rootCell));
        if(rootCell.getAdjacentMines() != 0 || rootCell.isMine() || rootCell.isRevealed()){
            throw new IllegalArgumentException("rootCell of this method should be valid.");
        }
        while (!toReveal.isEmpty()){
            GridCell currentCell = toReveal.poll();
            if(!currentCell.isRevealed() && !currentCell.isMine()){
                currentCell.setIsRevealed(true);

                if(currentCell.getAdjacentMines() == 0){
                    for (GridCell neighbor: currentCell.getNeighbors()){
                        if(!neighbor.isRevealed() && !neighbor.isMine() && !toReveal.contains(neighbor)){
                            toReveal.add(neighbor);
                        }
                    }
                }
            }
        }
    }
}

