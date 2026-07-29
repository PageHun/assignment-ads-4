import java.util.*;

public class Task5 {
    public static class ShortestPath {
        private Map<String, Integer> dist;
        private Set<String> settled;
        private PriorityQueue<Node> pq;
        private Map<String, List<Node>> adj;

        public ShortestPath() {
            dist = new HashMap<>();
            settled = new HashSet<>();
            pq = new PriorityQueue<>(new Node());
        }

        public void Dijkstra(Map<String, List<Node>> adj, String src) {
            this.adj = adj;

            for (String node : adj.keySet()) {
                dist.put(node, Integer.MAX_VALUE);
            }

            dist.put(src, 0);
            pq.add(new Node(src, 0));

            while (!pq.isEmpty()) {
                String u = pq.remove().node;

                if (settled.contains(u)) {
                    continue;
                }
                settled.add(u);
                e_Neighbours(u);
            }
        }

        private void e_Neighbours(String u) {
            int edgeDist = -1;
            int newDist = -1;

            List<Node> neighbors = adj.getOrDefault(u, new ArrayList<>());

            for (int i = 0; i < neighbors.size(); i++) {
                Node v = neighbors.get(i);

                if (!settled.contains(v.node)) {
                    edgeDist = v.cost;
                    newDist = dist.get(u) + edgeDist;

                    if (newDist < dist.getOrDefault(v.node, Integer.MAX_VALUE)) {
                        dist.put(v.node, newDist);
                    }
                    pq.add(new Node(v.node, dist.get(v.node)));
                }
            }
        }
    }

    public static class Node implements Comparator<Node> {

        public String node;
        public int cost;

        public Node() {}

        public Node(String node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            if (node1.cost < node2.cost)
                return -1;
            if (node1.cost > node2.cost)
                return 1;
            return 0;
        }
    }

    public static void main(String[] args) {

        Map<String, List<Node>> adj = new HashMap<>();

        adj.put("Edinburgh", new ArrayList<>());
        adj.put("Glasgow", new ArrayList<>());
        adj.put("Stirling", new ArrayList<>());
        adj.put("Perth", new ArrayList<>());
        adj.put("Dundee", new ArrayList<>());

        adj.get("Edinburgh").add(new Node("Glasgow", 70));
        adj.get("Edinburgh").add(new Node("Stirling", 50));
        adj.get("Edinburgh").add(new Node("Perth", 100));
        adj.get("Glasgow").add(new Node("Stirling", 50));
        adj.get("Stirling").add(new Node("Perth", 40));
        adj.get("Perth").add(new Node("Dundee", 60));

        ShortestPath dpq = new ShortestPath();
        dpq.Dijkstra(adj, "Edinburgh");

        System.out.println("The shortest path from Edinburgh to Dundee is " + dpq.dist.get("Dundee") + "km.");
    }
}