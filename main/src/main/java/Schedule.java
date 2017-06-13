import java.util.*;

/**
 * Created by qiu on 17-6-10.
 * 停靠点的无人机调度类
 */
public class Schedule {
    final int uavNumber = 10;//每辆装载车的无人机数量，0-9标识了无人机的id
    private ArrayList<UAVRoute> routes;//需要调度的无人机飞行路径
    private HashMap<UAVRoute, Integer> hashMap;//调度的结果保存在Map之中,Integer值标识了无人机的id
    private HashMap<Integer, Set<UAVRoute>> integerSetHashMap;//调度结果的另一种保存方式，更换了Key与Value
    Schedule(ArrayList<UAVRoute> routes) {
        this.routes = routes;
        hashMap = new HashMap<UAVRoute, Integer>();
        integerSetHashMap = new HashMap<Integer, Set<UAVRoute>>();
        doSchedule();

        //把hashmap中保存的结果转存入integerSetHashMap
        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object key = entry.getKey();//UAVRoute
            Object value = entry.getValue();//Integer
            if (!integerSetHashMap.containsKey((Integer) value)) {
                Set<UAVRoute> set = new HashSet<UAVRoute>();
                set.add((UAVRoute) key);
                integerSetHashMap.put((Integer) value, set);
            } else {
                integerSetHashMap.get((Integer) value).add((UAVRoute) key);
            }
        }
    }

    private void doSchedule(){//Bin packing problem 集装箱问题
        if (routes.size() <= uavNumber) {//无人机的数量大于routes的数量，直接分配即可
            for (int i = 0; i < routes.size(); i++) {
                hashMap.put(routes.get(i), i);
            }
        } else {//无人机数量小于routes的数量，意味着有的无人机需要跑2个或者以上的routes
            //do  something 贪心算法思路

        }
    }

    public HashMap<UAVRoute, Integer> getHashMap() {
        return hashMap;
    }

    public HashMap<Integer, Set<UAVRoute>> getIntegerSetHashMap() {
        return integerSetHashMap;
    }

    /*
    计算该次调度安排无人机的飞行时间,计算从所有无人机起飞到完成访问的最长的时间即可，无人机并行完成这些任务
    */
    public double getCost(){

    }
}
