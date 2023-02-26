package com.gangbb.concurrency.completableFuture;

import cn.hutool.core.collection.CollUtil;
import com.gangbb.concurrency.countDownLatch.DoFileDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 *  模拟场景：针对用户批量下载的文件进行修改上传 CompletableFuture版本
 *  CountDownLatch版本 {@link DoFileDemo}
 *
 * @author lyx
 * @date 2023-02-26
 **/
public class DoFileDemo2 {
    public static void main(String[] args) {
        // 下载文件总数,初始化
        List<Integer> resultList = new ArrayList<>(1000);
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        IntStream.range(0,1000).forEach(resultList::add);
    }

//    public List<R> sendAsyncBatch(List<P> list, Executor executor, TaskLoader<R,P> loader) {
//
//        List<R> resultList = Collections.synchronizedList(Lists.newArrayList());
//        if (CollectionUtils.isNotEmpty(list)) {
//            Executor finalExecutor = executor;
//            // 将任务拆分分成每50个为一个任务
//            CollUtil.split(list, 50)
//                    .forEach(tempList -> {
//                        CompletableFuture[] completableFutures = tempList.stream()
//                                .map(p -> CompletableFuture.supplyAsync(() -> {
//                                                    try {
//                                                        return loader.load(p);
//                                                    } catch (InterruptedException e) {
//                                                        e.printStackTrace();
//                                                    }
//                                                    return null;
//                                                }, finalExecutor)
//                                                .handle((result, throwable) -> {
//                                                    if (Objects.nonNull(throwable)) {
//                                                        //log.error("async error:{}", throwable.getMessage());
//                                                    } else if (Objects.nonNull(result)) {
//                                                        //log.info("async success:{}", result);
//                                                    } else {
//                                                        //log.error("async result is null");
//                                                    }
//                                                    return result;
//                                                }).whenComplete((r, ex) -> {
//                                                    if (Objects.nonNull(r)) {
//                                                        resultList.add((R) r);
//                                                    }
//                                                })
//                                ).toArray(CompletableFuture[]::new);
//                        CompletableFuture.allOf(completableFutures).join();
//                        System.out.println(resultList.size());
//                    });
//        }
//        return resultList;
//    }
}
