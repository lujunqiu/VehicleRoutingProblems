/**
 * Created by qiu on 17-6-6.
 * 客户节点类
 */
public class Node extends Point {
    private int id;//客户节点的数字标识符,唯一标识
    private int demand;//每个客户的需求量
    private boolean ifVisited = false;//节点是否被访问
    Node(){
        super();
    }

    Node(int id,int demand,int x,int y){
        super(x, y);
        this.id = id;
        this.demand = demand;
    }

    public int getId() {
        return id;
    }

    public int getDemand() {
        return demand;
    }

    public boolean isIfVisited() {
        return ifVisited;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public void setIfVisited(boolean ifVisited) {
        this.ifVisited = ifVisited;
    }

    @Override
    public String toString() {
        return id + " ";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Node other = (Node) obj;
        if (this.id != other.getId()) {
            return false;
        } else {
            return true;
        }
    }
}
