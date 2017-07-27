import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiu on 17-6-26.
 * 整体算法的框架
 */
public class VRPHeuristic {
    private Factory factory;//得到初始的地图信息，所有的相关节点集合
    private Initialize initialize;//初始化路径候选集的策略对象
    private PickDepot pickDepot;//选择停靠点的策略对象
    public List<RouteWithoutDepot> RouteToSelected;//路径候选集(不含停靠点)
    List<UAVRoute> RoutesWithDepot;//包含停靠点的路径集合，无人机飞行路径
    private int k = 3;//在构造无人机飞行路径时候使用的参数

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public void setInitialize(Initialize initialize) {
        this.initialize = initialize;
    }

    public void setPickDepot(PickDepot pickDepot) {
        this.pickDepot = pickDepot;
    }

    public void initializeRouteSet(){//初始化候选集
        RouteToSelected = initialize.initialize(factory.getNodes());
        RouteToSelected.sort((RouteWithoutDepot o1, RouteWithoutDepot o2) -> (int)(o1.getCost() - o2.getCost()));//所有侯选集路径按照代价排序

    }

    public CarrierRoute pickDepot(){//选择停靠点并且解决停靠点TSP
        return pickDepot.pickDepot(factory.getDepots(),RouteToSelected);
    }

    /*
    构造初始解
     */
    public Solution construct(){

        return null;
    }

    /*
    从候选集中选择无人机路径(不含停靠点)得到路径集合
     */
    private List<RouteWithoutDepot> pick(){
        List<RouteWithoutDepot> route = new ArrayList<>();
        route.add(RouteToSelected.get(0));//取出候选集中的第一个元素放入集合

        while (!ifPickFinished(route,factory.getNodes())) {//选择的路径集合是否包含所有客户节点

            List<RouteWithoutDepot> list = new ArrayList<>();//辅助集合
            for (int i = 0; i < k; i++) {//取出候选集中前ｋ条路径
                RouteWithoutDepot kthRoute = RouteToSelected.get(i + 1);
                for (Node node : kthRoute.getRoute()) {
                    if (ifContains(node, route)) {
                        kthRoute.deletePoint(node);
                    }
                }
                list.add(kthRoute);
            }
            list.sort((RouteWithoutDepot o1, RouteWithoutDepot o2) -> (int) (o1.getCost() - o2.getCost()));
            route.add(list.get(0));//这ｋ条路径重新计算代价，将最小的那条路径加入集合
            RouteToSelected.sort((RouteWithoutDepot o1, RouteWithoutDepot o2) -> (int)(o1.getCost() - o2.getCost()));//所有侯选集路径按照代价排序

        }
        return route;
    }

    /*
    pick()方法的辅助方法：检测选择的路径集合是否包含了所有客户节点
     */
    private boolean ifPickFinished(List<RouteWithoutDepot> routeWithoutDepotList,ArrayList<Node> nodeList){
        for (Node node : nodeList) {
            if (!ifContains(node, routeWithoutDepotList)) {
                return false;
            }
        }
        return true;
    }

    /*
    pick()方法的辅助方法：检测某一个节点是否已经被侯选集包含了。
     */
    private boolean ifContains(Node node, List<RouteWithoutDepot> routeWithoutDepotList) {
        for (RouteWithoutDepot routeWithoutDepot : routeWithoutDepotList) {
            if (routeWithoutDepot.ifContains(node)) {
                return true;
            }
        }
        return false;
    }

    public Solution tabuSearch(Solution s) {
        return null;
    }

    public void update(Solution s){//利用解更新候选集中的路径

    }

    public static void main(String[] args) {
        VRPHeuristic vrpHeuristic = new VRPHeuristic();
//        vrpHeuristic.setFactory();
//        vrpHeuristic.setInitialize();
//        vrpHeuristic.setPickDepot();
//        while (true) {//终止条件
//            vrpHeuristic.initializeRouteSet();//初始化路径候选集
//            Solution solution = vrpHeuristic.construct();//构造初始解
//            Solution tabuSolution = vrpHeuristic.tabuSearch(solution);
//            vrpHeuristic.update(tabuSolution);
//        }
        Factory factory = new Factory();//得到原始初始化的节点集合
        vrpHeuristic.setFactory(factory);

        Initialize initialize1 = new Initial_1();//初始化候选集策略方法_1
        vrpHeuristic.setInitialize(initialize1);

        PickDepot pickDepot1 = new PickDepot_1();//停靠点选择策略方法_1
        vrpHeuristic.setPickDepot(pickDepot1);

        vrpHeuristic.initializeRouteSet();//初始化候选集
        CarrierRoute carrierRoute = vrpHeuristic.pickDepot();//选择停靠点，得到停靠点集合

        Solution firstSolution = new Solution(carrierRoute);
    }

}
