
import java.util.*;

/**
 * Created by qiu on 17-6-9.
 * ＶＲＰ问题的完整解:装载车的路径（选定好停靠点后TSP），每个停靠点附近的route集合，每个无人机分配到的route映射
 */
public class Solution {
    private CarrierRoute carrierRoute;//装载车的行驶路径
    private HashMap<Depot, Schedule> scheduleHashMap;//每个停靠点的无人机调度
    private double cost = 0;

    Solution(CarrierRoute carrierRoute) {
        this.carrierRoute = carrierRoute;
        scheduleHashMap = new HashMap<Depot, Schedule>();
        for (int i = 0; i < carrierRoute.getRoute().size(); i++) {
            scheduleHashMap.put(carrierRoute.getRoute().get(i), new Schedule(carrierRoute.getRoute().get(i).getRoutes()));
        }
    }

    /*
    计算完整解的总代价
     */
    private void calculateCost() {
        cost = cost + carrierRoute.getDistance();//装载车行驶的路径
        Set<Map.Entry<Depot, Schedule>> entrySet = scheduleHashMap.entrySet();
        for (Map.Entry<Depot, Schedule> entry : entrySet) {
            Schedule schedule = entry.getValue();
            cost = cost + schedule.getCost();
        }
    }

    public double getCost() {
        calculateCost();
        return cost;
    }

    /*
    得到所有车辆停靠点的无人机调度安排
     */
    public Collection<Schedule> getSchedules(){
        return scheduleHashMap.values();
    }
}
