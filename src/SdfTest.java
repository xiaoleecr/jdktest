import com.sun.deploy.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

//        String begTime = "2021-01-0";
//        begTime = begTime.substring(0,10);
//        System.out.println(begTime);
    }
}
