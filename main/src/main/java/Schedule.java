import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by qiu on 17-6-10.
 * 停靠点的无人机调度类
 */
public class Schedule {
    final int uavNumber = 10;//每辆装载车的无人机数量
    ArrayList<UAVRoute> routes;//需要调度的无人机飞行路径
    HashMap<UAVRoute, Integer> hashMap;//调度的结果保存在Map之中
    Schedule(ArrayList<UAVRoute> routes) {
        this.routes = routes;
        hashMap = new HashMap<UAVRoute, Integer>();
        doSchedule();
    }

    private void doSchedule(){
        if (routes.size() <= uavNumber) {
            for (int i = 0; i < routes.size(); i++) {
                hashMap.put(routes.get(i), i);
            }
        } else {
            //do  something
        }
    }

    public HashMap<UAVRoute, Integer> getHashMap() {
        return hashMap;
    }
}
