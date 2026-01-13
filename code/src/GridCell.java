public class GridCell {
    private final int xCord;
    private final int yCord;
    private GridCell north;
    private GridCell south;
    private GridCell northEast;
    private GridCell northWest;
    private GridCell southEast;
    private GridCell southWest;
    private final boolean isMine;
    private int adjacentMines;

    GridCell(GridCell north, GridCell south, GridCell northEast, GridCell northWest, GridCell southEast, GridCell southWest, int xCord, int yCord, boolean isMine) {
        this.north = north;
        this.south = south;
        this.northEast = northEast;
        this.northWest = northWest;
        this.southEast = southEast;
        this.southWest = southWest;
        this.xCord = xCord;
        this.yCord = yCord;
        this.isMine = isMine;
        if (north != null) {
            north.south = this;
        }
        if (northEast != null) {
            northEast.southWest = this;
        }
        if (northWest != null) {
            northWest.southEast = this;
        }
        if (south != null) {
            south.north = this;
        }
        if (southEast != null) {
            southEast.northWest = this;
        }
        if (southWest != null) {
            southWest.northEast = this;
        }
    }

    /**
     * Method to get the x coordinate of the cell
     *
     * @return the x coordinate of the cell
     */
    public int getXCord() {
        return xCord;
    }

    /**
     * Method to get the y coordinate of the cell
     *
     * @return the y coordinate of the cell
     */
    public int getYCord() {
        return yCord;
    }

    /**
     * Method to get the isMine status of the cell
     *
     * @return the isMine status of the cell
     */
    public boolean isMine() {
        return isMine;
    }

    public void determineAdjacentMines() {
        if (isMine) {
            this.adjacentMines = -1; //-1 indicates that this cell is a mine
        }
        int count = 0;
        if (north != null && north.isMine()) count++;
        if (south != null && south.isMine()) count++;
        if (northEast != null && northEast.isMine()) count++;
        if (northWest != null && northWest.isMine()) count++;
        if (southEast != null && southEast.isMine()) count++;
        if (southWest != null && southWest.isMine()) count++;
        this.adjacentMines = count;
    }

    /**
     * Method to get the number of adjacent mines
     *
     * @return the number of adjacent mines
     */
    public int getAdjacentMines() {
        return this.adjacentMines;
    }
}