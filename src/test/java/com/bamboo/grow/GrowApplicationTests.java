package com.bamboo.grow;

import com.bamboo.grow.practice.RabbitMq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrowApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private RabbitMq sendMq;

    @Test
    public void contextLoads() {
    }

    @Test
    public void lanbda() {
        //lambda取代匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        //Java 8方式：
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
    }


    @Test
    public void LambdaList() {
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features.stream().forEach(System.out::println);
    }

    @Test
    public void lambdaPredicate() {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> String.valueOf(str).startsWith("J"));
    }

    /**
     * 使用lambda表达式和函数式接口Predicate
     *
     * @param names
     * @param condition Predicate接口非常适用于做过滤
     */
    public static void filter(List names, Predicate condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach(name -> System.out.println(name));
    }


    /**
     * 如何在lambda表达式中加入Predicate
     * 上个例子说到，java.util.function.Predicate 允许将两个或更多的 Predicate 合成一个。
     * 它提供类似于逻辑操作符AND和OR的方法，名字叫做and()、or()和xor()，
     * 用于将传入 filter() 方法的条件合并起来。例如，要得到所有以J开始，
     * 长度为四个字母的语言，可以定义两个独立的 Predicate 示例分别表示每一个条件，
     * 然后用 Predicate.and() 方法将它们合并起来
     */
    @Test
    public void LambdaMorePredicate() {
        List features = Arrays.asList("java", "Scala", "C++", "Haskell", "Lisp", "jiba");
        //创建过滤条件
        Predicate<String> startWithStr = n -> n.startsWith("j");
        Predicate<String> strLength = n -> n.length() == 4;
        features.stream().filter(startWithStr.and(strLength)).forEach(n -> System.out.println(n));
    }

    /**
     * Java 8中使用lambda表达式的Map和Reduce示例
     * 本例介绍最广为人知的函数式编程概念map。
     * 它允许你将对象进行转换。例如在本例中，我们将 costBeforeTax 列表的每个元素转换成为税后的值。
     * 我们将 x -> x*x lambda表达式传到 map() 方法，后者将其应用到流中的每一个元素。
     * 然后用 forEach() 将列表元素打印出来。使用流API的收集器类，可以得到所有含税的开销。
     * 有 toList() 这样的方法将 map 或任何其他操作的结果合并起来。由于收集器在流上做终端操作，因此之后便不能重用流了。
     */
    @Test
    public void lambdaMap() {
        // 不使用lambda表达式为每个订单加上12%的税
        List<Double> costBeforeTax = Arrays.asList(100.0, 200.0, 300.0, 400.0, 500.0);
        for (Double d : costBeforeTax) {
            d += .12 * d;
            System.out.println(d);
        }

        // 使用lambda表达式
        costBeforeTax.forEach(c -> {
            c += .12 * c;
            System.out.println(c);
        });

        //使用map方式
        costBeforeTax.stream().map((map) -> map += .12 * map).forEach(c -> System.out.println(c));

    }

    /**
     * Java 8中使用lambda表达式的Map和Reduce示例
     * 在上个例子中，可以看到map将集合类（例如列表）元素进行转换的。
     * 还有一个 reduce() 函数可以将所有值合并成一个。Map和Reduce操作是函数式编程的核心操作，因为其功能，reduce 又被称为折叠操作。
     * 另外，reduce 并不是一个新的操作，你有可能已经在使用它。
     * SQL中类似 sum()、avg() 或者 count() 的聚集函数，实际上就是 reduce 操作，因为它们接收多个值并返回一个值。
     * 流API定义的 reduceh() 函数可以接受lambda表达式，并对所有值进行合并。
     * IntStream这样的类有类似 average()、count()、sum() 的内建方法来做 reduce 操作，也有mapToLong()、mapToDouble() 方法来做转换。
     * 这并不会限制你，你可以用内建方法，也可以自己定义。
     * 在这个Java 8的Map Reduce示例里，我们首先对所有价格应用 12% 的VAT，然后用 reduce() 方法计算总和。
     */

    @Test
    public void lambdaReduce() {
        // 为每个订单加上12%的税
        // 老方法：
        List<Double> costBeforeTax = Arrays.asList(100.0, 200.0, 300.0, 400.0, 500.0);
        double total = 0;
        for (Double cost : costBeforeTax) {
            double price = cost + .12 * cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

        double totalMoney = costBeforeTax.stream().map((n) -> n += .12 * n).reduce((sum, n) -> sum + n).get();
        double c = costBeforeTax.stream().map(n -> n += .12 * n).reduce((sum, n) -> sum + n).get();
        System.out.println(c);
    }

    /**
     * 通过过滤创建一个String列表
     * 过滤是Java开发者在大规模集合上的一个常用操作，而现在使用lambda表达式和流API过滤大规模数据集合是惊人的简单。
     * 流提供了一个 filter() 方法，接受一个 Predicate 对象，即可以传入一个lambda表达式作为过滤逻辑。
     * 下面的例子是用lambda表达式过滤Java集合，将帮助理解。
     */

    @Test
    public void lambdaList() {
        List<String> strList = Arrays.asList("java", "Scala", "C++", "Haskell", "Lisp", "jiba");
        // 创建一个字符串列表，每个字符串长度大于2
        //List<String> filtered = strList.stream().filter(x -> x.length()> 2).collect(Collectors.);
        //Double aDouble = a.stream().filter(n -> n > 100).reduce((sum, n) -> sum + n).get();
        //System.out.println(aDouble);
        //System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
    }

    /**
     * 计算集合元素的最大值、最小值、总和以及平均值
     * IntStream、LongStream 和 DoubleStream 等流的类中，有个非常有用的方法叫做 summaryStatistics() 。
     * 可以返回 IntSummaryStatistics、LongSummaryStatistics 或者 DoubleSummaryStatistic s，描述流中元素的各种摘要数据。
     * 在本例中，我们用这个方法来计算列表的最大值和最小值。它也有 getSum() 和 getAverage() 方法来获得列表的所有元素的总和及平均值。
     */
    @Test
    public void sumMAXMIn() {
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        //IntSummaryStatistics s = primes.stream().distinct().mapToInt(x -> x).summaryStatistics();
        Integer integer = primes.parallelStream().reduce(Integer::max).get();
        Integer integer1 = primes.parallelStream().reduce(Integer.MAX_VALUE, (a, b) -> Integer.min(a, b));
        //primes.stream().parallel().max().ifPresent(System.out::println);
        System.out.println(integer1);
    }

    @Test
    public void random() {
        //伪代码
        long startTime = System.currentTimeMillis();   //获取开始时间
        Random random = new Random();
        DoubleStream doubles = random.doubles(-1.0001, 1.0001);

        doubles.limit(100).parallel().forEach(n -> System.out.println(n));
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + "ns");
    }

    @Test
    public void thread() {
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6})
                .parallel()
                .max()
                .ifPresent(System.out::println);
    }

    /**
     * =============简单、工作队列模式===============
     * =============没有绑定交换机===============
     */
    @Test
    public void simple_queue() {
        sendMq.simple_queue();
    }

    /**
     * =============发布/订阅模式===============
     * =============绑定fanout_exchange交换机===============
     */
    @Test
    public void ps_queue_1() {
        sendMq.ps_queue_1();
    }

    /**
     * =============路由模式===============
     * =============绑定direct_exchange交换机===============
     * =============routingKey===============
     */
    @Test
    public void routing_queue_1() {
        sendMq.routing_queue_1();
    }


    /**
     * =============主题模式===============
     * =============绑定direct_exchange交换机===============
     * =============routingKey===============
     */
    @Test
    public void topic_queue_1() {
        sendMq.topic_queue_1();

    }

}
