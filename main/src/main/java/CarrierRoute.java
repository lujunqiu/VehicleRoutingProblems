import java.util.ArrayList;

/**
 * Created by qiu on 17-6-7.
 */
public class CarrierRoute implements Route{
    ArrayList<Depot> route;

    CarrierRoute(){
        super();
        route = new ArrayList<Depot>();
    }

    public void addPoint(Point depot){
        route.add((Depot) depot);
    }

    public void deletePoint(Point depot){
        if (route.contains((Depot) depot)) {
            route.remove((Depot) depot);
        }
    }

    public ArrayList<Depot> getRoute() {
        return route;
    }

    public double getDistance(){
        double distance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            distance = distance + route.get(i).getDistance(route.get(i + 1));
        }
        return distance;
    }


}
