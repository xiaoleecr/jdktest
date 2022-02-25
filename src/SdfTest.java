import com.sun.deploy.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName SdfTest
 * @Description
 * @Author licongrui
 * @Date 2021/12/30
 */
public class SdfTest {
    public static void main(String[] args) {
        // 这是分支1重新测试
        List<Person> pList = new ArrayList<>();
        pList.add(new Person("Jack", 19));
        pList.add(new Person("Mike", 25));
        pList.add(new Person("Tom", 27));
        pList.add(new Person("Max", 27));
        pList.add(new Person());
        List<Person> collect = pList.stream().filter(p -> p.getAge() != null && p.getName().equals("jean")).map(p -> new Person(p.getName(),p.getAge())).distinct().collect(Collectors.toList());
        String names = pList.stream().filter(p-> null != p.getName()).map(p -> p.getName()).collect(Collectors.joining(","));


        //定时任务线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            //缓存淘汰
            System.out.println("每五秒钟淘汰一次");

        }, 0L, 5, TimeUnit.SECONDS);
    }

    class Cache {
        public ConcurrentHashMap<String, Node> evaluateClassCache;
        private Node head;
        private Node tail;
        //十分钟淘汰
        Long internal = 10 * 60 * 1000L;

        public void eliminate() {
            for (; ; ) {
                Long curTime = System.currentTimeMillis();
                if ((curTime - tail.accTime) > internal) {

                }
            }
        }

        public void add(String id, Object evaluateClass) {
            Node node = evaluateClassCache.get(id);
            if (node == null) {
                node = new Node(evaluateClass);
                node.setAccTime(System.currentTimeMillis());
                if (evaluateClassCache.size() >= 1) {
                    head.pre = node;
                    node.next = head;
                    head = node;
                } else {
                    head = node;
                    tail = node;
                }
                evaluateClassCache.put(id, node);
            }
        }

        public Node get(String id) {
            Node curNode = evaluateClassCache.get(id);
            if (null != curNode) {
                curNode.setAccTime(System.currentTimeMillis());
                if (curNode == head && curNode == tail) {
                    return curNode;
                }
                if (curNode == head) {
                    return curNode;
                }
                if (curNode == tail) {
                    curNode.next = head;
                    tail = curNode.pre;
                    curNode.pre = null;
                    head = curNode;
                    return head;
                }
                if (evaluateClassCache.size() > 1) {
                    Node pre = curNode.pre;
                    Node next = curNode.next;
                    pre.next = next;
                    next.pre = pre;
                    curNode.next = head;
                    curNode.pre = null;
                    head = curNode;
                    return head;
                }
                return curNode;
            }
            return null;
        }

        class Node {
            Node pre;
            Node next;
            Object evaluateClass;
            Long accTime;

            Node(Object evaluateClass) {
                this.evaluateClass = evaluateClass;
            }

            public Node getPre() {
                return pre;
            }

            public void setPre(Node pre) {
                this.pre = pre;
            }

            public Node getNext() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }

            public Object getEvaluateClass() {
                return evaluateClass;
            }

            public void setEvaluateClass(Object evaluateClass) {
                this.evaluateClass = evaluateClass;
            }

            public Long getAccTime() {
                return accTime;
            }

            public void setAccTime(Long accTime) {
                this.accTime = accTime;
            }
        }
    }

}
