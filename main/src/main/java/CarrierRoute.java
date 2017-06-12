import java.util.ArrayList;

/**
 * Created by qiu on 17-6-7.
 */
public class CarrierRoute implements Route{
    ArrayList<Depot> route;//规定第一个加入的停靠点为基站坐标(0,0)

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
        double distance = 0;//环路的长度
        for (int i = 0; i < route.size() - 1; i++) {
            distance = distance + route.get(i).getDistance(route.get(i + 1));
        }

        return distance + route.get(route.size() - 1).getDistance(route.get(0));
    }

    /*
    在所有选定的车辆停靠点的集合运行贪婪算法求解TSP，得到装载车的行驶路径
    */
    public void tsp(){

        int number = route.size();//TSP节点数量
        double[][] distance = new double[number][number];//距离矩阵
        boolean[] tags = new boolean[number];//标识节点是否访问过
        int[] visit = new int[number];//记录第i次访问的节点

        visit[0] = 0;//出发点为特殊的Depot节点--基站节点
        tags[0] = true;

        //初始化距离矩阵
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                distance[i][j] = route.get(i).getDistance(route.get(j));
            }
        }

        //确定了出发点之后,贪婪地寻找下一个节点
        for (int i = 1; i < number; i++) {
            double tempDistance = Double.MAX_VALUE;
            for (int j = 0; j < number; j++) {
                if (tags[j] == false && tempDistance > distance[visit[i - 1]][j]) {
                    tempDistance = distance[visit[i - 1]][j];
                    visit[i] = j;
                }
            }
            tags[visit[i]] = true;
        }

        //按照上面贪婪算法的结果，重新调整route变量保存的Depot节点的顺序
        ArrayList<Depot> tspRoute = new ArrayList<Depot>();
        for (int a :
                visit) {
            //System.out.println(route.get(a).getId());
            tspRoute.add(route.get(a));
        }
        route = tspRoute;
    }

    public static void main(String[] args) {
        //贪婪算法的测试代码：10个点以下的情况基本符合最优解
        CarrierRoute carrierRoute = new CarrierRoute();
        Depot depot0 = new Depot('A',0, 0);
        Depot depot1 = new Depot('B',1, 1);
        Depot depot2 = new Depot('C',2, 4);
        Depot depot3 = new Depot('D',3, 3);
        Depot depot4 = new Depot('E',2, 1);
        Depot depot5 = new Depot('F',5, 3);
        Depot depot6 = new Depot('G',6, 2);
        Depot depot7 = new Depot('H',4, 5);
        carrierRoute.addPoint(depot0);
        carrierRoute.addPoint(depot1);
        carrierRoute.addPoint(depot2);
        carrierRoute.addPoint(depot3);
        carrierRoute.addPoint(depot4);
        carrierRoute.addPoint(depot5);
        carrierRoute.addPoint(depot6);
        carrierRoute.addPoint(depot7);
        carrierRoute.tsp();
        for (Depot depot:
             carrierRoute.getRoute()) {
            System.out.println(depot.getId());
        }
        System.out.println(carrierRoute.getDistance());
    }
}
