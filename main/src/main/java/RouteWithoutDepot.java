import java.util.ArrayList;
/**
 * Created by qiu on 17-6-7.
 */
public class RouteWithoutDepot implements Route{
    int capacity = 100;//无人机单次飞行的最大距离
    ArrayList<Node> route;
    double cost = 0;

    RouteWithoutDepot(){
        super();
        route = new ArrayList<Node>();
    }

    public boolean ifContains(Node node){
        return route.contains(node);
    }

    public boolean ifOversize(){
        return getDistance() >= capacity ? true : false;
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
        if (route.size() == 0 || route.size() == 1) {
            return 0;
        }
        for (int i = 0; i < route.size() - 1; i++) {
            distance = distance + route.get(i).getDistance(route.get(i + 1));
        }
        return distance + route.get(route.size() - 1).getDistance(route.get(0));
    }

    public int compareTo(Object o) {
        RouteWithoutDepot other = (RouteWithoutDepot) o;
        if (getDistance() > other.getDistance()) {
            return 1;
        }
        if (getDistance() < other.getDistance()) {
            return -1;
        }
        return 0;
    }

    private void setCost(){
        this.cost = getDistance() / this.route.size();
    }

    public void hebing(){//合并比较短的route

    }

    public void chaifeng(){//拆分比较长的route

    }

    public double getCost() {
        setCost();//计算路径的代价
        return cost;
    }

    @Override
    public String toString() {
        String s="[";
        for(Node x:route){
            s=s+x+" ";
        }
        s=s+"]";
        return s;
    }
}
