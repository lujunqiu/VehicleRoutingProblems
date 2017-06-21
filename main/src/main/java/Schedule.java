import java.util.*;

/**
 * Created by qiu on 17-6-10.
 * 停靠点的无人机调度类
 */
public class Schedule {
    private final int uavNumber = 10;//每辆装载车的无人机数量，0-9标识了无人机的id
//    final int uavNumber = 5;//测试
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

    private void doSchedule(){//Bin packing problem 集装箱问题，贪心算法求解即可
        if (routes.size() <= uavNumber) {//无人机的数量大于等于routes的数量，直接分配即可
            for (int i = 0; i < routes.size(); i++) {
                hashMap.put(routes.get(i), i);
            }
        } else {//无人机数量小于routes的数量，意味着有的无人机需要跑2个或者以上的routes
            //将routes里面的UAVRoute排序，这里我们需要降序,默认是升序排序，需要重新给定比较器

            Collections.sort(routes, new Comparator<UAVRoute>() {
                public int compare(UAVRoute o1, UAVRoute o2) {
                    if (o1.getDistance() > o2.getDistance()) {
                        return -1;
                    }
                    if (o1.getDistance() < o2.getDistance()) {
                        return 1;
                    }
                    return 0;
                }
            });
//            //测试代码
//            Collections.sort(routes, new Comparator<UAVRoute>() {
//                public int compare(UAVRoute o1, UAVRoute o2) {
//                    if (o1.test > o2.test) {
//                        return -1;
//                    }
//                    if (o1.test < o2.test) {
//                        return 1;
//                    }
//                    return 0;
//                }
//            });


            // 表示平均每个无人机需要访问的UAVRoute的个数
            //int times = (int) routes.size() / uavNumber + 1;
            //选出路径长度最长的uavNumber条路径并派遣无人机对这些路径并发访问，最先回到装载车的无人机更换电池之后继续分配UAVRoute访问

            //以下为简易实现算法
            int n = 0;//无人机id
            int tag = 0;//标记
            for (int i = 0; i < routes.size(); i++) {
                if (tag % 2 == 0) {
                    hashMap.put(routes.get(i), n);
                    n++;
                    if (n == uavNumber) {
                        tag++;
                    }
                } else {
                    n--;
                    hashMap.put(routes.get(i), n);
                    if (n == 0) {
                        tag++;
                    }
                }
            }
        }
    }

    public HashMap<UAVRoute, Integer> getHashMap() {
        return hashMap;
    }

    public HashMap<Integer, Set<UAVRoute>> getIntegerSetHashMap() {
        return integerSetHashMap;
    }

    /*
    无人机并行完成这些任务，计算从所有无人机起飞到完成访问的最长的时间即可,无人机更换的电池的时间忽略
    */
    public double getCost(){
        double maxCost = 0;
        Set<Map.Entry<Integer, Set<UAVRoute>>> set = integerSetHashMap.entrySet();
        for (Map.Entry entry :
                set) {
            double temp = 0;
            Set<UAVRoute> set1 = (Set<UAVRoute>) entry.getValue();
            for (UAVRoute e :
                    set1) {
                temp = temp + e.getDistance();
            }
            if (temp > maxCost) {
                maxCost = temp;
            }
        }
        return maxCost;
    }

    /*
    调度算法测试正确,测试代码被注释
     */
    public static void main(String[] args) {
//        ArrayList<UAVRoute> uavRouteArrayList = new ArrayList<UAVRoute>();
//        UAVRoute uavRoute1 = new UAVRoute(1);
//        UAVRoute uavRoute2 = new UAVRoute(2);
//        UAVRoute uavRoute3 = new UAVRoute(3);
//        UAVRoute uavRoute4 = new UAVRoute(4);
//        UAVRoute uavRoute5 = new UAVRoute(5);
//        UAVRoute uavRoute6 = new UAVRoute(6);
//        UAVRoute uavRoute7 = new UAVRoute(7);
//        uavRouteArrayList.add(uavRoute1);
//        uavRouteArrayList.add(uavRoute2);
//        uavRouteArrayList.add(uavRoute3);
//        uavRouteArrayList.add(uavRoute4);
//        uavRouteArrayList.add(uavRoute5);
//        uavRouteArrayList.add(uavRoute6);
//        uavRouteArrayList.add(uavRoute7);
//        Schedule schedule = new Schedule(uavRouteArrayList);
//        //设置无人机数量为３台,10台进行测试
//        HashMap<Integer, Set<UAVRoute>> hashMap = schedule.getIntegerSetHashMap();
//        Set<Map.Entry<Integer, Set<UAVRoute>>> set = hashMap.entrySet();
//        for (Map.Entry entry:
//             set) {
//            Integer integer = (Integer) entry.getKey();
//            Set<UAVRoute> set1 = (Set<UAVRoute>) entry.getValue();
//            for (UAVRoute e :
//                    set1) {
//                System.out.println("无人机id" + integer + "routes id" + e.test);
//
//            }
//        }
    }
}
