package com.letcode.learn.queuetest;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 校验工具(幂等校验)
 *
 * @author zzh
 * @version 2018年6月15日12:52:22
 */
public class Validators {
    /**
     * 队列最大长度
     */
    public final static int MAX_LEN = 150;
    /**
     * 时间阀合(以S为单位) --> 最后一次请求与当前请求的时间差阀合
     */
    public final static int TIME_VALVE = 5;
    /**
     * 特征值队列
     */
    private static ConcurrentLinkedQueue<String> tokenQueue = new ConcurrentLinkedQueue<String>();
    /**
     * 锁
     */
    private static ReentrantLock lock = new ReentrantLock();
    /**
     * 当前队列长度
     */
    private static int queueLen = 0;
    /**
     * 最后操作时间戳
     */
    private static long lastTimeStamp = 0;


    public static boolean isExist(String token) {
        try {
            if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
                try {
                    if (tokenQueue.contains(token)) {
                        return true;
                    }

                    add(token);

                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 从监控队列当中移除
     */
    public static void remove(String token) {
        if (token != null) {
            tokenQueue.removeIf((s) -> s.equals(token));
        }
    }

    /**
     * 加入到监控队列当中
     */
    private static void add(String token) {
        if (queueLen++ >= MAX_LEN) {
            long curtimestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
            if (curtimestamp - lastTimeStamp >= TIME_VALVE) {    //当大于时间阀合，清空队列
                System.out.println("清空");
                tokenQueue.clear();
                lastTimeStamp = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));        //更新时间戳
                queueLen = 0;
            }
        }

        lastTimeStamp = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));        //更新时间戳
        tokenQueue.add(token);
    }


    public static void main(String[] args) {
//        if (Validators.isExist("1")) {
//            System.out.println("重复");
//        }
//
//        Validators.remove("1");
//
//        if (Validators.isExist("1")) {
//            System.out.println("重复1");
//        }

		for(int i = 0; i < 300; i++) {
			if(Validators.isExist(i + "")) {
				System.out.println("重复");
			}

			try {
				if(i % 52 == 0) {
					Thread.sleep(6000);
				}else {
					Thread.sleep(50);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("over");
    }
	
	/*	
	public static void main1(String[] args) {
		ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<String>();      
	    concurrentLinkedQueue.add("a");      
	    concurrentLinkedQueue.add("b");      
	    concurrentLinkedQueue.add("c");      
	    concurrentLinkedQueue.offer("d"); // 将指定元素插入到此队列的尾部。      
	    concurrentLinkedQueue.peek(); // 检索并移除此队列的头，如果此队列为空，则返回 null。      
//	    concurrentLinkedQueue.poll(); // 检索并移除此队列的头，如果此队列为空，则返回 null。      
	  
	    for (String str : concurrentLinkedQueue) {      
	        System.out.println(str);      
	    }   
	    
	    concurrentLinkedQueue.removeIf((s)->s.equals("c"));
	    
	    System.out.println("new");
	    
	    for (String str : concurrentLinkedQueue) {      
	        System.out.println(str);      
	    }   
	    
	    long time1 = DateUtils.getSecondTimeStamp();
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    long time2 = DateUtils.getSecondTimeStamp();
	    
	    System.out.println(time2 - time1);
	}
*/

}
