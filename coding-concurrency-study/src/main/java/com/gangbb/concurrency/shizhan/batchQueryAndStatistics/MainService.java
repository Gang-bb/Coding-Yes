package com.gangbb.concurrency.shizhan.batchQueryAndStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 模拟逻辑处理
 *
 * @author lyx
 * @date 2023-02-24
 **/
public class MainService {

    private static void waitFinish(List<Future<?>> futures) {
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<User> mockQueryData(){
        List<User> userList = new ArrayList<>(5);
        for (int i = 5000; i > 1; i-=1000) {
            User user = new User();
            user.setId(i);
            user.setName("用户" + i);
            userList.add(user);
        }
        return userList;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    doWork(finalI);
                }
            }).start();
        }
    }

    private static void doWork(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        // 模拟查询数据
        List<User> userList = mockQueryData();

        // 模拟处理数据
        List<Future<?>> futures = new ArrayList<>();
        for (User user : userList) {
            futures.add(ThreadUtil.getThreadUtilInstance().submit(() -> {
                try {
                    Thread.sleep(user.getId());
                    user.setWillDoValue("已处理数据");
                    System.out.println("标记<" +i +">。" + "线程" + Thread.currentThread() + user.name + "处理完数据");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
        waitFinish(futures);
        ThreadUtil.getThreadUtilInstance().shutdown();
//        System.out.println(userList);
        System.out.println("标记<" +i +">。" + "线程" + Thread.currentThread() +"。运行时间=" + (System.currentTimeMillis() - currentTimeMillis));
    }


    static class User {
        private Integer id;
        private String name;
        private String willDoValue;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWillDoValue() {
            return willDoValue;
        }

        public void setWillDoValue(String willDoValue) {
            this.willDoValue = willDoValue;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", willDoValue='" + willDoValue + '\'' +
                    '}';
        }
    }
}
