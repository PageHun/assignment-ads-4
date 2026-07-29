import java.util.*;

public class Task3 {
    public static class Graph<Vertex>{
        private Map<Vertex, List<Vertex>> map = new HashMap<>();

        public void addVertex(Vertex v){
            map.put(v, new LinkedList<Vertex>());
        }

        public void addEdge(Vertex source, Vertex dest, boolean bidirectional){
            if(!map.containsKey(source)){
                addVertex(source);
            }
            if (!map.containsKey(dest)){
                addVertex(dest);
            }

            map.get(source).add(dest);

            if(bidirectional == true){
                map.get(dest).add(source);
            }
        }


        public void dfs(Vertex start){
            Set<Vertex> visited = new HashSet<>();
            if(map.containsKey(start)){
                visitVertex(start, visited);
            }
            System.out.println();
        }

        public void visitVertex(Vertex v, Set<Vertex> visited){
            visited.add(v);
            System.out.print(v + " ");
            for(Vertex w : map.get(v)){
                if(!visited.contains(w)){
                    visitVertex(w, visited);
                }
            }
        }

        public void bfs(Vertex start){
            if(!map.containsKey(start)){
                return;
            }
            Set<Vertex> visited = new HashSet<>();
            Queue<Vertex> q = new LinkedList<>();
            q.add(start);
            visited.add(start);

            while(!q.isEmpty()){
                Vertex v = q.poll();
                System.out.print(v + " ");
                for(Vertex w : map.get(v)){
                    if(!visited.contains(w)){
                        visited.add(w);
                        q.add(w);
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        Graph<Character> g = new Graph<Character>();

        g.addEdge('A', 'C', true);
        g.addEdge('A', 'B', true);
        g.addEdge('A', 'D', true);
        g.addEdge('B', 'C', true);
        g.addEdge('B', 'E', true);
        g.addEdge('B', 'G', true);
        g.addEdge('C', 'D', true);
        g.addEdge('E', 'G', false);
        g.addEdge('E', 'F', true);
        g.addEdge('F', 'G', true);

        g.dfs('A');
        g.bfs('A');
    }
}
