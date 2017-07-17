import java.util.List;
import java.util.Set;

/**
 * Created by qiu on 17-6-21.
 * 策略模式所需的策略类
 */
public class Algorithmn {

}
interface Initialize{
    List<RouteWithoutDepot> initialize();//初始化路径候选集
}

interface PickDepot{
    CarrierRoute pickDepot();//选择停靠点
}

