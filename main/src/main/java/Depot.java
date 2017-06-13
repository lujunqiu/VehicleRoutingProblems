import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by qiu on 17-6-6.
 * 车辆停靠节点类
 */
public class Depot extends Point{
    private char id;//标识符号，用字母标识区别与客户节点的数字id,唯一标识
    private ArrayList<UAVRoute> routes ;//车辆停靠节点对应的无人机飞行routes集合
    Depot(){
        super();
    }

    Depot(char id, int x, int y) {
        super(x, y);
        this.id = id;
        routes = new ArrayList<UAVRoute>();
    }

    public char getId() {
        return id;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Depot other = (Depot) obj;
        if (this.id != other.getId()) {
            return false;
        } else {
            return true;
        }

    }
}
