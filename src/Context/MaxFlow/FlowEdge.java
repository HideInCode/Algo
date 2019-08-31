package Context.MaxFlow;

/**
 * 流量网络中的边(剩余网络)
 */
public class FlowEdge {
    private final int v;
    private final int w;
    private final double capacity;
    private double flow;
    
    public FlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0;
    }
    
    public int from() {
        return v;
    }
    
    public int to() {
        return w;
    }
    
    public double capacity() {
        return capacity;
    }
    
    public double flow() {
        return flow;
    }
    
    public int other(int vertex) {
        if (vertex == w) {
            return v;
        } else if (vertex == v) {
            return w;
        } else {
            throw new RuntimeException("Inconsistent edge");
        }
    }
    
    //剩余容量
    public double residualCapacityTo(int vertex) {
        if (vertex == v) {
            return flow;
        } else if (vertex == w) {
            return capacity - flow;
        } else {
            throw new RuntimeException("Inconsistent edge");
        }
    }
    
    public void addResidualFlowTo(int vertex, double delta) {
        if (vertex == v) {
            flow -= delta;
        } else if (vertex == w) {
            flow += delta;
        } else {
            throw new RuntimeException("Inconsistent edge");
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"v\":")
                .append(v);
        sb.append(",\"w\":")
                .append(w);
        sb.append(",\"capacity\":")
                .append(capacity);
        sb.append(",\"flow\":")
                .append(flow);
        sb.append('}');
        return sb.toString();
    }
}
