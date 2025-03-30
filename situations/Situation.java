package situations;

public class Situation {
    private int[][] s_data;
    public Situation (int[][] s_data){
        this.s_data = s_data;
    }
    public int getTilesIndex(int x, int y){
        return s_data[y][x];
    }
    public int[][] getSData() {
		return s_data;
	}
}
