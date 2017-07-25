import java.util.*;

/**
 * Created by qiu on 17-6-21.
 * 策略模式所需的策略类
 */

interface Initialize{
    List<RouteWithoutDepot> initialize(List<Node> customers);//初始化路径候选集
}

interface PickDepot{
    CarrierRoute pickDepot(List<Depot> depots,List<RouteWithoutDepot> routes);//选择停靠点
}

public class Algorithmn {
    public static void main(String[] args) {

    }
}

/*
初始化侯选集方法_1
 */
class Initial_1 implements Initialize {
    public List<RouteWithoutDepot> initialize(List<Node> customers) {
        List<RouteWithoutDepot> Memory = new ArrayList<RouteWithoutDepot>();//初始化候选集
        Map<Integer, Node> map = number(customers);//给客户编号
//
//        for (Map.Entry<Integer, Node> entry : map.entrySet()) {
//            System.out.println("编号："+ entry.getKey() + "节点id : " + entry.getValue().toString());
//        }
        RouteWithoutDepot tempRoute = new RouteWithoutDepot();
        for (Map.Entry<Integer, Node> entry : map.entrySet()) {
            tempRoute.addPoint(entry.getValue());
            if(tempRoute.ifOversize()) {
                tempRoute.deletePoint(entry.getValue());
                Memory.add(tempRoute);
                tempRoute = new RouteWithoutDepot();
                tempRoute.addPoint(entry.getValue());
            }
        }
        Memory.add(tempRoute);
        return Memory;
    }

    /*
    给客户节点编号
     */
    private Map<Integer,Node> number(List<Node> customers){
        HashMap<Integer, Node> map = new LinkedHashMap<Integer, Node>();//按照插入顺序存储
        Set<Node> nodeSet = new HashSet<Node>(customers);
        Node temp = null;
        for (int i = 0; i < customers.size(); i++) {
            if (map.isEmpty()) {
                temp = customers.get(i);
                map.put(i, temp);
                nodeSet.remove(temp);
                temp = nearestNode(temp, nodeSet);
            } else {
                map.put(i, temp);
                nodeSet.remove(temp);
                temp = nearestNode(temp, nodeSet);
            }
        }

        return map;
    }

    /*
    返回距离当前节点最近的节点
     */
    private Node nearestNode(Node node, Set<Node> customers) {
        double distance = Double.MAX_VALUE;
        Node nearest = null;
        for (Node customer : customers) {
            if (!customer.equals(node) && distance > node.getDistance(customer)) {
                nearest = customer;
                distance = node.getDistance(customer);
            }
        }
        return nearest;
    }

    /*
    测试代码
     */
    public static void main(String[] args) {
        //测试代码设置无人机单次飞行最大距离为５
        Initial_1 initial_1 = new Initial_1();
        Node node1 = new Node(1, 0, 0,0);
        Node node2 = new Node(2, 0, 1,0);
        Node node3 = new Node(3, 0, 2,0);
        Node node4 = new Node(4, 0, 3,1);
        Node node5 = new Node(5, 0, 1,2);
        Node node6 = new Node(6, 0, 1,1);
        Node node7 = new Node(7, 0, 0,2);
        Node node8 = new Node(8, 0, 1,3);
        Node node9 = new Node(9, 0, 2,1);
        Node node10 = new Node(10, 0, 0,3);
        List<Node> list = new ArrayList<Node>();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        list.add(node6);
        list.add(node7);
        list.add(node8);
        list.add(node9);
        list.add(node10);
        List<RouteWithoutDepot> list1 = initial_1.initialize(list);
        for (RouteWithoutDepot routeWithoutDepot : list1) {
            System.out.println(routeWithoutDepot + "距离：" + routeWithoutDepot.getDistance());
        }
    }
}

/*
初始化候选集方法_2
 */
class Initial_2 implements Initialize {
    public List<RouteWithoutDepot> initialize(List<Node> customers) {
        return null;
    }
}

/*
选择停靠点方法_1
 */
class PickDepot_1 implements PickDepot {
    @Override
    public CarrierRoute pickDepot(List<Depot> depots,List<RouteWithoutDepot> routes) {


    }
}





