package common;

import server.Map;
import tiles.Tile;

public class MapData {
    private String[][] mapTiles;

    public MapData(String[][] mapTiles){
        this.mapTiles = mapTiles;
    }

    public Tile[][] getMapTiles(){
        return Map.stringToTiles(mapTiles);
    }
}
