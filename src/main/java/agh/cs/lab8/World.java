package agh.cs.lab8;

import java.awt.*;
import java.io.FileNotFoundException;

public class World {
    public static void main(String[] args) throws FileNotFoundException {
        //new ResultToFile().writeHighestResult(4);
        //new ResultToFile().readHighestResult();
        EventQueue.invokeLater(GameFrame::new);
        /*GameFrame n = new GameFrame(map);
        while (map.gameOver()){
            System.out.print(map.toString());
            String scan = new Scanner(System.in).next();
            if(new LetsMove().convertToMoveDirection(scan) != null) {
                map.run(new LetsMove().convertToMoveDirection(scan));
            }
        }
        System.out.print(map.toString());
*/
    }

}
