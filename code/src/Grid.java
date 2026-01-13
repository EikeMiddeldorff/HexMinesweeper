import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Grid {
    private final GridCell[][] cells;
    private final List<GridCell> cellList = new ArrayList<>();
    private final List <GridCell> mineCells = new ArrayList<>();

    Grid() {
        cells = new GridCell[13][13];
        for (int x = 1; x < 12; x++) {
            for (int y = 1; y < 12; y++) {
                GridCell cell = defineGridCell(x, y);
                cells[x][y] = cell;
            }
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
}

