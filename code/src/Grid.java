import java.util.ArrayList;
import java.util.List;

public class Grid {
    private final GridCell[][] cells;
    private final List<GridCell> cellList = new ArrayList<>();
    private final List <GridCell> mineCells = new ArrayList<>();
    private final int size;
    private final int mineCount;

    Grid(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
        cells = new GridCell[size * 2 + 2][size * 2 + 2];
        for (int x = 1; x < size * 2; x++) {
            for (int y = 1; y < size * 2; y++) {
                if (!(x - y > size - 1 || y - x > size - 1)) {
                    GridCell cell = defineGridCell(x, y);
                    cells[x][y] = cell;
                }
            }
        }
        for (GridCell cell: cellList){
            cell.determineAdjacentMines();
        }
    }

    private GridCell defineGridCell(int x, int y) {
        GridCell cell;
        if(Math.random() < 0.1){
            cell = new GridCell(cells[x - 1][y - 1], cells[x + 1][y + 1], cells[x - 1][y], cells[x][y - 1], cells[x + 1][y], cells[x][y + 1], x, y, true);
            mineCells.add(cell);
        }else {
            cell = new GridCell(cells[x - 1][y - 1], cells[x + 1][y + 1], cells[x - 1][y], cells[x][y - 1], cells[x + 1][y], cells[x][y + 1], x, y, false);
        }
        cellList.add(cell);
        return cell;
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
}

