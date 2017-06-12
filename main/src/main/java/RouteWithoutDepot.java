import java.util.ArrayList;
/**
 * Created by qiu on 17-6-7.
 */
public class RouteWithoutDepot implements Route{
    int capacity = 100;//无人机单次飞行的最大距离
    ArrayList<Node> route;

    RouteWithoutDepot(){
        super();
        route = new ArrayList<Node>();
    }

    public void addPoint(Point node){
        route.add((Node) node);
    }

    public void deletePoint(Point node){
        if (route.contains((Node) node)) {
            route.remove((Node) node);
        }
    }

    public ArrayList<Node> getRoute() {
        return route;
    }

    public double getDistance(){
        double distance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            distance = distance + route.get(i).getDistance(route.get(i + 1));
        }
        return distance + route.get(route.size() - 1).getDistance(route.get(0));
    }

    public void hebing(){//合并比较短的route

    }

    public void chaifeng(){//拆分比较长的route

    }

}
