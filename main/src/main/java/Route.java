import java.util.List;

/**
 * Created by qiu on 17-6-6.
 */
public interface Route {
    public double getDistance();//计算长度
    public void addPoint(Point point);//增加节点
    public void deletePoint(Point point);//删除节点

}
