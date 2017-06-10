import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by qiu on 17-6-6.
 * 车辆停靠节点类
 */
public class Depot extends Point{
    ArrayList<UAVRoute> routes ;//车辆停靠节点对应的无人机飞行routes集合
    Depot(){
        super();
    }

    Depot(int x,int y) {
        super(x, y);
        routes = new ArrayList<UAVRoute>();
    }

    public ArrayList<UAVRoute> getRoutes() {
        return routes;
    }

    public void addRoutes(UAVRoute route){
        routes.add(route);
    }

    public void deleteRoutes(UAVRoute route){
        if (routes.contains(route)) {
            routes.remove(route);
        }
    }

}
