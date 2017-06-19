import java.util.ArrayList;

/**
 * Created by qiu on 17-6-7.
 */
public class UAVRoute implements Route{
    final int capacity = 100;//无人机单次飞行的最大距离,UAVRoute的路径长度不能超过这个数值
    ArrayList<Point> route;
//    int test;

    UAVRoute(int i){
        super();
//        test = i;
        route = new ArrayList<Point>();//无人机飞行路径包含车辆停靠点与客户节点
    }

    public void addPoint(Point point){
        route.add(point);
    }

    public void deletePoint(Point point) {
        if (route.contains(point)) {
            route.remove(point);
        }
    }

    public double getDistance(){
        double distance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            distance = distance + route.get(i).getDistance(route.get(i + 1));
        }
        return distance + route.get(route.size() - 1).getDistance(route.get(0));
    }


    public int compareTo(Object o) {
        UAVRoute other = (UAVRoute) o;
        if (getDistance() > other.getDistance()) {
            return 1;
        }
        if (getDistance() < other.getDistance()) {
            return -1;
        }
        return 0;
    }

}
