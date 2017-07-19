import java.util.List;
import java.util.Set;

/**
 * Created by qiu on 17-6-26.
 * 整体算法的框架
 */
public class VRPHeuristic {
    private Initialize initialize;//初始化路径候选集的策略对象
    private PickDepot pickDepot;//选择停靠点的策略对象
    List<RouteWithoutDepot> RouteToSelected;//路径候选集(不含停靠点)
    List<UAVRoute> RoutesWithDepot;//包含停靠点的路径集合

    public void setInitialize(Initialize initialize) {
        this.initialize = initialize;
    }

    public void setPickDepot(PickDepot pickDepot) {
        this.pickDepot = pickDepot;
    }

    public void initializeRouteSet(){//初始化候选集
        RouteToSelected = initialize.initialize(null);
    }

    public CarrierRoute pickDepot(){//选择停靠点并且解决停靠点TSP
        return pickDepot.pickDepot();
    }


    public Solution construct(){//从路径候选集中构造初始解
        return null;
    }
    private List<RouteWithoutDepot> pick(){//从候选集中选择无人机路径(不含停靠点)得到路径集合,合并较短路径
        return null;
    }

    public Solution tabuSearch(Solution s) {
        return null;
    }

    public void update(Solution s){//利用解更新候选集中的路径

    }

    public static void main(String[] args) {
        VRPHeuristic vrpHeuristic = new VRPHeuristic();
        while (true) {//终止条件
            vrpHeuristic.initializeRouteSet();//初始化路径候选集
            Solution solution = vrpHeuristic.construct();//构造初始解
            Solution tabuSolution = vrpHeuristic.tabuSearch(solution);
            vrpHeuristic.update(tabuSolution);
        }
    }

}
